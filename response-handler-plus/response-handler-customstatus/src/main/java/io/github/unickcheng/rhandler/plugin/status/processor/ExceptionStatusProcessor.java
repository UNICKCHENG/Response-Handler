/*
 * create on 2023-01-18
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package io.github.unickcheng.rhandler.plugin.status.processor;

import com.google.auto.service.AutoService;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.*;
import io.github.unickcheng.rhandler.plugin.status.annotation.RHandlerExceptionStatusEnum;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.Set;

/**
 * 使用注解来扩展自定义异常状态码 <br/>
 * 此处源码定制化较高、鲁棒性低，可以参考，但不要引用 @2023-01-19<br/>
 * TODO perf 简化代码格式
 * @see RHandlerExceptionStatusEnum 支持的注解
 * @author unickcheng
 */

@Deprecated
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"io.github.unickcheng.rhandler.plugin.status.annotation.*"})
public class ExceptionStatusProcessor extends AbstractProcessor {
    private Messager messager;
    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public synchronized boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(RHandlerExceptionStatusEnum.class);

        set.forEach(element -> {
            // 只允许枚举类使用该注解
            if (!element.getKind().equals(ElementKind.ENUM)) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        String.format("%s error: @%s must be used for a enum.", element.getSimpleName(), annotations));
            }
            // 增加 import
            addImportPackage(element, "io.github.unickcheng.rhandler.exception.ExceptionStatusInfo");
            // 增加需要的成员变量

            // 增加 get 方法
             addGetMethodForVariables(element);

            // 增加构造方法

            // 增加 HttpStatus 缺省的构造方法

        });
        return true;
    }

    private void addGetMethodForVariables(Element element) {
        trees.getTree(element).accept(new TreeTranslator() {
            @Override
            public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
                // generate get method
                List<JCTree.JCMethodDecl> newGetMethod = generateGetMethod(jcClassDecl);
                newGetMethod.forEach(getMethod -> {
                    if (!jcClassDecl.defs.contains(getMethod)) {
                        jcClassDecl.defs = jcClassDecl.defs.prepend(getMethod);
                    }
                });
                jcClassDecl.implementing = jcClassDecl.implementing.append(treeMaker.Ident(names.fromString("ExceptionStatusInfo")));
                super.visitClassDef(jcClassDecl);
            }
        });
    }

    private List<JCTree.JCMethodDecl> generateGetMethod(JCTree.JCClassDecl jcClassDecl) {
        List<JCTree.JCMethodDecl> getMethodList = List.nil();
        for (JCTree tree : jcClassDecl.defs) {
            String contentTmp = String.valueOf(tree);
            if (tree.getKind().equals(Tree.Kind.VARIABLE) && !contentTmp.startsWith("/*")) {
                JCTree.JCVariableDecl jcVariableDecl =(JCTree.JCVariableDecl) tree;
                messager.printMessage(Diagnostic.Kind.NOTE, jcVariableDecl.getName() + " has been processed");
                getMethodList = getMethodList.append(generateGetMethodDecl(jcVariableDecl));
            }
        }
        return getMethodList;
    }

    private JCTree.JCMethodDecl generateGetMethodDecl (JCTree.JCVariableDecl jcVariableDecl) {
        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        statements.append(treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), jcVariableDecl.getName())));
        JCTree.JCBlock body = treeMaker.Block(0, statements.toList());
        return treeMaker.MethodDef(
                treeMaker.Modifiers(Flags.PUBLIC)
                , getNewMethodName(jcVariableDecl.getName())
                , jcVariableDecl.vartype, List.nil()
                , List.nil(), List.nil(), body, null);
    }

    private Name getNewMethodName (Name name) {
        String s = name.toString();
        return names.fromString("get" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
    }


    /**
     * 类中导入额外的包. 注意如果该包没有被使用，在编译后不会在 Class 文件中存在 <br/>
     * TODO 未处理 packageList 中可能存在重复的类
     * @param element 注解的类元素
     * @param packageList 待增加的包列表
     */
    private void addImportPackage (Element element, String... packageList) {
        JCTree.JCCompilationUnit jcCompilationUnit = (JCTree.JCCompilationUnit) trees.getPath(element).getCompilationUnit();
        java.util.List<JCTree> jcTrees = new ArrayList<>(jcCompilationUnit.defs);
        // 获取已有的包
        java.util.List<JCTree> sourceImportList = new ArrayList<>();
        jcTrees.forEach(e->{
            if (e.getKind().equals(Tree.Kind.IMPORT)) {
                sourceImportList.add(e);
            }
        });
        // 获取待新增的包
        java.util.List<JCTree.JCImport> needImportList = buildNeedImportList(packageList);
        // 移除重复导入的包
        needImportList.forEach(jcImport -> {
            boolean isExistPackage = false;
            int sourceImportListLen = sourceImportList.isEmpty() ? 0 : sourceImportList.size();
            for (int i = 0; i < sourceImportListLen; ++i) {
                if (sourceImportList.get(i).toString().equals(jcImport.toString())) {
                    isExistPackage = true;
                    break;
                }
            }
            if (!isExistPackage) {
                jcTrees.add(0, jcImport);
            }
        });
        // 加载全部导入的类
        jcCompilationUnit.defs = List.from(jcTrees);
    }

    /**
     * 构建待新增导入包的列表
     * @param packageList 待新增导入包
     */
    private java.util.List<JCTree.JCImport> buildNeedImportList (String... packageList) {
        java.util.List<JCTree.JCImport> needImportList  = new ArrayList<>();
        if (packageList.length > 0) {
            for (String pkg : packageList) {
                JCTree.JCImport needImport = buildImport(pkg);
                needImportList.add(needImport);
            }
        }
        return needImportList;
    }

    /**
     * 使用 {@link TreeMaker} 构建待导入包
     * @param pkg 包地址
     */
    private JCTree.JCImport buildImport (String pkg) {
        int idx = pkg.lastIndexOf(".");
        return treeMaker.Import(
                treeMaker.Select(
                        treeMaker.Ident(names.fromString(pkg.substring(0, idx))),
                        names.fromString(pkg.substring(idx + 1))
                ),
                false
        );
    }
}

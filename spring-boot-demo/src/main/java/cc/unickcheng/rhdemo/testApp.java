/*
 * create on 2023-01-18
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo;

//import io.github.unickcheng.rhandler.plugin.cstatus.annotation.RHandlerExceptionStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * A temporary debugging AbstractProcessor entry
 * @author unickcheng
 */

//@RHandlerExceptionStatusEnum
@AllArgsConstructor
public class testApp {

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    public static void main( String[] args )
    {
    }
}

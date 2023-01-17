/*
 * create on 2023-01-17
 * GitHub https://github.com/UNICKCHENG/Response-Handler
 */

package cc.unickcheng.rhdemo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

/**
 * @author unickcheng
 */

@Getter
public class ResponseBody {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss zz")
    private final Date timestamp;
    private final int status;
    private final String message;
    private final Object data;

    public ResponseBody(@JsonProperty("timestamp") Date timestamp,
                        @JsonProperty("status") int status,
                        @JsonProperty("message") String message,
                        @JsonProperty("data") String data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

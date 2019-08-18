package hci.section.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {

    private int code;
    private String message;
    private String debugInfo;

    public Meta(int code, String message, String debugInfo) {
        this.setCode(code);
        this.setMessage(message);
        this.setDebugInfo(debugInfo);
    }

    public Meta(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }
}

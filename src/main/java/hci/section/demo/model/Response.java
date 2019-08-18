package hci.section.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String status;
    private Object data;
    private Meta meta;

    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";

    public Response() {

    }

    //return with data
    public Response(String status, Object data, Meta meta) {
        this.setStatus(status);
        this.setData(data);
        this.setMeta(meta);
    }

    //return with no data
    public Response(String status, Meta meta) {
        this.setStatus(status);
        this.setMeta(meta);
    }
}

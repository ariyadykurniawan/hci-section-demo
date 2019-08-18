package hci.section.demo.exception;

import hci.section.demo.model.Meta;
import hci.section.demo.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex) {
        String message = "Oopps something wrong with the server";
        Response response= new Response();
        response.setStatus(Response.STATUS_ERROR);
        response.setMeta(new Meta(HttpStatus.INTERNAL_SERVER_ERROR.value(),message, ex.getMessage()));

        return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<Response> handleDataNotFound (DataNotFound ex) {
        String message = "Data not found";
        Response response =  new Response();
        response.setStatus(Response.STATUS_ERROR);
        response.setMeta(new Meta(HttpStatus.NOT_FOUND.value(), message, ex.getMessage()));

        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataExist.class)
    public ResponseEntity<Response> handleDataNotFound (DataExist ex) {
        String message = "Data Already Exist";
        Response response =  new Response();
        response.setStatus(Response.STATUS_ERROR);
        response.setMeta(new Meta(HttpStatus.CONFLICT.value(), message, ex.getMessage()));

        return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
    }
}

package hci.section.demo.exception;

public class DataNotFound extends RuntimeException{

    public DataNotFound(Exception err){
        super(err);
    }

    public DataNotFound(){
        super();
    }
}

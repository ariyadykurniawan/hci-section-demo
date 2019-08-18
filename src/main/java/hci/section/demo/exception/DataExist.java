package hci.section.demo.exception;

public class DataExist extends RuntimeException{
    public DataExist(Exception err){
        super(err);
    }

    public DataExist(){
        super();
    }
}

package hci.section.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSection {

    public UserSection(String moduleName, Integer moduleOrder){
        this.moduleName = moduleName;
        this.moduleOrder = moduleOrder;
    }

    private String moduleName;

    private Integer moduleOrder;
}
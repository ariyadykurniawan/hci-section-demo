package hci.section.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="section")
@Setter
@Getter
public class Section {

    public Section (){ }

    public Section(String sectionName){
        this.sectionName  = sectionName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="section_name")
    private String sectionName;

}

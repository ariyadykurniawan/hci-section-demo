package hci.section.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="section_group_detail")
@Getter
@Setter
public class SectionGroupDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="id_section_group")
    private Long idSectionGroup;

    @Column(name="id_section")
    private Long idSection;

    @Column(name="order_no")
    private Long orderNo;
}

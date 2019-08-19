package hci.section.demo.entity;

import hci.section.demo.model.UserSection;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user")
@SqlResultSetMapping(
        name = "UserSectionMapping",
        classes = @ConstructorResult(
                targetClass = UserSection.class,
                columns = {
                        @ColumnResult(name="moduleName",type = String.class),
                        @ColumnResult(name="moduleOrder",type = Integer.class)
                }
        )
)

@NamedNativeQuery(name = "User.UserSectionMapping",
        query = "SELECT "+
                "s.section_name AS moduleName, sgd.order_no AS moduleOrder "+
                "FROM  user u JOIN section_group sg ON u.id_section_group = sg.id " +
                "JOIN section_group_detail sgd ON sgd.id_section_group = sg.id " +
                "JOIN section s ON s.id = sgd.id_section " +
                "WHERE u.id = ?1 " +
                "ORDER BY sgd.order_no",
        resultClass = UserSection.class,
        resultSetMapping ="UserSectionMapping"
)

@Getter
@Setter
public class User {

    public User(){ }
    public  User(String username, String firstName, String lastName, Long idSectionGroup){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idSectionGroup = idSectionGroup;
    }

    public  User(Long id,String username, String firstName, String lastName, Long idSectionGroup){
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idSectionGroup = idSectionGroup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="username",unique = true)
    private String username;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="id_section_group")
    private Long idSectionGroup;
}

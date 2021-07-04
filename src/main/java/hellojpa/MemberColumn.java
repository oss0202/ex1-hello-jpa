package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="M_MEMBER")
//@SequenceGenerator(name= "member_seq_generator", sequenceName = "member_seq")
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class MemberColumn {
    @Id
    private Long id;

    @Column(name = "name", updatable = false, nullable = false, length = 10)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public MemberColumn(){

    }
    //Getter, Setter…
}

package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="M_MEMBER")
public class MemberColumn {
    @Id
    private Long id;

    @Column(name = "name")
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
    //Getter, Setter…
}

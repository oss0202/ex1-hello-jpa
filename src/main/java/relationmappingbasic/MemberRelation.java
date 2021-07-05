package relationmappingbasic;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MemberRelation {
    @Id @GeneratedValue
    @Column(name ="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String userName;

    @Column(name = "TEAM_ID")
    private Long teamId;

}

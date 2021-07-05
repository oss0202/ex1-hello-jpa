package relationmappingbasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")// 1: 다 매핑에서 뭐랑 매핑 되는건가?, 반대편 사이트
    private List<MemberRelation> members = new ArrayList<MemberRelation>();

}

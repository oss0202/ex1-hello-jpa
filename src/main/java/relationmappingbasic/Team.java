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

    /*
    - 1: 다 매핑에서 뭐랑 매핑 되는건가?, 반대편 사이트
    - 나는 team에 의해서 관리가 된다.
    - mappedBy가 적힌 곳은 읽기만 된다.
    - mappedBy가 적힌 곳을 수정해도 반영이 안 된다.
     */
    @OneToMany(mappedBy = "team")
    private List<MemberRelation> members = new ArrayList<MemberRelation>();

    public void addMember(MemberRelation member) {
        member.setTeam(this);
        members.add(member);
    }
}

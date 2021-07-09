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

    /*
     // 객체지향 모델링을 위한 주석 처리
    @Column(name = "TEAM_ID")
    private Long teamId;
     */

    // 단방향 연관관계
    @ManyToOne
    // Join컬럼으로 사용
    @JoinColumn(name ="TEAM_ID")
    private Team team;

}

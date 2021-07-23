package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일테이블 전략
//@DiscriminatorColumn // 단일테이블 전략은 어노테이션이 없어도 자동으로 DTYPE이 생성된다.( 한테이블에 들어있어서 이게 어떤 테이블에서 인입된 것인지 모르기 때문이다.)
// 있는게 좋다. 없어도 물론 되긴 하지만, 없을 경우 상속받는 테이블들 중에서 어떤 테이블에 매핑되는지 모른다.
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}

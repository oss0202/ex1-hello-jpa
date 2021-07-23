package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
// 있는게 좋다. 없어도 물론 되긴 하지만, 없을 경우 상속받는 테이블들 중에서 어떤 테이블에 매핑되는지 모른다.
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}

package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현 클래스마다 테이블 전략
@DiscriminatorColumn// 테이블 자체가 다 다르기 때문에 의미가 없는 어노테이션이다.
public abstract class Item {// Item만 독단적으로 사용하지 않기 위해서 추상클래스로 선언
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}

package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        
        // 1. EntityManagerFactory 생성(persistence.xml name 매칭)
        // application loading 시점에 DB당 딱 하나만 생성되어야 한다.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        
        // 2. EntityManager 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //transaction 처리가 반드시 있어야 한다.
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        // 3. 실제 code 작성
        Member member = new Member();

        member.setId(2L);
        member.setName("HelloB");

        entityManager.persist(member);

        tx.commit();

        entityManager.close();
        //WAS 가 종료되는 시점에 EntityManagerFactory 를 닫는다
        // -> 내부적으로 Connection pooling에 대한 Resource가 Release된다.
        entityManagerFactory.close();;
    }
}

package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        
        // 1. EntityManagerFactory 생성(persistence.xml name 매칭)
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        
        // 2. EntityManager 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3. 실제 code 작성
        // ...

        entityManager.close();

        entityManagerFactory.close();;
    }
}

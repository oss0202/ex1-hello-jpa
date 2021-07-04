package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainSequence {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            MemberSequence member1 = new MemberSequence();
            member1.setUserName("A");

            MemberSequence member2 = new MemberSequence();
            member2.setUserName("B");

            MemberSequence member3 = new MemberSequence();
            member3.setUserName("C");

            System.out.println("============================");
            /*
                    DB SEQ  | Application Memory
                처음호출   1  |   1   ( DB Call)
미리 확보를 위해서 재호출    51  |   2   ( DB Call)
                         51  |   3   ( Memory Call)
                         51  |   4   ( Memory Call)
                       ...
             */
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1.getId() = " + member1.getId());
            System.out.println("member2.getId() = " + member2.getId());
            System.out.println("member3.getId() = " + member3.getId());
            System.out.println("============================");

            tx.commit();// 실제 INSERT 쿼리 실행
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        em.close();
        emf.close();;
    }
}

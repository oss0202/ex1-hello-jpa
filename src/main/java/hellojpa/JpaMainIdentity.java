package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainIdentity {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            MemberIdentity member = new MemberIdentity();
            member.setUserName("C");

            System.out.println("============================");
            em.persist(member);
            System.out.println("============================");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        Member member = new Member();

        member.setId(2L);
        member.setName("HelloB");

        em.persist(member);

        tx.commit();

        em.close();
        emf.close();;
    }
}

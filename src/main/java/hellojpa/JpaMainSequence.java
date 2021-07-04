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
            MemberSequence member = new MemberSequence();
            member.setUserName("C");

            System.out.println("============================");
            /*
             영속성 컨텍스트에 넣으려고 하는데, SEQUENCE 전략이네?
             "MEMBER_SEQ"에서 다음 값을 가져온 다음에 영속성 컨텍스트에 저장
             - DB에 INSERT 쿼리 날라가기 전.
             */
            em.persist(member);

            System.out.println("member.getId() = " + member.getId());
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

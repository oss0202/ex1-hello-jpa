package relationmappingbasic;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainrRelation {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            MemberRelation memberRelation = new MemberRelation();
            memberRelation.setUserName("member1");
            // member1 을 Team!에 소속시키고 싶다.
            memberRelation.setTeamId(team.getId());
            em.persist(memberRelation);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}

package relationmappingbasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainrRelationManyMistake {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            MemberRelation member = new MemberRelation();
            member.setUserName("member1");
            
            // 역방향( 주인이 아닝 방향 )만 연관관계 설정
            team.getMembers().add(member);

            //member.setTeam(team);

            em.persist(member);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}

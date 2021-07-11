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
            /* 1. 연관관계의 주인에 값을 입력하지 않음
             역방향( 주인이 아닝 방향 )만 연관관계 설정*/
            //team.getMembers().add(member);

            // 2. 올바른 연관관계 설정
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            System.out.println("------------------------------------");
            List<MemberRelation> members = findTeam.getMembers();

            for (MemberRelation memberRelation : members) {
                System.out.println("m = " + memberRelation.getUserName());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}

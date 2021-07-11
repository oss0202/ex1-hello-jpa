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
            em.persist(member);

            /*
            MemberRelation의 setTeam 실행 시
            Team에 있는 Members에도 Team 자동 추가되도록 메서드 생성
             */
            member.setTeam(team);
//            team.getMembers().add(member);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            System.out.println("------------------ 1 ------------------");
            List<MemberRelation> members = findTeam.getMembers();

            System.out.println("------------------ 2 ------------------");
            for (MemberRelation memberRelation : members) {
                System.out.println("m = " + memberRelation.getUserName());
            }

            System.out.println("------------------ 3 ------------------");
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}

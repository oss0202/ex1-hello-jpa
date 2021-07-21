package relationmappingbasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class JpaMainrRelationStackOverFlow {
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

            member.setTeam(team);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<MemberRelation> members = findTeam.getMembers();
            System.out.println("------------------------------------");
            for (MemberRelation memberRelation : members) {
                System.out.println("member = " + findTeam);
            }

            // Optional Test
            Optional<Team> optionalTeam = Optional.of(findTeam);
            Optional.ofNullable(optionalTeam);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}

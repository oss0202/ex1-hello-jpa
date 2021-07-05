package relationmappingbasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainrRelationOneWay {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            // 객체를 테이블에 맞추어 데이터 중심으로 모델링 진행
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            MemberRelation memberRelation = new MemberRelation();
            memberRelation.setUserName("member1");
            // member1 을 Team!에 소속시키고 싶다.
            memberRelation.setTeam(team);
            em.persist(memberRelation);

            //영속성 컨텍스트가 아닌 DB에서 조회해 오고 싶다면 -S
            em.flush();// 쿼리 실행
            em.clear();// 영속성 컨텍스트 삭제
            //영속성 컨텍스트가 아닌 DB에서 조회해 오고 싶다면 -E

            MemberRelation findMember = em.find(MemberRelation.class, memberRelation.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}

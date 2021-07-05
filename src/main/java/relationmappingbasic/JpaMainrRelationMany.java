package relationmappingbasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainrRelationMany {
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

            MemberRelation memberRelation1 = new MemberRelation();
            MemberRelation memberRelation2 = new MemberRelation();
            MemberRelation memberRelation3 = new MemberRelation();
            memberRelation1.setUserName("member1");
            memberRelation2.setUserName("member2");
            memberRelation3.setUserName("member3");
            // member1 을 Team!에 소속시키고 싶다.
            memberRelation1.setTeam(team);
            memberRelation2.setTeam(team);
            memberRelation3.setTeam(team);
            em.persist(memberRelation1);
            em.persist(memberRelation2);
            em.persist(memberRelation3);

            //영속성 컨텍스트가 아닌 DB에서 조회해 오고 싶다면 -S
            em.flush();// 쿼리 실행
            em.clear();// 영속성 컨텍스트 삭제
            //영속성 컨텍스트가 아닌 DB에서 조회해 오고 싶다면 -E

            MemberRelation findMember = em.find(MemberRelation.class, memberRelation1.getId());
            List<MemberRelation> members = findMember.getTeam().getMembers();
            for (MemberRelation member : members) {
                System.out.println("m = " + member.getUserName());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
    }
}

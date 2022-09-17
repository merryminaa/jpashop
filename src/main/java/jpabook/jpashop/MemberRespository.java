package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRespository {
    @PersistenceContext //스프링부트가 엔티티매니저를 자동주입
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
        //커맨드와 쿼리를 분리 => 다시 조회할 수 있도록 id만 리턴
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}

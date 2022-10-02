package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //select m from Member m where m.name = ?
    //메소드 이름에서 자동으로 쿼리 생성(구현부 기재할 필요가 없음)
    List<Member> findByName(String name);

}

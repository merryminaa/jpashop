package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //springframework가 제공하는 어노테이션 사용 권장, 전체 메소드에 적용됨
@RequiredArgsConstructor //생성자 주입
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * **/
    @Transactional //default: readOnly = false
    public Long join(Member member) {
        //중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     * **/
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    /**
     * 회원 수정
     * **/
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
        //커맨드와 쿼리 메소드를 분리
        //데이터가 변경되는 경우에는 결과 객체(영속상태가 끊긴)를 다시 넘기지 않음
        //필요시 변경된 데이터의 id 정도 리턴해줌
    }


}



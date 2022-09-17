package jpabook.jpashop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRespositoryTest {
    
    @Autowired
    MemberRespository memberRespository;

    @Test
    @Transactional() //springframework 어노테이션 사용 권장
    //트랜잭션 어노테이션이 테스트케이스에 있을 경우 테스트 성공 이후 자동으로 롤백처리
    @Rollback(false)
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRespository.save(member);
        Member findMember = memberRespository.find(saveId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
        // ==비교시 true
        //트랜잭션 안에서 영속성 컨텍스트에 있을 경우 같은 엔티티로 인식(1차 캐시로 관리)

    }
}
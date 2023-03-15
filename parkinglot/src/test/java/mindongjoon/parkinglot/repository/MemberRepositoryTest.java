package mindongjoon.parkinglot.repository;

import mindongjoon.parkinglot.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;


    @Test
    public void save() {
        Member newMember = Member.createMember("min", "1234", "dj.min43@gmail.com");
        memberRepository.save(newMember);
        Assertions.assertThat(newMember.getName()).isEqualTo("min");

    }

    @Test
    public void findOne() {
    }
}
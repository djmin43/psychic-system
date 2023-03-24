package mindongjoon.parkinglot.service;

import mindongjoon.parkinglot.domain.Car;
import mindongjoon.parkinglot.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void join() {
        Member newMember = createNewMember();
        Long memberId = memberService.join(newMember);
        assertThat(memberService.find(memberId).getName()).isEqualTo("min");
    }

    private static Member createNewMember() {
        Member newMember = Member.createMember("min", "1234", "dj.min43@gmail.com");
        return newMember;
    }

    @Test
    public void find() {
        Member newMember = createNewMember();
        Long joinId = memberService.join(newMember);
        Member findMember = memberService.find(joinId);
        assertThat(findMember.getName()).isEqualTo("min");

    }

    @Test
    public void addCar() {
        Member newMember = createNewMember();
        memberService.join(newMember);
        Car car1 = Car.createCar("서울 나 1234", "아반떼", "testImageUrl");
        Car car2 = Car.createCar("서울 나 1234", "아반떼", "testImageUrl");
        memberService.addCar(newMember.getId(), car1);
        memberService.addCar(newMember.getId(), car2);
        int carsSize = newMember.getCars().size();
        assertThat(carsSize).isEqualTo(2);
        assertThat(carsSize).isNotEqualTo(1);
        assertThat(carsSize).isNotEqualTo(0);

    }
}
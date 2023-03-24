package mindongjoon.parkinglot.repository;

import mindongjoon.parkinglot.domain.Car;
import mindongjoon.parkinglot.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CarRepository carRepository;


    @Test
    public void save() {
        Member newMember = getNewMember();
        assertThat(newMember.getName()).isEqualTo("min");

    }

    private Member getNewMember() {
        Member newMember = Member.createMember("min", "1234", "dj.min43@gmail.com");
        memberRepository.save(newMember);
        return newMember;
    }

    @Test
    public void findOne() {
        Member newMember = getNewMember();
        Member findMember = memberRepository.findOne(newMember.getId());
        System.out.println("newMemberId = " + findMember.getId());
        System.out.println("newMemberId = " + newMember.getId());
        assertThat(findMember.getId()).isEqualTo(newMember.getId());
    }

    @Test
    public void saveCar() {
        Member newMember = getNewMember();
        Car newCar = getNewCar();
        memberRepository.saveCar(newMember.getId(), newCar);
        assertThat(newMember.getCars().size()).isEqualTo(1);
    }
    private Car getNewCar() {
        Car newCar = Car.createCar("서울 나 1234", "아반떼", "testImageUrl");
        carRepository.save(newCar);
        return newCar;
    }


}
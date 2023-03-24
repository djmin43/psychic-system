package mindongjoon.parkinglot.repository;

import mindongjoon.parkinglot.domain.Car;
import mindongjoon.parkinglot.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save() {
        Car newCar = getNewCar();
        assertThat(newCar.getMember().getName()).isEqualTo("min");
    }

    private Car getNewCar() {
        Car newCar = Car.createCar("서울 나 1234", "아반떼", "testImageUrl");
        carRepository.save(newCar);
        return newCar;
    }

    @Test
    public void findOne() {
        Car newCar = getNewCar();
        Car findCar = carRepository.findOne(newCar.getId());
        System.out.println("findCarId = " + findCar.getId());
        System.out.println("findCarName = " + findCar.getCarName());
        assertThat(newCar.getId()).isEqualTo(findCar.getId());
    }
}
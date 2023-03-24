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

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CarServiceTest {

    @Autowired
    CarService carService;

    @Test
    public void find() {
        Car newCar = getNewCar();
        carService.add(newCar);
        Car findCar = carService.find(newCar.getId());
        assertThat(findCar.getRegistrationNumber()).isEqualTo("서울 나 1234");

    }

    @Test
    public void add() {
        Car newCar = getNewCar();
        Long carId = carService.add(newCar);
        assertThat(carService.find(carId).getCarName()).isEqualTo("아반떼");
    }

    private static Car getNewCar() {
        Car newCar = Car.createCar("서울 나 1234", "아반떼", "testImageUrl");
        return newCar;
    }
}
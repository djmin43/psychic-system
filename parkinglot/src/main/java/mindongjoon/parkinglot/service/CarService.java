package mindongjoon.parkinglot.service;

import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Car;
import mindongjoon.parkinglot.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    /**
     * 자동차 조회
     */
    public Car find(Long carId) {
        return carRepository.findOne(carId);
    }

    /**
     * 자동차 생성
     */
    public Long add(Car car) {
        carRepository.save(car);
        return car.getId();
    }


}

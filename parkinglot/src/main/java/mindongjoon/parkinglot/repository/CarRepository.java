package mindongjoon.parkinglot.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Car;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarRepository {

    private final EntityManager em;

    @Transactional
    public void save(Car car) {
        em.persist(car);
    }

    public Car findOne(Long id) {
        return em.find(Car.class, id);
    }
}

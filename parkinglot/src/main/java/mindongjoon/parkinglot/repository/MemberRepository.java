package mindongjoon.parkinglot.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Car;
import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.domain.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRepository {

    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    @Transactional
    public void saveCar(Long memberId, Car car) {
        Member findMember = em.find(Member.class, memberId);
        List<Car> cars = findMember.getCars();
        cars.add(car);
    }

}

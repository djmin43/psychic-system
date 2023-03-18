package mindongjoon.parkinglot.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationRepository {

    private final EntityManager em;

    @Transactional
    public void save(Reservation reservation) {
       em.persist(reservation);
    }

    public Reservation findOne(Long id) {
        return em.find(Reservation.class, id);
    }
}

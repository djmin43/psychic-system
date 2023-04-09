package mindongjoon.parkinglot.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    public List<Reservation> findAll() {
        return em.createQuery("SELECT u FROM Reservation u", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findByRange(LocalDateTime startAt, LocalDateTime endAt) {
        return em.createQuery("SELECT u FROM Reservation u WHERE u.startAt > :date1 AND u.endAt < :date2", Reservation.class)
                .setParameter("date1", startAt)
                .setParameter("date2", endAt)
                .getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Reservation reservation = em.find(Reservation.class, id);
        reservation.setMember(null);
        em.remove(reservation);
    }

}

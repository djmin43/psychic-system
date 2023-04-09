package mindongjoon.parkinglot.service;

import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.domain.Reservation;
import mindongjoon.parkinglot.repository.MemberRepository;
import mindongjoon.parkinglot.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    /**
     * 예약 조회 by id
     */
    public Reservation getById(Long id) {
        return reservationRepository.findOne(id);
    }

    /**
     * 예약 리스트 조회 by Dates
     */
    public List<Reservation> getByRange(LocalDateTime startAt, LocalDateTime endAt) {
        return reservationRepository.findByRange(startAt, endAt);
    }

    /**
     * 예약 리스트 조회 all
     */
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    /**
     * 예약하기
     */
    @Transactional
    public Long add(Reservation reservation) {
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    /**
     * 벌크예약 by Dates
     */
    @Transactional
    public void addBulk(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            reservationRepository.save(reservation);
            Long id = reservation.getId();
            System.out.println("id = " + id);
        }
    }

    /**
     * 예약변경
     */
    @Transactional
    public Long change(Long id, LocalDateTime startAt, LocalDateTime endAt) {
        Reservation findReservation = reservationRepository.findOne(id);
        findReservation.setStartAt(startAt);
        findReservation.setEndAt(endAt);
        return findReservation.getId();
    }

    /**
     * 예약 취소 by id
     */
    @Transactional
    public void cancelById(Long id) {
        reservationRepository.delete(id);
    }

}

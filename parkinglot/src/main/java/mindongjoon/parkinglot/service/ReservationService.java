package mindongjoon.parkinglot.service;

import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.domain.Reservation;
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

    /**
     * 예약 리스트 조회 by Dates
     */
    public List<Reservation> getByRange(LocalDateTime startAt, LocalDateTime endAt) {
        return reservationRepository.findByRange(startAt, endAt);
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
     * 반복예약
     */

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

    /**
     * 예약 취소 by Dates
     */
    @Transactional
    public void cancelByDates(LocalDateTime startAt, LocalDateTime endAt) {
        List<Reservation> findByRange = reservationRepository.findByRange(startAt, endAt);
        findByRange.stream().map(Reservation::getId).forEach(reservationRepository::delete);
    }




}

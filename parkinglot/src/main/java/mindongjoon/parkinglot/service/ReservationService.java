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



}

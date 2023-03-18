package mindongjoon.parkinglot.repository;

import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save() {
        LocalDateTime start = getStartTime();
        LocalDateTime end = getEndTime();
        Member newMember = getNewMember();
        Reservation reservation = getReservation(start, end, newMember);
        reservationRepository.save(reservation);
        assertThat(reservation.getEndAt()).isEqualTo(end);
    }

    private static LocalDateTime getEndTime() {
        LocalDateTime end = LocalDateTime.of(2023, 3, 18, 3, 05);
        return end;
    }

    private static LocalDateTime getStartTime() {
        LocalDateTime start = LocalDateTime.of(2023, 3, 18, 2, 05);
        return start;
    }

    private static Reservation getReservation(LocalDateTime start, LocalDateTime end, Member newMember) {
        Reservation reservation = Reservation.createReservation(start, end, newMember);
        return reservation;
    }

    @Test
    public void findOne() {
        Reservation reservation = getReservation(getStartTime(), getEndTime(), getNewMember());
        reservationRepository.save(reservation);
        Reservation findReservation = reservationRepository.findOne(reservation.getId());
        assertThat(reservation.getId()).isEqualTo(findReservation.getId());
    }

    private Member getNewMember() {
        Member newMember = Member.createMember("min", "1234", "dj.min43@gmail.com");
        memberRepository.save(newMember);
        return newMember;
    }

}
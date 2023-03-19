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
import java.util.List;

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
        LocalDateTime start = getLocalDateTime(2023, 3, 19, 3, 5);
        LocalDateTime end = getLocalDateTime(2023, 3, 20, 3, 5);
        Member newMember = getNewMember();
        Reservation reservation = getReservation(start, end, newMember);
        reservationRepository.save(reservation);
        assertThat(reservation.getEndAt()).isEqualTo(end);
    }


    private static LocalDateTime getLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    private static Reservation getReservation(LocalDateTime start, LocalDateTime end, Member newMember) {
        return Reservation.createReservation(start, end, newMember);
    }

    @Test
    public void findOne() {
        Reservation reservation = getReservation(
                getLocalDateTime(2023, 3, 19, 1, 2),
                getLocalDateTime(2023, 3, 20, 1, 2),
                getNewMember());
        reservationRepository.save(reservation);
        Reservation findReservation = reservationRepository.findOne(reservation.getId());
        assertThat(reservation.getId()).isEqualTo(findReservation.getId());
    }

    private Member getNewMember() {
        Member newMember = Member.createMember("min", "1234", "dj.min43@gmail.com");
        memberRepository.save(newMember);
        return newMember;
    }

    @Test
    public void findByRange() {
        LocalDateTime start = getLocalDateTime(2023, 3, 19, 3, 5);
        LocalDateTime end = getLocalDateTime(2023, 3, 20, 3, 5);
        Member newMember = getNewMember();
        Reservation reservation = getReservation(start, end, newMember);
        reservationRepository.save(reservation);
        List<Reservation> findRange = reservationRepository.findByRange(
                LocalDateTime.of(2023, 12, 1, 1, 1),
                LocalDateTime.of(2024, 1, 1, 1, 1)
        );

        for (Reservation reservation1 : findRange) {
            System.out.println("startAt = " + reservation1.getStartAt());
            System.out.println("endAt = " + reservation1.getEndAt());
        }

        assertThat(findRange.size()).isEqualTo(0);

    }

}
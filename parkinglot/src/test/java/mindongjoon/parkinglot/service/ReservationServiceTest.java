package mindongjoon.parkinglot.service;

import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

    @Test
    public void getByRange() {
    }

    @Test
    public void add() {
        Member newMember = Member.createMember("min", "1234", "dj.min43@gmail.com");
        Reservation reservation = createNewReservation(
                LocalDateTime.of(2023, 3, 19, 1, 2),
                LocalDateTime.of(2023, 3, 20, 1, 2),
                newMember);
        reservationService.add(reservation);
    }

    @Test
    public void addBulk() {
    }

    @Test
    public void change() {
    }

    @Test
    public void cancelById() {
    }

    @Test
    public void cancelByDates() {
    }

    private static Reservation createNewReservation(LocalDateTime start, LocalDateTime end, Member newMember) {
        return Reservation.createReservation(start, end, newMember);
    }

}
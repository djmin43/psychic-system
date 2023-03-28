package mindongjoon.parkinglot.service;

import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.domain.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

    @Test
    public void getByRange() {
        Member newMember = createNewMember();
        IntStream
                .range(0, 8)
                .mapToObj(i -> newMember)
                .forEach(this::createNew);
        List<Reservation> inRange = reservationService.getByRange(
                LocalDateTime.of(2020, 01, 01, 01, 01),
                LocalDateTime.of(2024, 12, 31, 12, 00)
        );
        List<Reservation> outOfRange = reservationService.getByRange(
                LocalDateTime.of(2024, 01, 01, 01, 01),
                LocalDateTime.of(2024, 12, 31, 12, 00)
        );

        assertThat(inRange.size()).isEqualTo(8);
        assertThat(outOfRange.size()).isEqualTo(0);
    }

    @Test
    public void getAll() {
        Member newMember = createNewMember();
        IntStream
                .range(0, 2)
                .mapToObj(i -> newMember)
                .forEach(this::createNew);
        List<Reservation> all = reservationService.getAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public void add() {
        Member newMember = createNewMember();
        createNew(newMember);
        List<Reservation> all = reservationService.getAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void addBulk() {
        Member newMember = createNewMember();
        Reservation reservation1 = createNewReservation(
                LocalDateTime.of(2023, 3, 19, 1, 2),
                LocalDateTime.of(2023, 3, 20, 1, 2),
                newMember);
        Reservation reservation2 = createNewReservation(
                LocalDateTime.of(2023, 3, 19, 1, 2),
                LocalDateTime.of(2023, 4, 20, 1, 2),
                newMember);
        ArrayList<Reservation> bulk = new ArrayList<>(0);
        bulk.add(reservation1);
        bulk.add(reservation2);
        reservationService.addBulk(bulk);
        List<Reservation> all = reservationService.getAll();
        System.out.println("all = " + all.size());

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

    private void createNew(Member member) {
        Reservation reservation = createNewReservation(
                LocalDateTime.of(2023, 3, 19, 1, 2),
                LocalDateTime.of(2023, 3, 20, 1, 2),
                member);
        reservationService.add(reservation);
    }

    private Member createNewMember() {
        Member newMember = Member.createMember("min", "1234", "dj.min43@gmail.com");
        memberService.join(newMember);
        return newMember;
    }

}
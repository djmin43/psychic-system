package mindongjoon.parkinglot.service;

import jakarta.persistence.EntityManager;
import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    public void getByRange() {
        Member newMember = createNewMember();
        IntStream
                .range(0, 8)
                .mapToObj(i -> newMember)
                .forEach(this::createNew);
        List<Reservation> inRange = reservationService.getByRange(
                LocalDateTime.of(2020, 1, 1, 1, 1),
                LocalDateTime.of(2024, 12, 31, 12, 0)
        );
        List<Reservation> outOfRange = reservationService.getByRange(
                LocalDateTime.of(2024, 1, 1, 1, 1),
                LocalDateTime.of(2024, 12, 31, 12, 0)
        );

        assertThat(inRange).hasSize(8);
        assertThat(outOfRange).isEmpty();
    }

    @Test
    public void getAll() {
        Member newMember = createNewMember();
        IntStream
                .range(0, 2)
                .mapToObj(i -> newMember)
                .forEach(this::createNew);
        List<Reservation> all = reservationService.getAll();
        assertThat(all).hasSize(2);
    }

    @Test
    public void add() {
        Member newMember = createNewMember();
        createNew(newMember);
        List<Reservation> all = reservationService.getAll();
        assertThat(all).hasSize(1);
        System.out.println("newMember = " + newMember.getReservations());
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
        assertThat(all).hasSize(2);
    }

    @Test
    public void change() {
        Member newMember = createNewMember();
        Reservation reservation = createNewReservation(
                LocalDateTime.of(2023, 3, 19, 1, 2),
                LocalDateTime.of(2023, 3, 20, 1, 2),
                newMember);
        reservationService.add(reservation);
        assertThat(reservation.getStartAt()).isEqualTo(LocalDateTime.of(2023, 3, 19, 1, 2));
        Long changeId = reservationService.change(reservation.getId(),
                LocalDateTime.of(2024, 3, 19, 1, 2),
                LocalDateTime.of(2024, 3, 20, 1, 2));
        Reservation byId = reservationService.getById(changeId);
        assertThat(byId.getMember().getName()).isEqualTo("min");
        assertThat(byId.getStartAt()).isEqualTo(LocalDateTime.of(2024, 3, 19, 1, 2));
        assertThat(byId.getEndAt()).isEqualTo(LocalDateTime.of(2024, 3, 20, 1, 2));
    }

    @Test
    public void cancelById() {
        Member newMember = createNewMember();
        Reservation reservation = createNewReservation(
                LocalDateTime.of(2023, 3, 19, 1, 2),
                LocalDateTime.of(2023, 3, 20, 1, 2),
                newMember);
        reservationService.add(reservation);
        Long memberId = reservation.getMember().getId();
        Member findMember = memberService.find(memberId);
        System.out.println("findMember = " + findMember.getName());
        System.out.println("findMember = " + findMember.getReservations());
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
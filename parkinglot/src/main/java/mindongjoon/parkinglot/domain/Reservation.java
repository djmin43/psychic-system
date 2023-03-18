package mindongjoon.parkinglot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @NotBlank
    private LocalDateTime startAt;

    @NotBlank
    private LocalDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // domain methods
    public static Reservation createReservation(LocalDateTime startAt, LocalDateTime endAt, Member member) {
        Reservation reservation = new Reservation();
        reservation.setStartAt(startAt);
        reservation.setEndAt(endAt);
        reservation.setMember(member);
        return reservation;


    }


}

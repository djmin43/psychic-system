package mindongjoon.parkinglot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // domain methods
    public static Reservation createReservation(LocalDateTime startAt, LocalDateTime endAt, Member member) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalStateException("예약 시작 시간이 예약 끝 시간보다 뒤입니다.");
        }
        Reservation reservation = new Reservation();
        reservation.setStartAt(startAt);
        reservation.setEndAt(endAt);
        reservation.setMember(member);
        return reservation;


    }


}

package mindongjoon.parkinglot.domain;

import jakarta.persistence.*;
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

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status; // 예약 상태 [BOOK, CANCEL]

    // 연관관계 메소드 -- 아주 중요함!!
    public void setMember(Member member) {
        this.member = member;
        member.getReservations().add(this);
    }

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

package mindongjoon.parkinglot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
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

    // 연관관계 메소드 -- 아주 중요함!!
    // 연관관계 주인은 reservation
    // 연관관계 주인이 아닌 객체는 Read-only
    // db의 입장에서는 foreign key 하나만 가지고 양방향으로 값을 가져올 수 있지만, 객체는 '참조'의 개념 때문에 서로 가져와야함
    // 객체 level에서 해당 값을 조회해야할 경우가 생기기 때문에 member.getReservations().add(this)를 쳐주면서 조회 편의성을 제공 (db에는 아무런 변경도 생기지 않음)
    public void setMember(Member member) {
        this.member = member;
        member.getReservations().add(this);
    }


}

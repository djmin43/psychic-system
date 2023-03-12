package mindongjoon.parkinglot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;


    @NotBlank
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;




}

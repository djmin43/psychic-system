package mindongjoon.parkinglot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Long id;

    @NotBlank
    private String registrationNumber;
    @NotBlank
    private String carName;
    private String imageUrl;

}

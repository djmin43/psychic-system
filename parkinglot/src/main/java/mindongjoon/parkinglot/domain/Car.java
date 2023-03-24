package mindongjoon.parkinglot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // domain method
    public static Car createCar(String registrationNumber, String carName, String imageUrl ) {
        Car car = new Car();
        car.setRegistrationNumber(registrationNumber);
        car.setCarName(carName);
        car.setImageUrl(imageUrl);
        return car;
    }

}

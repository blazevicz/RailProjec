package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.pl.deenes.model.LocomotiveType;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"name", "surname", "phoneNumber"})
@Table(name = "driver")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer driverId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "locomotive")
    private Set<LocomotiveType> locomotiveAuthorization;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<LineEntity> lineEntityAuthorization;

}

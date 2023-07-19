package org.pl.deenes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"name", "surname", "pesel"})
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

    @Column(name = "pesel")
    private Integer pesel;

/*    @Column(name = "locomotive")
    private Set<LocomotiveType> locomotiveAuthorization;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<LineEntity> lineEntityAuthorization;*/

}

package org.pl.deenes.delete;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@Setter
@Getter
@With
@NoArgsConstructor
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Integer petId;

    @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Owner owner;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pet")
    @Column(name = "toys")
    private Set<Toy> toys;

    public void addToys(@NonNull Toy toy) {
        toy.setPet(this);
        if (toys == null) {
            toys = new HashSet<>();
        }
        toys.add(toy);
    }

}

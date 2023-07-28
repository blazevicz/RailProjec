package org.pl.deenes.delete;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PetRepo {

    private final PetJPA petJPA;


    public void save(Pet pet) {
        petJPA.save(pet);
    }

}

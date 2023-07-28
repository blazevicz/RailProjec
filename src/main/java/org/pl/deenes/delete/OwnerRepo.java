package org.pl.deenes.delete;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OwnerRepo {

    private final OwnerJPA ownerJPA;


    void save(Owner owner) {
        ownerJPA.save(owner);

    }

}

package org.pl.deenes.delete;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@ToString
@AllArgsConstructor
@Slf4j
public class Runner {

    private final OwnerRepo ownerRepo;
    private final PetRepo petRepo;


    @Transactional
    public void run() {

        Toy build = Toy.builder().integer(1).build();
        Toy build1 = Toy.builder().integer(2).build();
        Toy build2 = Toy.builder().integer(3).build();

        Pet build3 = Pet.builder().toys(Set.of(build, build1, build2)).build();

        Owner build4 = Owner.builder().pet(build3).build();

        ownerRepo.save(build4);
        run2();

    }

    @Transactional
    public void run2() {
        Toy build = Toy.builder().integer(1).build();
        Toy build1 = Toy.builder().integer(2).build();
        Toy build2 = Toy.builder().integer(3).build();
        Toy build111 = Toy.builder().integer(11113).build();
        //  Pet test1 = Pet.builder().toys(Set.of(build, build1, build2)).build();


        Pet build4 = Pet.builder().build();
        Pet pet = build4.withToys(Set.of(build111));
        build4.addToys(build1);
        build4.addToys(build2);
        build4.addToys(build);
        petRepo.save(build4);
        petRepo.save(pet);


        //petRepo.save(test1);

    }


}

package org.pl.deenes.infrastructure.integration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pl.deenes.test.Child;
import org.pl.deenes.test.Parent;
import org.pl.deenes.test.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TestIT extends IntegrationReposIT {

    private final ParentRepository parentRepository;

    @Test
    void shouldWork() {
        Child child1 = Child.builder().uuid(UUID.randomUUID()).build();
        Child child2 = Child.builder().uuid(UUID.randomUUID()).build();

        Parent parent = Parent.builder()
                .kwr(123)
                .build();

        child1.setParent(parent);
        child2.setParent(parent);

        parent.setChildSet(Set.of(child1, child2));

        parentRepository.save(parent);

        List<Parent> all = parentRepository.findAll();

        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals(2, all.get(0).getChildSet().size());
    }


}

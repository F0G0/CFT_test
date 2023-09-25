package ru.cft.lab.cft_test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.lab.cft_test.repository.entity.Letters;
import ru.cft.lab.cft_test.repository.entity.Numbers;

import java.util.List;
import java.util.UUID;

public interface LettersRepository extends CrudRepository<Letters, UUID> {
    Letters findTopByOrderByStartAscEndAsc();
    List<Letters> getAllByOrderByStartAsc();
}

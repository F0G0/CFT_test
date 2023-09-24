package ru.cft.lab.cft_test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.lab.cft_test.repository.entity.Numbers;

import java.util.UUID;

@Repository
public interface NumbersRepository extends CrudRepository<Numbers, UUID> {
}

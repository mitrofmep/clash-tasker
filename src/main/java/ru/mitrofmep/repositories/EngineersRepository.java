package ru.mitrofmep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mitrofmep.models.Engineer;

import java.util.List;
import java.util.Optional;

@Repository
public interface EngineersRepository extends JpaRepository<Engineer, Integer> {
    List<Engineer> findByFirstName(String firstName);

    List<Engineer> findByDiscipline(String discipline);

    Optional<Engineer> findByTelegramUsername(String telegramUsername);

    List<Engineer> findByFirstNameStartingWith(String startingWith);

    Optional<Engineer> findByFirstNameAndLastName(String firstName, String LastName);

    Optional<Engineer> findByEmail(String email);
}
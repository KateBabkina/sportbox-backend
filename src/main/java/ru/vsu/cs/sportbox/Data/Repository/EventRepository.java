package ru.vsu.cs.sportbox.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sportbox.Data.Model.Event;

import java.util.Set;

@EnableJpaRepositories
@Repository
public interface EventRepository extends JpaRepository<Event,Integer>, JpaSpecificationExecutor<Event> {
    Event findById(int id);
    void removeEventById(int id);
}

package ru.vsu.cs.sportbox.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sportbox.Data.Model.Person;
import ru.vsu.cs.sportbox.Data.Model.Role;

@EnableJpaRepositories
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>  {
    Role findByName(String name);
}

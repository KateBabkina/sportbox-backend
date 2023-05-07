package ru.vsu.cs.sportbox.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface InventoryTypeRepository extends JpaRepository<InventoryType,Integer>, JpaSpecificationExecutor<InventoryType> {
    InventoryType findByType(String type);
    InventoryType findById(int id);
}

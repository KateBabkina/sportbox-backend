package ru.vsu.cs.sportbox.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sportbox.Data.Model.Inventory;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    List<Inventory> findByInventoryType(InventoryType inventoryType);
    List<Inventory> findByInventoryTypeAndSize(InventoryType inventoryType, int size);
    Inventory findById(int id);
    Inventory findByIdAndInventoryType(int id, InventoryType inventoryType);
    List<Inventory> findAll();
    void removeInventoryById(int id);
}

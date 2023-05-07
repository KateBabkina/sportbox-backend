package ru.vsu.cs.sportbox.Service;

import ru.vsu.cs.sportbox.Data.Dto.InventoryChangeDto;
import ru.vsu.cs.sportbox.Data.Dto.InventoryCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.InventoryFilterDto;
import ru.vsu.cs.sportbox.Data.Model.Inventory;
import ru.vsu.cs.sportbox.Responses.*;

import java.util.List;

public interface InventoryService {

    List<Inventory> filterInventory(InventoryFilterDto inventoryFilterDto);
    InventoryResponse addNewInventory(InventoryCreateDto inventoryCreateDto);
    InventoryResponse getInventoryById(int id);
    InventoryResponse deleteInventoryById(int id);
    InventoryResponse changeInventory(int id, InventoryChangeDto inventoryChangeDto);
}

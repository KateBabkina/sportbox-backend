package ru.vsu.cs.sportbox.Service;

import ru.vsu.cs.sportbox.Data.Dto.InventoryTypeFilterDto;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;
import ru.vsu.cs.sportbox.Responses.InventoryResponse;
import ru.vsu.cs.sportbox.Responses.InventoryTypeResponse;

import java.util.List;

public interface InventoryTypeService {
    List<InventoryType> filterInventoryType(InventoryTypeFilterDto inventoryTypeFilterDto);

    List<InventoryType> getAllInventoryTypes();

    InventoryTypeResponse getInventoryTypeById(int id);
}

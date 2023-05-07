package ru.vsu.cs.sportbox.Service.Impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.sportbox.Data.Dto.InventoryChangeDto;
import ru.vsu.cs.sportbox.Data.Dto.InventoryCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.InventoryFilterDto;
import ru.vsu.cs.sportbox.Data.Mapper.InventoryMapper;
import ru.vsu.cs.sportbox.Data.Model.Inventory;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;
import ru.vsu.cs.sportbox.Data.Repository.InventoryRepository;
import ru.vsu.cs.sportbox.Data.Repository.InventoryTypeRepository;
import ru.vsu.cs.sportbox.Responses.*;
import ru.vsu.cs.sportbox.Service.InventoryService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;
    private InventoryTypeRepository inventoryTypeRepository;
    private InventoryMapper inventoryMapper;

    @Override
    @Transactional
    public List<Inventory> filterInventory(InventoryFilterDto inventoryFilterDto) {
        if (inventoryFilterDto.getInventoryType() != null && inventoryFilterDto.getId() != 0) {
            ArrayList<Inventory> result = new ArrayList<>();
            result.add(inventoryRepository.findByIdAndInventoryType(inventoryFilterDto.getId(), inventoryTypeRepository.findByType(inventoryFilterDto.getInventoryType())));
            return result;
        } else if (inventoryFilterDto.getInventoryType() != null) {
            return inventoryRepository.findByInventoryType(inventoryTypeRepository.findByType(inventoryFilterDto.getInventoryType()));
        } else if (inventoryFilterDto.getId() != 0) {
            ArrayList<Inventory> result = new ArrayList<>();
            result.add(inventoryRepository.findById(inventoryFilterDto.getId()));
            return result;
        }
        return inventoryRepository.findAll();
    }

    @Override
    @Transactional
    public InventoryResponse addNewInventory(InventoryCreateDto inventoryCreateDto) {
        Inventory inventory = inventoryMapper.inventoryCreateDtoToInventory(inventoryCreateDto);
        if (inventory == null) {
            return new InventoryResponse("При добавлении инвентаря возникла ошибка.", false, null);
        }
        Inventory savedInventory = inventoryRepository.save(inventory);
        return new InventoryResponse("Инвентарь был успешно добавлен.", true, savedInventory);
    }

    @Override
    @Transactional
    public InventoryResponse getInventoryById(int id) {
        Inventory inventory = inventoryRepository.findById(id);
        if (inventory == null) {
            return new InventoryResponse("Инвентаря с указанным идентификатором не существует.", false, null);
        }
        return new InventoryResponse("Инвентарь был успешно найден.", true, inventory);
    }

    @Override
    @Transactional
    public InventoryResponse deleteInventoryById(int id) {
        Inventory inventory = inventoryRepository.findById(id);
        if (inventory != null) {
            inventoryRepository.removeInventoryById(id);
            return new InventoryResponse("Удаление инвентаря прошло успешно.", true, inventory);
        } else {
            return new InventoryResponse("Инвентаря с указанным идентификатором не существует.", false, null);
        }
    }

    @Override
    @Transactional
    public InventoryResponse changeInventory(int id, InventoryChangeDto inventoryChangeDto) {
        Inventory inventory = inventoryRepository.findById(id);
        if (inventory != null) {
            if (StringUtils.isNotBlank(inventoryChangeDto.getName())){
                inventory.setName(inventoryChangeDto.getName());
            }

            InventoryType inventoryType;
            if (StringUtils.isNotBlank(inventoryChangeDto.getInventoryType())) {
                inventoryType = inventoryTypeRepository.findByType(inventoryChangeDto.getInventoryType());
            } else {
                inventoryType = inventory.getInventoryType();
            }
            if (inventoryType == null) {
                return new InventoryResponse("Указанного типа инвентаря не существует.", false, null);
            }
            inventory.setInventoryType(inventoryType);
            if (inventoryType.getIsSizable() && inventoryChangeDto.getSize() != 0) {
                inventory.setSize(inventoryChangeDto.getSize());
            }

            inventoryRepository.save(inventory);
            return new InventoryResponse("Изменение инвентаря прошло успешно.", true, inventory);
        } else {
            return new InventoryResponse("Инвентаря с указанным идентификатором не существует.", false, null);
        }
    }
}

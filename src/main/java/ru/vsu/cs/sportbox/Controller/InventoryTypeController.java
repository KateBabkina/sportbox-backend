package ru.vsu.cs.sportbox.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sportbox.Data.Dto.InventoryFilterDto;
import ru.vsu.cs.sportbox.Data.Dto.InventoryTypeFilterDto;
import ru.vsu.cs.sportbox.Data.Model.Inventory;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;
import ru.vsu.cs.sportbox.Responses.InventoryResponse;
import ru.vsu.cs.sportbox.Responses.InventoryTypeResponse;
import ru.vsu.cs.sportbox.Service.InventoryService;
import ru.vsu.cs.sportbox.Service.InventoryTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory_type")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Tag(name = "Типы оборудования", description = "Методы для работы с типами оборудования")
public class InventoryTypeController {
    private InventoryTypeService inventoryTypeService;
    @PostMapping("/filter")
    @Operation(summary = "Фильтрация списка типов оборудования")
    public ResponseEntity<List<InventoryType>> filterInventoryType(@RequestBody InventoryTypeFilterDto inventoryTypeFilterDto) {
        HttpStatus httpStatus = HttpStatus.OK;
        List<InventoryType> inventoryTypes = inventoryTypeService.filterInventoryType(inventoryTypeFilterDto);
        return new ResponseEntity<>(inventoryTypes, httpStatus);
    }

    @GetMapping("/get_all")
    @Operation(summary = "Получение всех типов оборудования для выпадающего списка")
    public ResponseEntity<List<InventoryType>> getAllInventoryTypes() {
        HttpStatus httpStatus = HttpStatus.OK;
        List<InventoryType> inventoryTypes = inventoryTypeService.getAllInventoryTypes();
        return new ResponseEntity<>(inventoryTypes, httpStatus);
    }

    @GetMapping("/get_by_id")
    @Operation(summary = "Получение информации о типе оборудования по его id")
    public ResponseEntity<InventoryTypeResponse> getInventoryById(@Parameter(description = "Уникальный идентификатор типа оборудования")
                                                              @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        InventoryTypeResponse inventoryTypeGetResponse = inventoryTypeService.getInventoryTypeById(id);
        return new ResponseEntity<>(inventoryTypeGetResponse, httpStatus);
    }
}

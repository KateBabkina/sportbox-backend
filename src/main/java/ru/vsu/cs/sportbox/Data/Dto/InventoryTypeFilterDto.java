package ru.vsu.cs.sportbox.Data.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(description = "Информация, необходимая для фильтрации списка типов оборудования")
public class InventoryTypeFilterDto {
    private String inventoryType;
    private int minPrice;
    private int maxPrice;
    private String startDate;
    private String endDate;
}

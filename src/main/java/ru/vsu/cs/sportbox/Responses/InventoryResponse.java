package ru.vsu.cs.sportbox.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vsu.cs.sportbox.Data.Model.Inventory;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Ответ сервера с информацией об оборудовании")
public class InventoryResponse {
    private String message;
    private boolean status;
    private Inventory inventory;
}

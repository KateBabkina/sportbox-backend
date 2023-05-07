package ru.vsu.cs.sportbox.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vsu.cs.sportbox.Data.Model.Booking;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Ответ сервера с информацией о заказе")
public class BookingResponse {
    private String message;
    private boolean status;
    private Booking booking;
}

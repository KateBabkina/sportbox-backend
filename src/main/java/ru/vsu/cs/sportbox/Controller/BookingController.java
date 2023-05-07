package ru.vsu.cs.sportbox.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sportbox.Data.Dto.BookingAdminDto;
import ru.vsu.cs.sportbox.Data.Dto.BookingCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.BookingFilterDto;
import ru.vsu.cs.sportbox.Data.Model.Booking;
import ru.vsu.cs.sportbox.Responses.BookingResponse;
import ru.vsu.cs.sportbox.Service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Tag(name = "Заказы", description = "Методы для работы с заказами")
public class BookingController {
    private BookingService bookingService;

    @DeleteMapping("/cancel")
    @Operation(summary = "Отмена заказа по его id")
    public ResponseEntity<BookingResponse> deleteBookingById(@Parameter(description = "Уникальный идентификатор заказа")
                                                                 @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        BookingResponse bookingDeleteResponse = bookingService.deleteBookingById(id);
        return new ResponseEntity<>(bookingDeleteResponse, httpStatus);
    }

    @PostMapping("/add")
    @Operation(summary = "Создание нового заказа")
    public ResponseEntity<BookingResponse> addNewBooking(@RequestBody BookingCreateDto bookingCreateDto) {
        HttpStatus httpStatus = HttpStatus.OK;
        BookingResponse bookingCreateResponse = bookingService.addNewBooking(bookingCreateDto);
        return new ResponseEntity<>(bookingCreateResponse, httpStatus);
    }

    @GetMapping("/get_by_id")
    @Operation(summary = "Получение информации о заказе по его id")
    public ResponseEntity<BookingResponse> getBookingById(@Parameter(description = "Уникальный идентификатор заказа")
                                                              @RequestParam(value="id") int id) {
//        HttpStatus httpStatus;
        BookingResponse bookingGetResponse = bookingService.getBookingById(id);
//        if (bookingGetResponse.isStatus()) {
//            httpStatus = HttpStatus.OK;
//        } else {
//            httpStatus = HttpStatus.BAD_REQUEST;
//        }
        return new ResponseEntity<>(bookingGetResponse, HttpStatus.OK);
    }

    @PutMapping("/return")
    @Operation(summary = "Принятие возвращенного оборудование, закрытие заказа")
    public ResponseEntity<BookingResponse> changeBooking(@Parameter(description = "Уникальный идентификатор заказа")
                                                             @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        BookingResponse bookingReturnResponse = bookingService.returnBooking(id);
        return new ResponseEntity<>(bookingReturnResponse, httpStatus);
    }

    @PostMapping("/filter")
    @Operation(summary = "Фильтрация списка заказов")
    public ResponseEntity<List<BookingAdminDto>> filterBooking(@RequestBody BookingFilterDto bookingFilterDto) {
        HttpStatus httpStatus = HttpStatus.OK;
        List<BookingAdminDto> bookings = bookingService.filterBooking(bookingFilterDto);
        return new ResponseEntity<>(bookings, httpStatus);
    }
}

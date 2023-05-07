package ru.vsu.cs.sportbox.Service;

import ru.vsu.cs.sportbox.Data.Dto.BookingAdminDto;
import ru.vsu.cs.sportbox.Data.Dto.BookingCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.BookingFilterDto;
import ru.vsu.cs.sportbox.Data.Model.Booking;
import ru.vsu.cs.sportbox.Responses.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse deleteBookingById(int id);
    BookingResponse addNewBooking(BookingCreateDto bookingCreateDto);

    BookingResponse getBookingById(int id);

    BookingResponse returnBooking(int id);

    List<BookingAdminDto> filterBooking(BookingFilterDto bookingFilterDto);
}

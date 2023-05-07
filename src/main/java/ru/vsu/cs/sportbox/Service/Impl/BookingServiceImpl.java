package ru.vsu.cs.sportbox.Service.Impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.sportbox.Data.Dto.BookingAdminDto;
import ru.vsu.cs.sportbox.Data.Dto.BookingCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.BookingFilterDto;
import ru.vsu.cs.sportbox.Data.Mapper.BookingMapper;
import ru.vsu.cs.sportbox.Data.Model.Booking;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;
import ru.vsu.cs.sportbox.Data.Repository.BookingRepository;
import ru.vsu.cs.sportbox.Responses.BookingResponse;
import ru.vsu.cs.sportbox.Service.BookingService;
import ru.vsu.cs.sportbox.Specification.BookingSpecification;
import ru.vsu.cs.sportbox.Specification.EventSpecification;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingResponse deleteBookingById(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking != null) {
            Date curentDate = new Date();

            if (booking.getStartDate().after(curentDate)) {
                bookingRepository.removeBookingById(id);
                return new BookingResponse("Отмена заказа прошла успешно.", true, booking);
            } else {
                return new BookingResponse("Отменить заказ невозможно, время аренды уже наступило.", false, booking);
            }
        } else {
            return new BookingResponse("Заказа с указанным идентификатором не существует.", false, null);
        }
    }

    @Override
    @Transactional
    public BookingResponse addNewBooking(BookingCreateDto bookingCreateDto) {
        Booking booking = bookingMapper.bookingCreateDtoToBooking(bookingCreateDto);
        if (booking == null) {
            return new BookingResponse("Нет свободного инвентаря с указанными параметрами.", false, null);
        }
        if (booking.getPerson().getIsBaned()){
            return new BookingResponse("Ваш аккаунт был заблокирован за нарушение условий аренды. Обратитесь к сотрудникам компании для разблокировки аккаунта.", false, null);
        }
        Booking savedBooking = bookingRepository.save(booking);
        return new BookingResponse("Заказ был успешно оформлен.", true, savedBooking);

    }

    @Override
    @Transactional
    public BookingResponse getBookingById(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            return new BookingResponse("Заказ с указанным идентификатором не существует.", false, null);
        }
        return new BookingResponse("Заказ был успешно найден.", true, booking);
    }

    @Override
    @Transactional
    public BookingResponse returnBooking(int id) {
        Booking booking = bookingRepository.findById(id);
        if (booking != null) {
            Date curentDate = new Date();

            InventoryType inventoryType = booking.getInventory().getInventoryType();
            double priceForDay = inventoryType.getPrice();
            long days = ChronoUnit.DAYS.between(Instant.ofEpochMilli(booking.getEndDate().getTime()), Instant.ofEpochMilli(curentDate.getTime()));
            booking.setEndDate(curentDate);
            booking.setDebt(priceForDay * days);

            bookingRepository.save(booking);
            return new BookingResponse("Изменение заказа прошло успешно.", true, booking);
        } else {
            return new BookingResponse("Заказ с указанным идентификатором не существует.", false, null);
        }
    }

    @Override
    public List<BookingAdminDto> filterBooking(BookingFilterDto bookingFilterDto) {
        return bookingRepository.findAll(BookingSpecification.getBookingByIdAndEmailAndDate(bookingFilterDto))
                .stream()
                .map(booking -> new BookingAdminDto(booking.getId(),
                        booking.getInventory().getName(),
                        booking.getPerson().getEmail(),
                        booking.getPrice(),
                        booking.getDate().toString(),
                        booking.getStartDate().toString(),
                        booking.getEndDate().toString(),
                        booking.getDebt()))
                .collect(Collectors.toList());
    }
}

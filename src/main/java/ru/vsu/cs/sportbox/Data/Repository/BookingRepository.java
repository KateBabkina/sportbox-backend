package ru.vsu.cs.sportbox.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sportbox.Data.Model.Booking;
import ru.vsu.cs.sportbox.Data.Model.Event;
import ru.vsu.cs.sportbox.Data.Model.Inventory;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>, JpaSpecificationExecutor<Booking> {
    void removeBookingById(int id);
    Booking findById(int id);
}

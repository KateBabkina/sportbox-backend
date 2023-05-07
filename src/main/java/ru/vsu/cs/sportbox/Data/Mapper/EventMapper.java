package ru.vsu.cs.sportbox.Data.Mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vsu.cs.sportbox.Data.Dto.EventCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.InventoryCreateDto;
import ru.vsu.cs.sportbox.Data.Model.Event;
import ru.vsu.cs.sportbox.Data.Model.Inventory;
import ru.vsu.cs.sportbox.Data.Model.InventoryType;
import ru.vsu.cs.sportbox.Data.Repository.InventoryTypeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@AllArgsConstructor
public class EventMapper {
    private InventoryTypeRepository inventoryTypeRepository;

    public Event eventCreateDtoToEvent (EventCreateDto eventCreateDto){
        Event event = new Event();

        event.setName(eventCreateDto.getName());
        InventoryType inventoryType = inventoryTypeRepository.findByType(eventCreateDto.getInventoryType());
        if (inventoryType == null) {
            return null;
        }
        event.setInventoryType(inventoryType);
        event.setPrice(eventCreateDto.getPrice());
        event.setDescription(eventCreateDto.getDescription());

        Date sDate;
        Date eDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sDate = format.parse(eventCreateDto.getStartDate());
            eDate = format.parse(eventCreateDto.getEndDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        event.setStartDate(sDate);
        event.setEndDate(eDate);

        return event;
    }
}

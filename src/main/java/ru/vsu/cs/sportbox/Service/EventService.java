package ru.vsu.cs.sportbox.Service;

import ru.vsu.cs.sportbox.Data.Dto.EventChangeDto;
import ru.vsu.cs.sportbox.Data.Dto.EventCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.EventFilterDto;
import ru.vsu.cs.sportbox.Data.Model.Event;
import ru.vsu.cs.sportbox.Responses.*;

import java.util.List;
import java.util.Set;

public interface EventService {
    List<Event> filterEvent(EventFilterDto eventFilterDto);

    Set<Event> getRecommendations(int bookingId);

    EventResponse addNewEvent(EventCreateDto eventCreateDto);

    EventResponse getEventById(int id);

    EventResponse changeEvent(int id, EventChangeDto eventChangeDto);

    EventResponse deleteEventById(int id);
}

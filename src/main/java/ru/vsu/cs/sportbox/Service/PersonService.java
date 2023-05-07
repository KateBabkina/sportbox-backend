package ru.vsu.cs.sportbox.Service;

import ru.vsu.cs.sportbox.Data.Dto.PersonCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.PersonFilterDto;
import ru.vsu.cs.sportbox.Data.Dto.PersonLoginDto;
import ru.vsu.cs.sportbox.Data.Model.Person;
import ru.vsu.cs.sportbox.Responses.PersonResponse;

import java.util.List;

public interface PersonService {
    PersonResponse addNewPerson(PersonCreateDto personCreateDto);
    PersonResponse loginPerson(PersonLoginDto personLoginDto);
    PersonResponse getPersonById(int id);

    List<Person> filterPerson(PersonFilterDto personFilterDto);

    PersonResponse deletePersonById(int id);

    PersonResponse banPerson(int id);

    PersonResponse unbanPerson(int id);
}




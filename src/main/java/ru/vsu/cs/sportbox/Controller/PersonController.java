package ru.vsu.cs.sportbox.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sportbox.Data.Dto.*;
import ru.vsu.cs.sportbox.Data.Model.Inventory;
import ru.vsu.cs.sportbox.Data.Model.Person;
import ru.vsu.cs.sportbox.Responses.*;
import ru.vsu.cs.sportbox.Service.PersonService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Tag(name = "Пользователи", description = "Методы для работы с пользователями")
public class PersonController {

    private PersonService personService;

    @PostMapping("/add")
    @Operation(summary = "Регистрация нового пользователя")
    public ResponseEntity<PersonResponse> addNewPerson(@RequestBody PersonCreateDto personCreateDto) {
        HttpStatus httpStatus = HttpStatus.OK;
        PersonResponse registrationResponse = personService.addNewPerson(personCreateDto);
        return new ResponseEntity<>(registrationResponse, httpStatus);
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя")
    public ResponseEntity<PersonResponse> loginPerson(@RequestBody PersonLoginDto personLoginDto) {
        HttpStatus httpStatus = HttpStatus.OK;
        PersonResponse loginResponse = personService.loginPerson(personLoginDto);
        return new ResponseEntity<>(loginResponse, httpStatus);
    }

    @GetMapping("/profile")
    @Operation(summary = "Получение информации для профиля пользователя")
    public ResponseEntity<PersonResponse> getProfile(@Parameter(description = "Уникальный идентификатор пользователя")
                                                         @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        PersonResponse profileResponse = personService.getPersonById(id);
        return new ResponseEntity<>(profileResponse, httpStatus);
    }

    @PostMapping("/filter")
    @Operation(summary = "Фильтрация списка пользователей")
    public ResponseEntity<List<Person>> filterPerson(@RequestBody PersonFilterDto personFilterDto) {
        HttpStatus httpStatus = HttpStatus.OK;
        List<Person> persons = personService.filterPerson(personFilterDto);
        return new ResponseEntity<>(persons, httpStatus);
    }

    @GetMapping("/get_by_id")
    @Operation(summary = "Получение информации о пользователе по его id")
    public ResponseEntity<PersonResponse> getPersonById(@Parameter(description = "Уникальный идентификатор пользователя")
                                                            @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        PersonResponse personResponse = personService.getPersonById(id);
        return new ResponseEntity<>(personResponse, httpStatus);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Удаление пользователя по его id")
    public ResponseEntity<PersonResponse> deletePersonById(@Parameter(description = "Уникальный идентификатор пользователя")
                                                               @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        PersonResponse personResponse = personService.deletePersonById(id);
        return new ResponseEntity<>(personResponse, httpStatus);
    }

    @PutMapping("/ban")
    @Operation(summary = "Внесение пользователя в черный список по его id")
    public ResponseEntity<PersonResponse> banPerson(@Parameter(description = "Уникальный идентификатор пользователя")
                                                        @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        PersonResponse personResponse = personService.banPerson(id);
        return new ResponseEntity<>(personResponse, httpStatus);
    }

    @PutMapping("/unban")
    @Operation(summary = "Вынесение пользователя из черного списка по его id")
    public ResponseEntity<PersonResponse> unbanPerson(@Parameter(description = "Уникальный идентификатор пользователя")
                                                          @RequestParam(value="id") int id) {
        HttpStatus httpStatus = HttpStatus.OK;
        PersonResponse personResponse = personService.unbanPerson(id);
        return new ResponseEntity<>(personResponse, httpStatus);
    }
}

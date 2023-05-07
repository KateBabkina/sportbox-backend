package ru.vsu.cs.sportbox.Data.Mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vsu.cs.sportbox.Data.Dto.PersonCreateDto;
import ru.vsu.cs.sportbox.Data.Dto.PersonLoginDto;
import ru.vsu.cs.sportbox.Data.Model.Booking;
import ru.vsu.cs.sportbox.Data.Model.Person;
import ru.vsu.cs.sportbox.Data.Repository.PersonRepository;
import ru.vsu.cs.sportbox.Data.Repository.RoleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PersonMapper {
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public Person personCreateDtoToPerson (PersonCreateDto personCreateDto){
        Person person = new Person();

        person.setName(personCreateDto.getName());
        person.setPassword(passwordEncoder.encode(personCreateDto.getPassword()));
        person.setEmail(personCreateDto.getEmail());
        person.setRole(roleRepository.findByName("Customer"));

        return person;
    }
}

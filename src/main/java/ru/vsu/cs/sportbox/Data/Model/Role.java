package ru.vsu.cs.sportbox.Data.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy="role", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Person> persons;

}

package com.example.demo.phone;

import com.example.demo.person.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="number")
    private String number;

    @ManyToOne(targetEntity = Person.class, cascade = CascadeType.ALL)
    private Person person;

    @Builder
    public Phone(String number, Person person) {
        this.number = number;
        this.person = person;
    }
}

package com.example.demo.person;

import com.example.demo.phone.Phone;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "person")
    private List<Phone> phone;

    @Builder
    public Person(String name) {
        this.name = name;
    }
}

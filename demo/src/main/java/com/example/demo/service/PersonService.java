package com.example.demo.service;

import com.example.demo.person.Person;
import com.example.demo.phone.Phone;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PhoneRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    PersonRepository personRepo;
    PhoneRepository phoneRepo;

    public void savePerson() {
        Person person = Person.builder()
                .name("Johnny")
                .build();
        Phone phone = Phone.builder()
                .number("010-9030-6115")
                .person(person)
                .build();
        Phone phone2 = Phone.builder()
                .number("010-9030-6114")
                .person(person)
                .build();
        Phone phone3 = Phone.builder()
                .number("010-9030-6113")
                .person(person)
                .build();

        List<Phone> phones = new ArrayList<Phone>();
        phones.add(phone);
        phones.add(phone2);
        phones.add(phone3);

        phoneRepo.saveAll(phones);
    }

    public HashMap<String, Object> savePersonFromJSON(HashMap<String, Object> personMap) {
        HashMap<String, Object> returnMap = new HashMap<>();

        try
        {
            ObjectMapper mapper = new ObjectMapper();

            // JSON 단일 객체 값을 POJO 객체에 매핑합니다.
            Person person = mapper.convertValue(personMap.get("person"), Person.class);

            // JSON 배열 객체 값을 POJO 객체에 매핑합니다.
            Object objTypePhone = personMap.get("phone");
            String jsonStr = mapper.writeValueAsString(objTypePhone);
            ArrayList<Phone> phones = mapper.readValue(jsonStr, new TypeReference<ArrayList<Phone>>() {});

            // Phone(Many) 객체에 Person(One) 객체를 set 해줍니다.
            for (Phone phone : phones)
            {
                phone.setPerson(person);
            }

            // 값 출력 확인하기
//            System.out.println("person.getName : " + person.getName());
//            System.out.println("person.getPhone : " + person.getPhone());
//            for (int i = 0; i < phones.size(); i += 1)
//            {
//                System.out.println(phones.get(i).getNumber());
//                System.out.println(phones.get(i).getPerson().getName());
//            }

            // Phone(Many) 객체 정보를 Table에 Insert 합니다.
            phoneRepo.saveAll(phones);

            returnMap.put("msg", "처리 성공!");
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            // When : ObjectMapper.convertValue()
        }
        catch (JsonProcessingException e1)
        {
            e1.printStackTrace();
            // When : ObjectMapper.writeValuesAsString()
        }
        catch (IOException e2)
        {
            e2.printStackTrace();
            // When : ObjectMapper.readValue()
        }

        return returnMap;
    }
}

package com.jocamav.springbootrest.http;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PersonServiceDefault implements PersonService {

    private final AtomicLong idSequence = new AtomicLong();

    private HashMap<Long, Person> personStorage;

    public PersonServiceDefault() {
        this.personStorage = new HashMap<>();
    }

    @Override
    public List<Person> getList() {
        return personStorage.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Person get(Long id) {
        return personStorage.get(id);
    }

    @Override
    public Person save(Person person) {
        Long id = idSequence.incrementAndGet();
        Person personToSave = new Person(id, person.getName(), person.getFamilyName());
        personStorage.put(id, personToSave);
        return personToSave;
    }

    @Override
    public Person update(Long id, Person person) {
        Person personToUpdate = get(id);
        personToUpdate.setName(person.getName());
        personToUpdate.setFamilyName(person.getFamilyName());
        return personStorage.put(id, personToUpdate);
    }

    @Override
    public void delete(Long id) {
        personStorage.remove(id);
    }
}

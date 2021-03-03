package com.jocamav.springbootrest.http;

import java.util.List;

public interface PersonService {
    List<Person> getList();
    Person get(Long id);
    Person save(Person person);
    Person update(Long id, Person person);
    void delete(Long id);
}

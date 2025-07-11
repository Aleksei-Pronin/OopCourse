package ru.academits.pronin.lambda_main;

import ru.academits.pronin.lambda.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Петр", 20));
        persons.add(new Person("Василий", 22));
        persons.add(new Person("Дмитрий", 16));
        persons.add(new Person("Петр", 38));
        persons.add(new Person("Дмитрий", 14));
        persons.add(new Person("Иван", 54));
        persons.add(new Person("Мария", 17));
        persons.add(new Person("Дарья", 33));
        persons.add(new Person("Екатерина", 20));
        persons.add(new Person("Александр", 16));

        List<String> uniqueNames = persons.stream().map(Person::getName).distinct().toList();
        System.out.println("Список уникальных имен - " + uniqueNames);

        System.out.println(persons.stream().map(Person::getName).distinct().collect(Collectors.joining(", ", "Имена: ", ".")));

        System.out.println("Средний возраст людей, младше 18 - " + persons.stream().filter(person -> person.getAge() < 18).mapToInt(Person::getAge).average().orElse(0.0));

        Map<String, Double> personsByAverageAge = persons.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println(personsByAverageAge);

        persons.stream().filter(person -> person.getAge() >= 20 && person.getAge() <= 45).sorted((p1, p2) -> p2.getAge() - p1.getAge()).map(Person::getName).forEach(System.out::println);
    }
}
package ru.academits.pronin.lambda;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Петр", 20),
                new Person("Василий", 22),
                new Person("Дмитрий", 16),
                new Person("Петр", 38),
                new Person("Дмитрий", 14),
                new Person("Иван", 54),
                new Person("Мария", 17),
                new Person("Дарья", 33),
                new Person("Екатерина", 20),
                new Person("Александр", 16)
        );

        String uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNames);

        OptionalDouble averageAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        if (averageAge.isPresent()) {
            System.out.println("Средний возраст людей, младше 18 - " + averageAge.getAsDouble());
        } else {
            System.out.println("Нет людей младше 18");
        }

        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(averageAgeByName);

        persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45).sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .forEach(System.out::println);
    }
}
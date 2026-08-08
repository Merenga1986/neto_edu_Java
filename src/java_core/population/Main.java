package java_core.population;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        // Один экземпляр Random вместо new Random() на каждой итерации —
        // так генерация 10 млн объектов проходит заметно быстрее
        Random random = new Random();

        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)])
            );
        }

        // 1. Количество несовершеннолетних (возраст < 18)
        long minorsCount = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних: " + minorsCount);

        // 2. Фамилии призывников (мужчины от 18 до 27 лет включительно)
        List<String> conscripts = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Призывников найдено: " + conscripts.size());

        // 3. Список трудоспособных людей с высшим образованием, отсортированный по фамилии
        // (женщины 18-60 лет, мужчины 18-65 лет)
        List<Person> workforceHigherEducation = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.WOMAN && p.getAge() >= 18 && p.getAge() <= 60)
                           || (p.getSex() == Sex.MAN && p.getAge() >= 18 && p.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Трудоспособных с высшим образованием: " + workforceHigherEducation.size());
    }
}

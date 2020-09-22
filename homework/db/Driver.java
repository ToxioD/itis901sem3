package ru.itis;

import java.util.LinkedList;
import java.util.List;

/**
 * 14.09.2020
 * 02. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.1
 * @editor Lysenkov Dmitriy (KFU ITIS student)
 */
public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Car> cars;

    //added 22.09.2020
    public Driver(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        cars = new LinkedList<>();
    }

    //added 22.09.2020
    public Driver(Long id, String firstName, String lastName, Integer age, List<Car> cars) {
        this(id, firstName, lastName, age);
        this.cars = cars;
    }

    //added 22.09.2020
    public void addCar(Car car) {
        cars.add(car);
    }
}

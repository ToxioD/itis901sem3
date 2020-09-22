package ru.itis;

/**
 * 14.09.2020
 * 02. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.1
 * @editor Lysenkov Dmitriy (KFU ITIS student)
 */
public class Car {
    private Long id;
    private String model;
    private String color;

    private Driver driver;

    //added 22.09.2020
    public Car(Long id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }

    //added 22.09.2020
    public Car(Long id, String model, String color, Driver driver) {
        this(id, model, color);
        this.driver = driver;
    }

    //added 22.09.2020
    public void assignDriver(Driver driver) {
        this.driver = driver;
    }
}

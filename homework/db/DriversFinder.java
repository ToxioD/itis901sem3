package ru.itis;

import java.sql.*;
import java.util.*;

/**
 * 22.09.2020
 * 02. DB
 *
 * @author Lysenkov Dmitriy (KFU ITIS student)
 * @version v1.0
 */
// DATA ACCESS OBJECT (SEARCH BY ID IMPLEMENTED)
public class DriversFinder extends DriversDao {

    public DriversFinder(Connection connection) {
        super(connection);
    }

    public Optional<Driver> findById(Long id) {
        try {

            ResultSet driverTable = connection.createStatement()
                    .executeQuery("SELECT FROM Driver WHERE id=" + id + ";");
            ResultSet carsTable = connection.createStatement()
                    .executeQuery("SELECT FROM Car WHERE driver_id=" + id + ";");

            if (!driverTable.next()) return Optional.empty();

            Driver driver = new Driver(driverTable.getLong("id"),
                    driverTable.getString("first_name"),
                    driverTable.getString("last_name"),
                    driverTable.getInt("age"));

            List<Car> cars = new LinkedList<>();
            while (carsTable.next()) {
                cars.add(new Car(carsTable.getLong("id"),
                        carsTable.getString("model"),
                        carsTable.getString("color")));
            }

            for (Car car : cars) {
                driver.addCar(car);
                car.assignDriver(driver);
            }

            return Optional.of(driver);

        } catch (SQLException e) {}
    }
}

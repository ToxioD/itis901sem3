package ru.itis;

import java.sql.Connection;
import java.util.Optional;

/**
 * 14.09.2020
 * 02. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.1
 * @editor Lysenkov Dmitriy (KFU ITIS student)
 */
// DATA ACCESS OBJECT
public class DriversDao {

    protected Connection connection; //private -> protected 22.09.2020

    public DriversDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<Driver> findById(Long id) {
        return null;
    }
}

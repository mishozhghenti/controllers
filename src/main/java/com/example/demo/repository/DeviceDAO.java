package com.example.demo.repository;

import com.example.demo.model.Repository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DeviceDAO extends Repository {
    @Autowired
    DataSource pool;
    @Value("${db.mysql-database-name}")
    String dbName;


    public boolean createDevice(long uid, String vendor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        boolean committed = false;
        try {
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);

            String query = "insert into peripheral_devices " +
                    "(uid, vendor, date_created, status) " +
                    "values (?,?,?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, uid);
            preparedStatement.setString(2, vendor);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setBoolean(4, false);
            preparedStatement.executeUpdate();
            committed = true;
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            closeDAO(connection, preparedStatement, statement);
        }
        return committed;
    }

    public boolean changeDeviceStatus(long deviceId, boolean status){
        return false;
    }

    public boolean deleteDevice(long deviceId) {
        return false;
    }

    public boolean addDeviceToGateway(long deviceId, long gatewayId) {
        return false;
    }

    public boolean removeDeviceFromGateway(long deviceId, long gatewayId) {
        return false;
    }
}

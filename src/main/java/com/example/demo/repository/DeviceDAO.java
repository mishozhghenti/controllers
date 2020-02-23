package com.example.demo.repository;

import com.example.demo.model.Repository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DeviceDAO extends Repository {
    final DataSource pool;
    @Value("${db.mysql-database-name}")
    String dbName;

    public DeviceDAO(DataSource pool) {
        this.pool = pool;
    }

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
            e.printStackTrace();
        } finally {
            closeDAO(connection, preparedStatement, statement);
        }
        return committed;
    }

    public boolean changeDeviceStatus(long deviceId, boolean status) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);

            String query = "update peripheral_devices set status=? where device_id=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setLong(2, deviceId);

            int resultCode = preparedStatement.executeUpdate();
            if (resultCode == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDAO(connection, preparedStatement, statement);
        }
        return result;
    }

    public boolean deleteDevice(long deviceId) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);

            String query = "delete from peripheral_devices where device_id=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, deviceId);

            int resultCode = preparedStatement.executeUpdate();
            if (resultCode == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDAO(connection, preparedStatement, statement);
        }
        return result;
    }

    public boolean addDeviceToGateway(long deviceId, long gatewayId) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);

            String query = "insert into associated_devices (gateway_id, device_id) values (?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, gatewayId);
            preparedStatement.setLong(2, deviceId);

            int resultCode = preparedStatement.executeUpdate();
            if (resultCode == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDAO(connection, preparedStatement, statement);
        }
        return result;
    }

    public boolean removeDeviceFromGateway(long deviceId, long gatewayId) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);

            String query = "delete from associated_devices where gateway_id=? and device_id=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, gatewayId);
            preparedStatement.setLong(2, deviceId);

            int resultCode = preparedStatement.executeUpdate();
            if (resultCode == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDAO(connection, preparedStatement, statement);
        }
        return result;
    }
}

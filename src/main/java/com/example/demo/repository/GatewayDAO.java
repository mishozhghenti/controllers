package com.example.demo.repository;

import com.example.demo.model.Repository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class GatewayDAO extends Repository {

    @Autowired
    DataSource pool;
    @Value("${db.mysql-database-name}")
    String dbName;

    public boolean addNewGateway(String serialNumber, String name, String ip) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        boolean committed = false;
        try {
            // Get the connection from the pool.
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);

            String query = "insert into master_devices " +
                    "(serial_number, name, ip_v4) " +
                    "values (?,?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, serialNumber);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, ip);
            preparedStatement.executeUpdate();
            committed = true;
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            closeDAO(connection, preparedStatement, statement);
        }
        return committed;
    }

    public Object getAllGatewaysDetails(){
        return null;
    }

    public Object getSingleGatewayDetails(long gatewayId){
        return null;
    }
}

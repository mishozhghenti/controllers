package com.example.demo.repository;

import com.example.demo.model.Gateway;
import com.example.demo.model.Repository;
import com.example.demo.util.Utils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

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

    public List<Gateway> getAllGatewaysDetails() {
        List<Gateway> gateways = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Gateway> gatewayMap = new HashMap<>();
        try {
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);
            String query = "select master_devices.master_id,\n" +
                    "       master_devices.serial_number,\n" +
                    "       master_devices.name,\n" +
                    "       master_devices.ip_v4,\n" +
                    "       pd.device_id,\n" +
                    "       pd.uid,\n" +
                    "       pd.vendor,\n" +
                    "       pd.date_created,\n" +
                    "       pd.status\n" +
                    "from master_devices\n" +
                    "         left join associated_devices ad on master_devices.master_id = ad.gateway_id\n" +
                    "         left join peripheral_devices pd on ad.device_id = pd.device_id";

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Gateway currentGateway = Utils.fetchGateway(resultSet);

                if (gatewayMap.containsKey(currentGateway.getGatewayId())) {
                    if (currentGateway.getDevices() != null && currentGateway.getDevices().size() != 0) {
                        gatewayMap.get(currentGateway.getGatewayId()).getDevices().add(currentGateway.getDevices().get(0));
                    }
                } else {
                    gatewayMap.put(currentGateway.getGatewayId(), currentGateway);
                }
            }
            gateways.addAll(gatewayMap.values());
        } catch (SQLException ignored) {
        } finally {
            closeDAO(connection, preparedStatement, statement, resultSet);
        }
        return gateways;
    }

    public Gateway getSingleGatewayDetails(long gatewayId) {
        Gateway gateway = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();

            statement = connection.createStatement();
            statement.executeQuery("USE " + dbName);
            String query = "select master_devices.master_id,\n" +
                    "       master_devices.serial_number,\n" +
                    "       master_devices.name,\n" +
                    "       master_devices.ip_v4,\n" +
                    "       pd.device_id,\n" +
                    "       pd.uid,\n" +
                    "       pd.vendor,\n" +
                    "       pd.date_created,\n" +
                    "       pd.status\n" +
                    "from master_devices\n" +
                    "         left join associated_devices ad on master_devices.master_id = ad.gateway_id\n" +
                    "         left join peripheral_devices pd on ad.device_id = pd.device_id\n" +
                    "where master_devices.master_id = ?";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, gatewayId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                gateway = Utils.fetchGateway(resultSet);
            }
        } catch (SQLException ignored) {
            System.out.println(Arrays.toString(ignored.getStackTrace()));
        } finally {
            closeDAO(connection, preparedStatement, statement, resultSet);
        }
        return gateway;
    }
}

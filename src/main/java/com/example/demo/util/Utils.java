package com.example.demo.util;

import com.example.demo.constant.DBConstant;
import com.example.demo.model.Device;
import com.example.demo.model.Gateway;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {

    private static final String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";


    public static boolean isIPv4Match(String ipV4) {
        if (ipV4 == null) {
            return false;
        }
        return ipV4.matches(PATTERN);
    }


    public static Gateway fetchGateway(ResultSet resultSet) throws SQLException {
        Gateway gateway = new Gateway();

        gateway.setGatewayId(resultSet.getInt(DBConstant.MASTER_ID));
        gateway.setSerialName(resultSet.getString(DBConstant.SERIAL_NUMBER));
        gateway.setName(resultSet.getString(DBConstant.NAME));
        gateway.setIpV4(resultSet.getString(DBConstant.IP));
        String vendor = resultSet.getString(DBConstant.VENDOR);

        if (vendor == null) {
            return gateway;
        }
        Device device = new Device();

        device.setVendor(vendor);
        device.setDeviceId(resultSet.getInt(DBConstant.DEVICE_ID));
        device.setuID(resultSet.getLong(DBConstant.UID));
        device.setCreationDate(resultSet.getTimestamp(DBConstant.DATE_CREATION));
        device.setStatus(resultSet.getBoolean(DBConstant.STATUS));

        gateway.getDevices().add(device);
        return gateway;
    }

}

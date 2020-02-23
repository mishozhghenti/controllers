package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Gateway {
    private Integer gatewayId;
    private String serialName;
    private String name;
    private String ipV4;
    private List<Device> devices;

    public Gateway(Integer gatewayId, String serialName, String name, String ipV4) {
        init(gatewayId, serialName, name, ipV4, new ArrayList<>());
    }

    public Gateway(Integer gatewayId, String serialName, String name, String ipV4, List<Device> devices) {
        init(gatewayId, serialName, name, ipV4, devices);
    }

    public Gateway() {
        devices = new ArrayList<>();
    }

    private void init(Integer id, String serialName, String name, String ipV4, List<Device> devices) {
        this.gatewayId = id;
        this.serialName = serialName;
        this.name = name;
        this.ipV4 = ipV4;
        this.devices = devices;
    }

    public Integer getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Integer gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpV4() {
        return ipV4;
    }

    public void setIpV4(String ipV4) {
        this.ipV4 = ipV4;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "id=" + gatewayId +
                ", serialName='" + serialName + '\'' +
                ", name='" + name + '\'' +
                ", ipV4='" + ipV4 + '\'' +
                ", devices=" + devices +
                '}';
    }
}

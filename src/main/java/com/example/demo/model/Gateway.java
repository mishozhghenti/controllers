package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Gateway {
    private Integer id;
    private String serialName;
    private String name;
    private String ipV4;
    private List<Device> devices;

    public Gateway(Integer id, String serialName, String name, String ipV4) {
        init(id, serialName, name, ipV4, new ArrayList<>());
    }

    public Gateway(Integer id, String serialName, String name, String ipV4, List<Device> devices) {
        init(id, serialName, name, ipV4, devices);
    }

    public Gateway() {
    }

    private void init(Integer id, String serialName, String name, String ipV4, List<Device> devices) {
        this.id = id;
        this.serialName = serialName;
        this.name = name;
        this.ipV4 = ipV4;
        this.devices = devices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Gateway{" +
                "id=" + id +
                ", serialName='" + serialName + '\'' +
                ", name='" + name + '\'' +
                ", ipV4='" + ipV4 + '\'' +
                ", devices=" + devices +
                '}';
    }
}

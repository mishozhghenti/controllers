package com.example.demo.controller;

import com.example.demo.repository.DeviceDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/device")
public class DeviceController {

    final DeviceDAO deviceDAO;

    public DeviceController(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    @PostMapping("/add")
    public Object addDevice(@RequestParam(name = "uid") long uid,
                            @RequestParam(name = "vendor") String vendor) {
        boolean res = deviceDAO.createDevice(uid, vendor);
        return (res) ? "Device Added Successfully " : "Failed Adding New Device";
    }

    @PostMapping("/changeStatus")
    public Object changeDeviceStatus(@RequestParam(name = "deviceId") long deviceId, @RequestParam(name = "status") boolean status) {
        boolean changed = deviceDAO.changeDeviceStatus(deviceId, status);
        return (changed) ? "Device Status Changed Successfully " : "Failed Changing Device Status";
    }

    @PostMapping("/delete")
    public Object deleteDevice(@RequestParam(name = "deviceId") long deviceId) {
        boolean deleted = deviceDAO.deleteDevice(deviceId);
        return (deleted) ? "Device Deleted  Successfully " : "Failed Deleting Device";
    }

    @PostMapping("/addToGateway")
    public Object addToGateway(@RequestParam(name = "deviceId") long deviceId, @RequestParam(name = "gatewayId") long gatewayId) {
        boolean added = deviceDAO.addDeviceToGateway(deviceId, gatewayId);
        return (added) ? "Device Added To Gateway Successfully " : "Failed Adding Device To Gateway";
    }

    @PostMapping("/removeDeviceFromGateway")
    public Object removeDeviceFromGateway(@RequestParam(name = "deviceId") long deviceId, @RequestParam(name = "gatewayId") long gatewayId) {
        boolean removed = deviceDAO.removeDeviceFromGateway(deviceId, gatewayId);
        return (removed) ? "Device Removed Successfully From Gateway" : "Failed Removing Device From Gateway";
    }

}

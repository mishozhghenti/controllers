package com.example.demo.controller;

import com.example.demo.repository.GatewayDAO;
import com.example.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/gateway")
public class GatewayController {

    @Autowired
    GatewayDAO gatewayDAO;

    @GetMapping("/add")
    public Object addGateway(@RequestParam(name = "serialNumber") String serialNumber,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "ip") String ipV4) {
        if (!Utils.isIPv4Match(ipV4)) {
            return "Invalid ipV4";
        }

        boolean result = gatewayDAO.addNewGateway(serialNumber, name, ipV4);
        return (result) ? "Gateway Added Successfully " : "Failed Adding New Gateway";
    }

    @GetMapping("/getGatewayInfo")
    public Object getGatewayInfo(@RequestParam(name = "gatewayId") long gatewayId) {
        return null;
    }

    @GetMapping("/getAllGatewaysInfo")
    public Object getAllGatewaysInfo() {
        return null;
    }


}

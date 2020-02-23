package com.example.demo.controller;

import com.example.demo.model.Gateway;
import com.example.demo.repository.GatewayDAO;
import com.example.demo.util.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gateway")
public class GatewayController {

    final GatewayDAO gatewayDAO;

    public GatewayController(GatewayDAO gatewayDAO) {
        this.gatewayDAO = gatewayDAO;
    }

    @PostMapping("/add")
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
    public Gateway getGatewayInfo(@RequestParam(name = "gatewayId") long gatewayId) {
        return gatewayDAO.getSingleGatewayDetails(gatewayId);
    }

    @GetMapping("/getAllGatewaysInfo")
    public List<Gateway> getAllGatewaysInfo() {
        return gatewayDAO.getAllGatewaysDetails();
    }
}

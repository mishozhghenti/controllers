INSERT INTO gateways.master_devices (serial_number, name, ip_v4)
VALUES ('1234', 'MAGTI', '12.12.12.12');
INSERT INTO gateways.master_devices (serial_number, name, ip_v4)
VALUES ('45657', 'SUPER', '13.13.23.23');



INSERT INTO gateways.peripheral_devices (uid, vendor, date_created, status)
VALUES (1234, 'iTech LTD', '2020-02-23 20:31:04', 0);
INSERT INTO gateways.peripheral_devices (uid, vendor, date_created, status)
VALUES (7888, 'Tech Hub LTD', '2020-02-23 20:32:00', 0);

INSERT INTO gateways.associated_devices (gateway_id, device_id)
VALUES (1, 1);
INSERT INTO gateways.associated_devices (gateway_id, device_id)
VALUES (1, 2);
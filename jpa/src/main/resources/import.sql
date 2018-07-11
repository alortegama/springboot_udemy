INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Alvaro','Ortega','alortegama@outlook.com','2016-01-01','');
INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Eduardo','Melero','taniamelero@gmail.com','2016-01-01','');
INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Jorge','Pepito','taniamelero@gmail.com','2016-01-01','');
INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Sonia','Grillo','taniamelero@gmail.com','2016-01-01','');
)

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Factura equipos de oficina',null,1,NOW());
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1,1,1);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (2,1,4);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1,1,5);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1,1,7);

INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Factura Bicicleta','Alguna nota importante!',1,NOW());
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (3,2,6);

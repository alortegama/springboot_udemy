INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Alvaro','Ortega','alortegama@outlook.com','2016-01-01','');
INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Eduardo','Melero','a@gmail.com','2016-01-01','');
INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Jorge','Pepito','b@gmail.com','2016-01-01','');
INSERT INTO clientes (nombre,apellido,email,create_at,foto)  VALUES ('Sonia','Grillo','c@gmail.com','2016-01-01','');

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, CURDATE());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, CURDATE());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, CURDATE());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, CURDATE());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, CURDATE());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, CURDATE());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, CURDATE());

INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Factura equipos de oficina',null,1,CURDATE());
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1,1,1);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (2,1,4);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1,1,5);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1,1,7);

INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Factura Bicicleta','Alguna nota importante!',1,CURDATE());
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (3,2,6);



INSERT INTO users(username,password,enabled) VALUES ('alvaro','$2a$10$0Ijvc68nex0GbpWa.T8K1uYEPqo7w9fzJA6rfH7n0/SfEgrA66l/O',1);
INSERT INTO users(username,password,enabled) VALUES ('admin','$2a$10$CwIMSVdWR/IzOYgFMROvL.z6i8OGLfNRj8M0BHHU4B6ncyLayFNsO',1);


INSERT INTO authorities (user_id,authority) VALUES (1,'ROLE_USER');
INSERT INTO authorities (user_id,authority) VALUES (2,'ROLE_USER');
INSERT INTO authorities (user_id,authority) VALUES (2,'ROLE_ADMIN');

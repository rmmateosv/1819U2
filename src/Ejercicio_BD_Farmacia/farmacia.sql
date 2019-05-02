drop database if exists farmacia;
create database farmacia;
use farmacia;

create table proveedor(
	codigo int auto_increment primary key,
    nombre varchar(50)
)engine Innodb;

create table medicamento(
	codigo int auto_increment primary key,
    nombre varchar(50),
    stockMin int,
    stockMax int,
    stockReal int,
    proveedor int,
    foreign key (proveedor) references proveedor(codigo)
)engine Innodb;

create table venta(
	codigo int auto_increment primary key,
    fecha date,
    unidades int,
    medicamento int,
    foreign key (medicamento) references medicamento(codigo)
)engine Innodb;

create table pedido(
	codigo int auto_increment primary key,
    fecha date,
    unidades int,
    medicamento int,
    foreign key (medicamento) references medicamento(codigo),
    proveedor int,
    foreign key (proveedor) references proveedor(codigo)
)engine Innodb;

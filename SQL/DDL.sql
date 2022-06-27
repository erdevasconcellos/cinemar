create table actor (
	id int not null auto_increment,
	nombre varchar(50) collate 'utf8_general_ci',
	primary key (id)
);

create table clasificacion (
    id int not null auto_increment,
    cod varchar(6) not null,
    descripcion varchar(150) not null,
    primary key (id)
);

create table descuento (
	dia int not null,
	descuento float,
	primary key (dia)
);

create table cliente (
	id int not null auto_increment,
	dni varchar(9) not null,
	apellido varchar(50) not null collate 'utf8_spanish_ci',
	nombre varchar(50) not null collate 'utf8_spanish_ci',
	fch_nac datetime not null,
	tarj_desc char(1),
	email varchar(50) not null,
	pwd varchar(50) not null,
	primary key (id)
)

create table pelicula (
	id int not null auto_increment,
	titulo varchar(200) not null collate 'utf8_general_ci',
	id_clasificacion int,
	duracion int,
	primary key (id),
	foreign key `fk_clasif` (id_clasificacion) references clasificacion(id)
)

create table reparto (
	id_pelicula int not null,
	id_actor int not null,
	primary key (id_pelicula, id_actor),
	foreign key `fk_pelicula` (id_pelicula) references pelicula(id),
	foreign key `fk_actor` (id_actor) references actor(id)
)

create table sala (
	id int not null auto_increment,
	nombre varchar(25) collate 'utf8_general_ci',
	capacidad int not null,
	primary key (id)
)

create table medio_pago (
	id int not null auto_increment,
	nombre varchar(25),
	primary key (id)
)

create table butaca (
	id int not null auto_increment,
	id_sala int,
	fila varchar(2),
	num int,
	primary key (id),
	foreign key `fk_sala` (id_sala) references sala(id)
)

create table funcion (
	id int not null auto_increment,
	id_sala int not null,
	id_pelicula int not null,
	is3d enum('y', 'n'),
	fecha datetime not null,
	precio float not null,
	id_descuento int not null,
	primary key (id),
	foreign key (id_sala) references sala(id),
	foreign key (id_pelicula) references pelicula(id),
	foreign key (id_descuento) references descuento(dia)
)

create table reserva (
	id int not null auto_increment,
	id_funcion int,
	id_cliente int,
	id_butaca int,
	fecha datetime,
	pago float,
	id_medio_pago int,
	primary key (id),
	unique (id_funcion, id_butaca),
	foreign key `fk_res_funcion` (id_funcion) references funcion(id),
	foreign key `fk_res_cliente` (id_cliente) references cliente(id),
	foreign key `fk_res_butaca` (id_butaca) references butaca(id),
	foreign key `fk_res_mediopago` (id_medio_pago) references medio_pago(id)
)
create table imobiliaria (
	id int8 generated by default as identity,
	imobiliaria_email varchar(60) not null,
	is_admin boolean not null,
	imobiliaria_nome varchar(60) not null,
	imobiliaria_password varchar(20) not null,
	url_sistema varchar(150) not null,
	primary key (id)
);

create table negocio (
	dtype varchar(31) not null, 
	id int8 generated by default as identity,
	fonte varchar(15),
	status varchar(15),
	cod_imovel varchar(10),
	date_in timestamp,
	date_out timestamp,
	id_bitrix int8,
	tipo varchar(15),
	imobiliaria_id int8 not null,
	pipeline_id int8 not null,
	responsavel_id int8,
	primary key (id)
);

create table pipeline (
	id int8 generated by default as identity,
	id_sistema int8 not null,
	tipo varchar(60),
	imobiliaria_id int8,
	primary key (id)
);

create table responsavel_negocio (
	id int8 generated by default as identity, 
	email varchar(60), 
	id_sistema int8 not null,
	name varchar(60), 
	primary key (id)
);

alter table if exists negocio add constraint FK_negocio_imobiliaria foreign key (imobiliaria_id) references imobiliaria;
alter table if exists negocio add constraint FK_negocio_pipeline foreign key (pipeline_id) references pipeline;
alter table if exists negocio add constraint FK_negocio_responsavel foreign key (responsavel_id) references responsavel_negocio;
alter table if exists pipeline add constraint FK_pipeline_imobiliaria foreign key (imobiliaria_id) references imobiliaria;

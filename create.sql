
create table agendamento_procedimentos (
	agendamento_id int8 not null, 
	procedimentos varchar(255)
);

create table tb_agendamento (
	id  bigserial not null, 
	data_hora timestamp, 
	status varchar(255) not null, 
	tudor_id int8 not null, 
	primary key (id)
);

create table tb_pet (
	id  bigserial not null, 
	especie varchar(100), 
	idade int4 check (idade<=200 AND idade>=0), 
	nome varchar(100), 
	porte varchar(255) not null, 
	raca varchar(100), 
	sexo varchar(255) not null, 
	primary key (id)
);

create table tb_tutor (
	id  bigserial not null, 
	documento varchar(100), 
	email varchar(255) not null, 
	endereco varchar(100), 
	nome varchar(100), 
	telefone varchar(255) not null, 
	pet_id int8 not null, 
	primary key (id)
);


alter table if exists agendamento_procedimentos add constraint FKq1i01bh8r22mcl6cv3kynfv6y foreign key (agendamento_id) references tb_agendamento;
alter table if exists tb_agendamento add constraint FKn4k4k6w97v2g2fxkqtdvt5q55 foreign key (tutor_id) references tb_tutor;
alter table if exists tb_tutor add constraint FKcq3k5w7v6uba3nrhu2k8ksbfs foreign key (pet_id) references tb_pet;

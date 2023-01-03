create table aluno(
 id int auto_increment not null,
 nome varchar(80) not null,
 matricula char(20) not null,
 curso varchar(80) not null,
 primary key(id)
)engine=InnoDB default charset=utf8;

create table curso(
	id int auto_increment not null,
    nome varchar(80) not null,
    duracao varchar(50) not null,
    quantidade_periodo int not null,
    primary key(id)
)engine=InnoDB default charset=utf8;

create table disciplina(
	id int auto_increment not null,
    nome varchar(80) not null,
    carga_horaria int not null,
    professor varchar(80) not null,
    status varchar(30) not null,
    observacao varchar(200),
    primary key(id)
)engine=InnoDB default charset=utf8;

create table professor(
	id int auto_increment not null,
    nome varchar(80) not null,
    email varchar(80) not null,
    primary key(id)
)engine=InnoDB default charset=utf8;






create table doctor (
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    document varchar(6) not null,
    speciality varchar(100) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    complement varchar(100) not null,
    number varchar(20) not null,
    city varchar(100) not null,

    primary key (id)
);
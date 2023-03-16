drop table if exists User;
create table User(
    username varchar(100) primary key ,
    full_name varchar(100) not null ,
    password varchar(300) not null,
    role enum('ADMIN','USER') not null
);

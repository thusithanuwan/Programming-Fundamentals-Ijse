drop table if exists User;
create table User(
    username varchar(100) primary key ,
    full_name varchar(100) not null ,
    password varchar(300) not null,
    role enum('ADMIN','USER') not null
);
drop table if exists Customer;
create table Customer(
    id int primary key ,
    name varchar(100) not null ,
    address varchar(100) not null

);
drop table if exists Contact;
create table Contact(
    customer_id int not null ,
    contact varchar(100)  not null ,
    constraint uk_contact unique key (contact), # to restrict duplicating contacts
    constraint fk_contact foreign key (customer_id) references Customer(id),    # to restrict add contact without customer
    constraint pk_contact primary key (customer_id,contact)
);


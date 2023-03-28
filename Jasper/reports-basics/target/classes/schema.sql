create table if not exists Customer(
    id int primary key auto_increment,
    name varchar(100) not null,
    address varchar(300) not null
);
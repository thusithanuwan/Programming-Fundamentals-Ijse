CREATE TABLE AdminUser(
    full_name VARCHAR(100) NOT NULL ,
    user_name VARCHAR(100) PRIMARY KEY ,
    password VARCHAR(300) NOT NULL ,
    role enum('ADMIN','USER') NOT NULL
);

DELETE FROM AdminUser;


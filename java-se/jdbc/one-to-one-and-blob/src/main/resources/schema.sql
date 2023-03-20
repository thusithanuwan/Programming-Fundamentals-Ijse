create table if not exists Student(
                                      id int primary key auto_increment,
                                      name varchar(100) not null,
                                      address varchar(500) not null
);

create table if not exists Profile_Picture(
                                              student_id int primary key,
                                              picture MediumBlob not null,
                                              constraint fk_student_picture foreign key  (student_id) references Student (id)
);

insert into Student(name, address) values
                                       ('Kasun','Matara'),
                                       ('Nuwan','Kurunegala');
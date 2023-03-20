create table if not exists Items(
    code int primary key auto_increment,
    description varchar(1000) not null ,
    selling_price decimal(7,2) not null ,
    buying_price decimal(7,2) not null ,
    stock int not null
);

create table if not exists Item_Preview(
    item_code int primary key ,
    picture mediumblob not null ,
    constraint fk_item_preview foreign key (item_code) references Items (code)
);

insert into Items(description, selling_price, buying_price, stock) values
                            ('mouse','10','5','3');

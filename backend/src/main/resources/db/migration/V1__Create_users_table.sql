create table users (
    id serial primary key ,
    email varchar(100) unique not null ,
    `name` varchar(100),
    surname varchar(100)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
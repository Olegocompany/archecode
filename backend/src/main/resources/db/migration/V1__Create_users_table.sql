create table users (
    id serial primary key ,
    email varchar(100) unique not null ,
    `name` varchar(100),
    surname varchar(100),
    password varchar(255),
    role_id bigint unsigned not null,
    foreign key (role_id) references roles(id) on delete restrict on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
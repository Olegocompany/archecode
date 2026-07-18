create table projects (
    id serial primary key ,
    name varchar(200) not null ,
    description text,
    link text not null,
    branch varchar(50),
    user_id bigint unsigned not null,
    created_at timestamp default current_timestamp,

    foreign key (user_id) references users(id) on delete cascade on update cascade ,

    index idx_projects_user_id (user_id)
)
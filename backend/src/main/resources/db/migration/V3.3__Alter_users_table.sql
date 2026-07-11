alter table users add column role_id bigint unsigned not null;
alter table users add constraint fk_role_id foreign key (role_id) references roles(id) on delete restrict on update cascade ;
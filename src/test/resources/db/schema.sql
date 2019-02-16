drop table if exists user;
create table if not exists user
(
  id         int auto_increment,
  name       varchar(64)        not null,
  email      varchar(64)        not null,
  status     int      default 0 not null,
  created_at datetime default current_timestamp(),
  updated_at datetime default current_timestamp(),
  constraint user_pk primary key (id)
);


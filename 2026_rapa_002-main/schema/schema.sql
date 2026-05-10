drop database if exists `jdbc_shop`;
create database if not exists `jdbc_shop`;

drop user if exists `rapa_jdbc`;

create user
    `rapa_jdbc`@`%`
identified by
    'pwd1!'
;

grant
    all privileges
on
    *.*
to
    `rapa_jdbc`@`%`
;

flush privileges;



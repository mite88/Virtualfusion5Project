alter table post add column created_at datetime default now();
alter table post add column updated_at datetime default now();
alter table boards add column created_at datetime default now();
alter table boards add column updated_at datetime default now();


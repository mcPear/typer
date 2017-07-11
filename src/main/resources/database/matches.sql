
create database matches;
create user 'admin'@'localhost' identified by 'haslo';
grant all on matches.* to 'admin'@'localhost';
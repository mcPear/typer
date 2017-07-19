create table footballMatch(
id int not null auto_increment primary key,
teams varchar(100),
decimal rate1,
decimal rate0,
decimal rate2,
decimal rate10,
decimal rate02,
decimal rate12
)

select * from weather;
insert into weather (city,temp,pressure,time) values ('London',280.5,1024,now());
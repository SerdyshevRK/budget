create table money (
    id int primary key,
    type varchar(50),
    subject varchar(50),
    amount int,
    occurs_date timestamp,
    comment varchar(100)
)
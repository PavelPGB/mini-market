create table categories (id bigserial primary key, title varchar(255));
insert into categories (title)
values
('Food'),
('Electronic');

create table products (id bigserial primary key, title varchar(255), price int, category_id bigint references categories (id));
insert into products (title, price, category_id)
values
('Milk', 95, 1),
('Bread', 25, 1),
('Cheese', 360, 1),
('Samsung Watch X1000', 20000, 2),
('LG TV 1', 50000, 2);



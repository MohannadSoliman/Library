create schema library;
create table book(
	isbn int primary key,
    threshold int not null,
    quantity int not null,
    title varchar(20) not null,
    publisher varchar(30) not null,
    publication_year date,
    price double not null,
    category enum("Science", "Religion", "Art", "History", "Geography") not null,
    index idx_title (title),
    index idx_category (category),
    index idx_publisher (publisher)
);
create table publisher(
	publisher_name varchar(20) primary key,
    phone varchar(20) not null,
    address varchar(50)
);
create table author(
	isbn int not null,
    author_name varchar(30)
);
create table book_order(
	order_id int primary key auto_increment,
	isbn int,
    quantity int not null,
    purchase_date date not null
);
create table users(
	username varchar(30) primary key,
    user_password varchar(60) not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    address varchar(30) not null,
    email varchar(30) not null,
    phone varchar(20) not null,
    user_role enum("Manager", "Customer")
    
);
create table book_purchase(
	purchase_id int primary key auto_increment,
    isbn int,
    username varchar(30),
    quantity int not null,
    purchase_price double not null,
    purchase_date date not null
);
SHOW INDEX FROM book FROM library;
insert into book values("1", 10, 50, "The LOL", "The LOL publisher", "1990-2-2", 100, "Science");
insert into book values("2", 30, 120, "The LOL2", "The LOL publisher2", "1990-2-2", 100, "History");

insert into publisher values("The LOL publisher", "El Ehsan st", "123456789");
insert into publisher values("The LOL publisher2", "El Ehsan st2", "1234567892");
insert into publisher values("The LOL publisher3", "El Ehsan st3", "1234567893");

insert into author values("1", "The LOL author");
insert into author values("2", "The LOL author2");

insert into users values("The LOL username", "The LOL password", "LOLfirst", "LOLlast", "LOLaddress", "thelol@lol.com", "112233", "Manager");

alter table author add constraint fk_author_isbn foreign key (isbn) references book(isbn);
alter table author add constraint pk_author primary key (isbn, author_name);

alter table book add constraint fk_publisher foreign key (publisher) references publisher(publisher_name);

alter table book_order add constraint fk_order_isbn foreign key (isbn) references book(isbn);

alter table book_purchase add constraint fk_purchase_isbn foreign key (isbn) references book(isbn);
alter table book_purchase add constraint fk_purchase_username foreign key (username) references users(username);

call add_book("6", 5, 50, "The LOL4", "The LOL publisher3", "1990-2-2", 100, "History");
select title from book use index(idx_title) where title = "The LOL";
call buy_book(2, "The LOL username", 60);

select * from book;
select * from users;
select * from book_purchase;
select * from book_order;

update author set author_name = "The LOL author" where author_name = "The LOL author2";

call confirm_order_procedure(1);
call search_book("The LOL author", "author");
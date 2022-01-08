drop procedure if exists search_book;
DELIMITER //
create procedure search_book(target varchar(30), attribute varchar(30))
begin
	if attribute = "isbn"
    then select title from book where isbn = target;
    end if;
	if attribute = "title"
    then select title from book where title = target;
    end if;
	if attribute = "category"
    then select title from book where category = target;
    end if;
	if attribute = "publisher"
    then select title from book where publisher = target;
    end if;
	if attribute = "author"
    then select title from book natural join author where author_name = target;
    end if;
end //
DELIMITER ;
/*
isbn int primary key,
    threshold int not null,
    quantity int not null,
    title varchar(20) not null,
    publisher varchar(30) not null,
    publication_year date,
    price double not null,
    category enum("Science", "Religion", "Art", "History", "Geography") not null
    */
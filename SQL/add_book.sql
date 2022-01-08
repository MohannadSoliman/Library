DROP PROCEDURE if exists `library`.`add_book`;
DELIMITER //
create procedure add_book(isbn int, threshold int, quantity int, title varchar(20), publisher varchar(30), publication_year date, price int, category varchar(10))
begin
	insert into book values(isbn, threshold, quantity, title, publisher, publication_year, price, category);
end //
DELIMITER ;
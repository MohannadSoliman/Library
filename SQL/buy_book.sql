drop procedure if exists buy_book;
DELIMITER //
create procedure buy_book(purchase_isbn int, purchase_username varchar(30), purchase_quantity int)
begin
	-- first update book table
    update book set quantity = quantity - purchase_quantity where book.isbn = purchase_isbn;
    select price into @book_price from book where book.isbn = purchase_isbn;
    -- second create entry in book purchase
    insert into book_purchase(isbn, username, quantity, purchase_price, purchase_date) values(purchase_isbn, purchase_username, purchase_quantity,
								purchase_quantity * @book_price, "1999-9-9");
end //
DELIMITER ;
drop trigger if exists place_book_order;
DELIMITER //
create trigger place_book_order after update on book for each row
begin
	select quantity into @book_quantity from book where isbn = NEW.isbn;
    select threshold into @book_threshold from book where isbn = NEW.isbn;
    
    if @book_quantity < @book_threshold
    then insert into book_order(isbn, quantity, purchase_date) values(NEW.isbn, @book_threshold , "1999-9-9");
    end if;
end //
DELIMITER ;

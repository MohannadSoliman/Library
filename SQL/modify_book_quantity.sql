drop trigger if exists modify_book_qunatity;
DELIMITER //
create trigger modify_book_quantity before update on book for each row
begin
	select NEW.quantity into @book_quantity from book where isbn = NEW.isbn;
    if @book_quantity < 0
    then signal sqlstate "45000" set MESSAGE_TEXT = "Not Enough Books!";
    end if;
end //
DELIMITER ;

-- new book in order
/*

*/
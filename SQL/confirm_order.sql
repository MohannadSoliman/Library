drop trigger if exists confirm_order;
DELIMITER //
create trigger confirm_order before delete on book_order for each row
begin
	update book set quantity = OLD.quantity+quantity where book.isbn = OLD.isbn;
end //
DELIMITER ;

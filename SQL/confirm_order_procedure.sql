drop procedure if exists confirm_order_procedure;
DELIMITER //
create procedure confirm_order_procedure(id int)
begin
	delete from book_order where book_order.order_id = id;
end //
DELIMITER ;

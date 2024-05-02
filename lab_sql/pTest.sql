/*
*/
	select 
	product_category as "상품 분류",
	product_name  as "상품명", 
	current_inven as "현재 수량",
	imsqob as "발주 수량",
	current_inven+(imsqob*qntty_bndl) as "입고후 예상 수량" 
	from product 
	where current_inven <= min_stk;
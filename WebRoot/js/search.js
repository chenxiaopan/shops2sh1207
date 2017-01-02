//查询购物车里的商品
function searchShowCart() {

	var txt = $("#search").val();
	alert(txt);
	$.ajax({
		url : "forder_search",
		cache : false,
		data : {
			"search" : txt
		},
		success : function(data) {

			
		}
	});

}

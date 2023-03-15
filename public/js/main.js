var rootURL = "http://localhost:8081/products";

var findAll = function () {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
	$("#dashboard_title").text("All Products");
}

var findById = function (id) {
	console.log('findByID: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: showDetails
	});
}

var findByBrand = function (brand) {
	console.log('findByBrand: ' + brand);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/brand/' + brand,
		dataType: "json",
		success: renderList
	});
	$("#dashboard_title").text("Filtering by brand: " + brand);
}

var findByType = function (type) {
	console.log('findByType: ' + type);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/item/' + type,
		dataType: "json",
		success: renderList
	});
	$("#dashboard_title").text("Filtering by item type: " + type);
}

var findByDeal = function (deal) {
	console.log('findByDeal: ' + deal);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/deal/' + deal,
		dataType: "json",
		success: renderList
	});
	$("#dashboard_title").text("Filtering items with discount of: €" + deal + " or more");
}

var renderList = function (data) {
	console.log('renderList', data);
	var list = data;
	htmlStr = '';
	$('#merchContent').empty();
	$.each(list, function (index, product) {
		htmlStr += '<div class="details col-sm-4 text-center p-3 d-grid" id="'+ product.id +'">';
		htmlStr += '<h1>' + product.brand +'</h1>';
		htmlStr += '<img src="images/' + product.image + '" alt="product" class="displayCenter" ">';
		htmlStr += '<p><b>' + product.title +'</b></p>';
		htmlStr += '<p>' + product.type +'</p>';
		htmlStr += '<button class="infoButton btn btn-primary" id="'+product.id +'">More Details</button></div>';
	});
	$('#merchContent').append(htmlStr);
}

let showDetails = function (item) {
	console.log(item)
	$('#detailsModal').find('.modal-title').text(item.title);
	$('#pic').attr('src', 'images/' + item.image);
	$('#description').html("<strong>Item Description</strong>: "+item.description);
	$('#rrp').val("€" + item.rrp);
	$('#online_price').val("€" + item.online);
	let difference = (item.rrp - item.online).toFixed(2);
	let percentage = ((difference / item.rrp) * 100).toFixed(0);
	$('#saving').val("€" + difference + " (" + percentage + "%)");
	$('#detailsModal').modal('show');
}


//When the DOM is ready.
//Students dont have to code this section.
$(document).ready(function () {
    $(document).on("click", ".infoButton", function () { findById(this.id); });
    $(document).on("click", "#home_but", function () { 
        console.log("Home button clicked - list all");
		findAll();
     });
	$(document).on("click", "#listBrand_but", function () { 
        console.log("Search brand clicked");
		$('#filterModalBrand').modal('show');
     });	
	$(document).on("click", "#listType_but", function () { 
		$('#filterModalType').modal('show');
        console.log("Search type clicked");
     });	
	$(document).on("click", "#listDeals_but", function () { 
		$('#filterModalDeal').modal('show');
        console.log("List deals clicked");
     });	
	 $(document).on("click", "#submitBrandButton", function () {
		let brand = $('#brand').val();
		$('#brand').val("")
		$('#filterModalBrand').modal('hide');
		if ((brand) == "") {
			findAll();
		}
		else {
			console.log("Brand search parameter: "+$('#brand').val());
			findByBrand(brand);
		}
	});
	$(document).on("click", "#submitTypeButton", function () {
		let type = $('#type').val();
		$('#type').val("")
		$('#filterModalType').modal('hide');
		if ((type) == "") {
			findAll();
		}
		else {
			console.log("Type search parameter: "+$('#type').val());
			findByType(type);
		}
	});
	$(document).on("click", "#submitDealButton", function () {
		let deal = $('#deal').val();
		$('#deal').val("")
		$('#filterModalDeal').modal('hide');
		if ((deal) == "") {
			findAll();
		}
		else {
			if(isNaN(deal)){
				alert("Please enter a numerical value, no letters");
			}
			else{
				console.log("deal search parameter: "+$('#deal').val());
				findByDeal(deal);
			}
		}
	});
	findAll();
});
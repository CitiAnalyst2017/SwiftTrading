

app.service('stock_data',function($http){
	var url = './TestData/stock_data.json'; 
	var sdata;
	$http({
		method:'GET',
		url:url
	}).then(function successCallback(response){
		sdata = response.data;
	},
	function errorCallback(response){});

	this.getdata = function(){
		return sdata;
	}

});
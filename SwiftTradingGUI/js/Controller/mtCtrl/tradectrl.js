

app.controller('tradeCtrl',function($scope,$http){
	$scope.price = 0;
	$scope.qty = 0;
	$scope.types = ["Mrkt","Limit","IOC"];
	$scope.positions = ["Long","Short"];

	var send_url = 'swifttrading/rest/trade';
	// var send_url = './MockData/postData';

	$scope.sendOrder = function(){

		var order = {
			"type":$scope.type,
			"position":$scope.position,
			"code":$scope.code,
			"price":$scope.price,
			"quantity":$scope.qty,
			"lossprice":$scope.lossp*0.01,
			"profitprice":$scope.profp*0.01,
			"expiration":$scope.expir
		};

		$http({
			method:'POST',
			url:send_url,
			data:order,
		}).success(function(){

		}).error(function(){
			alert("InternetError");
		});
	};

	

});
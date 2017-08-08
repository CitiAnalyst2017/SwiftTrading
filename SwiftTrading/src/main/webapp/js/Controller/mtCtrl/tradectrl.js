

app.controller('tradeCtrl',function($scope,$http){
	$scope.price = 0;
	$scope.qty = 0;

	var send_url = '/rest/trade';

	var order = {
		"type":$scope.type,
		"code":$scope.code,
		"price":$scope.price,
		"quantity":$scope.qty,
		"lossprice":$scope.lossp*0.01,
		"profitprice":$scope.profp*0.01,
		"expiration":$scope.expir
	};

	$scope.sendOrder = function(){
		$http({
			method:'POST',
			url:send_url,
			data:order,
		});
	};

});
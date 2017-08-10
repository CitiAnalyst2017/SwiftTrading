
/*book the order and send it to the server*/
app.controller('tradeCtrl',function($scope,$http,$rootScope){
	$scope.price = 0;
	$scope.qty = 0;
	$scope.types = ["MARKET","LIMIT","IOC"];
	$scope.positions = ["LONG","SHORT"];

	var send_url = url_prefix + 'trade';

	$scope.sendOrder = function(){

		var order = {
			"type":$scope.type,
			"position":$scope.position,
			"code":$scope.code,
			"buyprice":$scope.price,
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
			alert("send order successfully");
			$scope.type = "";
			$scope.position = "";
			$scope,code = "";
			$scope.price = 0;
			$scope.qty = 0;
			$scope.lossp = "";
			$scope.profp = "";
			$scope.expir = "";

		}).error(function(){
			alert("InternetError");
		});
	};


	$scope.total = function(){
		if($scope.type != 'LIMIT'){
			$scope.showprice = false;
			return false;
		}else{
			$scope.showprice = true;
			return true;
		}
	};
	
});

/*get pending order every 1 second*/
app.controller('ptradelistCtrl',function($scope,$http,$interval){

	var pending_order_url = url_prefix + 'trade/pending';

	$scope.nopt = false;
	$scope.errormsg = false;
	$scope.opdis = false;
	$scope.oper = "";

	// mock data
	$scope.porders = [{
		"code":"app",
		"position":"long",
		"quantity":214,
		"bprice":102,
		"nprice":103,
		"profit":214,
		"status":"buying",
		"time":"2016-3-17"
	},{
		"code":"mic",
		"position":"short",
		"quantity":345,
		"bprice":102,
		"nprice":103,
		"profit":345,
		"status":"bought",
		"time":"2017-6-17"
	}];

	/*$http({
			method:'GET',
			url:pending_order_url,
		}).success(function(){
			$scope.porders = data;
			if(porders.length == 0){
				$scope.nopt = true;
			}
		}).error(function(){
			$scope.errormsg = true;
		});*/


	/*$interval(function(){
		$http({
			method:'GET',
			url:pending_order_url,
		}).success(function(){
			$scope.porders = data;
			if(porders.length == 0){
				$scope.nopt = true;
			}
		}).error(function(){
			$scope.errormsg = true;
		});
	},2000);*/

	$scope.opert = function(){
		/*this function can not be used now*/
		/*$http({
			method:'POST',
			url:pending_order_url,
			data:this.porder,
		}).success(function(){
			this.oper = "Operating";
			this.opdis = true;
			alert("operate successfully!");
		}).error(function(){
			alert("InternetError");
		});	*/
		this.oper = "ing";
		this.opdis = true;
	};

	$scope.sellstock = function(){
		alert("haha");
	};

});
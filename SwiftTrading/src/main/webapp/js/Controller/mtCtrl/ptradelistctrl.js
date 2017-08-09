
/*get pending order every 1 second*/
app.controller('ptradelistCtrl',function($scope,$http,$interval){

	var pending_order_url = url_prefix + 'trade/pending';

	$scope.nopt = false;
	$scope.errormsg = false;

	$interval(function(){
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
	},1000);

});
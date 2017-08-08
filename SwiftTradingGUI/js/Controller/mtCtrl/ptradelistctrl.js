
/*get pending order every 1 second*/
app.controller('ptradelistCtrl',function($scope,$http,$interval){

	var pending_order_url = url_prefix + 'trade/pending';

	$scope.nopt = function(){
			return false;	
		};

	$interval(function(){
		$http({
			method:'GET',
			url:pending_order_url,
		}).success(function(){
			$scope.porders = data;
		}).error(function(){
			
		});
	},1000);

});
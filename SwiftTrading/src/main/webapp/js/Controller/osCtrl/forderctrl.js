

app.controller('forderCtrl',function($scope,$http,$interval){
	var fo_url = url_prefix + 'order/finish';
	$scope.errormsg = false;
	$scope.nofo = false;

	$interval($http({
		method:'GET',
		url:fo_url,
	}).success(function(data){
		$scope.forders = data;
		if($scope.forders.length == 0){
			$scope.nofo = true;
		}
	}).error(function(){
		$scope.errormsg = true;
	}),1000);
	
});
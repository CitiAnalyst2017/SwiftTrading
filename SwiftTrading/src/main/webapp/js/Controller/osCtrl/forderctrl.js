

app.controller('forderCtrl',function($scope,$http,$interval){
	var fo_url = url_prefix + 'order/finish';
	$scope.errormsg = false;
	$scope.nofo = false;
	$scope.flistshow = true;
	$scope.bys = ["User"];

	$http({
		method:'GET',
		url:fo_url + '/user',
	}).success(function(data){
		$scope.bys.concat(data);
	}).error(function(){
		$scope.errormsg = true;
		$scope.flistshow = false;
	});

	$scope.selectby = function(){
		$http({
			method:'GET',
			url:fo_url + '/' + this.by,
		}).success(function(data){
			$scope.bys.concat(data);
		}).error(function(){
			$scope.errormsg = true;
			$scope.flistshow = false;
		});
	};

	/*$interval($http({
		method:'GET',
		url:fo_url,
	}).success(function(data){
		$scope.flistshow = true;
		$scope.forders = data;
		if($scope.forders.length == 0){
			$scope.nofo = true;
		}
	}).error(function(){
		$scope.errormsg = true;
	}),1000);*/
	
});
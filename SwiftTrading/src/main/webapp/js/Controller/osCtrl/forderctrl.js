

app.controller('forderCtrl',function($scope,$http,$interval){
	var fo_url = url_prefix + 'trade/';
	$scope.errormsg = false;
	$scope.nofo = false;
	$scope.flistshow = true;
	$scope.bys = ["user"];

	$http({
		method:'GET',
		url:fo_url + "created/user",
	}).success(function(data){
		$scope.forders=data;
	}).error(function(){
		$scope.errormsg = true;
		$scope.flistshow = false;
	});
	
	$http({
		method:'GET',
		url:fo_url + 'created',
	}).success(function(data){
		$scope.bys=$scope.bys.concat(data);
	}).error(function(){
		$scope.errormsg = true;
		$scope.flistshow = false;
	});

	$scope.selectby = function(){
		$http({
			method:'GET',
			url:fo_url + "created/"+this.by,
		}).success(function(data){
			$scope.forders=data;
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
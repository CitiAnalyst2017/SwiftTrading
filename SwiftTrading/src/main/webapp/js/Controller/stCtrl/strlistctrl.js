

app.controller('strlistCtrl',function($scope,$http,$interval,$timeout){
	var str_url = url_prefix + '';
	$scope.errormsg = false;

	/*$http({
			method:'GET',
			url:str_url
		}).success(function(data){
			$scope.strates = data;
		}).error(function(){
			$scope.errormsg = true;
	});*/

	$scope.strates = [{
		"id":"2412",
		"name":"asfa",
		"status":"running"
	},{
		"id":"122",
		"name":"vfg",
		"status":"finished"
	}];

	$scope.cancelstr = function(){
		this.strate.status = "Stoping";
		$http({
			method:'POST',
			url:str_url,
			data:this.strade,
		}).success(function(){
			//if success, flush the page 1 second later
			$timeout(function(){
				$http({
					method:'GET',
					url:str_url
				}).success(function(data){
					$scope.strates = data;
				}).error(function(){
					$scope.errormsg = true;
				})
			},1000);
			
		}).error(function(){
			alert("InternetError");
		});
	};

	$scope.showdetail = function(){
		alert("asd");
	};
});
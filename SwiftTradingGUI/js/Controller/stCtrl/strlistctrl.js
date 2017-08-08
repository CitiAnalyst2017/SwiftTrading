

app.controller('strlistCtrl',function($scope,$http,$interval){
	var str_url = url_prefix + '';
	$scope.errormsg = false;

	$interval(function(){
		$http({
			method:'GET',
			url:str_url
		}).success(function(data){

		}).error(function(){
			$scope.errormsg = true;
		});
	},1000);
});
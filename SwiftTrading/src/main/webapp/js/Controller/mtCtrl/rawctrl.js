/*get raw data from server*/
app.controller('rawCtrl',function($scope,$http,$interval){

	var raw_url = url_prefix + 'security/'+$scope.code;

	$scope.errormsg = false;

	var getrawdata = function(){
		$http({
			method:'GET',
			params:{code:$scope.code},
			url:raw_url
		}).success(function(data){
			$scope.errormsg = false;
			$scope.raws = data;
		}).error(function(){
			$scope.errormsg = true;
		});
	};

	$scope.searchrawclick = function(){
		getrawdata();	
		$interval(function(){
			getrawdata();
		},1000);
	};


});
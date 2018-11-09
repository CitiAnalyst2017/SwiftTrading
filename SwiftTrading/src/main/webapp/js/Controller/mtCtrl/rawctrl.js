/*get raw data from server*/
app.controller('rawCtrl',function($scope,$http,$interval){

	$scope.errormsg = false;

	var getrawdata = function(){
		var raw_url = url_prefix + 'security/'+$scope.code+'/orderbook';
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
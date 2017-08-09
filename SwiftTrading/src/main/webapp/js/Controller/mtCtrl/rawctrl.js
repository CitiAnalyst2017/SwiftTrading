/*get raw data from server*/
app.controller('rawCtrl',function($scope,$http,$interval){

	var raw_url = url_prefix + 'security/'+$scope.code;

	var getrawdata = function(){
		$http({
			method:'GET',
			params:{code:$scope.code},
			url:raw_url
		}).success(function(data){
			$scope.raws = data;
		}).error(function(){
			alert("Internet Error");
		});
	};

	$scope.searchrawclick = function(){
		getrawdata();	
	};

/*
	$interval(function(){
		getrawdata();
	},1000);*/
});
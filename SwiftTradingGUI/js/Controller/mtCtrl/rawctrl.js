
app.controller('rawCtrl',function($scope,$http,$interval){

	var raw_url = './MockData/raw_data.json';

	var getrawdata = function(){
		$http({
			method:'GET',
			params:{"code":$scope.code},
			url:raw_url
		}).success(function(data){
			$scope.raws = data;
		}).error(function(){
			alert("Internet Error");
		});
	}

	$scope.searchrawclick = function(){
		getrawdata();
	}

	var validationInput(){
		if($scope.code == ""){
			return false;
		}else{
			return true;
		}
	}

	$interval(function(){
		if(validationInput)
		getrawdata();
	},1000);
});
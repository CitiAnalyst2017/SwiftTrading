
/*ng-switch will create his own scope 
in ther words its scope is child scope*/
app.controller('stradeCtrl',function($scope,$http){

	$scope.names = ["MovingAverage","Boll Band"];

	var strade_url = url_prefix + 'strategy/'+$scope.sname; 

	$scope.maclick = function(){
		var maorder = {
			"code":$scope.macode,
			"longperiod":$scope.malp,
			"shortperiod":$scope.masp,
			"exit":$scope.maex
		};

		sendstr(maorder);

	};

	$scope.bbclick = function(){

		var bborder = {
			"code":$scope.bbcode,
			"period":$scope.bbpe,
			"standarddeviation":$scope.bbsd,
			"exit":$scope.bbex
		};

		sendstr(bborder);
	};

	var sendstr = function(sdata){
		$http({
			method:'POST',
			url:strade_url,
			data:sdata,
		}).success(function(){}).error(function(){
			alert("InternetError");
		 });
	};
});
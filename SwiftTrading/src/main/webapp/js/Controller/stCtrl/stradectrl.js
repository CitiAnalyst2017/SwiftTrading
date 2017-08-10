
/*ng-switch will create his own scope 
in ther words its scope is child scope*/
app.controller('stradeCtrl',function($scope,$http){

	$scope.names = ["MovingAverage","BollBand"];

	$scope.maclick = function(){
		var strade_url = url_prefix + 'strategy/'+$scope.sname; 
		var maorder = {
			"code":$scope.macode,
			"longperiod":$scope.malp,
			"shortperiod":$scope.masp,
			"exit":$scope.maex
		};

		sendstr(maorder,strade_url);

	};

	$scope.bbclick = function(){
		var strade_url = url_prefix + 'strategy/'+$scope.sname; 
		var bborder = {
			"code":$scope.bbcode,
			"period":$scope.bbpe,
			"std":$scope.bbsd,
			"exit":$scope.bbex
		};

		sendstr(bborder,strade_url);

	};

	var sendstr = function(sdata,surl){
		$http({
			method:'POST',
			url:surl,
			data:sdata,
		}).success(function(){
			alert("send order successfully");
		}).error(function(){
			alert("InternetError");
		 });
	};
});
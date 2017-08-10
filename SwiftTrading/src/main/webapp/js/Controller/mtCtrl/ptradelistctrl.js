
/*get pending order every 1 second*/
app.controller('ptradelistCtrl',function($scope,$http,$interval,$rootScope){

	var pending_order_url = url_prefix + 'trade/pending';

	$scope.nopt = false;
	$scope.errormsg = false;
	$scope.opdis = false;
	$scope.oper = "";

	$scope.pr = function(p){
		if(p.profit>0){
			return "r";
		}else{
			return "g";
		}
	};

	// mock data
	/*$scope.porders = [{
		"code":"app",
		"position":"long",
		"quantity":214,
		"buyprice":102,
		"nowprice":103,
		"profit":214,
		"status":"CREATED",
		"starttime":"2016-3-17",
		"type":"sdf"
	},{
		"code":"mic",
		"position":"short",
		"quantity":345,
		"buyprice":102,
		"nowprice":103,
		"profit":-130,
		"status":"OPEN",
		"starttime":"2017-6-17",
		"type":"MARKET"
	}];
*/
	$http({
		method:'GET',
		url:pending_order_url,
	}).success(function(data){
		$scope.errormsg = false;
		$scope.porders = data;
		if(porders.length == 0){
			$scope.nopt = true;
		}
	}).error(function(){
			$scope.errormsg = true;
	});


	$interval(function(){
		$http({
			method:'GET',
			url:pending_order_url,
		}).success(function(data){
			$scope.errormsg = false;
			$scope.porders = data;
			if(porders.length == 0){
				$scope.nopt = true;
			}
		}).error(function(){
			$scope.errormsg = true;
		});
	},2000);

	$scope.opert = function(){

		if(this.porder.type != "MARKET"){
			this.porder.status = 'CLOSING';
			$http({
				method:'PUT',
				url:url_prefix + '/trade/',
				data:this.porder,
			}).success(function(){
				this.oper = "ing";
				this.opdis = true;
				alert("operate successfully!");
			}).error(function(){
				alert("InternetError");
			});
		}else{
			$('#sello').modal('show');
		}
				
	};

	$scope.sellm = function(){
		this.porder.sellprice = $scope.sellpr;
		$http({
			method:'POST',
			url:url_prefix + '/trade/',
			data:this.porder,
		}).success(function(){

		}).error(function(){
			alert("InternetError");
		});
	};

});
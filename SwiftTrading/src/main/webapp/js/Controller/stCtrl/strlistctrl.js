

app.controller('strlistCtrl',function($scope,$http,$interval,$timeout){

	var str_url = url_prefix + 'strategy';

	var poller;

	$scope.errormsg = false;

	$http({
			method:'GET',
			url:str_url
		}).success(function(data){
			$scope.errormsg = false;
			$scope.strates = data;
		}).error(function(){
			$scope.errormsg = true;
	});

	$scope.startstr = function(){
		this.strate.status = "Running";
		$http({
			method:'PUT',
			url:str_url,
			data:this.strate,
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

	$scope.stopstr = function(){
		this.strate.status = "Stoping";
		$http({
			method:'PUT',
			url:str_url,
			data:this.strate,
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
		$('#strd').modal('show');
		var det_url = url_prefix + 'strategy/'+this.strate.id+'/orders';
		poller= $interval(function(){
			$http({
			method:'GET',
			url:det_url,
		}).success(function(data){
			$scope.sdts = data;
		}).error(function(){
			alert("InternetError");
		});
		},2000);	
		
	};
	
	$scope.close = function(){
		$interval.cancel(poller);
	}

	//when closing the dialog clean the $interval
	$scope.closedialog = function(){
		$interval.cancel(poller);
	};

});
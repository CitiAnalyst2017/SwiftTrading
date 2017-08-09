'use strict';

var can_be_operated_orders = [];
var finshed_orders = [];
var can_be_operated_orders = [];
var finished_orders = [];

var url_prefix = '/swifttrading/rest/';

var app = angular.module('st',[]);

app.controller('indexCtrl',function($scope,$location){
	$scope.topnav = './view/top-nav.html';  
	$scope.main = './view/MTrade/Trade.html'; 

	$scope.mtclick = function(){
		$scope.main = './view/MTrade/Trade.html';
	};
	$scope.stclick = function(){
		$scope.main = './view/STrade/StrategyTrade.html';

	};
	$scope.oclick = function(){
		$scope.main = './view/OrderHistory/OrderHistory.html';
	}
	});

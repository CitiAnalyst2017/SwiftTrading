/*this is used to controll performance page*/

app.controller('perfCtrl',function($http,$scope){

	var perf_url = url_prefix + 'strategy/performance';

	var ma_total_pr = 56.33;

	var bb_total_pr = 24.03;

	var ma_pr_details = [['C1',24.13],['C2',17.2],['C3',8.11],['C4',
                        5.33],['C5',1.06],['C6',0.5]];

	var bb_total_details = [['Cc1',5],['Cc2',4.32],['Cc3',3.68],['Cc4',
                        0.85],['Cc5',0.6],['Cc6',0.14]];

	$http({
		method:'GET',
		url:perf_url,
	}).success(function(data){

	}).error(function(){});

	var draw_diagram = function(){

	};

	 Highcharts.chart('pcontainer', {
        chart: {
            type: 'column',
			width: 800,
            height: 400
        },
        credits:{
            enabled:false
        },
        title: {
            text: 'Strategy Performance Analyse'
        },
        subtitle: {
            text: 'Click to view Strategy Details'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: 'Profit(%)'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.1f}%'
                }
            }
        },
        series: [{
            name: 'Strategy Name',
            colorByPoint: true,
            data: [{
                name: 'Moving Average Strategy',
                y: ma_total_pr,
                drilldown: 'A'
            }, {
                name: 'Bollinger Band Strategy',
                y: bb_total_pr,
                drilldown: 'B'
            }]
        }],
        drilldown: {
            series: [{
                name: 'Moving Average Strategy',
                id: 'A',
                data: ma_pr_details
            }, {
                name: 'Bollinger Band Strategy',
                id: 'B',
                data: bb_total_details
            }]
        }
    });

	
});
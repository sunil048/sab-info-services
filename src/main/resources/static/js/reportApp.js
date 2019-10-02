/**
 * 
 */
/*if (angular == undefined){
	alert('not loaded')
}else{
	alert('loaded')
}*/
var reportApp = angular.module('plmreport',[]);
reportApp.config(['$httpProvider', function($httpProvider) {
 /*   $httpProvider.defaults.useXDomain = true;*/
	// Define a new http header
	$httpProvider.defaults.headers.common['auth-token'] = 'Basic YWRtaW46YWRtaW4=';
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}
]);

reportApp.directive('taskDir',function(){

	function linkFunction(scope,elem,attrs){
		/*
		elem.bind('click',function(){
			alert('table clicked');
		})
		*/
	}//$compile automitically populate theese values

	return {
		restrict:'ACE',
		templateUrl:'templates/taskDeatailsTemp.htm',
		link:linkFunction,
		 headers: { 'auth-token': 'Basic YWRtaW46YWRtaW4=' }
	}
})


reportApp.controller('generateReport',function($http,$scope,plmFactory,plmService){
	try{
		plmFactory.getHttpData('rest/taskList').success(function(data){
			// var data = angular.toJson(data); No need to conver json
			//console.log(data);
			$scope.data = data;
			}).error(function(response){
				console.log("data : ",data);
				console.log("status : ",status);
				console.log("header : ",headers);
				console.log("config : ",config);
				console.log(response)
				$scope.error = "Error in connection";
				
			})
	}catch (e) {
		$scope.error = e;
	}

	//var data = plmFactory.getTaskData();
	//console.log(data);
   //$scope.data = data;

  /* var data = plmService.getData();
	console.log(data);
   $scope.data = data;*/
	
});
reportApp.controller('logController',function($http,$scope,logServices,plmService,plmFactory){
	
	$scope.getTaskLogs = function(taskId){
		//alert(taskId)
		/*var data = plmFactory.getHttpData();
		console.log("data is "+data);
		alert(data)*/
		plmFactory.getHttpData('taskDetails/'+taskId).success(function(data){
			$scope.data =  data;
		}).error(function(response){
		});
		
	}
	
})
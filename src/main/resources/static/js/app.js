var app = angular.module("sabwikiAplication",['ngRoute'])
app.config(function($routeProvider){
	$routeProvider.when('/createbook',{
		templateUrl:'/sab-edu/templates/book.htm',
		controller:'bookController'
	})
});
app.factory('sabtokservice',['$http','$q',function($http,$q){
	
	var URL="http://localhost:7007/sabtokservices/backend/myserv/test";
		 
		    var factory = {
		    		multiply:multiply
		    		
		    };
		    
		    function multiply(a, b) {
		       return a * b
		    }
		    
		    return factory;
   
}])
app.controller("myCtr",function($scope){
	$scope.msg="welcome";
})
app.controller("bookController",['$scope','sabtokservice',function($scope,sabtokservice){
    $scope.saveBook = function(){
	var k = sabtokservice.multiply(4,5);
	alert(k)
}
	

}])
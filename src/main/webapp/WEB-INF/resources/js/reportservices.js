/**
 * 
 */

 reportApp.factory('plmFactory',function($http,$q){
 	var plm = {};
 	plm.getTaskData = function(){
 		var defer = $q.defer();
 		 var temp = {};
 		
 		$http({
 			method:'GET',
 			url:'http://localhost:8080/plm/taskList',
 			headers: { 'auth-token': 'Basic YWRtaW46YWRtaW4=' }
 		}).then(function(response){
 			 temp = response.data;
 			 defer.resolve(temp);
 		})
 		return defer.promise;
 	}

 	plm.test = function(){
 		return "success"
 	}

 	plm.getHttpData = function(url){
 		return $http({method:'GET',url:'http://localhost:8080/plm/'+url,headers: { 'auth-token': '[{"key":"Authorization","value":"Basic YWRtaW46YWRtaW4=","description":""}]' }})
 	}
 	return plm;

 })

 reportApp.service('plmService',function($http){
 	return {
 		getData : function(){
 			return "test"
 		}
 	}
 })
 reportApp.service('logServices',function($http){
 	return {
 		getData : function(taskId){
 			$http.get('http://localhost:8080/plm/taskList').success(function(data){
 				return data;
 				}).error(function(response){
 				})
 		//	return "test"
 		}
 	}
 })
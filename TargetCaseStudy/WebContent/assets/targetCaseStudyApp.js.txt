//Define an angular module for our app
var targetCaseStudy = angular.module('targetCaseStudy', []);
 
targetCaseStudy.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/ShowProductDetails/:productId', {
        templateUrl: 'templates/show_product_detail.html',
        controller: 'ShowProductDetailsController'
    }).
      when('/ShowAllProducts', {
        templateUrl: 'templates/show_all_products.html',
        controller: 'ShowProductController'
      }).
      when('/ShowProductByCategory', {
          templateUrl: 'templates/show_products_by_cat.html',
          controller: 'ShowProductController'
        }).
        when('/ShowProductByCategory/:cat', {
            templateUrl: 'templates/show_products_category.html',
            controller: 'ShowProductCatController'
          }).
      otherwise({
        redirectTo: '/ShowAllProducts'
      });
}]);

/**
 * This controller again call the factory method getProductDetails, where we this controller 
 * provide the product id and call webservices to get related data for this product.
 * 
 */ 
targetCaseStudy.controller('ShowProductCatController', function($scope, $routeParams, productFactory) {
     
	$scope.productCategoryList = {};
	$scope.category = $routeParams.cat;
	getProductCategory($scope.category);
    function getProductCategory(category) {
    	productFactory.getProductCategory(category).success(function(response) {
    		$scope.productCategoryList = response;
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    }    
});
 
/**
 * This controller again call the factory method getProductDetails, where we this controller 
 * provide the product id and call webservices to get related data for this product.
 * 
 */ 
targetCaseStudy.controller('ShowProductDetailsController', function($scope, $routeParams, productFactory) {
     
	$scope.product = {};
	$scope.product_Id = $routeParams.productId;
    getProductDetails($scope.product_Id);
    function getProductDetails(product_Id) {
    	productFactory.getProductDetails(product_Id).success(function(response) {
    		$scope.product = response;
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    }    
});
 
/**
 * ShowProductController is used to get all the product from the server using factory
 * method getAllProductInfo and then push into the productlist array, later we use this array
 * to display all the product in a table.
 */ 
targetCaseStudy.controller('ShowProductController', function($scope, productFactory) {
 
    $scope.productList = {};
    
    getAllProduct();
   
    function getAllProduct() {
    	productFactory.getAllProductInfo().success(function(response) {
    		$scope.productList = response;
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    }
});

/**
 * This is a factory where we produce all the data for our case study. 
 * We use angular js http methods to get data using all the web services
 * available. 
 * <Example>
 * 		http://localhost:8080/TargetCaseStudy/service/products/allproduct
 *  create a factory with the above url and feed data to the appropriate controller.
 */
targetCaseStudy.factory('productFactory', ['$http', function($http) {
	 
    var factory = {};
    
    var baseurl = 'http://localhost:8080/TargetCaseStudy/service/products/';
    factory.getAllProductInfo = function() {
    	return $http.get(baseurl + "allproducts");
    }
    factory.getProductDetails = function(productId) {
    	return $http.get(baseurl + productId);
    }
    
    factory.getProductCategory = function(category) {
    	return $http.get(baseurl + "category/" +category);
    }
    return factory;
}])

targetCaseStudy.filter('unique', function() {
   return function(collection, keyname) {
      var output = [], 
          keys = [];

      angular.forEach(collection, function(item) {
          var key = item[keyname].toLowerCase();
          if(keys.indexOf(key) === -1) {
              keys.push(key);
              output.push(item);
          }
      });
      return output;
   };
});
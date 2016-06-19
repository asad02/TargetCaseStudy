//Define an angular module for our app
var targetCaseStudy = angular.module('targetCaseStudy', []);
 
targetCaseStudy.config(['$routeProvider',
  function($routeProvider, $locationProvider) {
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
            templateUrl: 'templates/show_all_products.html',
            controller: 'ShowProductCatController'
          }).
          when('/CreateNewProduct', {
              templateUrl: 'templates/create_product_detail.html',
              controller: 'CreateProductDetailsController'
            }).
      otherwise({
        redirectTo: '/ShowAllProducts'
      });
}]);

/**
 * This controller again call the factory method getProductDetails, where we
 * this controller provide the product id and call webservices to get related
 * data for this product.
 * 
 */ 
targetCaseStudy.controller('ShowProductCatController', function($scope, $routeParams, productFactory, $location, $filter) {
	$scope.sort = {       
        sortingOrder : 'productId',
        reverse : false
    };
	$scope.gap = 5;
	$scope.viewby = 5;
	$scope.filteredItems = [];
	$scope.groupedItems = [];
	$scope.itemsPerPage = $scope.viewby;
	$scope.pagedItems = [];
	$scope.currentPage = 0;
	$scope.items = [];
	$scope.productCategoryList = {};
	$scope.category = $routeParams.cat;
	
	getProductCategory($scope.category);
	
    function getProductCategory(category) {
    	productFactory.getProductCategory(category).success(function(response) {
    		$scope.productCategoryList = response.product;
    		$scope.items = response.product;
    		$scope.search();
    		$scope.groupToPages();
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    } 
    
    $scope.deleteProduct = function (productId) {
    	deleteProduct(productId);
    }
    
    function deleteProduct(productId) {
    	productFactory.deleteProduct(productId).success(function(response) {
    		if(response.successCode == '200') {
    			$location.path('#ShowAllProducts');
    		}
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    	
    }
    
    $scope.setItemsPerPage = function(num) {
    	$scope.itemsPerPage = num;
    	$scope.currentPage = 1;
    	getProductCategory($scope.category);	
    }
    
    var searchMatch = function (haystack, needle) {
    	if (!needle) {
    		return true;
    	}
    	return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
    };

	  // init the filtered items
	  $scope.search = function () {
	      $scope.filteredItems = $filter('filter')($scope.items, function (item) {
	          for(var attr in item) {
	              if (searchMatch(item[attr], $scope.query))
	                  return true;
	          }
	          return false;
	      });
	      // take care of the sorting order
	      if ($scope.sort.sortingOrder !== '') {
	          $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sort.sortingOrder, $scope.sort.reverse);
	      }
	      $scope.currentPage = 0;
	  };
	  
	
	  // calculate page in place
	  $scope.groupToPages = function () {
	      $scope.pagedItems = [];
	      
	      for (var i = 0; i < $scope.filteredItems.length; i++) {
	          if (i % $scope.itemsPerPage === 0) {
	              $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
	          } else {
	              $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
	          }
	      }
	      if($scope.pagedItems.length > 5) {
	    	  $scope.gap = 5;
	      } else {
	    	  $scope.gap = $scope.pagedItems.length;
	      }
	  };
	  
	  $scope.range = function (size,start, end) {
	      var ret = [];        
	      console.log(size,start, end);
	                    
	      if (size < end) {
	          end = size;
	          start = size-$scope.gap;
	      }
	      for (var i = start; i < end; i++) {
	          ret.push(i);
	      }        
	       console.log(ret);        
	      return ret;
	  };
	  
	  $scope.prevPage = function () {
	      if ($scope.currentPage > 0) {
	          $scope.currentPage--;
	      }
	  };
	  
	  $scope.nextPage = function () {
	      if ($scope.currentPage < $scope.pagedItems.length - 1) {
	          $scope.currentPage++;
	      }
	  };
	  
	  $scope.setPage = function () {
	      $scope.currentPage = this.n;
	  };
});
 
/**
 * This controller again call the factory method getProductDetails, where we
 * this controller provide the product id and call web services to get related
 * data for this product.
 * 
 */ 
targetCaseStudy.controller('ShowProductDetailsController', function($scope, $routeParams, productFactory) {
     
	$scope.product = {};
	$scope.success = true;
	$scope.product_Id = $routeParams.productId;
    getProductDetails($scope.product_Id);
    function getProductDetails(product_Id) {
    	productFactory.getProductDetails(product_Id).success(function(response) {
    		$scope.product = response;
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    } 
    
    $scope.submitFormData = function () {
    	productFactory.updateProduct($scope.product, $scope.product_Id).success(function (response) {
    		if(response.successCode == '200') {
    			$scope.success = false;	
    		}
    	}).
    	error(function(error) {
    		alert("Error Occurred:" + error.message);
    	});
    };
});

/**
 * This controller again call the factory method getProductDetails, where we
 * this controller provide the product id and call webservices to get related
 * data for this product.
 * 
 */ 
targetCaseStudy.controller('CreateProductDetailsController', function($scope, $routeParams, productFactory, $location) {
     
	$scope.product = {};
	$scope.created = true;
	$scope.submitProductData = function () {
		$scope.submitted = true;
    	if ($scope.form.$valid) {
	    	productFactory.createProduct($scope.product).success(function (response) {
	    		if(response.successCode == '200') {
	    			$location.path('#ShowAllProducts');
	    			$scope.created = false;
	    		}
	    		
	    	}).
	    	error(function(error) {
	    		alert("Error Occurred:" + error.message);
	    	});
    	}
    };
});
 
/**
 * ShowProductController is used to get all the product from the server using
 * factory method getAllProductInfo and then push into the productlist array,
 * later we use this array to display all the product in a table.
 */ 
targetCaseStudy.controller('ShowProductController', function($scope, productFactory, $location, $filter) {
 
    $scope.productList = {};
    $scope.sort = {       
            sortingOrder : 'productId',
            reverse : false
        };
	$scope.gap = 5;
	$scope.viewby = 5;
	$scope.filteredItems = [];
	$scope.groupedItems = [];
	$scope.itemsPerPage = $scope.viewby;
	$scope.pagedItems = [];
	$scope.currentPage = 0;
	$scope.items = [];
    
    getAllProduct();
   
    function getAllProduct() {
    	productFactory.getAllProductInfo().success(function(response) {
    		$scope.productList = response.product;
    		$scope.totalItems = $scope.productList.length;
    		$scope.items = response.product;
    		$scope.search();
    		$scope.groupToPages();
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    }
    
    $scope.deleteProduct = function (productId) {
    	deleteProduct(productId);
    }
    
    function deleteProduct(productId) {
    	productFactory.deleteProduct(productId).success(function(response) {
    		if(response.successCode == '200') {
    			$location.path('#ShowAllProducts');
    		}
    	}).error(function(error){
    		$scope.status = "Error Retriving data";
    	});
    }

    $scope.setItemsPerPage = function(num) {
    	$scope.itemsPerPage = num;
    	$scope.currentPage = 1;
    	getAllProduct();	
    }
    
    var searchMatch = function (haystack, needle) {
    	if (!needle) {
    		return true;
    	}
    	return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
    };

	  // init the filtered items
	  $scope.search = function () {
	      $scope.filteredItems = $filter('filter')($scope.items, function (item) {
	          for(var attr in item) {
	              if (searchMatch(item[attr], $scope.query))
	                  return true;
	          }
	          return false;
	      });
	      // take care of the sorting order
	      if ($scope.sort.sortingOrder !== '') {
	          $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sort.sortingOrder, $scope.sort.reverse);
	      }
	      $scope.currentPage = 0;
	  };
	  
	
	  // calculate page in place
	  $scope.groupToPages = function () {
	      $scope.pagedItems = [];
	      
	      for (var i = 0; i < $scope.filteredItems.length; i++) {
	          if (i % $scope.itemsPerPage === 0) {
	              $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
	          } else {
	              $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
	          }
	      }
	      if($scope.pagedItems.length > 5) {
	    	  $scope.gap = 5;
	      } else {
	    	  $scope.gap = $scope.pagedItems.length;
	      }
	  };
	  
	  $scope.range = function (size,start, end) {
	      var ret = [];        
	      console.log(size,start, end);
	                    
	      if (size < end) {
	          end = size;
	          start = size-$scope.gap;
	      }
	      for (var i = start; i < end; i++) {
	          ret.push(i);
	      }        
	       console.log(ret);        
	      return ret;
	  };
	  
	  $scope.prevPage = function () {
	      if ($scope.currentPage > 0) {
	          $scope.currentPage--;
	      }
	  };
	  
	  $scope.nextPage = function () {
	      if ($scope.currentPage < $scope.pagedItems.length - 1) {
	          $scope.currentPage++;
	      }
	  };
	  
	  $scope.setPage = function () {
	      $scope.currentPage = this.n;
	  };
});

/**
 * This is a factory where we produce all the data for our case study. We use
 * angular js http methods to get data using all the web services available.
 * <Example> http://localhost:8080/TargetCaseStudy/service/products/allproduct
 * create a factory with the above url and feed data to the appropriate
 * controller.
 */
targetCaseStudy.factory('productFactory', ['$http', function($http) {
	 
    var factory = {};
    
    var baseurl = 'http://localhost:8080/TargetCaseStudy/service/products/';
    factory.getAllProductInfo = function() {
    	return $http.get(baseurl + "productinfo");
    }
    factory.getProductDetails = function(productId) {
    	return $http.get(baseurl + productId);
    }
    
    factory.getProductCategory = function(category) {
    	return $http.get(baseurl + "category/" +category);
    }
    factory.updateProduct = function (product, productId) {
        return $http.put(baseurl + productId, product);
    };
    factory.createProduct = function (product) {
        return $http.post(baseurl+"createproduct", product);
    };
    factory.deleteProduct = function (productId) {
        return $http.delete(baseurl+ productId);
    };
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

targetCaseStudy.$inject = ['$scope', '$filter'];

targetCaseStudy.directive("customSort", function() {
	return {
	    restrict: 'A',
	    transclude: true,    
	    scope: {
	      order: '=',
	      sort: '='
	    },
	    template : 
	      ' <a ng-click="sort_by(order)" >'+
	      '    <span ng-transclude></span>'+
	      '    <i ng-class="selectedCls(order)"></i>'+
	      '</a>',
	    link: function(scope) {
	                
	    // change sorting order
	    scope.sort_by = function(newSortingOrder) {       
	        var sort = scope.sort;
	        	
	        if (sort.sortingOrder == newSortingOrder){
	            sort.reverse = !sort.reverse;
	        }                    
	
	        sort.sortingOrder = newSortingOrder;        
	    };
	
	    scope.selectedCls = function(column) {
	        if(column == scope.sort.sortingOrder){
	            return ('icon-chevron-' + ((scope.sort.reverse) ? 'down' : 'up'));
	        }
	        else{            
	            return'icon-sort' 
	        } 
	    };      
	  }
	}
});
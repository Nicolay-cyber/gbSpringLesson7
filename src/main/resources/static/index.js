angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
            $scope.productList = response.data;
            $scope.pageArray = $scope.generatePageArray(1, $scope.productList.totalPages);
        });
    };
    $scope.loadCart = function (){
        $http.get(contextPath + '/cart')
            .then(function (response){
               $scope.cart = response.data;
            });
    }
    $scope.addToCart = function (productId, productTitle, productCost){

        $http({
            url: contextPath + '/cart',
            method: 'POST',
            params: {
                productId: productId,
                productTitle: productTitle,
                productCost: productCost
            }
        }).then(function (response) {

            $scope.loadCart();
        });
    }

    $scope.removeFromCart = function (productId){
        $http.delete(contextPath + '/cart/' + productId)
            .then(function (response){
               $scope.loadCart();
            });
    };

    $scope.generatePageArray = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }
    $scope.changeCount = function (delta, id) {
        $http({
            url: contextPath + '/cart/change_count',
            method: 'GET',
            params: {
                delta: delta,
                id: id
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts(currentPageIndex);
            });
    }

    $scope.createProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function successCallBack(response) {
                $scope.newProduct = null;
                $scope.loadProducts(currentPageIndex);
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    }
    $scope.loadCart();
    $scope.loadProducts(currentPageIndex);

});
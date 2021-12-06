angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeCost = function (delta, id) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'GET',
            params: {
                delta: delta,
                id: id
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.createProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.loadProducts();
            })
    }

    $scope.costFilter = function () {
        $http({
            url: contextPath + '/products/cost_between',
            method: 'GET',
            params: {
                min: $scope.costFilter.min,
                max: $scope.costFilter.max
            }
        }).then(function (response) {
            $scope.ProductList = response.data;
        });
    }

    $scope.loadProducts();
});
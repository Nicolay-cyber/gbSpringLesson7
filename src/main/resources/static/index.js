angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
            $scope.ProductList = response.data.content;
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

    /*
        $scope.costFilter = function () {
            $http({
                url: contextPath + '/products/cost_between',
                method: 'GET',
                params: {
                    min: $scope.costFilter.min,
                    max: $scope.costFilter.max,
                    name_part: $scope.costFilter().name_part
                }
            }).then(function (response) {
                $scope.ProductList = response.data;
            });
        }
    */

    $scope.loadProducts();
});
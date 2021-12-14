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

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts(currentPageIndex);
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

    $scope.loadProducts(currentPageIndex);
});
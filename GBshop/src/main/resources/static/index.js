angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8190/api/v1';

    $scope.fillTable = function (pageIndex = 1, size = 2) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min: $scope.filter ? $scope.filter.min : null,
                max: $scope.filter ? $scope.filter.max : null,
                p: pageIndex,
                s: $scope.size
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.fillCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.CartContainer = response.data;
            });
    }

    $scope.addToCart = function (productId) {
        $http.post(contextPath + '/cart/' + productId)
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.deleteAllCart = function () {
        $http.delete(contextPath + '/cart/')
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.fillCart();
                $scope.fillTable();
            });
    };

    $scope.fillTable();
    $scope.fillCart();
});
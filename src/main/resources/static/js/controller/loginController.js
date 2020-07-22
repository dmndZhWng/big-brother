'use strict';

app.controller('loginController', ['$scope', '$window', '$location', '$http', 'userService', function($scope, $window, $location, $http, userService) {

    const serviceUrl = '/login';
    $scope.isLogging = false;

    $scope.login = function(username, password) {
        $scope.isLogging = true;
        let promise = userService.loginPromise(username, password);
        promise.then(
            function successCallback(response) {
                $scope.isLogging = false;
                if (response.data) {
                    userService.setCurrentUser(username);
                    let token = $window.btoa($scope.username + ":" + $scope.password);
                    $http.defaults.headers.common["Authorization"] = "Basic " + token;
                    $location.path("/home");
                }
            },
            function errorCallback(response) {
                $scope.isLogging = false;
                console.log(response);
            }
        );
    }
}]);

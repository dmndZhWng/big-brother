'use strict';

app.controller('homeController', ['$scope', '$http', '$window', 'userService', 'surveillanceService', function($scope, $http, $window, userService, surveillanceService) {

    const serviceUrl = '/surveillance'
    const userUrl = '/home';

    $scope.user = 'toto';
    $scope.adminRight = '';
    $scope.userRight = '';
    $scope.code64 = '';
    $scope.currentTs = '';

    $scope.isTakingPicture = false;
    $scope.isFire = false;

    loadUser();
    refreshSensor();

    $scope.takePicture = function() {
        $scope.isTakingPicture = true;
        let picturePromise = surveillanceService.takePicturePromise($scope.user);
        picturePromise.then(
            function successCallback(response) {
                $scope.isTakingPicture = false;
                console.log(response);
                $scope.code64 = response.data.base64Code;
                $scope.currentTs = response.data.currentTimeStamp;
            },
            function errorCallback(response) {
                $scope.isTakingPicture = false;
                console.log(response);
            }
        );
    }

    $scope.refreshSensor = function() {
        refreshSensor();
    }

    function refreshSensor() {
        senseFire();
    }

    function senseFire() {
        let fireRequest = buildGetRequest(serviceUrl + '/fire');
        $http(fireRequest).then(
            function successCallback(response) {
                $scope.isFire = response.data;
            },

            function errorCallback(response) {
                console.log(response);
            }
        );
    }

    function loadUser() {
        $scope.user = userService.getCurrentUser();

        let userRightPromise = userService.getUserRightPromise();
        userRightPromise.then(
            function successCallback(response) {
                $scope.userRight = response.data;
            },
            function errorCallback(response) {
                console.log(response);
            }
        );

        let adminRightPromise = userService.getAdminRightPromise();
        adminRightPromise.then(
            function successCallback(response) {
                $scope.adminRight = response.data;
            },
            function errorCallback(response) {
                console.log(response);
            }
        );
    }
}]);

'use strict';

app.controller('homeController', ['$scope', '$interval', '$sce', 'surveillanceService', function($scope, $interval, $sce, surveillanceService) {

    $scope.cameraIpAdress = '';
    $scope.isFire = false;

    refreshSensor();
    $interval(refreshSensor, 5000); // ms
    loadCameraIpAdress();

    $scope.refreshSensor = function() { // the refresh sensor button
        refreshSensor();
    }

    function refreshSensor() {
        senseFire();
    }

    function loadCameraIpAdress() {
        let cameraPromise = surveillanceService.cameraPromise();
        cameraPromise.then(
            function successCallback(response) {
                $scope.cameraIpAdress = $sce.trustAsResourceUrl(response.data);
            },
            function errorCallback(response) {
                console.log(response);
            }
        );
    }

    function senseMotion() {
        let motionPromise = surveillanceService.motionPromise();
        motionPromise.then(
            function successCallback(response) {
                $scope.isMoving = response.data;
            },
            function errorCallback(response) {
                console.log(response);
            }
        );
    }

    function senseFire() {
        let firePromise = surveillanceService.firePromise();
        firePromise.then(
            function successCallback(response) {
                $scope.isFire = response.data;
            },
            function errorCallback(response) {
                console.log(response);
            }
        );
    }
}]);

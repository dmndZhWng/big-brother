'use strict';

app.factory('surveillanceService', ['$http', function($http) {
    const surveillanceUrl = '/surveillance';

    let firePromise = function() {
        return $http(buildGetRequest(surveillanceUrl + '/fire'));
    }

    let cameraPromise = function() {
        return $http(buildGetRequest(surveillanceUrl + '/stream'))
    }

    return {
        firePromise: firePromise,
        cameraPromise: cameraPromise
    }
}]);
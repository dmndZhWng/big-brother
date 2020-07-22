'use strict';

app.factory('surveillanceService', ['$http', function($http) {
    const surveillanceUrl = '/home';

    let takePicturePromise = function(username) {
        let pictureRequest = {
            method: 'POST',
            url: baseUrl + "/surveillance/take-picture" + "?username=" + username
        };

        return $http(pictureRequest);
    }

    return {
        takePicturePromise: takePicturePromise
    }
}]);
'use strict';

app.factory('mechanicService', ['$http', function($http) {
    const mechanicUrl = '/meca';

    let movePromise = function(direction) {
        return $http(buildPostRequest(mechanicUrl + '/move?direction=' + direction, null));
    }

    return {
        movePromise: movePromise
    }
}]);
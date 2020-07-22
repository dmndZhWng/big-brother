'use strict';

app.factory('userService', ['$http', function($http) {

    const loginUrl = '/login';
    const homeUrl = '/home';

    let currentUsername = '';

    //
    let getCurrentUser = function() {
        return currentUsername;
    }

    //
    let setCurrentUser = function(username) {
       return currentUsername = username;
    }

    //
    let getUserRightPromise = function() {
        return $http(buildGetRequest(homeUrl + '/userRight'));
    }

    //
    let getAdminRightPromise = function() {
        return $http(buildGetRequest(homeUrl + '/adminRight'));
    }

    //
    let loginPromise = function(username, password) {

        let credential = {
            username: username,
            password: password
        }
        return $http(buildPostRequest(loginUrl, credential));
    }

    return {
        loginPromise: loginPromise,
        getUserRightPromise: getUserRightPromise,
        getAdminRightPromise: getAdminRightPromise,
        getCurrentUser: getCurrentUser,
        setCurrentUser: setCurrentUser
    };
}]);
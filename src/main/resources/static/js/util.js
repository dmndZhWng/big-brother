'use strict';

//
function buildGetRequest(url, requestData) {
    return {
        method: "GET",
        url: baseUrl + url,
        data: requestData
    }
}

//
function buildPostRequest(url, requestData) {
    return {
        method: 'POST',
        url: baseUrl + url,
        data: requestData
    }
}


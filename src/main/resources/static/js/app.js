"use strict";

var app = angular.module("big-brother", ["ngRoute", "ngSanitize"]);

app.config(function($routeProvider, $locationProvider, $httpProvider) {
  $locationProvider.hashPrefix('')
  $routeProvider
    .when("/", {
      templateUrl: "views/home.html",
      controller: "homeController"
    });

  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});


app.config(function($sceDelegateProvider) {  
  $sceDelegateProvider.resourceUrlWhitelist([
      'self',
//      'http://192.168.1.18*'
  ]);
});


var baseUrl = window.location.protocol + "//" + window.location.host + "/big-brother";

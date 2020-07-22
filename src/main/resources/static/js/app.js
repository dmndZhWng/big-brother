"use strict";

var app = angular.module("big-brother", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider, $httpProvider) {
  $locationProvider.hashPrefix('')
  $routeProvider
    .when("/", {
      templateUrl: "views/login.html",
      controller: "loginController"
    })
    .when("/home", {
      templateUrl: "views/home.html",
      controller: "homeController"
    });

  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});
var baseUrl = window.location.protocol + "//" + window.location.host + "/big-brother";

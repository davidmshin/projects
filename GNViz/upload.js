var app = angular.module('contentDemo1');
//UPLOAD, ROUTE
app.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/home', {
                    templateUrl: 'home.html',
                    controller: 'uploadCtrl'
                }).
                when('/mainPage', {
                    templateUrl: 'tempIndex.html',
                    controller: 'AppCtrl'
                }).
                otherwise({
                    redirectTo: '/home'
                });
        }]);

//Directive to parse the file
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel); //gets the fileModel object from attrs
            var modelSetter = model.assign; //
            element.bind('change', function(e){
                var changedFile = e.target.files[0];
                scope.$apply(function(){
                    modelSetter(scope, changedFile); //setting scope.myFile to the uploaded file

                });
            });
        }
    };
}]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl, successCommand, errorCommand){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
            successCommand();

        })
        .error(function(error){
            errorCommand(error);
        });
    }


}]);

app.controller('uploadCtrl', ['$http', '$scope', 'fileUpload', '$location', '$window', '$routeParams', '$rootScope', function($http, $scope, fileUpload, $location, $window, $routeParams, $rootScope){
    $scope.showAnimate = true;
    $rootScope.port = '8010';
    $scope.start = false;
    $scope.myFiles = false;
    $scope.goAway = true;


    $http.get('http://dev-vis.appistry.int:'+ $scope.port + '/variants/vcfs/').then(function(response) {
     $scope.fileNames = response.data;
    });

    $scope.fileNameChanged = function() {
        console.log("select file");
        $scope.start=true;
    };

    $scope.uploadFile = function(){
        $scope.goAway = true;
        $scope.myFiles = false;
        var file = $scope.myFile;
        var uploadUrl = "http://dev-vis.appistry.int:"+$rootScope.port+"/variants/upload/"; //unpolished way but saving port number in rootScope. $rootScope.port is essentially same for all the files
        if ($scope.myFile != null){
          $scope.showLoad = true;
          fileUpload.uploadFileToUrl(file, uploadUrl, function(){
              $scope.showLoad = false;
              $window.localStorage.setItem('loaded-vcf', file.name);
              $location.path('/mainPage');
              $location.url($location.path()); // resets query params
              $location.replace();
              console.log("success");
          }, function(error){
              console.log("error");
          });
      }else if ($scope.name != null){
        $scope.showLoad = false;
        $location.path('/mainPage');
        $location.replace();
      }
    };


//post file selected to endpoint
    $scope.postFile = function(){
        var uploadUrl = 'http://dev-vis.appistry.int:' + $scope.port +'/variants/load/';//unpolished way but saving port number in rootScope. $rootScope.port is essentially same for all the files

        $scope.showLoad = true;
        $http({method: "POST", url: uploadUrl, data: {'vcf_name':$scope.name}}).success(function(response) {
            $scope.showLoad = false;
            $window.localStorage.setItem('loaded-vcf', $scope.name);
            $location.path('/mainPage');
            $location.url($location.path()); // resets query params
            $location.replace();
            console.log("Posted Successful")
        }).error(function() {
            console.log("Post Failed");
        });
    };



    //select an existing file for post to endpoint
    $scope.select = function(name) {
        $scope.name =name;
        console.log($scope.name);
        $scope.myFiles = true;
    };



    $scope.cancelUpload = function(){
        $location.path('/mainPage');
        $location.url($location.path()); // resets query params
        $location.replace();
    }

    //load variant view page if previously uploaded file
    var loadedVcf = $window.localStorage.getItem('loaded-vcf');
    $scope.changeFile = ($routeParams.changeFile == 'true');
    $scope.loadVcfFromParam = function(vcf){
        $scope.name = vcf;
        $scope.showLoad = true;
        $scope.postFile();
    }
    if (loadedVcf != null && !$scope.changeFile){
        $scope.loadVcfFromParam(loadedVcf);
    }

}]);

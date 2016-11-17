var app = angular.module('contentDemo1', ['ngRoute','ngMaterial', 'ngMessages', 'ngMdIcons', 'ui.bootstrap', 'ngSanitize']);

//Main Controller
app.controller('AppCtrl', ['$scope', '$rootScope', '$sce', '$http', '$mdDialog', '$mdMedia', function($scope, $rootScope, $sce, $http, $mdDialog, $mdMedia) {
    // JSON urls
    $scope.method = 'GET';

    $rootScope.port = '8010';
    $scope.port = $rootScope.port;
    $scope.url ='http://dev-vis.appistry.int:'+ $scope.port +'/gnviz/tiers/'; // Cards & Variants Info
    $scope.urlGraph = 'http://dev-vis.appistry.int:'+ $scope.port +'/dataviz/tiers/'; // Histogram
    $scope.urlVariants = 'http://dev-vis.appistry.int:'+ $scope.port +'/gnviz/tiers/'; // Variants
    $scope.start = false;

    //Histogram
    $scope.histogram = false;
    $scope.methodP = 'POST';
    $scope.urlUpdate = 'http://dev-vis.appistry.int:'+ $scope.port +'/gnviz/filter/update/';
    $scope.isCollapsed = true; //Expand & Collapse

    // Get CF chart initial/update url depending on tier
    // Attach to controller since no need to attach to scope
    this.getCFUrl = function(tierNum){
        return 'http://dev-vis.appistry.int:'+ $scope.port +'/dataviz/cfchart/' + tierNum + "/";
    }
    var urlCFGraph = this.getCFUrl;

    //Pass Initial Histogram
    $scope.showLoad = true;
    $http({method: $scope.method, url: $scope.urlGraph}).then(function(response) {
        $scope.tier_distribution = $sce.trustAsHtml(response.data.tier_distribution);
    }, function(response) {
        console.log("Histogram initial http request error");
    });

    //Initial cfchart called on passTier()
    $scope.initialChart = function(tierNum) {
        $http({method: $scope.method, url: urlCFGraph(tierNum)}).then(function(response) {
            $scope.cfchart = $sce.trustAsHtml(response.data.cfchart);
        }
            , function(response) {
            console.log("Cross-filter chart initial http request error");
        });
    }

    //parseInt
    $scope.parseInt = function(str) {
        return parseInt(str);
    }
    //parseFloat
    $scope.parseFloat = function(str) {
        return parseFloat(str);
    }

    //Tweak histogram, hardcoded changes
    $scope.changeFilters = function() {
        var filters = {
            "filters": {
            "tierOne": {
              "CLNSIG": {
                "enum": ["4","5","6"]
              }
            },
            "tierFour": {
                "dbNSFP_Polyphen2_HVAR_pred": {
                    "enum": [0],
                    "type": 1
                },
                "dbNSFP_Polyphen2_HDIV_pred": {
                    "enum": [0],
                    "type": 1
                },
                "dbNSFP_MutationTaster_pred": {
                    "enum": [0],
                    "type": 1
                },
                "dbNSFP_LRT_pred": {
                    "enum": [0],
                    "type": 1
                },
                "dbNSFP_SIFT_pred": {
                    "enum": [0],
                    "type": 1
                }
            }
          }
        }

        $http({method: $scope.methodP, url: $scope.urlUpdate, data:filters}).then(function(response) {
            $scope.tier_distribution = $sce.trustAsHtml(response.data.tier_distribution);

        }, function(response) {
            console.log("Histogram http POST request error");

        });
    }

    $scope.isClicked = function() {
         $http({method: $scope.method, url: $scope.urlGraph}).then(function(response) {
             $scope.tier_distribution = $sce.trustAsHtml(response.data.tier_distribution);
         }, function(response) {
             console.log("Updated histogram http GET request error");

         });

    }

    /*CARDS AND VARIANTS*/
    //Load necessary data on load
    $scope.fetch=function() {
        // convert table acronym to display names
        var urlDisplayNames = 'http://dev-vis.appistry.int:'+$scope.port+'/gnviz/display-names/';
         $http({method: $scope.method, url: urlDisplayNames}).then(function(response) {
            var displayNames = response.data;
            $scope.displayName = function(acronym) {
                return displayNames[acronym];
            }
        }, function(response) {
             console.log("Display names http GET request error");
        });

        $scope.objectName = null;
        $scope.response = null;

        //Everything in golds
        $http({method: $scope.method, url: $scope.url}).then(function(response) {
            $scope.golds= response.data;
            $scope.data = response.data;
            $scope.name = response.data[0].name;
            $scope.description = response.data[0].description;

        }, function(response) {
            console.log("Cards http GET request error");

    }).finally(function(){
            $scope.showLoad = false;
        });
    }

    //Data input to objectName & http request for cfchart on 'Explore' button
    $scope.passTier = function(id) {
        $scope.objectName = id;
        $scope.initialChart(id.tier);

    }

    /*DIALOG,VARIANTS VIEW*/
    $scope.showTabDialog = function(ev) {
        self.dialogOpen = true;

        $mdDialog.show({
          scope:$scope,
          preserveScope:true,
          controller: 'DialogController',
          templateUrl: 'tabTemplate.tmpl.html',
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose:true,
          locals: {
              objectName : $scope.objectName,
              urlCFGraph : urlCFGraph
          }
        }).then(function(result) {
            self.dialogOpen = false;
        }, function(){
            self.dialogOpen = false;
        });

    };

}]);

//Dialog Controller-Variants View
app.controller('DialogController', ['$scope', '$sce', '$http', '$mdDialog', 'objectName', 'urlCFGraph', function($scope, $sce, $http, $mdDialog, objectName, _urlCFGraph_) {

        $scope.objectName = objectName;
        urlCFGraph = _urlCFGraph_
        $scope.methodP = 'POST';
        $scope.port = '8010';
        $scope.xAxis="ESP_MAF";
        $scope.yAxis="predScore";
        $scope.color = "None";
        $scope.size = "None";
        $scope.itemsPerPage = 10;
        $scope.currentPage = 1;
        $scope.maxSize = 5;
        $scope.highlightList = [];
        $scope.highlightIndex = 0;
        $scope.highlightPages = [];

        $scope.pageCount = function() {
            return Math.ceil($scope.objectName.variants.length / $scope.itemsPerPage);
        };

        $scope.updateChart = function(pageUpate, data) {
            $scope.currentPage   = pageUpate[0];
            $scope.highlightPages  = pageUpate;
            $scope.highlightList = data;
            $scope.highlightIndex = 0;
            $scope.$apply() // set up spec, pass etc FIXME. spy on scope.apply assert that called
        };

        $scope.increment = function(value){
          //update the page array to get the next page
          $scope.highlightIndex = Math.abs(($scope.highlightIndex + value) % $scope.highlightPages.length);
          $scope.currentPage = $scope.highlightPages[$scope.highlightIndex]
        };

        $scope.totalItems = $scope.objectName.variants.length;

        $scope.$watch('currentPage + itemsPerPage', function() { // FIXME scope.broadcast, broadcast string. rootscope.apply, tests that filtered objs set.
            var begin = (($scope.currentPage - 1) * $scope.itemsPerPage),
                end = begin + $scope.itemsPerPage;

            $scope.filteredObjects = $scope.objectName.variants.slice(begin, end);
        });

        $scope.clearSelection = function(){
          $scope.highlightList = [];
        };

        $scope.styleFunction = function (object){
          for (i=0; i < $scope.highlightList.length;i++){
            if (object.indexer == $scope.highlightList[i] ){
              return 'selected';
            }
          }
        }

        $scope.apply = function(tierNum) {
            var CFILTERS =  {
                    "xAxis" : $scope.xAxis,
                    "yAxis" : $scope.yAxis,
                    "color" : $scope.color,
                    "size" : $scope.size
                }


            $http({method: $scope.methodP, url: urlCFGraph(tierNum), data:CFILTERS}).then(function(response) {
                $scope.cfchart = $sce.trustAsHtml(response.data.cfchart);
                  alert("Updated to -->"+$scope.xAxis+", "+$scope.yAxis+", "+$scope.color+", "+$scope.size);
            }, function(response) {
              console.log("error posting");
            });
        }

        $scope.hide = function() {
            $mdDialog.hide();
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };

        $scope.answer = function(answer) {
            $mdDialog.hide(answer);
        };

}]);

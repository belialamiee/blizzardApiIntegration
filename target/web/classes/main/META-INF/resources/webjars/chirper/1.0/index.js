angular.module("itemData", []);
(function () {
    var controller = ["$scope", "$http", function ($scope, $http) {
        $scope.character = null;
        $scope.status = null;
        $scope.submit = function () {
            $scope.character = null;
            $scope.status = "Fetching...";

            //ideally we would abstract this out into it's own class, will do if we have time
          var url ="https://us.api.battle.net/wow/character/" + $scope.realmName + "/" + $scope.characterName + "?fields=items&locale=en_US&apikey=2hfxragxemjygj2eycpfuvverz923ze7";
            $http.get(url).then(
                // On success...
                function (result) {
                    //at this point we should save to the database and then update the current pages details


                    $scope.status = null;
                    $scope.character = result.data;

                },
                // On error...
                function (error) {
                    $scope.status = error.data;
                    //at present we console.log this but this should be removed when completed so that the users cannot see information they should not be privy to
                    console.log($scope.status);
                }
            );
        }
    }];

    angular.module("itemData").directive("itemFetcher", function () {
        return {
            restrict: "E",
            controller: controller,
            template: "<div class='form'>" +
            "  <div class='form-group row'>" +
            "    <label class='col-md-4'>Realm Name</label> <input class='col-md-2' ng-model='realmName' />" +
            "  </div>" +
            "  <div class='form-group row'>" +
            "    <label class='col-md-4'>Character Name</label> <input class='col-md-2' ng-model='characterName' />" +
            "  </div>" +
            "  <div class='form-group row'>" +
            "    <button ng-click='submit()' class='btn btn-primary'>" +
            "      Look up" +
            "    </button>" +
            "  </div>" +
            "</div>" +
            "<div class='alert' ng-if='status'>{{status}}</div>" +
            "<item-details  item='item'  ng-show='item'></item-details>"
        }
    });
}());
(function () {
    angular.module("itemData").directive("itemDetails", function () {
        return {
            restrict: "E",
            scope: {
                "item":"=",
                "hello":"="
            },
            template: "<p>                                                      " +
            "  <label>user ID</label>  " +
            "</p>                                                     "
        }
    });
}());
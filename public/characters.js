angular.module("characterData", []);
(function () {
    var controller = ["$scope", "$http", function ($scope, $http) {
        $scope.character = null;
        $scope.characterImage = null;
        $scope.status = null;
        $scope.statsArray = null;
        $http.get('/assets/stats.json').then(function (response) {
            $scope.statsArray = response.data;
        });

        // console.log($scope.statsArray);
        //set the Search Term and submit the form
        $scope.addSearch = function (rName, cName) {
            $scope.realmName = rName;
            $scope.characterName = cName;
            $scope.submit();
        };

        $scope.submit = function () {
            $scope.character = null;
            $scope.status = "Fetching...";
            //ideally we would abstract this out into it's own class, will do if we have time
            var url = "https://us.api.battle.net/wow/character/" + $scope.realmName + "/" + $scope.characterName + "?fields=items+stats&locale=en_US&apikey=2hfxragxemjygj2eycpfuvverz923ze7";
            $http.get(url).then(
                function (result) {
                    $scope.status = null;
                    //update this pages details
                    $scope.character = result.data;
                    console.log($scope.character);
                    //the data returned contains a thumbnail of our character, but we can modify the url we use to return a better image.
                    var thumbNail = result.data.thumbnail;
                    var charDetails = thumbNail.slice(0, -11);
                    $scope.character.characterImage = "http://render-api-us.worldofwarcraft.com/static-render/us/" + charDetails + "-profilemain.jpg";
                    $http.post('/add-search-items', {"realm": $scope.realmName, "name": $scope.characterName});

                },
                function (error) {
                    $scope.status = error.data;
                }
            );
        }
    }];

//Directive for displaying form for fetching characters
    angular.module("characterData").directive("characterFetcher", function () {
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
            "    <button ng-click='submit()' class='btn btn-primary form-submit'>" +
            "      Look up" +
            "    </button>" +
            "  </div>" +
            "</div>" +
            "<div class='alert' ng-if='status'>{{status}}</div>" +
            "<character-details character='character' stats-array='statsArray' ng-show='character'></character-details>"
        }
    });
}());


//Directive for rendering the character display
(function () {
    angular.module("characterData").directive("characterDetails", function () {
        return {
            restrict: "E",
            scope: {"character": "=", "statsArray": "=statsArray"},
            template: '<hr/>' +
            '<div class="panel panel-default">' +
            '<div class="panel-body">' +
            '<div class="row" ng-show="character">' +
            '<div class="col-md-2">' +
            '<div class="row"><label>Agility</label> {{character.stats.agi}} </div>' +
            '<div class="row"><label>Strength</label> {{character.stats.str}} </div>' +
            '<div class="row"><label>Stamina</label> {{character.stats.sta}} </div>' +
            '<div class="row"><label>Health</label> {{character.stats.health}} </div>' +
            '<div class="row"><label>Mastery</label> {{character.stats.mastery | number:0}} </div>' +
            '<div class="row"><label>Haste</label> {{character.stats.haste | number:0}} </div>' +
            '<div class="row"><label>Crit</label> {{character.stats.crit | number:0}} </div>' +
            '</div>' +
            '<div class="col-md-1">' +
            '<div class="row item">' +
            '<img ng-if="character.items.head" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.head.icon}}.jpg">' +
            '<stats-display ng-if="character.items.head" stats-array="statsArray" content="character.items.head"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.neck" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.neck.icon}}.jpg">' +
            '<stats-display ng-if="character.items.neck" stats-array="statsArray" content="character.items.neck"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.shoulder" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.shoulder.icon}}.jpg">' +
            '<stats-display ng-if="character.items.shoulder" stats-array="statsArray" content="character.items.shoulder"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.back" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.back.icon}}.jpg">' +
            '<stats-display ng-if="character.items.back" stats-array="statsArray" content="character.items.back"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.chest" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.chest.icon}}.jpg">' +
            '<stats-display ng-if="character.items.chest" stats-array="statsArray" content="character.items.chest"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.tabard" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.tabard.icon}}.jpg">' +
            '<stats-display ng-if="character.items.tabard" stats-array="statsArray" content="character.items.tabard"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.shirt" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.shirt.icon}}.jpg">' +
            '<stats-display ng-if="character.items.shirt" stats-array="statsArray" content="character.items.shirt"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.wrist" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.wrist.icon}}.jpg">' +
            '<stats-display ng-if="character.items.wrist" stats-array="statsArray" content="character.items.wrist"></stats-display>' +
            '</div>' +
            '</div>' +
            '<div class="col-md-6 character-display">' +
            '<div class="row">' +
            '<img width="100%"  ng-src="{{character.characterImage}}"/>' +
            '</div>' +
            '<div class="row">' +
            '<div class="col-md-offset-4 col-md-2 item">' +
            '<img ng-if="character.items.mainHand" width="100%" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.mainHand.icon}}.jpg"/>' +
            '<stats-display ng-if="character.items.mainHand" stats-array="statsArray" content="character.items.mainHand"></stats-display>' +
            '</div>' +
            '<div class="col-md-2 item">' +
            '<img ng-if="character.items.offHand" width="100%" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.offHand.icon}}.jpg"/>' +
            '<stats-display ng-if="character.items.offHand" stats-array="statsArray" content="character.items.offHand"></stats-display>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="col-md-1">' +
            '<div class="row item">' +
            '<img ng-if="character.items.hands" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.hands.icon}}.jpg">' +
            '<stats-display ng-if="character.items.hands" stats-array="statsArray" content="character.items.hands"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.waist" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.waist.icon}}.jpg">' +
            '<stats-display ng-if="character.items.waist" stats-array="statsArray" content="character.items.waist"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.legs" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.legs.icon}}.jpg">' +
            '<stats-display ng-if="character.items.legs"  stats-array="statsArray" content="character.items.legs"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.feet" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.feet.icon}}.jpg">' +
            '<stats-display ng-if="character.items.feet" stats-array="statsArray" content="character.items.feet"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.finger1" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.finger1.icon}}.jpg">' +
            '<stats-display ng-if="character.items.finger1" stats-array="statsArray" content="character.items.finger1"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.finger2" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.finger2.icon}}.jpg">' +
            '<stats-display ng-if="character.items.finger2" stats-array="statsArray" content="character.items.finger2"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.trinket1" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.trinket1.icon}}.jpg">' +
            '<stats-display ng-if="character.items.trinket1"  stats-array="statsArray" content="character.items.trinket1"></stats-display>' +
            '</div>' +
            '<div class="row item">' +
            '<img ng-if="character.items.trinket2" ng-src="http://media.blizzard.com/wow/icons/56/{{character.items.trinket2.icon}}.jpg">' +
            '<stats-display ng-if="character.items.trinket2"  stats-array="statsArray" content="character.items.trinket2"></stats-display>' +
            '</div>' +
            '</div>' +
            '<div class="col-md-2">' +
            '<div class="row"><label>Name</label> {{character.name}} </div>' +
            '<div class="row"><label>Realm</label> {{character.realm}} </div>' +
            '<div class="row"><label>Level</label> {{character.level}} </div>' +
            '<div class="row"><label>Achievements</label> {{character.achievementPoints}} </div>' +
            '<div class="row"><label>Avg Item Level</label> {{character.items.averageItemLevel}} </div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div ng-hide="character">' +
            'There is no data available for this search criteria' +
            '</div>'
        }
    });
}());


//Directive for displaying form for fetching characters
angular.module("characterData").directive("statsDisplay", function () {
    return {
        restrict: "E",
        scope: {"content": "=", "statsArray": "=statsArray"},
        template: "<div class='item-stats'>" +
        "{{content.name}}"+
        "<div ng-repeat='stats in content.stats'>" +
        "<p>{{statsArray.bonusStats[stats.stat].name}}" + " " + "{{stats.amount}} </p>" +
        "</div>" +
        "</div>"
    }
});
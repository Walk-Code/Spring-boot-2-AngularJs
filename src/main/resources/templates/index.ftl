<!DOCTYPE html>

<html lang="zh-CN" ng-app="demo">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet" />
    </head>
    <body>
        <div ui-view></div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/localforage/1.7.1/localforage.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ngStorage/0.3.11/ngStorage.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/UserService.js"></script>
        <script src="js/UserController.js"></script>
    </body>
</html>

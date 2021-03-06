app.service('DialogService', function ($mdDialog, $mdMedia) {
    this.show = function (controller, templateUrl, locals, event, success, cancel) {
        $mdDialog.show({
                controller: controller,
                templateUrl: templateUrl,
                parent: angular.element(document.body),
                clickOutsideToClose: true,
                locals: locals,
                targetEvent: event,
                fullscreen: $mdMedia('xs')
            })
            .then(function (result) {
                success && success(result);
            }, function (error) {
                cancel && cancel(error);
            });
    }
});
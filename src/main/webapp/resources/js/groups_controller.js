app.controller('groupsCtrl', function ($scope, $state, UserService, EditGroupDialogService, UserGroupsService, UserEntity, EventHandler) {
    $scope.userGroups = UserGroupsService.getGroups();
    $scope.groupInvites = UserGroupsService.getInvites();
    $scope.state = UserGroupsService.getState();
    $scope.user = UserService.get();

    $scope.accept = function (invite) {
        invite.loading = true;
        var userGroup = {user: invite.user, group: invite.group};
        invite.accepted = true;
        UserEntity.update({entity: "group_invites", id: invite.group.groupId}, invite,
            function () {
                UserEntity.save({entity: "groups"}, userGroup,
                    function (data) {
                        $scope.userGroups.push(data);
                        invite.loading = false;
                        $scope.groupInvites.splice($scope.groupInvites.indexOf(invite));
                        UserEntity.delete({entity: "group_invites", id: invite.group.groupId});
                    })
            });
    };

    $scope.cancel = function (invite) {
        invite.loading = true;
        invite.accepted = false;
        UserEntity.update({entity: "group_invites", id: invite.group.groupId}, invite,
            function () {
                UserEntity.delete({entity: "group_invites", id: invite.group.groupId},
                    function () {
                        invite.loading = false;
                        $scope.groupInvites.splice($scope.groupInvites.indexOf(invite));
                    });
            });
    };

    $scope.go = function (userGroup) {
        $state.transitionTo('user.group.main', {groupId: userGroup.group.groupId});
    };

    $scope.create = function (event) {
        EditGroupDialogService.show($scope.group, event,
            function (group) {
                var userGroup = {user: $scope.user, group: group};
                UserEntity.save({entity: 'groups'}, userGroup,
                    function (data) {
                        EventHandler.message('Group has been created');
                        $scope.userGroups.push(data);
                    });
            });
    };
});
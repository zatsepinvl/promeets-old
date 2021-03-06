package com.promeets.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.promeets.model.entity.Group;
import com.promeets.model.entity.User;
import com.promeets.model.entity.UserGroup;
import com.promeets.model.entity.UserGroupPK;

/**
 * Created by Vladimir on 12.04.2016.
 */
public interface UserGroupRepository extends CrudRepository<UserGroup, UserGroupPK> {
    @Query("select userGroup from UserGroup  userGroup where userGroup.id.user.id=(:userId) and userGroup.id.group.id=(:groupId)")
    UserGroup getUserGroupByUserIdAndGroupId(@Param("userId") long userId, @Param("groupId") long groupId);

    @Query("select userGroupPK.user from UserGroup userGroup where  userGroup.id.group.groupId=(:groupId) order by userGroup.id.user.userId")
    Iterable<User> getUsersByGroupId(@Param("groupId") long id);

    @Query("select userGroup from UserGroup userGroup where  userGroup.id.group.groupId=(:groupId) order by userGroup.id.user.userId")
    Iterable<UserGroup> getUserGroupsByGroupId(@Param("groupId") long id);

    @Query("select userGroupPK.group from UserGroup userGroup where  userGroup.id.user.userId=(:userId)")
    Iterable<Group> getGroupsByUserId(@Param("userId") long id);

    @Query("select userGroup from UserGroup userGroup where  userGroup.id.user.userId=(:userId)")
    Iterable<UserGroup> getUserGroupsByUserId(@Param("userId") long id);

    @Modifying
    @Query("delete from UserGroup userGroup where userGroup.id.group.groupId=(:groupId)")
    void deleteUserGroupsByGroupId(@Param("groupId") long id);
}

package com.zxr.dao;

import com.zxr.pojo.Role;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhaoxiangrui
 * @date 2020/11/12 22:24
 */
@CacheNamespace
public interface RoleMapper {

    @Select("select * from " +
            "sys_role r, sys_user_role ur " +
            "where r.id = ur.roleid and ur.userid = #{uid}")
    List<Role> selectRoleByUserId(Integer uid);
}

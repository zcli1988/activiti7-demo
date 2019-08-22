package org.activiti.examples.mapper;

import org.activiti.examples.db.RolePo;
import org.activiti.examples.db.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangkai
 * @since JDK8
 */
@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{0}")
    UserPo selectByUsername(String username);

    @Select("select * from t_user u join t_user_role ur on u.id = ur.user_id join t_role r on ur.role_id = r.id where u.id = #{0}")
    RolePo selectRoleByUserId(Integer userId);

    @Select("select url from t_role_permission rp join t_user_role ur on ur.role_id = rp.role_id where ur.user_id = #{0}")
    List<String> selectPermissionsByUserId(Integer userId);
}

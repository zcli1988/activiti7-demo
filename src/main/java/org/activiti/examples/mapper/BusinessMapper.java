package org.activiti.examples.mapper;

import org.activiti.examples.db.BusinessPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @author wangkai
 * @since JDK8
 */
@Mapper
public interface BusinessMapper {
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
    @Insert("insert into t_business values (null,#{apply_user_id},#{content},#{pass},#{createUser},now(),#{updateUser},now())")
    void insert(BusinessPo businessPo);
}

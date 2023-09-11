package com.jiayuliu.apiserver.mapper;

import com.jiayuliu.apiserver.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeptLogMapper {

    @Insert("insert into dept_log(create_time, description) VALUES (#{createTime}, #{description})")
        void insert(DeptLog deptLog);
}

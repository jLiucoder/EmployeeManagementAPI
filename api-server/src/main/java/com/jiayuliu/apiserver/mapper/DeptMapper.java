package com.jiayuliu.apiserver.mapper;

import com.jiayuliu.apiserver.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /***
     * look up all dept from db
     * @return list of dept
     */
    @Select("select * from dept")
    List<Dept> list();

    /***
     * look up single dept
     * @param id
     * @return
     */
    @Select("select * from dept where id=#{id}")
    Dept get(Integer id);

    /***
     * delete single dept
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    /***
     * insert single dept
     * @param dept
     */
    @Insert("insert into dept(name, create_time,update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Update("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}

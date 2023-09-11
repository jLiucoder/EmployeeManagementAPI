package com.jiayuliu.apiserver.service;

import com.jiayuliu.apiserver.pojo.Dept;

import java.util.List;

public interface DeptService {

    /***
     * look up info about all dept
     */
    List<Dept> list();

    /***
     * look up single dept
     * @param id
     */
    Dept get(Integer id);

    /***
     * delete single dept
     * @param id
     */
    void delete(Integer id);

    /***
     * insert single dept
     * @param dept
     */
    void add(Dept dept);

    /***
     * update single dept
     * @param id
     */
    void update(Integer id, Dept dept);
}

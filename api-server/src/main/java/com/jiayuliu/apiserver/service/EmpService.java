package com.jiayuliu.apiserver.service;

import com.jiayuliu.apiserver.pojo.Emp;
import com.jiayuliu.apiserver.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {


    PageBean page(Integer page, Integer pageSize, String name,
                  Short gender,LocalDate begin,LocalDate end);


    void delete(List<Integer> ids);

    void add(Emp emp);

    Emp get(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);


}

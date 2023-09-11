package com.jiayuliu.apiserver.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jiayuliu.apiserver.mapper.EmpMapper;
import com.jiayuliu.apiserver.pojo.Emp;
import com.jiayuliu.apiserver.pojo.PageBean;
import com.jiayuliu.apiserver.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public PageBean page(Integer page, Integer pageSize, String name,
                         Short gender,LocalDate begin,LocalDate end) {

        PageHelper.startPage(page, pageSize);

        List<Emp>list = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) list;


        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        if(emp.getGender() == 1){
            emp.setImage("springbootbucket-practice.s3.us-east-2.amazonaws.com/cb699cc9-9dfa-4732-88bb-76152c95c358.png");
        }else{
            emp.setImage("springbootbucket-practice.s3.us-east-2.amazonaws.com/87d4945b-351e-4776-adc7-0f3738b38753.png");
        }
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp get(Integer id) {
        return empMapper.get(id);
    }

    @Override
    public void update(Emp emp) {
        if(emp.getGender() == 1){
            emp.setImage("springbootbucket-practice.s3.us-east-2.amazonaws.com/cb699cc9-9dfa-4732-88bb-76152c95c358.png");
        }else{
            emp.setImage("springbootbucket-practice.s3.us-east-2.amazonaws.com/87d4945b-351e-4776-adc7-0f3738b38753.png");
        }
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }




}

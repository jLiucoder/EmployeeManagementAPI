package com.jiayuliu.apiserver.service.impl;

import com.jiayuliu.apiserver.mapper.DeptMapper;
import com.jiayuliu.apiserver.mapper.EmpMapper;
import com.jiayuliu.apiserver.pojo.Dept;
import com.jiayuliu.apiserver.pojo.DeptLog;
import com.jiayuliu.apiserver.service.DeptLogService;
import com.jiayuliu.apiserver.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public Dept get(Integer id) {
        return deptMapper.get(id);
    }

//    roll back on all exceptions, not only run time exceptions, only runtime exceptions are rolled back by default
    @Transactional(rollbackFor = Exception.class) // transactional annotation is used to rollback the transaction when exception occurs
    @Override
    public void delete(Integer id) {

//        use try finally to make sure the log is inserted even if the transaction is rolled back
        try{
            deptMapper.delete(id);
//            int i = 1/0; // throw an exception to test the rollback
            empMapper.deleteByDeptId(id); // delete the employees in the dept

        }finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("delete dept id: {}" + id);
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Integer id, Dept dept) {
        Dept temp = deptMapper.get(id);
        temp.setName(dept.getName());
        temp.setUpdateTime(LocalDateTime.now());
        deptMapper.update(temp);
    }


}

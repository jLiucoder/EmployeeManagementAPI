package com.jiayuliu.apiserver.controller;
import com.jiayuliu.apiserver.anno.Log;
import com.jiayuliu.apiserver.pojo.Emp;
import com.jiayuliu.apiserver.pojo.PageBean;
import com.jiayuliu.apiserver.pojo.Result;
import com.jiayuliu.apiserver.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping()
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        log.info("page: {}, pageSize: {}, name:{}, gender:{}, begin:{},end:{}", page, pageSize , name, gender, begin, end);
        PageBean pg = empService.page(page, pageSize, name, gender, begin, end);

        return Result.success(pg);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping()
    public Result add(@RequestBody Emp emp){
        log.info("emp:{}", emp);
        empService.add(emp);
        return Result.success();
    }

    @GetMapping("{id}")
    public Result get(@PathVariable Integer id){
        log.info("id:{}", id);
        Emp emp = empService.get(id);
        return Result.success(emp);
    }


    @Log
    @PutMapping()
    public Result update(@RequestBody Emp emp){
        log.info("emp:{}", emp);
        empService.update(emp);
        return Result.success();
    }
}

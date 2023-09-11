package com.jiayuliu.apiserver.controller;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.jiayuliu.apiserver.anno.Log;
import com.jiayuliu.apiserver.pojo.Dept;
import com.jiayuliu.apiserver.pojo.Result;
import com.jiayuliu.apiserver.service.DeptService;
import com.jiayuliu.apiserver.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /***
     * look up all depts
     * @return success
     */
    @GetMapping()
    public Result list() {
        log.info("look up all depts");

        List<Dept> list = deptService.list();

        return Result.success(list);
    }

    @Log
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Dept dept) {
        log.info("update single dept");

        deptService.update(id, dept);

        return Result.success();
    }

    /***
     * look up single dept
     * @param id
     * @return success
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("look up single dept {}", id);

        Dept dept = deptService.get(id);

        return Result.success(dept);
    }

    /***
     * delete single dept
     * @param id
     * @return success
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("delete single dept {}", id);

        deptService.delete(id);


        return Result.success();
    }

    /***
     * insert single dept
     * @param dept
     * @return success
     */
    @Log
    @PostMapping()
    public Result add(@RequestBody Dept dept) {
        log.info("insert single dept {}", dept);

        deptService.add(dept);

        return Result.success();
    }


}

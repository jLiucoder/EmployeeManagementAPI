package com.jiayuliu.apiserver.controller;

import com.jiayuliu.apiserver.pojo.Emp;
import com.jiayuliu.apiserver.pojo.Result;
import com.jiayuliu.apiserver.service.EmpService;
import com.jiayuliu.apiserver.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("emp login: {}", emp);
        Emp e = empService.login(emp);

//        login successfully
        if(e != null){
            Map<String, Object>claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

//            jwt has the current employee's id, name, username
            String jwt = JwtUtils.generateToken(claims);

            return Result.success(jwt);

        }
        return Result.error("wrong username or password");
    }
}

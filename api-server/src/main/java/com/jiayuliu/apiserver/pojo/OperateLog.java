package com.jiayuliu.apiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    private Integer id; //ID
    private Integer operateUser; //user ID
    private LocalDateTime operateTime; //time
    private String className; //class ame
    private String methodName; //method name
    private String methodParams; //params
    private String returnValue; //returned value
    private Long costTime; //time elapsed
}

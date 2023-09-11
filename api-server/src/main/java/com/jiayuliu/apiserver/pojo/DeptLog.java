package com.jiayuliu.apiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {

    private Integer id; //ID
    private LocalDateTime createTime; //creation time
    private String description; //description
}

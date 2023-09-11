package com.jiayuliu.apiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id; //ID
    private String name; //dept name
    private LocalDateTime createTime; //creation time
    private LocalDateTime updateTime; //update time
}

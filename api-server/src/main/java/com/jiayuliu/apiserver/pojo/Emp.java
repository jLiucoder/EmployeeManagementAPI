package com.jiayuliu.apiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id; //ID
    private String username; //username
    private String password; //password
    private String name; //name
    private Short gender; //gender , 1 male, 2 female
    private String image; //image url
    private Short job; //role , 1 head teacher , 2 lecture , 3 Head of Academics , 4 Head of Teaching and Research , 5 counselors
    private LocalDate entrydate; //entry date
    private Integer deptId; //department ID
    private LocalDateTime createTime; //creation time
    private LocalDateTime updateTime; //updated time
}

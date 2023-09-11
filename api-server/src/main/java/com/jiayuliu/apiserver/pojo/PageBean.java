package com.jiayuliu.apiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    private long total; //total count of records
    private List<Emp> rows; //page size
}

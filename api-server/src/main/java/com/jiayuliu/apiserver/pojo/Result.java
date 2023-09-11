package com.jiayuliu.apiserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//response code，1 success; 0 failure
    private String msg;  //description
    private Object data; //returned data

    //CUD successful response
    public static Result success(){
        return new Result(1,"success",null);
    }
    //R successful response
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //Failure response
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}

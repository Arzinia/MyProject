package com.imooc.sell.VO;

//http请求返回给前端的最外层对象

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 8848731630266574133L;
    //    错误码
    private Integer code;
//    提示信息
    private String msg;
//    返回的具体内容
    private T data;
}

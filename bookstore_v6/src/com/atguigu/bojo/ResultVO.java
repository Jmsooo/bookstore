package com.atguigu.bojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    private boolean flag;
    private String msg;
    private T data;

}

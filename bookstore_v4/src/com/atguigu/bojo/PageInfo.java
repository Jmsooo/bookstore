package com.atguigu.bojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {

    private Integer currentPage;
    private Long totalPage;
    private Long totalSize;
    private Integer pageSize;
    private List<T> data;

}

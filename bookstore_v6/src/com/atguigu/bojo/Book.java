package com.atguigu.bojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int bookId;
    private String bookName;
    private String author;
    private Double price;
    private int sales;
    private int stock;
    private String imgPath;

}

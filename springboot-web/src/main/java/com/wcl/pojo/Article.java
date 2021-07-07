package com.wcl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor //有参构造方法
@NoArgsConstructor  //无参构造方法
@Component
public class Article implements Serializable {
    private int id;
    private Date pubDate;
    private String title;
    private String author;
    private String content;

    public Article(int id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.pubDate = new Date();
    }
}

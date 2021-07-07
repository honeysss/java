package com.wcl.mapper;

import com.wcl.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ArticleMapper {
    //增加文章
    public void insertArticle(Article art);
    //查看某个文章信息
    public Article selectArticleById(int id);
    //修改文章
    public void updateArticleById(Article art);
    //删除文章
    public void deleteArticleById(int id);
    //通过显示条数和符合的文章标题查询文章
    public List<Article> selectArticles(HashMap hm);
    //通过文章名称模糊查询一共有多少条数据
    public int selectAllNums(String title);
    //查询所有文章
    public List<Article> selectAllArticles();
    //通过文章名称查询是否已经存在该文章
    public Article selectArticleByName(String title);
//    插入文章记录
    public void insertIntoArticle();
}

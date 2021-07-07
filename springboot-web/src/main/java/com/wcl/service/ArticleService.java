package com.wcl.service;


import com.wcl.mapper.ArticleMapper;
import com.wcl.pojo.Article;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleService {
    //封装DAO
//    @Resource(name="articleMapper")

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private  RedisTemplate<String, Object> redisTemplate;

//    测试redis
    public void testRedis() {
        Date date = new Date();
        Article art = new Article(99999,date,"钢铁是怎样炼成的","忘记了","我肚子也太疼了啊啊啊啊");
//        将数据保存到redis数据库中
        redisTemplate.opsForValue().set("article", art);
//        从redis中取出数据
        Article art2 = (Article) redisTemplate.opsForValue().get("article");
        redisTemplate.opsForValue().set("name","小明");
        redisTemplate.opsForValue().set("age",15);

        System.out.println(redisTemplate.opsForValue().get("name"));
        System.out.println(redisTemplate.opsForValue().get("age"));
    }

//插入文章记录
    public void insertIntoArticle() {
        articleMapper.insertIntoArticle();
    }

    //增加文章
    public void insertArticleService(Article art, String path) throws IOException {
//        //增加文章的时候在articles文件夹下创建一个以文章名称命名的.txt文件
//        String title = art.getTitle();
//        path += "\\" + title + ".txt";
//        System.out.println(path);
//        File file = new File(path);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        //把content中的内容写进这个文件
//        String content = art.getContent();
//		System.out.println("content===========" + content);
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        for (int i = 0; i < content.length(); i ++) {
//            bw.write(content.charAt(i));
//        }
//        bw.close();
//
//        //插入的时候把数据库中的content作为一个路径
//        art.setContent(path);
        articleMapper.insertArticle(art);
    }

    //查看某个文章信息
    public Article selectArticleByIdService(int id) {
        return articleMapper.selectArticleById(id);
    }

    //修改文章
    public void updateArticleService(Article art,  String path,String oldTitle) throws IOException {
        //修改文章的时候先获取到存放这个文章的文本路径

//        String path2 = path + "\\" + oldTitle + ".txt";
//        File file = new File(path2);
//        String title = art.getTitle();
//        //        如果两次名字不一致
//        if (!oldTitle.equals(title)) {
//            file.delete();
//            path2 = path + "\\" + title + ".txt";
//            file = new File(path2);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//        }
//
//        //把content中的内容写进这个文件
//        String content = art.getContent();
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        for (int i = 0; i < content.length(); i ++) {
//            bw.write(content.charAt(i));
//        }
//        bw.close();
//        //插入的时候把数据库中的content作为一个路径
//        art.setContent(path2);
        articleMapper.updateArticleById(art);
    }

    //删除文章
    public void deleteArticleService(int id, String content) throws UnsupportedEncodingException {
        //删除文章的时候把文章的路径也删除掉
        //如果文件存在的话删除
//        File file = new File(content);
//        if (file.exists()) {
//            file.delete();
//        }
        articleMapper.deleteArticleById(id);
    }

    //通过显示条数和符合的文章标题查询文章
    public List<Article> selectArticlesService(String title, int pageNum, int showNum) {

        int limit1 = (pageNum-1) * showNum;
        int limit2 = pageNum * showNum;
        HashMap hm = new HashMap();
        hm.put("title", title);
        hm.put("limit1", limit1);
        hm.put("limit2", limit2);
        return articleMapper.selectArticles(hm);

    }

    //通过文章名称和显示条数和符合的文章标题查询一共有多少页
    public int selectAllPageNumService(String title, int showNum) {
        int length = articleMapper.selectAllNums(title);
        int allPageNum = length % showNum == 0 ? length / showNum : length / showNum + 1;
        return allPageNum;
    }

    //通过文章名称模糊查询一共有多少条数据
    public int selectAllNumService(String title) {
        int allNum = articleMapper.selectAllNums(title);
        return allNum;
    }

    //查询所有
    public List<Article> selectAllArticlesService() {
        return articleMapper.selectAllArticles();
    }

    //通过文章名称查询是否已经存在该文章
    public Boolean selectArticleByNameService(String title) {
        Article article = articleMapper.selectArticleByName(title);
        boolean flag = true;
        if ("null".equals(article) || null == article) {
            flag = false;
        }
        return flag;
    }
}

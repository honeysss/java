package com.wcl.controller;

import com.wcl.pojo.Article;
import com.wcl.service.ArticleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.io.FileUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

@Controller
public class ArticleController {
    //封装Service
    @Resource(name="articleService")
    private ArticleService as;
    public ArticleService getAs() {
        return as;
    }
    public void setAs(ArticleService as) {
        this.as = as;
    }
    private int showNum = 5;

//    测试redis
    @RequestMapping("testRedis.action")
    public void testRedis() {
        as.testRedis();
    }


//    插入文章记录
    @RequestMapping("insertIntoArticle.action")
    public void insertIntoArticle() {
//        for(int i = 0; i < 10; i ++) {
//            as.insertIntoArticle();
//            System.out.println(11);
//        }
    }

    //模糊查询文章
    @RequestMapping("search.action")
    public String selectArticles(Model model,
                                 int pageNum,
                                 HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        String title = "";
        if (request.getParameter("title") != null && !"".equals(request.getParameter("title"))) {
            title = request.getParameter("title");
        }

        int allPageNum = as.selectAllPageNumService(title, showNum);
        int allNum = as.selectAllNumService(title);
        //如果当前页大于总页数 当前页减一
        if (pageNum > allPageNum && pageNum != 1) {
            pageNum--;
        }
//        获得所有的文章对象
        ArrayList<Article> articles = (ArrayList<Article>) as.selectArticlesService(title, pageNum, showNum);
//        循环遍历
//        for(Article art: articles) {
//            String path = null;
//            String str = null;
//            String content = "";
//            path= art.getContent();
//            File file = new File(path);
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            while((str = br.readLine()) != null) {
//                content += str;
//                content += "\n";
//            }
//            br.close();
//            art.setContent(content);
//        }
        model.addAttribute("articles", articles);
        model.addAttribute("allPageNum", allPageNum);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("title", title);
        model.addAttribute("allNum", allNum);
        String type = request.getParameter("type");
        model.addAttribute("type", type);
        return "index";
    }

    //查看文章详情
    @RequestMapping("detail.action")
    public String detail(Model model, int id) throws IOException {
        //        获得所有的文章对象
        Article article = as.selectArticleByIdService(id);
//        循环遍历
//        String path = null;
//        String str = null;
//        String content = "";
//        path= article.getContent();
//        File file = new File(path);
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        while((str = br.readLine()) != null) {
//            content += str;
//            content += "\n";
//        }
//        br.close();
//        article.setContent(content);
        model.addAttribute("article", article);
        return "article-detail";
    }

    //显示修改文章
    @RequestMapping("update-show.action")
    public String updateShow(Model model, int id, int pageNum, String title) throws IOException {

//        获得所有的文章对象
        Article article = as.selectArticleByIdService(id);
//        循环遍历
//        String path = null;
//        String str = null;
//        String content = "";
//        path= article.getContent();
//        File file = new File(path);
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        while((str = br.readLine()) != null) {
//            content += str;
//            content += "\n";
//        }
//        br.close();
//        article.setContent(content);
        model.addAttribute("article", article);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("title2", title);
        return "article-modify";
    }

    //确认修改文章
    @RequestMapping("confirmUpdate.action")
    public String confirmUpdate(Integer id, Date pubDate, int pageNum, String title2, String oldTitle, HttpServletRequest request) throws IOException {

        String path = System.getProperty("user.dir");
        path = path + "\\src\\main\\resources\\static\\articles";
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        Article article = new Article(id, pubDate, title, author, content);
        as.updateArticleService(article, path, oldTitle);
        return "forward:search.action?pageNum=" + pageNum + "&title=" + title2 + "&type=update";
    }

    //删除文章
    @RequestMapping("delete.action")
    public String delete(int id, int pageNum, String title, String content) throws UnsupportedEncodingException {
        String path = System.getProperty("user.dir");
        path = path + "\\src\\main\\resources\\static\\articles\\" + content + ".txt";
        as.deleteArticleService(id, path);
        return "forward:search.action?pageNum=" + pageNum + "&title=" + title + "&type=delete";
    }


    //发布文章
    @RequestMapping("publish.action")
    public String publash(HttpServletRequest request) throws IOException {
        String path = System.getProperty("user.dir");
        path = path + "\\src\\main\\resources\\static\\articles";
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        Article article = new Article(0, null, title, author, content);
        as.insertArticleService(article, path);
        return "forward:search.action?pageNum=1&title=&type=publish";
    }

    //通过文章名称查询是否已经存在该文章
    @RequestMapping("ifExistTheArticle.action")
    public void ifExistTheArticle(String title, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        boolean flag = as.selectArticleByNameService(title);
        out.print(flag);
        out.close();
    }

    //下载文章
    @RequestMapping("download.action")
    public ResponseEntity<byte[]> download(String title
    ) throws IOException {
        String path = System.getProperty("user.dir");
        path = path + "\\src\\main\\resources\\static\\articles\\" + title + ".txt";
        File file = new File(path);
        System.out.println(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachement", path);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }


}

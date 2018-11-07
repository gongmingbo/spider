package hounify.service;
////package hounify.controller;
//
//import com.geccocrawler.gecco.GeccoEngine;
//import com.geccocrawler.gecco.annotation.Gecco;
//import com.geccocrawler.gecco.annotation.HtmlField;
//
//import com.geccocrawler.gecco.request.HttpGetRequest;
//import com.geccocrawler.gecco.request.HttpRequest;
//
//import com.geccocrawler.gecco.spider.HtmlBean;
//
//
//import java.util.List;
//
//@Gecco(matchUrl="http://www.sina.com.cn/", pipelines="testPipline", downloader="myDownloder")
//public class Test implements HtmlBean {
//
//    private static final long serialVersionUID = -377053120283382723L;
//
//
//    public List<LiSpider> getUrls() {
//        return urls;
//    }
//
//    public void setUrls(List<LiSpider> urls) {
//        this.urls = urls;
//    }
//
//    @HtmlField(cssPath = "#syncad_0 li")
//    private List<LiSpider> urls;
//
//    @Override
//    public String toString() {
//        return "Test{" +
//                "urls=" + urls +
//                '}';
//    }
//
//    public static void main(String[] args) throws Exception {
//        HttpRequest request = new HttpGetRequest("http://www.sina.com.cn/");
//      //  request.setCharset("GBK");
//        GeccoEngine.create()
//                .classpath("hounify")
//                //开始抓取的页面地址
//                .start(request)
//                //开启几个爬虫线程
//                .thread(1)
//                .run();
//    }
//
//
//}
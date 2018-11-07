package hounify.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.sun.corba.se.impl.encoding.MarshalInputStream;
import hounify.Application;
import hounify.entity.Configuration;
import hounify.entity.SpiderContent;
import hounify.repository.ConfigurationRepository;
import hounify.repository.ContentRepository;
import hounify.repository.CrawlRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.management.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gongmingbo on 2018/4/24.
 * 爬取a标签管道
 */
@Service("newPipelines")
public class Pipeline extends JsonPipeline {
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private ContentRepository contentRepository;

    @Override
    public void process(JSONObject jo) {
        HttpRequest currRequest = HttpGetRequest.fromJson(jo.getJSONObject("request"));
        String currURL = currRequest.getUrl();
        String id = currRequest.getParameter("id");
        List<Configuration> configurationlist =
                configurationRepository.findAllByIdAndState(Long.parseLong(id), "1");
        JSONObject jsonObject = new JSONObject(jo);
        // 返回json的数组
        JSONArray jsonArray = jsonObject.getJSONArray("name");
        System.out.println(currURL + " ____爬取记录____：" + jsonArray.size());
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String name = (String) jsonObject2.get("title");
                String url = (String) jsonObject2.get("url");
                SpiderContent s = new SpiderContent();
                s.setContent_title(name);
                s.setUrl(url);
                s.setParent_url(currURL);
                s.setPublish_time(new Timestamp(new Date().getTime()));
                s.setTo_top_time(PiplineUntil.getTime());
                List<SpiderContent> crawllist = contentRepository.findAll();
               // List<SpiderContent> crawllist = contentRepository.findAllByContent_type("spider");
                Configuration c = configurationlist.get(0);
                boolean isExist = isBooleam(crawllist, url, name,c.getColumn_id(),c.getSite());
                if (!isExist) {                  
                        String key = getKeyWord(c.getKeyWord());
                        if (name.matches(key) && !StringUtils.isEmpty(name) && !StringUtils.isEmpty(url)) {
                        	    s.setColumn_id(Integer.parseInt(c.getColumn_id()));
							    s.setSite_id(Integer.parseInt(c.getSite()));
                                s.setContent_type("spider");
                                s.setResources_id(c.getUuid());
                                s.setPublisher_id("spider");
                            SpiderContent cc = contentRepository.save(s);
                            System.out.print("____成功保存记录____" + name + "\n" + url);
                        }
                    
                }
                //DeriveSchedulerContext.into(currRequest.subRequest(url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断该条记录是否已经存在数据库
     *
     * @param crawllist
     * @param url
     * @return
     */
    public boolean isBooleam(List<SpiderContent> crawllist, String url, String name,String colum,String site) {
        for (SpiderContent c : crawllist) {
            String strURL = c.getUrl();
            String strName = c.getContent_title();
            String columnId= c.getColumn_id()+"";
            String siteId = c.getSite_id()+"";
            if (url.equals(strURL) && name.equals(strName) && colum.equals(columnId)&& site.equals(siteId)) {
                return true;
            }
        }
        return false;
    }

    public String getKeyWord(String key) {
        String keyWord = ".*" + key + ".*";
        String keys2=keyWord.replaceAll(";|；", ".*");
        return keys2;

    }
}

package hounify.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 内容管理基本信息表
 */
@Entity
@Table(name = "hg_content_management_info")
public class SpiderContent {
    /**
     * id 主键 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    /**
     * 内容资源id 外键 关联点赞、评论、附件、图片、视频等
     */
    private String resources_id;
    /**
     * 内容标题
     */
    private String content_title;
    /**
     * 内容类型
     */
    private String content_type;
    /**
     * 内容概述
     */
    private String content_description;
    /**
     * 发布人id
     */
    private String publisher_id;
    /**
     * 发布人昵称
     */
    private String publisher_name;
    /**
     * 发布时间
     */
    private Timestamp publish_time;
    /**
     * 审核人id
     */
    private String reviewer_id;
    /**
     * 内容正文
     */
    private String content_body;
    /**
     * 站点id
     */
    private Integer site_id;
    /**
     * 栏目id
     */
    private Integer column_id;
    /**
     * 备注
     */
    private String remark;
    /**
     * 置顶时间
     */
    private Timestamp to_top_time;
    /**
     * 当前是否置顶
     */
    private int istop;
    /**
     * 新闻类型：如体育
     */
    private String news_type;
    /**
     * 新闻状态：默认为0，正常；删除为1，不显示
     */
    private int news_state;
    /**
     * 爬取到的URL
     */
    private String url;
    
    private String parent_url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParent_url() {
        return parent_url;
    }

    public void setParent_url(String parent_url) {
        this.parent_url = parent_url;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResources_id() {
        return resources_id;
    }

    public void setResources_id(String resources_id) {
        this.resources_id = resources_id;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getContent_description() {
        return content_description;
    }

    public void setContent_description(String content_description) {
        this.content_description = content_description;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public Timestamp getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Timestamp publish_time) {
        this.publish_time = publish_time;
    }

    public String getReviewer_id() {
        return reviewer_id;
    }

    public void setReviewer_id(String reviewer_id) {
        this.reviewer_id = reviewer_id;
    }

    public String getContent_body() {
        return content_body;
    }

    public void setContent_body(String content_body) {
        this.content_body = content_body;
    }

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    public Integer getColumn_id() {
        return column_id;
    }

    public void setColumn_id(Integer column_id) {
        this.column_id = column_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getTo_top_time() {
        return to_top_time;
    }

    public void setTo_top_time(Timestamp to_top_time) {
        this.to_top_time = to_top_time;
    }

    public int getIstop() {
        return istop;
    }

    public void setIstop(int istop) {
        this.istop = istop;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public int getNews_state() {
        return news_state;
    }

    public void setNews_state(int news_state) {
        this.news_state = news_state;
    }

    @Override
    public String toString() {
        return "SpiderContent{" +
                "id=" + id +
                ", resources_id='" + resources_id + '\'' +
                ", content_title='" + content_title + '\'' +
                ", content_type='" + content_type + '\'' +
                ", content_description='" + content_description + '\'' +
                ", publisher_id='" + publisher_id + '\'' +
                ", publisher_name='" + publisher_name + '\'' +
                ", publish_time=" + publish_time +
                ", reviewer_id='" + reviewer_id + '\'' +
                ", content_body='" + content_body + '\'' +
                ", site_id=" + site_id +
                ", column_id=" + column_id +
                ", remark='" + remark + '\'' +
                ", to_top_time=" + to_top_time +
                ", istop=" + istop +
                ", news_type='" + news_type + '\'' +
                ", news_state=" + news_state +
                '}';
    }
}

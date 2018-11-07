package hounify.entity;

import javax.persistence.*;
import java.awt.print.PrinterAbortException;
import java.sql.Timestamp;

/**
 * Created by gongmingbo on 2018/4/26.
 * 爬虫配置表
 */
@Entity
@Table(name = "hg_configuration")
public class Configuration {
	/**
	 * 主键id自增长
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;
    private String uuid;
    /**
     * 爬取地址
     */
    private String url;
    /**
     * 自定义爬取规则
     */
    private String my_rule;
    @Column(name = "content_type")
    private String contentType;
    /**
     * 站点
     */
    private String site;
    /**
     * 栏目
     */
    private String column_id;
    /**
     * 编辑配置人员id
     */
    private String user_id;
    /**
     * 爬取的内容
     */
    private String content;
    @Column(name = "key_word")
    /**
     * 爬取关键字
     */
    private String keyWord;
    private String state;
    
    public String getMy_rule() {
		return my_rule;
	}

	public void setMy_rule(String my_rule) {
		this.my_rule = my_rule;
	}

    public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getColumn_id() {
		return column_id;
	}

	public void setColumn_id(String column_id) {
		this.column_id = column_id;
	}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    private Timestamp timestamp;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyWord() {
        return keyWord;
    }


    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", state='" + state + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}

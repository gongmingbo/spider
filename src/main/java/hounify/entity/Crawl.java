package hounify.entity;

import javax.persistence.*;
import java.awt.print.PrinterAbortException;
import java.sql.Timestamp;


@Table
@Entity
public class Crawl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TestSequence")
    @SequenceGenerator(name = "TestSequence", sequenceName = "SEQ_Test", allocationSize = 1)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHrefurl() {
        return hrefurl;
    }

    public void setHrefurl(String hrefurl) {
        this.hrefurl = hrefurl;
    }

    private String name;
    private String hrefurl;

    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    @Column(name = "parent_url")
    private String parentUrl;

    public Timestamp getSave_time() {
        return save_time;
    }

    public void setSave_time(Timestamp save_time) {
        this.save_time = save_time;
    }

    private Timestamp save_time;

    @Override
    public String toString() {
        return "Crawl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hrefurl='" + hrefurl + '\'' +
                '}';
    }
}

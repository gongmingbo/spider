package hounify.repository;
import hounify.entity.SpiderContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gongmingbo on 2018/4/26.
 * 爬取到的类容持久层
 */
@Repository
public interface ContentRepository extends JpaRepository<SpiderContent, Long> {
	 
}

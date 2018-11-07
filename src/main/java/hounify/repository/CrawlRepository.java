package hounify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hounify.entity.Crawl;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CrawlRepository extends JpaRepository<Crawl, Long>{

}

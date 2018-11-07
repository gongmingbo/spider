package hounify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import hounify.service.StartPider;

/**
 * Created by gongmingbo on 2018/5/7.
 * springBoot初始化类
 */
@Component
@Order(1)
public class Myinitspider implements CommandLineRunner {
    @Autowired
    private StartPider startPider;
    @Override
    public void run(String... args) throws Exception {
        startPider.startSpider();
    }
}

//package hounify.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

//package hounify.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.alibaba.fastjson.JSON;
//import com.geccocrawler.gecco.GeccoEngine;
//import com.geccocrawler.gecco.dynamic.DynamicGecco;
//import com.geccocrawler.gecco.request.HttpGetRequest;
//import com.geccocrawler.gecco.request.HttpRequest;
//import com.geccocrawler.gecco.scheduler.SchedulerContext;
//import com.geccocrawler.gecco.spider.HrefBean;
//import com.geccocrawler.gecco.spring.SpringGeccoEngine;
//import com.geccocrawler.gecco.spring.SpringPipelineFactory;
//import hounify.entity.Configuration;
//import hounify.entity.Crawl;
//import hounify.repository.ConfigurationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import hounify.repository.CrawlRepository;
//import sun.text.resources.cldr.af.FormatData_af_NA;
//
//@Controller
//public class MyController {
//	@Autowired
//	SpringPipelineFactory springPipelineFactory;
//	@Autowired
//	ConfigurationRepository configurationRepository;
//
//	public String start() {
//		List<Configuration> configs = configurationRepository.findByState("1");
//		System.out.println(configs);
//		List<HttpRequest> urls = new ArrayList<HttpRequest>();
//		if (configs.size() > 0) {
//			for (Configuration c : configs) {
//				HttpRequest request = new HttpGetRequest(c.getUrl());
//				urls.add(request);
//				if (c.getContent().endsWith("a")) {
//					DynamicGecco.html()
//					        .gecco(new String[] { c.getUrl() }, "myDownloder", 5000, "newPipelines")
//							.requestField("request").request().build()
//							.listField("name", HrefBean.class)
//							.csspath(c.getContent()).build().register();
//
//				} else {
//					String div = c.getContent();
//					String endElement = div.substring(div.lastIndexOf(" "));
//					DynamicGecco.html()
//					        .gecco(new String[] { c.getUrl() }, "myDownloder", 5000, "customPipeline")
//							.requestField("request").request().build()
//							.listField("list", DynamicGecco.html()
//							.stringField("endElement").csspath(endElement).build().register())
//							.csspath(c.getContent()).build().register();
//				}
//			}
//		}
//		GeccoEngine
//		       .create()
//		       .pipelineFactory(springPipelineFactory)
//		       .classpath("hounify")
//		       .start(urls)
//		       .interval(50000)
//			   .thread(urls.size())
//				// .loop(true)
//			   .start();
//		return "ok";
//
//	}
//
//	@RequestMapping("/test")
//	@ResponseBody
//	public Object getContent(HttpServletRequest httpRequest) {
//		String name=httpRequest.getParameter("name");
//		Map<String, String> map=new HashMap<String, String>();
//         map.put("name", name);
//         
//		return map ;
//	}
//}

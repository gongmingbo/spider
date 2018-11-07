package hounify.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;

import hounify.entity.Configuration;
import hounify.entity.SpiderContent;
import hounify.repository.ConfigurationRepository;
import hounify.repository.ContentRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.bcel.generic.NEW;
import org.hibernate.annotations.GenerationTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by gongmingbo on 2018/5/2.
 * 自定义管道
 */
@Service("customPipeline")
public class CustomPipeline extends JsonPipeline {
	@Autowired
	private ConfigurationRepository configurationRepository;
	@Autowired
	private ContentRepository contentRepository;

	@Override
	public void process(JSONObject jo) {
		System.out.println(jo);
		HttpRequest currRequest = HttpGetRequest.fromJson(jo.getJSONObject("request"));
		String id = currRequest.getParameter("id");
		String currURL = currRequest.getUrl();
		List<Configuration> configurationlist = configurationRepository.findAllByIdAndState(Long.parseLong(id), "1");
		JSONObject jsonObject = new JSONObject(jo);
		// 返回json的数组
		JSONArray jsonArray = jsonObject.getJSONArray("list");
		System.out.println(currURL + " ____爬取记录____：" + jsonArray.size());
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				String name = (String) jsonObject2.get("endElement");
				SpiderContent s = new SpiderContent();
				s.setParent_url(currURL);
				s.setTo_top_time(PiplineUntil.getTime());
				s.setPublish_time(getTime(name));
				List<SpiderContent> crawllist = contentRepository.findAll();
				// List<SpiderContent> crawllist =
				// contentRepository.findAllByContent_type("spider");
				Configuration c = configurationlist.get(0);
				boolean isExist = isBooleam(crawllist, name, c.getColumn_id(), c.getSite());
				if (!isExist) {
					String key = getKeyWord(c.getKeyWord());
					if (name.matches(key) && !StringUtils.isEmpty(name)) {
						s.setContent_type("spider");
						s.setColumn_id(Integer.parseInt(c.getColumn_id()));
						s.setSite_id(Integer.parseInt(c.getSite()));
						s.setResources_id(c.getUuid());
						s.setPublisher_id("spider");
						// 自定义解析
						s.setContent_title(name);
						if (!StringUtils.isEmpty(c.getMy_rule())) {
							Map<String, String> map = getUrlAadName(name, c.getMy_rule());
							s.setContent_title(map.get("name"));
							String url = map.get("url");
							if (url.startsWith("http")) {
								s.setUrl(url);
								isExist = isBooleam(crawllist, url, map.get("name"), c.getColumn_id(), c.getSite());
							} else {
								String urlAddress = currURL.substring(0, currURL.indexOf("/", 10));
								s.setUrl(urlAddress + url);
								isExist = isBooleam(crawllist, urlAddress + url, map.get("name"), c.getColumn_id(),
										c.getSite());
							}

						}
						if (!isExist) {
							SpiderContent cc = contentRepository.save(s);
							System.out.print("____成功保存记录____" + name);
						}
					}
				}
				// DeriveSchedulerContext.into(currRequest.subRequest(url));
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
	public boolean isBooleam(List<SpiderContent> crawllist, String name, String colum, String site) {
		for (SpiderContent c : crawllist) {
			String strName = c.getContent_title();
			String columnId = c.getColumn_id() + "";
			String siteId = c.getSite_id() + "";
			if (name.equals(strName) && colum.equals(columnId) && site.equals(siteId)) {
				return true;
			}
		}
		return false;
	}

	public boolean isBooleam(List<SpiderContent> crawllist, String url, String name, String colum, String site) {
		for (SpiderContent c : crawllist) {
			String strURL = c.getUrl();
			String strName = c.getContent_title();
			String columnId = c.getColumn_id() + "";
			String siteId = c.getSite_id() + "";
			if (url.equals(strURL) && name.equals(strName) && colum.equals(columnId) && site.equals(siteId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 解析关键字
	 */
	public String getKeyWord(String key) {
		String keyWord = ".*" + key + ".*";
		String keys2 = keyWord.replaceAll(";|；", ".*");
		return keys2;

	}
		/**
		 * 自定义解析URL
		 */
	public static Map<String, String> getUrlAadName(String str, String regexUrl) {
		Map<String, String> map = new HashMap<String, String>();
		String regex = "<a.*?/a>";
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(str);
		while (mt.find()) {
			System.out.println(mt.group());
			String s2 = ">.*?</a>";// 标题部分
			Pattern pt2 = Pattern.compile(s2);
			Matcher mt2 = pt2.matcher(mt.group());
			while (mt2.find()) {
				String name = mt2.group().replaceAll(">|</a>", "");
				System.out.println("标题：" + name);
				map.put("name", name);
			}
			Pattern pt3 = Pattern.compile(regexUrl);
			Matcher mt3 = pt3.matcher(mt.group());
			while (mt3.find()) {
				String url = mt3.group().replaceAll("\'|amp;", "");
				System.out.println("网址：" + url);
				map.put("url", url);
			}
		}
		return map;
	}
 
	public Timestamp getTime(String currURL) {
		try {
			String regex = ">.*/span>";
			Pattern pt = Pattern.compile(regex);
			Matcher mt = pt.matcher(currURL);
			while (mt.find()) {
				String name = mt.group().replaceAll(">|</span>", "");
				SimpleDateFormat fDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = fDateFormat.parse(name);
				return new Timestamp(date.getTime());
			}
			return new Timestamp(new Date().getTime());
		} catch (Exception e) {
			return new Timestamp(new Date().getTime());
		}

	}
}
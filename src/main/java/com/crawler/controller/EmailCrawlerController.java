package com.crawler.controller;

import com.crawler.work.EmailCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EmailCrawlerController {
    private static Logger logger = LoggerFactory.getLogger(EmailCrawlerController.class);

    @RequestMapping(value = {"/searchEmail.do"})
    @ResponseBody
    public String emailCrawler(HttpServletRequest request) {
        String url = request.getParameter("url");
        if (StringUtils.isEmpty(url)) {
            return "参数不能为空!";
        }
        EmailCrawler crawler = new EmailCrawler("crawl", true, url);
        crawler.getConf().setTopN(5);
        try {
            crawler.start(3);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return crawler.emailArr;
    }
}
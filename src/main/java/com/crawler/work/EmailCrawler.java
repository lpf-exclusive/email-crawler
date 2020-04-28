package com.crawler.work;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.crawler.utils.HtmlAnalysisUtils;
import org.springframework.util.StringUtils;

public class EmailCrawler extends BreadthCrawler {

    public String emailArr;

    public EmailCrawler(String crawlPath, boolean autoParse, String website) {
        super(crawlPath, autoParse);

        addSeed(website);

        addRegex("[a-zA-z]+://[^\\s]*");
        /*不要爬取jpg|png|gif*/
        addRegex("-.*\\.(jpg|png|gif).*");
        /*不要爬取包含"#"的链接*/
        addRegex("-.*#.*");

    }

    public static void main(String[] args) throws Exception {
        EmailCrawler crawler = new EmailCrawler("crawl", true, "https://www.instructables.com");
        crawler.getConf().setTopN(5);
        crawler.start(5);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        System.out.println("visiting:" + page.url() + "\tdepth=" + page.meta("depth"));
        String email = HtmlAnalysisUtils.getEmail(page.html());
        if (StringUtils.isEmpty(emailArr)) {
            emailArr = email;
        } else {
            emailArr = emailArr + ";" + email;
        }
    }

}
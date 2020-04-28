package com.crawler.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlAnalysisUtils {

    /**
     * <p>
     * 方法名称：getEmail
     * </p>
     * <p>
     * 方法描述：匹配邮箱
     * </p>
     *
     * @param html
     * @return email
     * @author Administrator
     * @since 2018年12月18日
     * <p>
     * history 2018年12月18日 Administrator 创建
     * <p>
     */
    public static String getEmail(String html) {
        html = html.replaceAll("&nbsp;", "");
        Document document = Jsoup.parse(html);
        String body = document.body().toString();
        HashSet<String> hashSet = new HashSet<>();
        String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        while (matcher.find()) {
            String matchWord = matcher.group(0).toLowerCase();
            if (matchWord.length() > 50) {
                continue;
            }
            hashSet.add(matchWord);
        }
        StringBuilder builder = new StringBuilder();
        for (String email : hashSet) {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append(email);
        }
        return builder.toString();
    }
}
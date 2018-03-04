package com.spider.parser;

import org.jsoup.select.Elements;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/12/7.
 */
public interface ParserCallBack {
    String selectEle(Elements document);
}

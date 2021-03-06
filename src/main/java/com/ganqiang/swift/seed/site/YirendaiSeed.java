package com.ganqiang.swift.seed.site;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ganqiang.swift.fetch.site.CommonLinkFetcher;
import com.ganqiang.swift.net.http.HttpHelper;
import com.ganqiang.swift.parse.site.YirendaiParser;
import com.ganqiang.swift.seed.AbstractSeed;
import com.ganqiang.swift.seed.InsideSeed;

public final class YirendaiSeed extends AbstractSeed implements InsideSeed
{
  private static final Logger logger = Logger.getLogger(YirendaiSeed.class);

  /**
   * http://www.yirendai.com/LenderInvest/applyInfoListPage.action?pager.offset=10
   * &isJYD=&iapproveNo=&currRate=&iapproveAmt=&productType=
   */
  @Override
  public int loadPageSize()
  {
    String html = HttpHelper.getContentFromUrl("http://www.yirendai.com/loan/list/1");
    Document doc = Jsoup.parse(html);
    Elements elements = doc.select("a[class=m-pageNum]");
    int totalpage = Integer.valueOf(elements.get(elements.size() - 1).text());
    logger.info("Loading [yirendai] list total page number is ["+ totalpage +"]");
    return Integer.valueOf(totalpage);
  }

  @Override
  public String getHomePage()
  {
    return "http://www.yirendai.com/";
  }

  @Override
  public String getPreListLink()
  {
    return "http://www.yirendai.com/loan/list/";
  }

  @Override
  public String getPreDetailLink()
  {
    return "http://www.yirendai.com/loan/view/";
  }

  @Override
  public String getLogo()
  {
    return "http://www.yirendai.com/ccms/images/header_2013/header_2013_logo.png";
  }

  @Override
  public String getPlatform()
  {
    return "宜人贷";
  }

  @Override
  protected void setFetcher()
  {
    this.fetcher = new CommonLinkFetcher();
  }

  @Override
  protected void setParser()
  {
    this.parser = new YirendaiParser();
  }

  @Override
  public String getDetailLinkMark()
  {
    return "/loan/view/";
  }


}

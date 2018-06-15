package com.upload;

/**
 * 断点续传
 * @author raolesong
 * 2018年1月16日 下午6:19:36
 */
public class TestMethod
{
	public TestMethod()
	{ // /xx/weblogic60b2_win.exe
		try
		{
			SiteInfoBean bean = new SiteInfoBean(
					"http://i1.cximg.com/images/84bd33606b889b25/20180105/f8954cba07d4458b89de3fef2101d83b.png", "d:\\",
					"1.png", 5);
			// SiteInfoBean bean = new
			// SiteInfoBean("http://localhost:8080/down.zip","L:\\temp","weblogic60b2_win.exe",5);
			SiteFileFetch fileFetch = new SiteFileFetch(bean);
			fileFetch.start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new TestMethod();
	}
}
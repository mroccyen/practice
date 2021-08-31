package com.ruyuan.twelve.juc.week06;


/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 * 1.将短网址转为长网址
 * 2.如果转换失败了，将请求的来源进行进行缓存，方便后续来分析问题
 **/
public class ArticleAccessHandler {

    /**
     * 假设拦截到一个请求某个文章的请求
     *
     * @param articleAccessInfo 文章访问信息
     * @return 结果
     */
    public CommonResponse intercept(ArticleAccessInfo articleAccessInfo) {
        // 将短号转为长号
        String targetUrl = null;
        try {
            targetUrl = convertShortNumber(articleAccessInfo.getShortUrl());
        } catch (Exception e) {
            System.out.println("短网址：" + articleAccessInfo.getShortUrl() + "转换长网址失败，调用proxy代理类的异步方法持久化数据");
            // 通过主动模式的代理类来持久化请求
            AsyncRequestPersistence.getInstance().store(articleAccessInfo);
        }

        return CommonResponse.success(targetUrl);
    }

    /**
     * 将短网址转为长网址
     *
     * @param shortUrl 短网址
     * @return
     */
    private String convertShortNumber(String shortUrl) {
        // 查询数据库中短网址对应的长网址
        throw new NullPointerException("转短网址失败");
    }
}
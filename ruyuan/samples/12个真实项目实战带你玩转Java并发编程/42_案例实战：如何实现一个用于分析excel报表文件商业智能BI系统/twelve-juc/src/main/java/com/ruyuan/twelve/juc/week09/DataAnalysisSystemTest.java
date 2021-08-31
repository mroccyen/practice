package com.ruyuan.twelve.juc.week09;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 **/
public class DataAnalysisSystemTest {

    public static void main(String[] args) throws InterruptedException {
        DataAnalysisSystem dataAnalysisSystem = new DataAnalysisSystem();
        dataAnalysisSystem.init();

        dataAnalysisSystem.exportFile("123.excel");
        dataAnalysisSystem.exportFile("456.excel");
        dataAnalysisSystem.exportFile("789.excel");

        Thread.sleep(100);
    }
}
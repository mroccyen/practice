package com.ruyuan.twelve.juc.week05;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:购房合同文件
 **/
public class HouseContractFile {

    /**
     * 购房合同文件名称
     */
    private String fileName;

    /**
     * 购房人名称
     */
    private String buyerName;

    /**
     * 卖房人的名称
     */
    private String sellName;

    /**
     * 文件内容
     */
    private byte[] file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "HouseContractFile{" +
                "fileName='" + fileName + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", sellName='" + sellName + '\'' +
                '}';
    }
}
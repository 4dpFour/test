package com.nuaa.dataplatform.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contract implements Serializable {
    private int id;
    private String url;
    private String contractNo;
    private String contractName;
    private String projectNo;
    private String projectName;
    private String purchaser;
    private String purchaserTelNo;
    private String supplier;
    private String supplierTelNo;
    private String subjectName;
    private String subjectUnitPrice;
    private String contractValue;
    private String announceDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaserTelNo() {
        return purchaserTelNo;
    }

    public void setPurchaserTelNo(String purchaserTelNo) {
        this.purchaserTelNo = purchaserTelNo;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierTelNo() {
        return supplierTelNo;
    }

    public void setSupplierTelNo(String supplierTelNo) {
        this.supplierTelNo = supplierTelNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectUnitPrice() {
        return subjectUnitPrice;
    }

    public void setSubjectUnitPrice(String subjectUnitPrice) {
        this.subjectUnitPrice = subjectUnitPrice;
    }

    public String getContractValue() {
        return contractValue;
    }

    public void setContractValue(String contractValue) {
        this.contractValue = contractValue;
    }

    public String getAnnounceDate() {
        return announceDate;
    }

    public void setAnnounceDate(String announceDate) {
        this.announceDate = announceDate;
    }

    public boolean allFieldEmpty() {
        return url == null &&
               contractNo == null &&
               contractName == null &&
               projectNo == null &&
               projectName == null &&
               purchaser == null &&
               purchaserTelNo == null &&
               supplier == null &&
               supplierTelNo == null &&
               subjectName == null &&
               subjectUnitPrice == null &&
               contractValue == null &&
               announceDate == null;
    }

    public boolean indexEmpty() {
        return (contractNo == null || contractNo.equals("")) && (contractName == null || contractName.equals(""));
    }

    public Contract() {
    }

    public Contract(String url, String contractNo, String contractName, String projectNo, String projectName, String purchaser, String purchaserTelNo, String supplier, String supplierTelNo, String subjectName, String subjectUnitPrice, String contractValue, String announceDate) {
        this.url = url;
        this.contractNo = contractNo;
        this.contractName = contractName;
        this.projectNo = projectNo;
        this.projectName = projectName;
        this.purchaser = purchaser;
        this.purchaserTelNo = purchaserTelNo;
        this.supplier = supplier;
        this.supplierTelNo = supplierTelNo;
        this.subjectName = subjectName;
        this.subjectUnitPrice = subjectUnitPrice;
        this.contractValue = contractValue;
        this.announceDate = announceDate;
    }

    public Contract(Map<String, String> constructMap) {
        this.url = constructMap.get("url");
        this.contractNo = constructMap.get("contractNo");
        this.contractName = constructMap.get("contractName");
        this.projectNo= constructMap.get("projectNo");
        this.projectName = constructMap.get("projectName");
        this.purchaser = constructMap.get("purchaser");
        this.purchaserTelNo = constructMap.get("purchaserTelNo");
        this.supplier = constructMap.get("supplier");
        this.supplierTelNo = constructMap.get("supplierTelNo");
        this.subjectName = constructMap.get("subjectName");
        this.subjectUnitPrice = constructMap.get("subjectUnitPrice");
        this.contractValue = constructMap.get("contractValue");
        this.announceDate = constructMap.get("announceDate");
    }

    public Contract(String content) {
        this.contractNo = matchData(content, "合同编号");
        this.contractName = matchData(content, "合同名称");
        this.projectNo = matchData(content, "项目编[号码]");    //【项目编号】有时也叫【项目编码】。。
        this.projectName = matchData(content, "项目名称");
        this.purchaser = matchData(content, "采购人");
        this.purchaserTelNo = matchData(content, "联系方式");
        content = content.replaceFirst("联系方式", "");    //两个联系方式的匹配头一样，删掉第一个
        this.supplier = matchData(content, "供应商");
        this.supplierTelNo = matchData(content, "联系方式");
        this.subjectName = matchData(content, "主要标的名称");
        this.subjectUnitPrice = matchData(content, "主要标的单价");
        this.contractValue = matchData(content, "合同金额");
        this.announceDate = matchData(content, "合同公告日期");
        //把 x年x月x日 改成 x-x-x
        if (announceDate != null && !announceDate.equals("")) {
            announceDate = announceDate.replace('年', '-').replace('月', '-').replace('日', '\0');
        } else {
            announceDate = "1970-01-01";
        }
    }

    /** 去除首尾的空格、下划线、冒号 */
    public String clearTrim(String str) {
        char t; int lenth = 0;
        for (int i = 0; i < str.length(); i++) {
            t = str.charAt(i);
            if (t == ' ' || t == '_' || t == '：' || t == ':' || t == '　') {
                lenth++;
            } else {
                break;
            }
        }
        str = str.substring(lenth);
        lenth = str.length();
        for (int i = str.length() - 1; i >= 0; i--) {
            t = str.charAt(i);
            if (t == ' ' || t == '_' || t == '：' || t == ':' || t == '　') {
                lenth--;
            } else {
                break;
            }
        }
        str = str.substring(0, lenth);
        return str;
    }

    private String matchData(String content, String patternString) {
        Matcher matcher = Pattern.compile("(?<=" + patternString + ".{0,100}[:：\\s])[\\S]+").matcher(content);
        return matcher.find() ? clearTrim(matcher.group(0)) : null;
    }

    public String toString() {
        return "合同编号：" + contractNo + "\n" +
                "合同名称：" + contractName + "\n" +
                "项目编号：" + projectNo + "\n" +
                "项目名称：" + projectName + "\n" +
                "采购人：" + purchaser + "\n" +
                "联系方式：" + purchaserTelNo + "\n" +
                "供应商：" + supplier + "\n" +
                "联系方式：" + supplierTelNo + "\n" +
                "主要标的名称：" + subjectName + "\n" +
                "主要标的单价：" + subjectUnitPrice + "\n" +
                "合同金额：" + contractValue + "\n" +
                "合同公告日期：" + announceDate;
    }
}

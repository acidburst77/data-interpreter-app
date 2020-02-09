package com.mysoft.datainterpreterapp.models;

import com.opencsv.bean.CsvBindByName;

public class Logs {
    @CsvBindByName
    private String ssoid;
    @CsvBindByName
    private Long ts;
    @CsvBindByName
    private String grp;
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String subType;
    @CsvBindByName
    private String url;
    @CsvBindByName
    private String orgId;
    @CsvBindByName
    private String formId;
    @CsvBindByName
    private String code;
    @CsvBindByName
    private String ltpa;
    @CsvBindByName
    private String sudirresponse;
    @CsvBindByName
    private String ymdh;

    private Integer countVisits;

    public Logs() {}

    public Logs(String ssoid, Long ts, String grp, String type, String subType, String url, String orgId, String formId, String code, String ltpa, String sudirresponse, String ymdh) {
        this.ssoid = ssoid;
        this.ts = ts;
        this.grp = grp;
        this.type = type;
        this.subType = subType;
        this.url = url;
        this.orgId = orgId;
        this.formId = formId;
        this.code = code;
        this.ltpa = ltpa;
        this.sudirresponse = sudirresponse;
        this.ymdh = ymdh;
    }

    public Logs(String ssoid, String formId, Long ts) {
        this.ssoid = ssoid;
        this.formId = formId;
        this.ts = ts;
    }

    public Logs(String formId, Integer countVisits) {
        this.formId = formId;
        this.countVisits = countVisits;
    }

    public Integer getCountVisits() {
        return countVisits;
    }

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLtpa() {
        return ltpa;
    }

    public void setLtpa(String ltpa) {
        this.ltpa = ltpa;
    }

    public String getSudirresponse() {
        return sudirresponse;
    }

    public void setSudirresponse(String sudirresponse) {
        this.sudirresponse = sudirresponse;
    }

    public String getYmdh() {
        return ymdh;
    }

    public void setYmdh(String ymdh) {
        this.ymdh = ymdh;
    }
}

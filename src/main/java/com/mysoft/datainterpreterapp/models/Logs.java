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
}

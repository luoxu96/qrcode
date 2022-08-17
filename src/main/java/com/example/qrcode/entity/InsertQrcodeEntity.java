package com.example.qrcode.entity;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

/**
 * @Author: luoxu
 * @Date: 2022/8/16 12:10
 * @Description:
 */
public class InsertQrcodeEntity {
    String BILL_ID;
    Integer SEQ_NO;
    String LABLE;
    InputStream DOC;
    String MEMO;
    String CREATE_USER;
    Date CREATE_TIME;

    public String getBILL_ID() {
        return BILL_ID;
    }

    public void setBILL_ID(String BILL_ID) {
        this.BILL_ID = BILL_ID;
    }

    public Integer getSEQ_NO() {
        return SEQ_NO;
    }

    public void setSEQ_NO(Integer SEQ_NO) {
        this.SEQ_NO = SEQ_NO;
    }

    public String getLABLE() {
        return LABLE;
    }

    public void setLABLE(String LABLE) {
        this.LABLE = LABLE;
    }

    public InputStream getDOC() {
        return DOC;
    }

    public void setDOC(InputStream DOC) {
        this.DOC = DOC;
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    public String getCREATE_USER() {
        return CREATE_USER;
    }

    public void setCREATE_USER(String CREATE_USER) {
        this.CREATE_USER = CREATE_USER;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }
}

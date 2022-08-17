package com.example.qrcode.service;

import com.example.qrcode.entity.InsertQrcodeEntity;

import java.util.ArrayList;

/**
 * @Author: luoxu
 * @Date: 2022/8/16 13:59
 * @Description:
 */
public interface QrcodeService {
    public Integer insertQRCode(ArrayList<InsertQrcodeEntity> insertQRCodeEntityArrayList);
}

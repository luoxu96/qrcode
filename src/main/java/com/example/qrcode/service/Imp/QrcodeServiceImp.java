package com.example.qrcode.service.Imp;

import com.example.qrcode.entity.InsertQrcodeEntity;
import com.example.qrcode.mapper.QRCodeMapper;
import com.example.qrcode.service.QrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: luoxu
 * @Date: 2022/8/16 14:06
 * @Description:
 */
@Service
public class QrcodeServiceImp implements QrcodeService {

    @Autowired
    public QRCodeMapper qrCodeMapper;
    @Override
    public Integer insertQRCode(ArrayList<InsertQrcodeEntity> insertQRCodeEntityArrayList) {
        return qrCodeMapper.insertQRCode(insertQRCodeEntityArrayList);
    }
}

package com.example.qrcode.mapper;



import com.example.qrcode.entity.InsertQrcodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: luoxu
 * @Date: 2021/8/13 4:50 PM
 * @Description:
 */
@Mapper
public interface QRCodeMapper {

    Integer insertQRCode(@Param("insertQRCodeEntityList") List<InsertQrcodeEntity> insertQRCodeEntityList);

}

package com.example.qrcode.controller;

import com.alibaba.fastjson.JSON;
import com.example.qrcode.entity.InsertQrcodeEntity;
import com.example.qrcode.entity.RequestQrcodeEntity;
import com.example.qrcode.service.QrcodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * @Author: luoxu
 * @Date: 2022/8/16 12:07
 * @Description:
 */
@Controller
public class QrcodeController {

    @Autowired
    public QrcodeService qrcodeService;

    //二维码中的内容
    private static String QR_CODE_TEXT = "这是二维码中的内容";
    //二维码图片的宽度
    private static int WIDTH = 300;
    //二维码图片的高度
    private static int HEIGHT = 300;
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLUE = 0xFF0000FF;


    @RequestMapping(value="/Qrcode",method = RequestMethod.POST)
    public @ResponseBody String Qrcode(@RequestBody RequestQrcodeEntity qrcodeEntity) throws WriterException, FileNotFoundException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        System.out.println("打印参数"+ JSON.toJSONString(qrcodeEntity));

        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//H最高容错等级
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        QR_CODE_TEXT=qrcodeEntity.getCode();

        BitMatrix bitMatrix = qrCodeWriter.encode(QR_CODE_TEXT, BarcodeFormat.QR_CODE, WIDTH, HEIGHT,hints);

        BufferedImage bufferedImage=deleteWhite(bitMatrix);

        InputStream inputStream=bufferedImageToInputStream(bufferedImage);


        ArrayList<InsertQrcodeEntity> arrayList=new ArrayList<InsertQrcodeEntity>();
        InsertQrcodeEntity insertQRCodeEntity=new InsertQrcodeEntity();
        insertQRCodeEntity.setBILL_ID("6668888816");
        insertQRCodeEntity.setCREATE_USER("luoxu");
        insertQRCodeEntity.setDOC(inputStream);
        insertQRCodeEntity.setSEQ_NO(1);
        insertQRCodeEntity.setCREATE_TIME(new Date());
        insertQRCodeEntity.setMEMO("测试物流转票增加二维码列印");
        insertQRCodeEntity.setLABLE("二维码列印");
        arrayList.add(insertQRCodeEntity);
        qrcodeService.insertQRCode(arrayList);

        return "请求成功";
    }



    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    //去白边的话，调用这个方法
    private static BufferedImage  deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }

        int width = resMatrix.getWidth();
        int height = resMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, resMatrix.get(x, y) ? BLACK
                        : WHITE);
            }
        }
        return image;
    }



    /**
     * 将BufferedImage转换为InputStream
     * @param image
     * @return
     */
    public InputStream bufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;
        } catch (IOException e) {

        }
        return null;
    }

}

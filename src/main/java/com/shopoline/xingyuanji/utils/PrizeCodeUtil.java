package com.shopoline.xingyuanji.utils;

import com.shopoline.xingyuanji.Config;
import com.shopoline.xingyuanji.entity.PrizeCode;
import com.shopoline.xingyuanji.service.db2.IPrizeCodeService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrizeCodeUtil {
    @Autowired
    private IPrizeCodeService prizeCodeService;

    public static void main(String[] args) {

    }

    //    保存
    public  void save(int num){
        List<PrizeCode> list = new ArrayList<>();
        int id = (int) (System.currentTimeMillis()/1000);
        for (int i=0;i<num;i++){
            PrizeCode prizeCode = new PrizeCode();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            uuid = new SimpleHash("md5",uuid).toHex();
            System.out.println(uuid);
            prizeCode.setImg("https://www.xingyuanji.com/wxp/towx/");
            prizeCode.setUid(uuid);
            prizeCode.setStatus(0);
            list.add(prizeCode);
            id=id+i;
            createQrImg(uuid,id);
            prizeCode.setId(id);
            if (i>9){
                prizeCodeService.save(list);
                list.clear();
                i=0;
                num=num-10;
            }
        }
        if (list.size()>0) {
            prizeCodeService.save(list);
        }
    }


    //    创建二维码
    private void createQrImg(String uid,Integer id){
        String url = Config.SERVER+"wxp/towx/"+uid;
        String savePath = Config.UPLOAD_DIR+id+".png";
        QRCodeUtils.createQRCode(url,savePath);
    }
}

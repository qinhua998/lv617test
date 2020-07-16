package com.example.lv617.lv617test.controler;

import com.example.lv617.lv617test.service.DataSourceDetailService;
import com.example.lv617.lv617test.utils.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName：TestControler
 * @Description：生成数据库表设计文档
 * @Author：lv617
 * @Date：2018/9/4 11:29
 * @Version：1.0
 **/
@RestController
public class DataSourceDetailControler {

    @Autowired
    private DataSourceDetailService dataSourceDetailService;

    @Autowired
    MyProperties myProperties;


    @RequestMapping("/")
    public String getDoc(){
        String dbName = myProperties.getDbName();
        String fileUrl = myProperties.getFileUrl();
        return getDbDetail(dbName,fileUrl);
    }


    public String getDbDetail(String dbName,String fileUrl){
        try {
            List<Map<String,Object>> list = this.dataSourceDetailService.getAllDataSourceName(dbName);
            this.dataSourceDetailService.toWord(list,fileUrl,dbName);
        } catch (Exception e) {
            e.printStackTrace();
            return "生成数据库表设计文档失败";
        }
        return "生成数据库表设计文档成功";
    }
}

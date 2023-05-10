package com.hy.fourseas.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入时填充字段
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
       metaObject.setValue("createTime", LocalDateTime.now());
       metaObject.setValue("updateTime", LocalDateTime.now());
       metaObject.setValue("createUser", BaseContext.getCurrentID());
       metaObject.setValue("updateUser", BaseContext.getCurrentID());

    }


    /**
     * 更新时填充字段
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentID());
    }
}

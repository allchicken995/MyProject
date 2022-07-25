package com.xyj.myproject.utils.dataMasking;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {

    /**
     * 用于数据脱敏的注解，放到实体类中需要脱敏的属性上即可
     *
     * @return
     */
    DataMaskingFunc maskFunc() default DataMaskingFunc.NO_MASK;

}

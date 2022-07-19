package com.xyj.myproject.utils.dataMasking;

import org.springframework.util.StringUtils;

public interface DataMaskingOperation {

    String MASK_CHAR = "*";

    String mask(String content, String maskChar);

}

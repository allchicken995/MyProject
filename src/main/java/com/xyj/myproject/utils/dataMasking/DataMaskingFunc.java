package com.xyj.myproject.utils.dataMasking;

import org.springframework.util.StringUtils;

public enum DataMaskingFunc {

    NO_MASK((str, maskChar) -> {
        return str;
    }),
    ALL_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
            }
            return sb.toString();
        } else {
            return str;
        }
    }),
    MIDDLE_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)){
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(0, 1));
            sb.append(DataMaskingOperation.MASK_CHAR);
            sb.append(DataMaskingOperation.MASK_CHAR);
            sb.append(DataMaskingOperation.MASK_CHAR);
            sb.append(str.substring(str.length() - 1));
            return sb.toString();
        }else {
            return str;
        }
    });

    private final DataMaskingOperation operation;

    private DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }

    public DataMaskingOperation operation() {
        return this.operation;
    }

}

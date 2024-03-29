package com.xyj.myproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xyj.myproject.utils.dataMasking.DataMasking;
import com.xyj.myproject.utils.dataMasking.DataMaskingFunc;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2019-09-03 15:06:48
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 915478504870211231L;

    private Integer id;
    //账号
    private String account;
    //用户名
    private String userName;
    //用户密码
    @DataMasking(maskFunc = DataMaskingFunc.MIDDLE_MASK)
    private String password;
    //上一次登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
    //账号是否可用。默认为1（可用）
    private Boolean enabled;
    //是否过期。默认为1（没有过期）
    private Boolean accountNonExpired;
    //账号是否锁定。默认为1（没有锁定）
    private Boolean accountNonLocked;
    //证书（密码）是否过期。默认为1（没有过期）
    private Boolean credentialsNonExpired;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //创建人
    private Integer createUser;
    //修改人
    private Integer updateUser;
    //手机号
    private String phoneNumb;

}
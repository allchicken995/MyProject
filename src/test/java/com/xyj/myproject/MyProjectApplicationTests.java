package com.xyj.myproject;

import com.xyj.myproject.entity.SysUser;
import com.xyj.myproject.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyProjectApplicationTests {

    @Resource
    private SysUserService sysUserService;

    @Test
    void contextLoads() {

        SysUser user = sysUserService.selectByPhoneNumb("111");
        System.out.println(user);

    }

    public static void main(String[] args) {

    }

}

package com.xyj.myproject.controller;

import com.xyj.myproject.common.entity.JsonResult;
import com.xyj.myproject.common.utils.ResultTool;
import com.xyj.myproject.entity.SysUser;
import com.xyj.myproject.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Hutengfei
 * @Description:
 * @Date Create in 2019/8/28 19:34
 */
@Slf4j
@RestController
public class UserController {
    @Resource
    SysUserService sysUserService;

    @PostMapping("/getUser")
    public JsonResult getUser() {
        List<SysUser> users = sysUserService.queryAllByLimit(1, 100);
        return ResultTool.success(users);
    }
}

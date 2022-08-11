package com.xyj.myproject.controller;

import com.xyj.myproject.common.entity.JsonResult;
import com.xyj.myproject.common.utils.ResultTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/")
    public JsonResult test(){
        return ResultTool.success();
    }

}

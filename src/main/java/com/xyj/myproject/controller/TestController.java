package com.xyj.myproject.controller;

import com.xyj.myproject.common.entity.JsonResult;
import com.xyj.myproject.common.utils.ResultTool;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping(value = "/test/{value1}/{value2}")
    public JsonResult test(@PathVariable("value1") String value1, @PathVariable("value2") String value2){
        System.out.println(value1);
        System.out.println(value2);
        return ResultTool.success(value1 + "###" + value2);
    }

}

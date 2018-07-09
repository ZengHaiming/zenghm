package com.zenghm.spring.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create date:2018/07/03
 * Created by: Airlen
 * Class name:HandleController
 * @author Airlen
 * @date 2018/07/03
 *
 */
@Controller("/handle")
public class HandleController {
    @GetMapping("/hello")
    @ResponseBody
    public String simple(){
        return "This is first handler.";
    }
}

package com.zenghm.web.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create date:2018/06/16.
 * Created by: Airlen.
 * Class name:IndexController.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {
    @RequestMapping(value = "/indexFrame.do", method = RequestMethod.GET)
    public String indexPage(){
        return "jsp/index/index";
    }
}

package org.activiti.examples.controller;

import org.activiti.examples.req.BusinessReq;
import org.activiti.examples.service.BusinessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@RestController
@RequestMapping("/business/")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @RequestMapping(value = "apply",method = RequestMethod.POST)
    public Object addApply(BusinessReq businessReq){
        return businessService.addApply(businessReq);
    }
}

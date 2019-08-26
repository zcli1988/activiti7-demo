package org.activiti.examples.controller;

import org.activiti.examples.req.LoginReq;
import org.activiti.examples.service.impl.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@RestController
public class SystemController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginReq loginReq) {
        return loginService.login(loginReq.getUsername(), loginReq.getPassword());
    }

}

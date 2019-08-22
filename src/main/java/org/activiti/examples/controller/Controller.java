package org.activiti.examples.controller;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.examples.req.LoginReq;
import org.activiti.examples.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangkai
 * @since JDK8
 */
@RestController
public class Controller {

    @Resource
    private ProcessRuntime processRuntime;
    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/documents", method = RequestMethod.POST)
    public String processFile(@RequestBody String content, @RequestParam String username) {
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("categorizeProcess")
                .withVariable("fileContent", content)
                .build());
        String message = ">>> Created Process Instance: " + processInstance;
        System.out.println(message);
        return message;
    }

    @RequestMapping(value = "/process/definitions", method = RequestMethod.GET)
    public List<ProcessDefinition> getProcessDefinition() {
        return processRuntime.processDefinitions(Pageable.of(0, 100)).getContent();
    }

    @RequestMapping(value = "/process/instances", method = RequestMethod.GET)
    public List<ProcessInstance> getProcessInstance() {
        return processRuntime.processInstances(Pageable.of(0, 100)).getContent();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginReq loginReq) {
        return loginService.login(loginReq.getUsername(), loginReq.getPassword());
    }

}

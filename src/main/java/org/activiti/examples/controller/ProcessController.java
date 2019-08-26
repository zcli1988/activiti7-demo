package org.activiti.examples.controller;

import org.activiti.examples.service.ProcessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@RestController
@RequestMapping("/process/")
public class ProcessController {

    @Resource
    private ProcessService processService;

    @RequestMapping(value = "definitions", method = RequestMethod.GET)
    public Object getProcessDefinition() {
        return processService.getProcessDefinations();
    }

    @RequestMapping(value = "start", method = RequestMethod.POST)
    public Object start(@RequestBody String content) {
        return processService.startProcess(content);
    }

    @RequestMapping(value = "instances", method = RequestMethod.GET)
    public Object getProcessInstance() {
        return processService.getProcessInstances();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Object delete(@RequestParam String key) {
        return processService.deleteProcess(key);
    }

}

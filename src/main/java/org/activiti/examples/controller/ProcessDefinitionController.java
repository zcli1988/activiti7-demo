package org.activiti.examples.controller;

import org.activiti.examples.service.impl.ProcessDefinitionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@RestController
@RequestMapping("/process/")
public class ProcessDefinitionController {

    @Resource
    private ProcessDefinitionService processDefinitionService;

    @RequestMapping(value = "definitions", method = RequestMethod.GET)
    public Object getProcessDefinition() {
        return processDefinitionService.getProcessDefinations();
    }

}

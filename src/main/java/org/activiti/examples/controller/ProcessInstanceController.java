package org.activiti.examples.controller;

import org.activiti.examples.service.impl.ProcessInstanceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@RestController
@RequestMapping("/process/")
public class ProcessInstanceController {

	@Resource
	private ProcessInstanceService processInstanceService;

	@RequestMapping(value = "start", method = RequestMethod.POST)
	public Object start(@RequestParam String processDefinitionKey, String businessKey) {
		return processInstanceService.startProcess(processDefinitionKey, businessKey);
	}

	@RequestMapping(value = "instances", method = RequestMethod.GET)
	public Object getProcessInstance() {
		return processInstanceService.getProcessInstances();
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(@RequestParam String processInstanceId) {
		return processInstanceService.deleteProcess(processInstanceId);
	}

}

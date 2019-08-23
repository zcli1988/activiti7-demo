package org.activiti.examples.service.impl;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.examples.resp.SuccessResp;
import org.activiti.examples.service.ProcessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private ProcessRuntime processRuntime;

    @Override
    public Object getProcessDefinations() {
        return processRuntime.processDefinitions(Pageable.of(0, 100));
    }

    @Override
    public Object startProcess(String content) {
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("categorizeProcess")
                .withVariable("fileContent", content)
                .build());
        String message = ">>> Created Process Instance: " + processInstance;
        return new SuccessResp(message);
    }

    @Override
    public Object getProcessInstances() {
        return processRuntime.processInstances(Pageable.of(0, 100));
    }

}

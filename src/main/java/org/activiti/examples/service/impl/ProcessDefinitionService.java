package org.activiti.examples.service.impl;

import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@Service
public class ProcessDefinitionService {

    @Resource
    private ProcessRuntime processRuntime;

    public Object getProcessDefinations() {
        return processRuntime.processDefinitions(Pageable.of(0, 100));
    }

}

package org.activiti.examples.service;

import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.examples.resp.SuccessResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@Service
public class TaskService {
    @Resource
    private TaskRuntime taskRuntime;

    public Object getTaskList() {
        return taskRuntime.tasks(Pageable.of(0, 10));
    }

    public Object claim(String taskId){
        taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(taskId).build());
        return getTaskList();
//        return new SuccessResp();
    }

}

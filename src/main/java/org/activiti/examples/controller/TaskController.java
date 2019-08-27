package org.activiti.examples.controller;

import org.activiti.examples.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@RestController
@RequestMapping("/task/")
public class TaskController {

    @Resource
    private TaskService taskService;

    @RequestMapping(value = "task", method = RequestMethod.GET)
    public Object getTaskList() {
        return taskService.getTaskList();
    }

    @RequestMapping(value = "task/claim", method = RequestMethod.GET)
    public Object claimTask(@RequestParam String taskId) {
        return taskService.claimTask(taskId);
    }

    @RequestMapping(value = "task/release", method = RequestMethod.GET)
    public Object release(@RequestParam String taskId) {
        return taskService.releaseTask(taskId);
    }

}

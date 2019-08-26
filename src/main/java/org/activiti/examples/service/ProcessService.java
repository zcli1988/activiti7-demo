package org.activiti.examples.service;

/**
 * @author wangkai
 * @since JDK8
 */
public interface ProcessService {
    Object getProcessDefinations();

    Object startProcess(String content);

    Object getProcessInstances();

    Object deleteProcess(String key);
}

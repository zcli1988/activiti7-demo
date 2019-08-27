package org.activiti.examples.service;

import org.activiti.examples.db.BusinessPo;
import org.activiti.examples.mapper.BusinessMapper;
import org.activiti.examples.req.BusinessReq;
import org.activiti.examples.resp.SuccessResp;
import org.activiti.examples.service.impl.ProcessInstanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wangkai
 * @since JDK8
 */
@Service
public class BusinessService {

    @Resource
    private BusinessMapper businessMapper;
    @Resource
    private ProcessInstanceService processInstanceService;

    @Transactional
    public Object addApply(BusinessReq businessReq) {
        BusinessPo businessPo = new BusinessPo().setContent(businessReq.getContent()).setApply_user_id(businessReq.getApplyUserId()).setCreateUser(1).setUpdateUser(1);
        businessMapper.insert(businessPo);
        String businessKey = "business:" + businessPo.getId();
        processInstanceService.startProcess(BusinessPo.class.getSimpleName(), businessKey);
        return new SuccessResp();
    }

}

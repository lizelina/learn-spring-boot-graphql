/**
 * @system：AEES云(aees-base)
 * @filename: EsUserSearchService
 * @copyright: Copyright (c) 2022 攸信信息技术 All Rights Reserved
 */
package com.learn.graphql.service;


import com.learn.graphql.domain.others.EsUser;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @version: 1.00.00
 * @description: 用户搜索
 * @author: zbchen
 * @date: 2022-12-26 19:16
 */
public interface EsUserSearchService {

    /**
     * 根据userIds 查tenantId=入参下的数据
     *
     * @param tenantId:
     * @param userIds:
     * @Author zhangbaochen 2022/12/27
     * @return: java.util.List<com.umsin.base.model.po.EsUser>
     * @throws:
     */
    List<EsUser> getByIds(String tenantId, Collection<? extends Serializable> userIds);


}
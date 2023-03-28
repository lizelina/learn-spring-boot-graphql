/**
 * 系统名称：AEES云(learn-graphql-java)
 * 文件名: TaskFollower
 * 版权声明: Copyright (c) 2023 攸信信息技术 All Rights Reserved
 */
package com.learn.graphql.domain.task;

import lombok.Data;

/**
 * @version: 1.00.00
 * @description: 任务的关注人
 * @author: lizelin
 * @date: 2023-03-23 3:33 PM
 */
@Data
public class Follower {

    // 主键id
    private Long id;

    // 关注人,对应人员id
    private String follower;

    // 任务id
    private Long tskId;

    // 租户id
    private Long tenantId;

    // 是否删除,0否，1是
    private String deleteFlag;

}
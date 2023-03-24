/**
 * 系统名称：AEES云(learn-graphql-java)
 * 文件名: TaskRelevance
 * 版权声明: Copyright (c) 2023 攸信信息技术 All Rights Reserved
 */
package com.learn.graphql.domain.task;

import lombok.Data;

/**
 * @version: 1.00.00
 * @description: 任务关联会议
 * @author: lizelin
 * @date: 2023-03-23 3:31 PM
 */
@Data
public class TaskRelevance {

    // 主键id
    private Long id;

    // 任务id
    private Long tskId;

    // 被链接的任务id或会议id
    private Long refId;

    // 关联类型 1:任务;2:会议;3:知识库;4:其他
    private String refType;

    // 1:有关联;2:依赖于;3:被依赖
    private String refLabel;

    // 网页地址
    private String refUri;

    // 链接文字
    private String linkRemark;

    // 租户id
    private Long tenantId;

    // 是否删除,0否，1是
    private String deleteFlag;
}
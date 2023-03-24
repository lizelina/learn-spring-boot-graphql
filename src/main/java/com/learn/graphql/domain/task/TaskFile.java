/**
 * 系统名称：AEES云(learn-graphql-java)
 * 文件名: TaskFile
 * 版权声明: Copyright (c) 2023 攸信信息技术 All Rights Reserved
 */
package com.learn.graphql.domain.task;

import lombok.Data;

import java.util.Date;

/**
 * @version: 1.00.00
 * @description: 任务里的file实体类
 * @author: lizelin
 * @date: 2023-03-23 3:13 PM
 */
@Data
public class TaskFile {

    // 主键id
    private Long id;

    // 文件名称
    private String fileName;

    // 文件路径
    private String path;

    // 文件大小
    private Long size;

    // 任务id
    private Long refId;

    // 关联的类型 1:任务;2:评论
    private String refType;

    // 租户id
    private Long tenantId;

    // 创建人id
    private Long createBy;


    private Date createTime;

    // 更新人id
    private Long updateBy;


    private Date updateTime;

    // 是否删除,0否，1是
    private String deleteFlag;

}
package com.learn.graphql.domain.task;

import com.learn.graphql.domain.others.EsUser;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Task {

    private Long id;

    // 任务主题
    private String title;

    // 任务号
    private String taskNo;

    // 父id
//    private Long parentId;

    // 执行人id(UserId)
    private String executor;

    // 验收人(UserId)
    private String accepter;

    private EsUser acceptor;

    // 到期日
    private Date deadline;

    // 优先级id
    private Long priorityLevelId;

    // 项目id
    private Long projectId;

    // 任务类型id
    private Long taskTypeId;

    // 任务类型名称
    private String taskTypeName;

    // 模块
    private String module;

    // 标签
    private String label;

    // 描述
    private String describe;

    // 当前节点id
    private Long nodeId;

    // 完成-执行措施
    private String nodeName;

    // 任务状态 1：开始  2：进行  3：完成  4：验收
    private String status;

    // 完成结果
    private Long completeResultId;

    // 完成-执行措施
    private String measures;

    // 工作满意度评分
    private Integer score;

    // 评语
    private String comment;

    // 数据内容(表单提交的数据)
    private String content;

    private Long linkId;

    // 数据内容(Json提交的数据)
//    private JSONObject jsonContent;

    // 租户id
    private Long tenantId;

    // 完成时间
    private Date completeTime;

    // 分配时间
    private Date assignTime;

    private List<File> files;

    private List<Relevance> relevances;

    private List<Follower> followers;
}

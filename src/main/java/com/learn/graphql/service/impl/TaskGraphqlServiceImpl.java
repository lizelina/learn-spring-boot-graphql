package com.learn.graphql.service.impl;

import com.learn.graphql.domain.task.Task;
import com.learn.graphql.service.TaskGraphqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class TaskGraphqlServiceImpl implements TaskGraphqlService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EsUserSearchServiceImpl esUserSearchService;

    private final static String EXECUTOR = "executor";
    private final static String ACCEPTER = "accepter";

    @Override
    public Task getTaskById(Long id) {

        log.info(id.toString());
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id);
        Query query = new Query(criteria);

        Task tsTaskInfo = mongoTemplate.findById(id, Task.class, "ts_task_infos");
        return tsTaskInfo;
    }

    @Override
    public List<Task> getTasksByStatus(String type, Long userId, Long tenantId) {
        Criteria criteria = new Criteria();
        List<String> nodes = null;
        //按照任务列表类型查询相关数据
        switch (type) {
            //待完成 1:开始;2:进行;4:重启
            //执行人为当前用户
            //到期时间正序
            case "0":
                criteria.and(EXECUTOR).is(userId);
                nodes = Arrays.asList("1","2","4");
                break;
            //待验收 3:完成;
            //验收人为当前用户在完成节点
            //完成时间倒序
            case "1":
                criteria.and(ACCEPTER).is(userId);
                nodes = Arrays.asList("3");
                break;
            //我分配的 1:开始;2:进行;4:重启
            //分配人为当前用户
            //分配时间降序
            case "2":
                criteria.and(ACCEPTER).is(userId);
                nodes = Arrays.asList("1","2","4");
                break;

            //近期完成 3:完成;5：结束 近3个月
            //执行人为当前用户
            //完成时间倒序排序
            case "4":
                criteria.and(EXECUTOR).is(userId);
                nodes = Arrays.asList("3","5");
                break;
            default:
        }
        //指定任务节点
        criteria.and("status").in(nodes);
        criteria.and("tenantId").is(tenantId);
        Query query = new Query(criteria);
        List<Task> infos = mongoTemplate.find(query, Task.class, "ts_task_infos");
//        if (CollUtil.isNotEmpty(infos)){
//            for (Task info : infos) {
//                List<EsUser> byIds = esUserSearchService.getByIds(info.getTenantId().toString(), Arrays.asList(info.getAccepter()));
//                info.setAcceptor(CollUtil.isEmpty(byIds)?null:byIds.get(0));
//            }
//        }
        return infos;
    }

    @Override
    public Task updateTask(Long id, String title) {
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id);
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("title", title);

        Task task = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true),
                Task.class,
                "ts_task_infos");
        return task;
    }
}
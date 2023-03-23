package com.learn.graphql.service.impl;

import com.learn.graphql.domain.task.Task;
import com.learn.graphql.service.TaskGraphqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskGraphqlServiceImpl implements TaskGraphqlService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Task getTaskById(Long id) {

        log.info(id.toString());
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id);
        Query query = new Query(criteria);

        Task tsTaskInfo = mongoTemplate.findById(id, Task.class, "ts_task_infos");
        return tsTaskInfo;
    }
}
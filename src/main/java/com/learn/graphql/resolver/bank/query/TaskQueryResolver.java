package com.learn.graphql.resolver.bank.query;

import com.learn.graphql.domain.task.Task;
import com.learn.graphql.service.TaskGraphqlService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private TaskGraphqlService taskGraphqlService;

    public Task aTask(Long id) {
        Task taskById = taskGraphqlService.getTaskById(id);
        return taskById;
    }

    public List<Task> tasksByStatus(String type, String userId, String tenantId) {
        List<Task> tasks = taskGraphqlService.getTasksByStatus(type, Long.parseLong(userId), Long.parseLong(tenantId));
        return tasks;
    }


}


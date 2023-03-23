package com.learn.graphql.resolver.bank.query;

import com.learn.graphql.domain.task.Task;
import com.learn.graphql.service.TaskGraphqlService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private TaskGraphqlService taskGraphqlService;


    public Task getTask(Long id) {
        Task taskById = taskGraphqlService.getTaskById(id);
        return taskById;
    }
}


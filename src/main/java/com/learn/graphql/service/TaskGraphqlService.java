package com.learn.graphql.service;


import com.learn.graphql.domain.task.Task;

public interface TaskGraphqlService {

    Task getTaskById(Long Id);
}

package com.learn.graphql.service;


import com.learn.graphql.domain.task.Task;

import java.util.List;

public interface TaskGraphqlService {

    Task getTaskById(Long Id);

   List<Task> getTasksByStatus(String type, Long userId, Long tenantId);
}

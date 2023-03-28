package com.learn.graphql.resolver.bank.mutation;

import com.learn.graphql.domain.bank.input.UpdateTaskNameInput;
import com.learn.graphql.domain.task.Task;
import com.learn.graphql.service.TaskGraphqlService;
import graphql.GraphQLContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Component
@Slf4j
public class TaskMutation implements GraphQLMutationResolver {

    @Autowired
    private TaskGraphqlService taskGraphqlService;

    public Task updateTaskById(@Valid UpdateTaskNameInput input, DataFetchingEnvironment e){
        log.info(" DataFetchingEnvironment");
       GraphQLContext graphQlContext = e.getGraphQlContext();
        e.getSelectionSet().getFields().stream().map(SelectedField::getName).collect(Collectors.toSet());
        Task task = taskGraphqlService.updateTask(input.getId(), input.getTitle());
        return task;
    }
}

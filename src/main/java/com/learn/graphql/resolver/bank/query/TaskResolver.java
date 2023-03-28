/**
 * 系统名称：AEES云(learn-graphql-java)
 * 文件名: TaskResolver
 * 版权声明: Copyright (c) 2023 攸信信息技术 All Rights Reserved
 */
package com.learn.graphql.resolver.bank.query;

import com.learn.graphql.domain.others.EsUser;
import com.learn.graphql.domain.task.Task;
import com.learn.graphql.domain.task.File;
import com.learn.graphql.domain.task.Follower;
import com.learn.graphql.domain.task.Relevance;
import com.learn.graphql.service.EsUserSearchService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: 1.00.00
 * @description: Task数据处理
 * @author: lizelin
 * @date: 2023-03-23 4:59 PM
 */
@Slf4j
@Component
public class TaskResolver implements GraphQLResolver<Task> {

    @Autowired
    private EsUserSearchService esUserSearchService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public List<File> files(Task task) {
        log.info("获取任务附件 id {}", task.getId());
        return task.getFiles();
    }

    public List<Relevance> relevances(Task task) {
        log.info("获取任务关联 id {}", task.getId());
        return task.getRelevances();
    }

    public List<Follower> followers(Task task) {
        log.info("获取任务关注人 id {}", task.getId());
        return task.getFollowers();
    }

    public EsUser acceptorEs(Task task) {
        ArrayList<String> userIds = new ArrayList<>();
        userIds.add(task.getAccepter());
        List<EsUser> esUsers = esUserSearchService.getByIds(task.getTenantId().toString(), userIds);
        if (esUsers != null && esUsers.size() > 0) {
            return esUsers.get(0);
        }
        return null;
    }

    public CompletableFuture<EsUser> executorEs(Task task) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<String> userIds = new ArrayList<>();
            userIds.add(task.getAccepter());
            List<EsUser> esUsers = esUserSearchService.getByIds(task.getTenantId().toString(), userIds);
            if (esUsers != null && esUsers.size() > 0) {
                return esUsers.get(0);
            }
            return null;
        }, executorService);
    }

    public EsUser executorErrorEs(Task task) {

        //throw new GraphQLException("Executor获取失败");
        //包装RuntimeException
        throw new RuntimeException("SQL error");
    }

}
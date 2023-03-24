/**
 * @system：AEES云(aees-base)
 * @filename: EsUserSearchServiceImpl
 * @copyright: Copyright (c) 2022 攸信信息技术 All Rights Reserved
 */
package com.learn.graphql.service.impl;

import com.learn.graphql.domain.others.EsUser;
import com.learn.graphql.service.EsUserSearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @version: 1.00.00
 * @description: 用户搜索
 * @author: zbchen
 * @date: 2022-12-26 19:18
 */
@Service
public class EsUserSearchServiceImpl implements EsUserSearchService {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<EsUser> getByIds(String tenantId, Collection<? extends Serializable> userIds) {
        List<EsUser> esUsers = new ArrayList<>();
        BoolQueryBuilder userQuery = QueryBuilders.boolQuery();
        userQuery.must(QueryBuilders.termsQuery("userId", userIds));
        userQuery.must(QueryBuilders.termQuery("tenantId", tenantId));
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder().withQuery(userQuery);
        NativeSearchQuery query = queryBuilder.build();
        SearchHits<EsUser> hits = elasticsearchRestTemplate.search(query, EsUser.class);
        hits.forEach(hit -> {
            EsUser user = hit.getContent();
            esUsers.add(user);
        });
        return esUsers;
    }


}
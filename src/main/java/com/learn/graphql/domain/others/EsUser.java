/**
 * 系统名称：AEES云(aees-cloud)
 * 文件名: EsUser
 * 版权声明: Copyright (c) 2022 攸信信息技术 All Rights Reserved
 */
package com.learn.graphql.domain.others;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @version: 1.00.00
 * @description: 员工只用于搜索
 * @author: zhangbaochen
 * @date: 2022-07-18 13:55
 */
@Data
@Document(indexName = "aees-test-user", shards = 1, replicas = 1)
public class EsUser {


    @Id
    @ReadOnlyProperty
    private String id;

    /**
     * 员工id   用户管理后台搜索结果返回
     */
    @Field(type = FieldType.Keyword, index = false)
    private String staffId;

    /**
     * 头像
     */
    @Field(type = FieldType.Keyword, index = false)
    private String headPortrait;

    /**
     * 用户Id
     */
    @Field(type = FieldType.Keyword, index = true)
    private String userId;

    /**
     * 租户Id
     */
    @Field(type = FieldType.Keyword, index = true)
    private String tenantId;

    /**
     * 用户名称
     */
    @Field(type = FieldType.Text, index = true, analyzer = "ngram_py_analyzer", searchAnalyzer = "lowercase_analyzer")
    private String userName;


    /**
     * 电话    自定义分词器 ngram
     */
    @Field(type = FieldType.Text, index = true, analyzer = "phone_analyzer")
    private String phone;


    /**
     * 岗位  分词  不支持拼音搜索
     */
    @Field(type = FieldType.Text, index = true, analyzer = "ngram_lowercase", searchAnalyzer = "lowercase_analyzer")
    private String postName;

    /**
     * 部门  分词  不支持拼音搜索
     */
    @Field(type = FieldType.Text, index = true, analyzer = "ngram_lowercase", searchAnalyzer = "lowercase_analyzer")
    private String orgName;

    /**
     * 工号  2位开始分词，最多10位
     */
    @Field(type = FieldType.Text, index = true, analyzer = "number_analyzer", searchAnalyzer = "lowercase_analyzer")
    private String staffCode;

}
package com.bossien.service;

import com.bossien.entity.ExamDossierInfo;

/**
 * Created by A on 2017/7/25.
 */
public interface IExamDossierInfoService {
    /**
     * 修改
     * @param examDossierInfo
     */
    void update(ExamDossierInfo examDossierInfo);

    /**
     * 查询单条记录
     * @param projectId
     * @return
     */
    ExamDossierInfo selectOne(String projectId);

    /**
     * 批量删除
     * @param projectId
     */
    void delete(String projectId);

    /**
     * 新增
     * @param examDossierInfo
     */
    void insert(ExamDossierInfo examDossierInfo);
}

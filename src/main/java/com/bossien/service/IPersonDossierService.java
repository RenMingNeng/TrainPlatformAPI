package com.bossien.service;

import com.bossien.entity.PersonDossier;

import java.util.List;
import java.util.Map;

/**
 * Created by A on 2017/7/25.
 */
public interface IPersonDossierService {
    /**
     * 新增
     * @param personDossier
     */
    void insert(PersonDossier personDossier);

    /**
     * 批量添加
     * @param personDossiers
     * @return
     */
    int insertBatch(List<PersonDossier> personDossiers);

    /**
     * 修改
     * @param personDossier
     */
    void update(PersonDossier personDossier);

    /**
     * 查询列表
     * @param personDossier
     * @return
     */
    List<PersonDossier> selectList(PersonDossier personDossier);

    /**
     * 统计
     * @param personDossier
     * @return
     */
    Integer selectCount(PersonDossier personDossier);

    /**
     * 查询单条记录
     * @param personDossier
     * @return
     */
    PersonDossier selectOne(PersonDossier personDossier);

    /**
     * 查询用户排行
     * @param userId
     * @param companyId
     * @return
     */
    Map<String, Object> selectRank(String userId, String companyId);

    /**
     *
     * @param params
     * @return
     */
    int updateByUserIds(Map<String, Object> params);

    Integer selectCountByParams(Map map);

    List<Map<String,Object>> selectListByParams(Map map);

}

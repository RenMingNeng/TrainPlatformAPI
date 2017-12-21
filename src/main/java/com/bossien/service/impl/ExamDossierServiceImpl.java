package com.bossien.service.impl;

import com.bossien.entity.ExamDossierInfo;
import com.bossien.mapper.tp.ExamDossierInfoMapper;
import com.bossien.service.IExamDossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by A on 2017/7/25.
 */

@Service
public class ExamDossierServiceImpl implements IExamDossierService {
    @Autowired
    private ExamDossierInfoMapper examDossierInfoMapper;


    public void update(ExamDossierInfo examDossierInfo) {

        examDossierInfoMapper.update(examDossierInfo);
    }

    public ExamDossierInfo selectOne(String projectId) {

        return examDossierInfoMapper.selectOne(projectId);
    }

    public void delete(String projectId) {

        examDossierInfoMapper.delete(projectId);
    }

    public void insert(ExamDossierInfo examDossierInfo) {

        examDossierInfoMapper.insert(examDossierInfo);
    }
}

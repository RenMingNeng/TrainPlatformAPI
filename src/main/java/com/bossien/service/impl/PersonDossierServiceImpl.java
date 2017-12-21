package com.bossien.service.impl;

import com.bossien.entity.PersonDossier;
import com.bossien.mapper.tp.PersonDossierMapper;
import com.bossien.service.IPersonDossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by A on 2017/7/25.
 */

@Service(value="personDossierService")
public class PersonDossierServiceImpl  implements IPersonDossierService {

    @Autowired
    private PersonDossierMapper personDossierMapper;

    public void insert(PersonDossier personDossier) {

        personDossierMapper.insert(personDossier);
    }

    public int insertBatch(List<PersonDossier> personDossiers) {

        return personDossierMapper.insertBatch(personDossiers);
    }

    public void update(PersonDossier personDossier) {

        personDossierMapper.update(personDossier);
    }

    public List<PersonDossier> selectList(PersonDossier personDossier) {

        return personDossierMapper.selectList(personDossier);
    }

    public Integer selectCount(PersonDossier personDossier) {

        return personDossierMapper.selectCount(personDossier);
    }

    public PersonDossier selectOne(PersonDossier personDossier) {

        return personDossierMapper.selectOne(personDossier);
    }

    public Map<String, Object> selectRank(String userId, String companyId) {
        return null;
    }

    public int updateByUserIds(Map<String, Object> params) {
        return 0;
    }

    public List<Map<String, Object>> selectListByParams(Map map) {

        return personDossierMapper.selectListByParams(map);
    }

    public Integer selectCountByParams(Map map) {

        return personDossierMapper.selectCountByParams(map);
    }

}

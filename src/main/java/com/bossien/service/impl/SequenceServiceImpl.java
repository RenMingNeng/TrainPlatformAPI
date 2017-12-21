package com.bossien.service.impl;

import com.bossien.common.util.UUIDGenerator;
import com.bossien.service.ISequenceService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service
public class SequenceServiceImpl implements ISequenceService {

    public String  generator() {
//        ShortUuid.Builder builder = new ShortUuid.Builder();
//        return builder.build(UUID.randomUUID()).toString();
        return UUIDGenerator.genID()+ UUIDGenerator.genID();
    }
}

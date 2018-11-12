package com.hjw.service.impl;

import com.alibaba.fastjson.JSON;
import com.hjw.core.model.MockDto;
import com.hjw.service.MockService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by ho on 2018/11/11.
 */
@Service
public class MockServiceImpl implements MockService{

    private static Logger logger = LoggerFactory.getLogger(MockServiceImpl.class);

    private int EXPIRE_DAY_MIN = 1;   //最短有效时间
    private int EXPIRE_DAY_MAX = 5;   //最长有效时间

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String save(MockDto mockDto) {

        String uuid = UUID.randomUUID().toString();

        int workDay = mockDto.getWorkDay() > this.EXPIRE_DAY_MAX ? EXPIRE_DAY_MAX : (mockDto.getWorkDay() < EXPIRE_DAY_MIN ? EXPIRE_DAY_MIN : mockDto.getWorkDay());

        redisTemplate.opsForValue().set(uuid, JSON.toJSONString(mockDto), workDay, TimeUnit.DAYS);

        return uuid;
    }

    @Override
    public MockDto getByUuId(String uuid) {

        String jsonStr = redisTemplate.opsForValue().get(uuid);

        logger.info("jsonStr:"+jsonStr);

        if(Strings.isBlank(jsonStr)){
            throw new RuntimeException("Mock数据ID非法");
        }

        return JSON.parseObject(jsonStr, MockDto.class);
    }
}

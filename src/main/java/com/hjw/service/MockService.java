package com.hjw.service;

import com.hjw.core.model.MockDto;

/**
 * Created by ho on 2018/11/11.
 */
public interface MockService {

    /**
     * 保存mock数据
     * @return uuid
     */
    public String save(MockDto mockDto);

    /**
     * 获取mock数据
     * @param uuid
     * @return
     */
    public MockDto getByUuId(String uuid);

}

package com.hjw.controller;

import com.hjw.core.model.MockDto;
import com.hjw.core.model.ReturnT;
import com.hjw.service.MockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by ho on 2018/11/11.
 */
@Controller
@RequestMapping("/mock")
public class MockController {

    private static Logger logger = LoggerFactory.getLogger(MockController.class);

    @Autowired
    private MockService mockService;

    /**
     * 保存Mock数据
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ReturnT<String> add(MockDto mockDto){

        String uuid = mockService.save(mockDto);

        return new ReturnT<String>(uuid);
    }

    /**
     * 执行mock接口
     * @param uuid
     * @param request
     * @param response
     */
    @RequestMapping("/run/{uuid}")
    public void run(@PathVariable("uuid") String uuid, HttpServletRequest request, HttpServletResponse response) {

        MockDto mockDto = mockService.getByUuId(uuid);
        if(mockDto == null){
            throw new RuntimeException("Mock数据ID非法");
        }

        if(mockDto.getRespType() == null){
            throw new RuntimeException("Mock数据响应数据类型(MIME)非法");
        }

        // write response
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(mockDto.getRespType());

            PrintWriter writer = response.getWriter();
            writer.write(mockDto.getRespExample());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }


    }

}

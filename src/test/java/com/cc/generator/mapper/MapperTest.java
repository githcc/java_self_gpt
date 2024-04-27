package com.cc.generator.mapper;

import com.cc.generator.domain.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MapperTest {

    @Autowired
    RequestMapper requestMapper;

    @Test
    void listUserAndCompany11() {
        Request request = requestMapper.selectById(1);
        System.out.println(request);
    }

}
package com.cc.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.generator.domain.Request;
import com.cc.generator.mapper.RequestMapper;
import com.cc.generator.service.RequestService;
import org.springframework.stereotype.Service;

/**
* @author wyswy
* @description 针对表【request】的数据库操作Service实现
* @createDate 2024-04-27 18:37:23
*/
@Service
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request>
    implements RequestService {

}





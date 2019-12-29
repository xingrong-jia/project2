package com.jiaxingrong.service;

import com.jiaxingrong.model.ExpressRequestBody;

import java.util.Map;

public interface ConfigService {

    Map listmall();

    Map listexpress();

    Integer updateExpress(ExpressRequestBody requestBody);

    Integer updateMall(ExpressRequestBody requestBody);

    Map listOrder();

    Integer updateOrder(ExpressRequestBody requestBody);

    Map listWx();

    Integer updateWx(ExpressRequestBody requestBody);
}

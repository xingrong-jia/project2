package com.jiaxingrong.service;

import com.jiaxingrong.model.Brand;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface BrandService {

    Map brandList(int pageNumber, int size);

   // Map brandCreat(Brand brand);

    Brand brandCreat(Brand brand);

    Brand brandUpdate(Brand brand);

    boolean brandDelete(Brand brand);

    Map queryBrandList(Laypage laypage);
}

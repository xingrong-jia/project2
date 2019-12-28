package com.jiaxingrong.service;

import com.jiaxingrong.model.CateGory;
import com.jiaxingrong.model.L1;

import java.util.List;

public interface CategoryService {
    List<CateGory> categoryList();

    List<L1> categoryL1();

    CateGory categoryCreate(CateGory cateGory);

    boolean categoryUpdate(CateGory cateGory);

    boolean categoryDelete(CateGory cateGory);
}

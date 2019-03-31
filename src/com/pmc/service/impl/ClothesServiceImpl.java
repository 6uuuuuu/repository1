package com.pmc.service.impl;

import com.pmc.bean.Clothes;
import com.pmc.service.ClothesService;
import com.pmc.utills.BusinessException;
import com.pmc.utills.ProductXmlUtils;

import java.util.List;

public class ClothesServiceImpl implements ClothesService {
    @Override
    public List<Clothes> getClothesList() throws BusinessException {
        List<Clothes> clothesList = ProductXmlUtils.productXmlParse();
        return clothesList;
    }
}

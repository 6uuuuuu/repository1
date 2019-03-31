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

    @Override
    public Clothes findClothesbyClothesId(String buyId, int buyNum) throws BusinessException {
        List<Clothes> clothesList = getClothesList();
        for (Clothes c : clothesList) {
            if (c.getId() == buyId && c.getNum() > buyNum) {
                //商品存在且库存数大于购买数时才返回该商品信息
                return c;
            }
        }
        return null;
    }
}

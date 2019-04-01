package com.pmc.service.impl;

import com.pmc.bean.Clothes;
import com.pmc.service.ClothesService;
import com.pmc.utils.BusinessException;
import com.pmc.utils.ProductXmlUtils;

import java.util.ArrayList;
import java.util.List;

public class ClothesServiceImpl implements ClothesService {
    private static List<Clothes> clothesList = new ArrayList<>();

    @Override
    public List<Clothes> getClothesList() throws BusinessException {
        if (clothesList.size() == 0) {
            //第一次初始化从xml中读取商品信息
            clothesList = ProductXmlUtils.productXmlParse();
        }
        //非初始化时取之前获取的商品信息（可能在操作过程中做过修改）
        return clothesList;
    }

    @Override
    public Clothes findClothesByClothesId(String buyId, int buyNum) throws BusinessException {
        for (Clothes c : clothesList) {
            if (c.getId().equals(buyId) && c.getNum() > buyNum) {
                //商品存在且库存数大于购买数时才返回该商品信息
                int num =  c.getNum()-buyNum;
                c.setNum(num);
                return c;
            }
        }
        return null;
    }

    @Override
    public void alterClothesByClothesId() throws BusinessException {
        ProductXmlUtils.productXmlWrite(clothesList);
    }
}

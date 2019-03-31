package com.pmc.service;

import com.pmc.bean.Clothes;
import com.pmc.utills.BusinessException;

import java.util.List;

public interface ClothesService {
    public List<Clothes> getClothesList() throws BusinessException;
    public Clothes findClothesbyClothesId(String buyId,int buyNum) throws BusinessException;
}

package com.pmc.utills;

import javax.lang.model.element.VariableElement;

/**
 * 逻辑出现错误导致运行时出现异常，继承运行时异常类
 */
public class BusinessException extends RuntimeException {
    public BusinessException(){
        super();
    }
    public BusinessException(String errorMessage){
        super(errorMessage);
    }
}

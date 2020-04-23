package com.el.cloud.thread.countLatch;

import lombok.Getter;

/**
 * @Auther: roman.zhang
 * @Date: 2019/4/16 20:58
 * @Version:V1.0
 * @Description:CountryEnum
 */
public enum CountryEnum {
    ONE(1,"齐"), TWO(2,"楚"), THREE(3,"燕"), FOUR(4,"赵"), FIVE(5,"魏"),SIX(6,"韩");

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode=retCode;
        this.retMessage=retMessage;
    }
    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if(index==value.getRetCode()){
                return value;
            }
        }
        return null;
    }
}

package com.el.cloud.designpattern.responsibilityofchain;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 *  抽象的处理者
 */
public abstract class Approver {
    Approver approver; //下一个处理者
    String name;

    public Approver(String name) {
        this.name = name;
    }

    //下一个处理者
    public void setApprover(Approver approver){
        this.approver = approver;

    }

    //处理审批请求的方法，得到一个请求，处理是子类完成的，因此为抽象方法。
    public abstract void processRequest(PurchaseRequest purchaseRequest);
}

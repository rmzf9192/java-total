package com.el.cloud.designpattern.responsibilityofchain;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 *  具体请求者1
 */
public class CollegeApprover extends Approver {
    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if(purchaseRequest.getPrice()>5000 && purchaseRequest.getPrice()<= 10000){
            System.out.println("请求编号 id = "+purchaseRequest.getId()+"被"+this.name+"处理了");
        }else{
            approver.processRequest(purchaseRequest);
        }
    }
}

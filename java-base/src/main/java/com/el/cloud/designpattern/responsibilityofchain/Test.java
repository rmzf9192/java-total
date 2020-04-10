package com.el.cloud.designpattern.responsibilityofchain;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 8000, 1);

        DepartmentApprover departmentApprover = new DepartmentApprover("张主任");
        CollegeApprover collegeApprover = new CollegeApprover("李院长");
        ViceSchoolMasterApprover viceSchoolMasterApprover = new ViceSchoolMasterApprover("王副校长");

        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("李校长");

        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setApprover(schoolMasterApprover);
        //请求处理者必须构成环
        schoolMasterApprover.setApprover(departmentApprover);


        departmentApprover.processRequest(purchaseRequest);


        viceSchoolMasterApprover.processRequest(purchaseRequest);
    }
}

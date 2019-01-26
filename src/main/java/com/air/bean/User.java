package com.air.bean;

import java.util.List;

public class User {
    private UserLogin userlogin;
    private UserDesc userDesc;
    private List<Address> addressList;

    public UserLogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(UserLogin userlogin) {
        this.userlogin = userlogin;
    }

    public UserDesc getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(UserDesc userDesc) {
        this.userDesc = userDesc;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}

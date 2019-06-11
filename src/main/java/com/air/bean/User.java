package com.air.bean;

import java.util.List;

public class User {
    private UserLogin userLogin;
    private UserDesc userDesc;
    private List<Address> addressList;

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
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

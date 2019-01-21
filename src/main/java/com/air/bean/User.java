package com.air.bean;

import java.util.List;

public class User {
    private UserLogin userlogin;
    private Userdesc userdesc;
    private List<Address> addressList;

    public UserLogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(UserLogin userlogin) {
        this.userlogin = userlogin;
    }

    public Userdesc getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(Userdesc userdesc) {
        this.userdesc = userdesc;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}

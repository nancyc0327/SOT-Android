package com.example.mytestapplication;

/**
 * Created by ncai2 on 5/2/17.
 */

public class ContactModel {
    private String mRegionNum = "Region Number:";
    private String mName = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private String mPhone = "Phone Number: ";
    private String mAddress = "Address:";
    private String mEmail = "Email:";

    public ContactModel(String regionNum, String name, String phone, String address, String email)
    {
        mRegionNum = regionNum;
        mName = name;
        mPhone = phone;
        mAddress = address;
        mEmail = email;
    }

    public String getDescription(String seperator)
    {
        return mRegionNum+seperator+mName+seperator+mPhone+seperator+mAddress+seperator+mEmail;
    }
}

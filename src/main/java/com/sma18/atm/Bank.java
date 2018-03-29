package com.sma18.atm;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private String bankCode;
    private ArrayList<Passbook> accountList;

    private String createAccount() {     //랜덤 8자리의 계좌번호 리턴, 같은 은행끼리의 번호는 겹치지 않는다
        String acc = "";
        Random rn = new Random();
        ArrayList<String> accList = new ArrayList<String>();
        for (Passbook anAccountList : accountList) {
            accList.add(anAccountList.getAccount());
        }
        do {
            for (int i = 0; i < 8; i++)
                acc += rn.nextInt(10);
            acc = bankCode + acc;
        } while (accList.contains(acc));
        return acc;
    }

    public Bank(String name, String bankCode) {
        this.name = name;
        this.bankCode = bankCode;
        this.accountList = new ArrayList<Passbook>();
    }

    public void addPassbook(Passbook pb) {
        pb.setAccount(createAccount());
        accountList.add(pb);
    }

    public Passbook searchPassbook(String account) {
        Passbook temp = null; //예외처리 고려해보자
        for (Passbook anAccountList : accountList) {
            if (anAccountList.getAccount().equals(account))
                temp = anAccountList;
        }
        return temp;
    }

    public String getName() {
        return this.name;
    }

    public String getBankCode() {
        return bankCode;
    }
}

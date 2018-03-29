package com.sma18.atm;

public class Passbook {
    private String account;
    private int balance;
    private String password;
    private Bank bank;

    public Passbook(Bank bank, String password) {
        this.bank = bank;
        bank.addPassbook(this);
        this.balance = 0;
        this.password = password;
    }

    public Bank getBank() {
        return bank;
    }

    public String getAccount() {
        return this.account;
    }

    public int getBalance() {
        return balance;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void addBalance(int add) {
        this.balance += add;
    }

    public boolean subBalance(int sub) { //성공적으로 차감되면 true
        boolean ret = false;
        if (balance >= sub) {
            balance -= sub;
            ret = true;
        }
        return ret;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

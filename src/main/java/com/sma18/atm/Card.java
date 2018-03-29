package com.sma18.atm;

public class Card {
    private String cardNum;
    private String password;
    private Passbook passbook;

    public Card(Passbook passbook) {
        this.passbook = passbook;
        this.cardNum = "0000";
        this.password = passbook.getPassword(); //따로 입력 없을 시 통장 비밀번호와 같게 설정
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Passbook getPassbook() {
        return this.passbook;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

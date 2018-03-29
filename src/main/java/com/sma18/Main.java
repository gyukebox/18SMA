package com.sma18;

import com.sma18.atm.ATM;
import com.sma18.atm.Bank;
import com.sma18.atm.Passbook;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Bank A = new Bank("A", "01");
        Bank B = new Bank("B", "02");
        Bank C = new Bank("C", "03");
        Map<String, Bank> bankCode = new HashMap<String, Bank>();
        bankCode.put(A.getBankCode(), A);
        bankCode.put(B.getBankCode(), B);
        bankCode.put(C.getBankCode(), C);
        ATM atm = new ATM(A, bankCode);
        Passbook p1 = new Passbook(A, "1234");
        Passbook p2 = new Passbook(A, "4321");

        atm.input(p1);
        atm.withdraw(1000);
        atm.deposit(2000);
        atm.check();
        atm.transfer(2000, p2.getAccount());
        atm.check();
        System.out.println(p2.getBalance());
    }
}

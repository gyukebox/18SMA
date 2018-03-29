package com.sma18.atm;

import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<String, Bank> bankCodeMap;
    private Bank bank;                  //ATM 은행 종류
    private int currentCash;            //ATM에 남아잇는 현금
    private Passbook currentPassbook;   //현재 입력받은 통장
    private Card currentCard;
    private Scanner scanner = new Scanner(System.in);

    private Bank getBank(String account) {
        return bankCodeMap.get(account.substring(0, 2));
    }

    public ATM(Bank bank, Map<String, Bank> map) {
        this.currentCash = 0;
        this.bank = bank;
        bankCodeMap = map;
        currentPassbook = null;
        currentCard = null;
    }

    public void input(Passbook pb) {
        currentPassbook = pb;
    }

    public void input(Card card) {
        currentPassbook = card.getPassbook();
        currentCard = card;
    }

    public void input(Bank bank, String accountNum) {
        currentPassbook = bank.searchPassbook(accountNum);
        if (currentPassbook == null)
            System.out.println("유효한 계좌가 아닙니다");
    }

    public boolean withdraw(int cash) {        //입금
        currentPassbook.addBalance(cash);
        currentCash += cash;
        return true;
    }

    public boolean deposit(int cash) {         //출금
        boolean ret = false;
        //은행 비교해서 수수료
        if (currentCash < cash)
            System.out.println("ATM의 현금이 부족합니다");
        else {
            if (currentPassbook.subBalance(cash)) {
                currentCash += cash;
                ret = true;
            } else
                System.out.println("통장 잔고가 부족합니다.");
        }
        return ret;
    }

    public boolean transfer(int cash, String account) {        //송금
        boolean ret = false;
        Passbook desPb = getBank(account).searchPassbook(account);
        if (currentPassbook.subBalance(cash)) {
            currentCash += cash;
            desPb.addBalance(cash);
            //은행 비교해서 수수료
            ret = true;
        } else
            System.out.println("통장 잔고가 부족합니다.");
        return ret;
    }

    public boolean check() {                   //잔금확인
        System.out.println("잔고 : " + currentPassbook.getBalance() + "원");
        return true;
    }

    public void printReceipt() {

    }

    public boolean task(int flag) {    //1:withdraw 2:deposit 3:transfer 4:check
        int cash;
        boolean ret = false;
        switch (flag) {
            case 1:
                System.out.print("입금할 금액을 입력해주세요 : ");
                cash = scanner.nextInt();
                ret = withdraw(cash);
                break;
            case 2:
                System.out.print("출금할 금액을 입력해주세요  : ");
                cash = scanner.nextInt();
                ret = deposit(cash);
                break;
            case 3:
                System.out.print("송금할 계좌를 입력해주세요 : ");
                String acc = scanner.nextLine();
                System.out.print("송금할 금액을 입력해주세요 : ");
                cash = scanner.nextInt();
                ret = transfer(cash, acc);
                break;
            case 4:
                ret = check();
                break;
            default:
                System.out.println("잘못된 입력입니다");
        }
        return ret;
    }

    public void process() {
        int flag = 0; //1:withdraw 2:deposit 3:transfer 4:check
        String tmpPW, pw;
        //input
        System.out.println("작업를 선택해주세요 (1:입금 2:출금 3:송금 4:잔액확인)");
        flag = scanner.nextInt();
        System.out.print("비밀번호를 입력해주세요 : ");
        tmpPW = scanner.nextLine();
        if (currentCard == null)
            pw = currentPassbook.getPassword();
        else
            pw = currentCard.getPassword();
        boolean pwFlag = false;
        do {    //루프문 빠져나올 조건 추가해야함
            tmpPW = scanner.nextLine();
            if (pw.equals(tmpPW))
                pwFlag = true;
            else
                System.out.println("틀린 비밀번호 입니다. 다시 입력해주세요");
        } while (!pwFlag);
        task(flag);
        printReceipt();
    }

}

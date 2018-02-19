package com.javarush.task.task25.task2513;

import java.util.Random;

/* 
Обеспечение отсутствия прерывания важной операции

Логика очень простая: В задаче два запроса.
Первый списание средств с одного счета, второй — зачисление этих средств на другой счет.
Пошел запрос на списание (Необходимо изменить баланс) и если на том конце произойдет разрыв
соединения или другими словами время соединения (RANDOM) будет больше порогового значения (THRESHOLD_VALUE),
то операцию продолжать нельзя и нужно ее отъелдить (передать другому потоку, который проверит соединение и т.д. и т.п.).
*/
public class Solution {
    private static final Integer THRESHOLD_VALUE = 500;
    private static final Random RANDOM = new Random();

    public synchronized void moveMoney(Account from, Account to, int amount) {
        from.setBalance(from.getBalance() - amount);
        if(RANDOM.nextInt(5000) > THRESHOLD_VALUE)
            Thread.yield();
        to.setBalance(to.getBalance() + amount);
    }

    class Account {
        private int balance;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Account account1 = solution.new Account();
        account1.setBalance(1000000);
        Account account2 = solution.new Account();
        solution.moveMoney(account1, account2, RANDOM.nextInt(5000));
        System.out.println("account1 = " + account1.getBalance());
        System.out.println("account2 = " + account2.getBalance());
    }
}

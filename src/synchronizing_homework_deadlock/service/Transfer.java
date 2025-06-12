package synchronizing_homework_deadlock.service;

import synchronizing_deadlock_04.bank.model.Account;

public class Transfer implements Runnable {

    private Account accFrom;
    private Account accTo;
    private int sum;

    public Transfer(Account accFrom, Account accTo, int sum) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.sum = sum;
    }


    @Override
    public void run() {

        // To avoid deadlock between two clients, process them in the same order.

        Account first, second;
        if (accFrom.getAccNumber() < accTo.getAccNumber()) {
            first = accFrom;
            second = accTo;
        } else {
            first = accTo;
            second = accFrom;
        }

        synchronized (first) {
            try {
                Thread.sleep(1000); // test delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (second) {
                if (accFrom.getBalance() >= sum) {
                    accFrom.credit(sum);
                    accTo.debit(sum);
                }
            }
        }
    }


}

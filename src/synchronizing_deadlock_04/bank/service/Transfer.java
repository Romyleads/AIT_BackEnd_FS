package synchronizing_deadlock_04.bank.service;

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
        synchronized (accFrom) {
            try {
                Thread.sleep(1000); // ставим задержку, смотрим в jconsole состояние мертвого удерживания локов
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (accTo) {
                if (accFrom.getBalance() >= sum) {
                    accFrom.credit(sum);
                    accTo.debit(sum);

                }
            }
        }
    }

}

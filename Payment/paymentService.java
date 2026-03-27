package Payment;

class paymentService {
    paymentRepository p;
    LockAccount lock;
    ledgerService ledgerService;

    paymentService(paymentRepository p, LockAccount lock, ledgerService l) {
        this.p = p;
        this.lock = lock;
        this.ledgerService = l;
    }

    public boolean sendMoney(int from, int to, int amount, int id) {
        if (p.isExist(id)) {
            return false;
        }
        payment pay = new payment(id,from,to,amount);
        p.savePayment(pay);

        lock.Lock();

        int balance = ledgerService.getBalance(from);
        if (balance < amount) {
            System.out.println("Balance Is Low For This Transaction!");
            lock.Unlock();
            p.getAcconut(id).markFailed();
            System.out.println("Status: "+p.getAcconut(id).getStatus());
            return false;
        }
        ledgeEntry sender = new ledgeEntry(from,entryType.DEBIT,amount);
        ledgerService.addEntry(sender);
        p.getAcconut(id).markDebitDone();
        System.out.println("Status: "+p.getAcconut(id).getStatus());

        ledgeEntry receiver = new ledgeEntry(to,entryType.CREDIT,amount);
        ledgerService.addEntry(receiver);
        p.getAcconut(id).markCreditDone();
        System.out.println("Status: "+p.getAcconut(id).getStatus());

        p.getAcconut(id).markSuccess();
        System.out.println("Status: "+p.getAcconut(id).getStatus());

        lock.Unlock();
        return true;
    }
}

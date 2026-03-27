package Payment;

class RecoverClass{
    private ledgerService ledgerService;
    private paymentRepository paymentRepository;

    RecoverClass(ledgerService l,paymentRepository p) {
        this.ledgerService = l;
        this.paymentRepository = p;
    }

    public void recoverTransactions() {
        for (int i: paymentRepository.getRepo().keySet()) {
            paymentStatus failed = paymentStatus.DEBIT_DONE;
            payment p = paymentRepository.getRepo().get(i);
            if (failed == p.getStatus()) {
                ledgeEntry ledgeEntry = new ledgeEntry(p.getFromUser(),entryType.CREDIT,p.getAmount());
                ledgerService.addEntry(ledgeEntry);
            }
        }
        System.out.println("Recovered Successfully!");
    }
}

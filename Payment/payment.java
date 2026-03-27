package Payment;

class payment{
    private int paymentId;
    private int fromUser;
    private int toUser;
    private int amount;
    private paymentStatus status = paymentStatus.INITIATED;

    public payment(int paymentId, int fromUser, int toUser, int amount) {
        this.paymentId = paymentId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
    }

    public void markDebitDone() {
        this.status = paymentStatus.DEBIT_DONE;
    }
    public void markCreditDone() {
        this.status = paymentStatus.CREDIT_DONE;
    }
    public void markSuccess() {
        this.status = paymentStatus.SUCCESS;
    }
    public void markFailed() {
        this.status = paymentStatus.FAILED;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getFromUser() {
        return fromUser;
    }

    public int getAmount() {
        return amount;
    }

    public paymentStatus getStatus() {
        return status;
    }
}

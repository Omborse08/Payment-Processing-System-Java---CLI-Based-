package Payment;

class ledgeEntry {
    private int entryid;
    private entryType type;
    private int amount;

    public ledgeEntry(int userid, entryType type, int amount) {
        this.entryid = userid;
        this.type = type;
        this.amount = amount;
    }

    public int getEntryid() {
        return entryid;
    }

    public entryType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}



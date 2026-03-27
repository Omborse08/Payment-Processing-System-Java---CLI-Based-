package Payment;

import java.util.LinkedList;
import java.util.List;

class ledgerService {
    private List<ledgeEntry> entries = new LinkedList<>();

    public void addEntry(ledgeEntry ledgeEntry) {
        entries.add(ledgeEntry);
    }

    public int getBalance(int id) {
        int balance = 0;
        for (int i=0 ; i<entries.size() ; i++) {
            ledgeEntry e = entries.get(i);
            if (e.getEntryid() == id) {
                if (entryType.CREDIT == e.getType()) {
                    balance += e.getAmount();
                }
                if (entryType.DEBIT == e.getType()) {
                    balance -= e.getAmount();
                }
            }
        }
        return balance;
    }

    public void history(int id) {
        for (int i = 0;i<entries.size();i++) {
            ledgeEntry e = entries.get(i);
            if (id == e.getEntryid()) {
                System.out.println("ID: "+e.getEntryid()+" ,Amount: "+e.getAmount()+" ,Type: "+e.getType());
            }
        }
    }
}

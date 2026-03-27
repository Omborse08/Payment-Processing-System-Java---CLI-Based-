package Payment;
import java.util.*;
class paymentRepository{
    private Map<Integer,payment> repo = new HashMap<>();

    public Map<Integer, payment> getRepo() {
        return repo;
    }

    public void savePayment(payment pay) {
        repo.put(pay.getPaymentId(),pay);
    }

    public boolean isExist(int id) {
        if (repo.containsKey(id)) {
            return true;
        }
        return false;
    }

    public payment getAcconut(int id) {
        return repo.get(id);
    }

}
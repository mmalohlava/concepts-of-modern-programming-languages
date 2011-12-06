import org.multiverse.api.references.IntRef
import static org.multiverse.api.StmUtils.newIntRef
@Grab(group = 'org.multiverse', module = 'multiverse-beta', version = '0.7-RC-1') import groovyx.gpars.stm.GParsStm

public class Account {
    private final IntRef amount = newIntRef(0);

    public void transfer(final int a) {
        GParsStm.atomic {
            amount.increment(a);
        }
    }

    public int getCurrentAmount() {
        GParsStm.atomicWithInt {
            amount.get();
        }
    }
}

final Account account = new Account()
account.transfer(10)
def t1 = Thread.start {
    account.transfer(2)
    account.transfer(3)
}
def t2 = Thread.start {
    account.transfer(20)
    account.transfer(30)
}

[t1, t2]*.join()
println account.currentAmount


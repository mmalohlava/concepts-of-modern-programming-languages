import java.util.concurrent.locks.ReentrantLock

ReentrantLock lock = new ReentrantLock()

lock.lock()
try {
    println 'Holding the lock'
} finally {
    lock.unlock()
}
println 'Not holding the lock'






ReentrantLock.metaClass.withLock = {nestedCode ->
    delegate.lock()
    try {
        nestedCode()
    } finally { delegate.unlock() }
}

//TASK Protect the content of the following code block with the lock defined earlier and leveraging the simple locking DSL
final t = Thread.start {
    //xxx {
    println 'Holding the lock'
    //}
    println 'Not holding the lock'
}
t.join()
println 'done'


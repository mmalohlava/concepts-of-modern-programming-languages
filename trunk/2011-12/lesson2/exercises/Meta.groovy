final a = 'foo'

assert a.respondsTo('trim')
assert a.respondsTo('trim', [].toArray())
println 'trim() ' + a.respondsTo('trim')
println 'MetaMethod for trim() ' + a.metaClass.getMetaMethod('trim')
println '==========================================='
println 'getBytes ' + a.hasProperty('bytes')
println 'MetaProperty for getBytes ' + a.metaClass.getMetaProperty('bytes')
println '==========================================='




println a.respondsTo('trim')
println '   groovy '.trim()
println '==========================================='
use(TrimCategory) {

//TASK Find out which method gets invoked here

    println a.respondsTo('trim')
    println '   groovy '.trim()
}

println 'done'


class TrimCategory {
    static final metaMethod = String.metaClass.getMetaMethod('trim')

    static String trim(String s) {
        '*' + metaMethod.invoke(s, [] as Class[]) + '*'
    }
}

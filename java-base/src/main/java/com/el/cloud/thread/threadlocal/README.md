场景一：
每个线程需要一个独享对象（通常是工具类，典型需要使用的类有SimpleDateFormat和Random）

每个Thread内有自己的实例副本，不共享

比喻：教材只有一本，一起做笔记有线程安全问题。复印后没有问题，使用ThradLocal相当于复印了教材。
场景二：
每个线程内需要保存全局变量（例如在拦截器中获取用户信息），可以让不同方法直接使用，避免参数传递的麻烦

##ThreadLocal的总结
让某个需要用到的对象实现线程之间的隔离（每个线程都有自己独立的对象）
可以在任何方法中轻松的获取到该对象
根据共享对象生成的时机选择使用initialValue方法还是set方法
对象初始化的时机由我们控制的时候使用initialValue 方式
如果对象生成的时机不由我们控制的时候使用 set 方式
##使用ThreadLocal的好处
达到线程安全的目的
不需要加锁，执行效率高
更加节省内存，节省开销
免去传参的繁琐，降低代码耦合度
##ThreadLocal注意点
1.内存泄漏
内存泄露；某个对象不会再被使用，但是该对象的内存却无法被收回
强引用：当内存不足时触发GC，宁愿抛出OOM也不会回收强引用的内存

弱引用：触发GC后便会回收弱引用的内存

正常情况

当Thread运行结束后，ThreadLocal中的value会被回收，因为没有任何强引用了

非正常情况

当Thread一直在运行始终不结束，强引用就不会被回收，存在以下调用链 Thread-->ThreadLocalMap-->Entry(key为null)-->value因为调用链中的 value 和 Thread 存在强引用，所以value无法被回收，就有可能出现OOM。

JDK的设计已经考虑到了这个问题，所以在set()、remove()、resize()方法中会扫描到key为null的Entry，并且把对应的value设置为null，这样value对象就可以被回收。

#如何避免内存泄漏
但是只有在调用set()、remove()、resize()这些方法时才会进行这些操作，如果没有调用这些方法并且线程不停止，那么调用链就会一直存在，所以可能会发生内存泄漏。

##ThreadLocal的空指针异常问题
如果get方法返回值为基本类型，则会报空指针异常，如果是包装类型就不会出错。这是因为基本类型和包装类型存在装箱和拆箱的关系，造成空指针问题的原因在于使用者。
##共享对象
如果在每个线程中ThreadLocal.set()进去的东西本来就是多个线程共享的同一对象，比如static对象，
那么多个线程调用ThreadLocal.get()获取的内容还是同一个对象，还是会发生线程安全问题。
##可以不使用ThreadLocal就不要强行使用
 如果在任务数很少的时候，在局部方法中创建对象就可以解决问题，这样就不需要使用ThreadLocal。
##优先使用框架的支持，而不是自己创造
 例如在Spring框架中，如果可以使用RequestContextHolder，那么就不需要自己维护ThreadLocal，因为自己可能会忘记调用remove()方法等，造成内存泄漏。 
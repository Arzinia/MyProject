# Java基础
## 1. 多态

**多态是同一个行为具有多个不同表现形式或形态**。实现：Java提供了编译时多态和运行时多态两种多态机制。前者是通过方法重载实现的，后者是通过方法的重写实现的。在方法覆盖中，子类可以覆盖父类的方法，因此同类的方法会在父类与子类中有着不同的表现形式。多态存在的三个必要条件 1.继承：方法覆盖 2.重写：方法重写 3.父类引用指向子类对象：引出动态绑定机制（只有实例方法是动态绑定的）

## 2. 重载与重写

方法的重载和重写都是实现多态的方式，区别在于前者实现的是编译时的多态性，而后者实现的是运行时的多态性。重载发生在一个类中，同名的方法如果有不同的参数列表（参数类型不同、参数个数不同或者二者都不同）则视为重载；重写发生在子类与父类之间，重写要求子类被重写方法与父类被重写方法有相同的参数列表，有兼容的返回类型，比父类被重写方法更好访问，不能比父类被重写方法声明更多的异常（里氏代换原则）

### 重载的例子：

```java
public class OverloadTest {
    public static void print(String s,Integer a){
        System.out.println(s+" is "+String.valueOf(a));
    }
    public static void print(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {
        String s = "Yujie";
        Integer a = 24;
        print(s,a);
        print(s);
    }
}
```

### 程序的运行结果：

```java
Yujie is 24
Yujie
```

### 重写的例子：

```java
class Father{
    public void Age(Integer age){
        System.out.println("The father is "+age+" years old");
    }
}
class Son extends Father{
    public void Age(Integer age){
        Integer sonAge = age-25;
        System.out.println("The son is "+sonAge+" years old");
    }
}
public class OverrideTest {
    public static void main(String[] args) {
        Integer age = 28;
        Father father = new Father();
        Father son = new Son();
        father.Age(age);
        son.Age(age);
    }
}
```

### 程序的运行结果

```java
The father is 28 years old
The son is 3 years old
```

# 3. final，finally，finalize的区别

## 简单区别

final用于声明属性，方法和类，分别表示属性不可交变，方法不可覆盖，类不可继承

finally是异常处理语句结构的一部分，表示总是执行

finalize是Object类的一个方法，在垃圾收集器执行的时候会调用被回收对象的此方法，供垃圾收集时的其他资源回收，例如关闭文件等

## 详细区别

虽然这个单词在Java中都存在，但是并没太多关联

**final: Java中的关键字，修饰符**

1. 如果一个类被声明为final，就意味着它不能再派生出新的子类，不能作为父类被继承。因此，一个类不能同时被声明为abstract抽象类的和final的类
2. 如果将变量或者方法声明为final，可以保证它们在使用中不被改变
3. 被声明为final的变量必须在声明时给定初值，而在以后的引用中只能读取，不可修改
4. 被声明final的方法只能使用，不能重载

**finally: Java的一种异常处理机制**

finally是对Java异常处理模型的最佳补充。finally结构使代码总会执行，而不管无异常发生。使用finally可以维护对象的内部状态，并可以清理非内存资源。特别是在关闭数据库连接这方面，如果程序员把数据库连接的close()方法放到finally中，就会大大降低程序出错的几率。

**finalize: Java中的一个方法名**

Java技术使用finalize()方法在垃圾收集器将对象从内存中清除出去前，做必要的清理工作。这个方法是由垃圾收集器在确定这个对象没被引用时对这个对象调用的。它是在Object类中定义的，因此所的类都继承了它。子类覆盖finalize()方法以整理系统资源或者执行其他清理工作。finalize()方法是在垃圾收集器删除对象之前对这个对象调用的

# 4. 抽象类和接口的区别

1. 抽象类需要被子类继承，接口需要被类实现
2. 抽象类中的方法需要被子类全部实现，如果不能全部实现，该子类只能是抽象类；接口中的方法如果不能被全部实现，那么该类必须标明为抽象类
3. 抽象类只能继承一个抽象类，接口可以继承多个接口，也可以实现多个接口
4. 抽象类中可以进行方法实现以及方法申明，接口只能进行方法申明
5. 抽象类中的变量是普通变量，接口中的变量默认都是public static的

# 5. Java中的反射

定义：Java反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为Java语言的反射机制。反射机制允许我们在运行时发现和使用类型的信息。实现：在Java中实现反射最重要的一步，也是第一步就是获取Class对象，得到Class对象后可以通过该对象调用相应的方法来获取该类中的属性、方法以及调用该类中的方法

**Class对象：**

在Java中用来表示运行时类型信息的对应类就是Class类，在java.lang包中。每当我们编写并且编译一个新创建的类就会产生一个对应Class对象并且这个Class对象会被保存在同名.class文件里

**被动加载Class对象**

当我们new一个新对象或者引用静态成员变量时，Java虚拟机(JVM)中的类加载器子系统会将对应Class对象加载到JVM中，然后JVM再根据这个类型信息相关的Class对象创建我们需要实例对象或者提供静态变量的引用值。（也就是触发一个类的初始化，初始化之前必须加载了个类，加载一个类就加载了Class对象）

**主动加载Class对象**

1.从全限定名：Class.forName() 一般用这个，灵活，可以传入字符串，装入类，并做类的静态初始化，不做非静态初始化，返回Class的对象

2.从对象:Object.getClass() 对类进行静态初始化、非静态初始化；返回引用运行时真正所指的对象所属的类的Class的对象

3. Object.class：将类装入内存，不做类的初始化工作，返回Class的对象

## 举例：利用反射获取unsafe类下的方法：

```java
import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class UnsafeInstance {
    public static Unsafe reflectGetUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
```

# 6. Java中的关键字



**1. transient:**

修饰的变量不会被序列化，如果一个引用类型被transient修饰，则其反序列化的值为null，如果一个基本类型被transient修饰，则其反序列化的值为0

**2.  static：**

1 ) 用来修饰成员变量，将其变为类的成员，从而实现所有对象对于该成员的共享；

2 ) 用来修饰成员方法，将其变为类方法，可以直接使用“类名.方法名”的方式调用，常用于工具类。静态方法不能调用非静态方法或成员变量，因为这两者都依附于对象。 而非静态方法可以调用静态成员变量或方法。

3 ) 静态块用法，将多个类成员放在一起初始化，使得程序更加规整，其中理解对象的初始化过程非常关键

# 7. Java中的线程池

**ThreadPoolExecutor线程池的核心参数：**

1. corePoolSize：线程池的核心线程数，说白了就是，即便是线程池里没有任何任务，也会有corePoolSize个线程在候着等任务
2. maximumPoolSize:最大线程数，不管你提交多少任务，线程池里最多工作线程数就是maximumPoolSize
3. keepAliveTime:线程的存活时间。当线程池里的线程数大于corePoolSize时，如果等了keepAliveTime时长还没有任务可执行，则线程退出
4. unit：用来指定keepAliveTime的单位，比如秒:TimeUnit.SECONDS
5. workQueue：一个阻塞队列，如果任务数超过核心线程数，提交的任务将会被放到这个队列里
6. threadFactory：线程工厂，用来创建线程，主要是为了给线程起名字，默认工厂的线程名字：pool-1-thread-3
7. handler：拒绝策略，当线程池里线程被耗尽，且队列也满了的时候会调用

**线程池的工作流程：**

任务被提交到线程池，会先判断当前线程数量是否小于corePoolSize，如果小于则创建线程来执行提交的任务，否则将任务放入workQueue队列，如果workQueue满了，则判断当前线程数量是否小于maximumPoolSize,如果小于则创建线程执行任务，否则就会调用handler，以表示线程池拒绝接收任务

**线程池的拒绝策略：**

1. ThreadPoolExecutor.AbortPolicy  丢弃任务，并抛出 RejectedExecutionException 异常
2. ThreadPoolExecutor.CallerRunsPolicy：该任务被线程池拒绝，由调用 execute方法的线程执行该任务
3. ThreadPoolExecutor.DiscardOldestPolicy ： 抛弃队列最前面的任务，然后重新尝试执行任务
4. ThreadPoolExecutor.DiscardPolicy：丢弃任务，不过也不抛出异常

**线程池的种类：**

**1、newCachedThreadPool**

创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。这种类型的线程池特点是：工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统OOM。

**2、newFixedThreadPool**

创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。

**3、newSingleThreadExecutor**

创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。如果这个线程异常结束，会有另一个取代它，保证顺序执行。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。

**4、newScheduleThreadPool**

创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。

**5、newSingleThreadScheduledExecutor**

创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。线程池中最多执行1个线程，之后提交的线程活动将会排在队列中以此执行并且可定时或者延迟执行线程活动

# 8. ListIterator和Iterator迭代器的区别

**Iterator迭代器包含的方法有：**

hasNext()：如果迭代器指向位置后面还有元素，则返回 true，否则返回

falsenext()：返回集合中Iterator指向位置后面的元素

remove()：删除集合中Iterator指向位置后面的元素

**ListIterator迭代器包含的方法有：**

add(E e): 将指定的元素插入列表，插入位置为迭代器当前位置之前

hasNext()：以正向遍历列表时，如果列表迭代器后面还有元素，则返回 true，否则返回

falsehasPrevious():如果以逆向遍历列表，列表迭代器前面还有元素，则返回 true，否则返回

falsenext()：返回列表中ListIterator指向位置后面的元素

nextIndex():返回列表中ListIterator所需位置后面元素的索引

previous():返回列表中ListIterator指向位置前面的元素

previousIndex()：返回列表中ListIterator所需位置前面元素的索引

remove():从列表中删除next()或previous()返回的最后一个元素（有点拗口，意思就是对迭代器使用hasNext()方法时，删除ListIterator指向位置后面的元素；当对迭代器使用hasPrevious()方法时，删除ListIterator指向位置前面的元素）

set(E e)：从列表中将next()或previous()返回的最后一个元素返回的最后一个元素更改为指定元素e

**不同点**

1. 使用范围不同，Iterator可以应用于所有的集合，Set、List和Map和这些集合的子类型。而ListIterator只能用于List及其子类型

2. ListIterator有add方法，可以向List中添加对象，而Iterator不能

3. ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历，但是ListIterator有hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator不可以

4. ListIterator可以定位当前索引的位置，nextIndex()和previousIndex()可以实现。Iterator没有此功能

# 9. Java中同步的实现机制

1. 同步方法，使用synchronized修饰方法，如果修饰的是普通方法，则会锁住当前实例对象，修饰的是静态方法，锁住的是当前类的class对象

2. 同步代码块，使用synchronized修饰代码块，锁住的是括号里配置的对象

3. 使用volatile修饰变量

4. 使用ReentrantLock实现线程同步，ThreadLocal类用来放线程的局部变量，每个线程都有单独的局部变量，彼此不共享

5. 使用ThreadLocal

6. 使用阻塞队列实现同步：LinkedBlockingQueue

7. 使用原子操作类：AtomicInteger

# 10. volatile关键字：

**作用：**

1. 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的

2. 禁止进行指令重排序3.volatile 只能保证对单次读/写的原子性。i++ 这种操作不能保证原子性

**实现原理：**

1. 有volatile变量修饰的共享变量进行写操作的时候会使用CPU提供的Lock前缀指令。Lock前缀的指令作用是将CPU高速缓存中的数据写回到系统内存中，其他CPU通过总线嗅探机制感知到变量是无效的，下一次使用时候要重新从内存中拿

2. 在每个volatile写操作的前面插入一个StoreStore屏障，在每个volatile写操作的后面插入一个StoreLoad屏障。在每个volatile读操作的后面插入一个LoadLoad屏障。在每个volatile读操作的后面插入一个LoadStore屏障（store存储，线程工作内存到主内存，load装载，把主内存中的数据装载到线程的工作内存中）

**volatile举例：**

```java
public class VolatileTest extends Thread{
    private static volatile boolean flag = false;
    @Override
    public void run() {
        while (!flag){
        }
        System.out.println("标志位被更新");
    }
    public static void main(String[] args) throws InterruptedException {
        new VolatileTest().start();
        Thread.sleep(2000);
        flag = true;
    }
}
```

上述程序中，如果flag变量未被volatile关键字修饰，则程序一直会阻塞

# 11. synchronized关键字：

**作用**：synchronized可以保证在同一个时刻，只有一个线程可以执行某个方法或者某个代码块(主要是对方法或者代码块中存在共享数据的操作)，synchronized可保证一个线程的变化(主要是共享数据的变化)被其他线程所看到（保证可见性）

**底层原理**：synchronized修饰的方法锁住的是调用这个方法的对象，在每个对象的对象头中都有一个monitor对象，每个线程想获得锁的时候必须获得monitor对象，获得后monitor对象中的计数器会+1，释放锁的时候计数器-1；monitor在Java中是通过ObjectMonitor实现，ObjectMonitor中有两个队列，_WaitSet 和 _EntryList，用来保存ObjectWaiter对象列表(每个等待锁的线程都会被封装成ObjectWaiter对象)，_owner指向持有ObjectMonitor对象的线程

**synchronized关键字举例：**

```java
public class SynchronizedTest implements Runnable{
    private static int sum = 0;
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedTest());
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(sum);
    }

    @Override
    public void run() {
        synchronized (SynchronizedTest.class){
            for(int i = 0;i<10000;i++){
                sum++;
            }
        }
    }
}
```

**程序的运行结果：**

```java
100000
```

如果不加synchronized关键字，则输出的结果总是小于100000，且每次结果都不一样

# 12.锁膨胀机制：

**偏向锁**：如果一个线程获得了锁，那么锁立即就进入偏向模式，此时Mark Word 的结构也变为偏向锁结构，当这个线程再次请求锁时，无需再做任何同步操作，就获取锁（也就是一个线程想要拿到锁，就去测试对象头的MarkWord中有没有指向自己的记录，如果有，则说明自己以前拿到过偏向锁，而且此间没有发生过竞争，锁一直在自己这里，则直接用即可。如果MarkWord中没有指向自己的记录，则去测试偏向锁记录是否还是1，如果还是，则说明没有膨胀成轻量级锁，只是期间别的线程用了这个锁，导致记录改变了，这是就用CAS尝试把MarkWord的记录指向自己。如果偏向锁记录不是1了，说明偏向锁已经膨胀成其他锁了，这个时候只能尝试使用CAS竞争锁了）

**轻量级锁**：如果出现竞争了，第二条参与竞争的线程就会申请撤销偏向锁。偏向锁失效后，膨胀为轻量级锁。线程的会在其栈桢中创建用于存储锁记录的空间，并将对象头中的Mark Word复制到锁记录中，然后线程尝试使用CAS将对象头中的Mark Word替换为指向锁记录的指针。如果成功，当前线程获得锁，如果失败，表示其他线程竞争锁，当前线程便尝试使用自旋来获取锁，自旋若干次后还失败，就会膨胀为重量级锁

**不同**：1. 轻量级锁每次退出同步块都需要释放锁，而偏向锁是在竞争发生时才释放锁2. 每次进入退出同步块都需要CAS更新对象头3. 争夺轻量级锁失败时，自旋尝试抢占锁

# 13. synchronized关键字和Lock区别：

1. 首先synchronized是java内置关键字，在jvm层面，Lock是个java类；

2. synchronized无法判断是否获取锁的状态，Lock可以判断是否获取到锁；
3. synchronized会自动释放锁(a 线程执行完同步代码会释放锁 ；b 线程执行过程中发生异常会释放锁)，Lock需在finally中手工释放锁（unlock()方法释放锁），否则容易造成线程死锁；
4. 用synchronized关键字的两个线程1和线程2，如果当前线程1获得锁，线程2线程等待。如果线程1阻塞，线程2则会一直等待下去，而LOCK可以设定等待时间，并且可以响应中断
5. synchronized的锁可重入、不可中断、非公平，而Lock锁可重入、可中断、可公平（两者皆可）
6. Lock锁适合大量同步的代码的同步问题，synchronized锁适合代码少量的同步问题



# 14. ReentrantLock：

ReentrantLock就是持有一个名为sync的内部类，这个内部类继承了AQS，重写了tryAcquire(独占方式，尝试获取资源，获得则返回true，失败返回false)/tryRelease/tryAcquireShare/tryReleaseShare这些方法，然后ReentrantLock各个方法的实现就是调用sync的来实现。ReentrantLock还持有公平锁 FairSync和非公平锁NonfairSync两个内部类，这两个内部类继承了Sync，内部具体实现了公平锁和非公平锁的各种操作。公平锁FariSync和非公平锁NonfairSync的区别是tryAcquire的实现。每一次的tryAcquire都会检查CLH队列中是否仍有前驱的元素，如果仍然有那么继续等待，通过这种方式来保证先来先服务的原则。而在非公平锁中：首先是检查并设置锁的状态，这种方式会出现即使队列中有等待的线程，但是新的线程仍然会与排队线程中的对头线程竞争（但是排队的线程是先来先服务的），所以新的线程可能会抢占已经在排队的线程的锁，这样就无法保证先来先服务，但是已经等待的线程们是仍然保证先来先服务的。读写锁用state的低16位保存写锁(独占锁)的状态；高16位保存读锁(共享锁)的状态

# 15. AQS:

1. acquire(int)：

此方法是独占模式下线程获取共享资源的顶层入口。如果获取到资源，线程直接返回，否则进入等待队列，直到获取到资源为止1、tryAcquire(int arg)尝试直接去获取资源，如果成功则直接返回；2、如果tryAcquire(int arg)失败，则addWaiter()将该线程加入等待队列的尾部，并标记为独占模式；3、如果线程在等待过程中被中断过，它是不响应的。只是获取资源后才再进行自我中断selfInterrupt()，将中断补上。（注意：获取资源后把中断补上）

2. CLH队列中的Node结点

是对每一个访问同步代码的线程的封装，其包含了需要同步的线程本身以及线程的状态，如是否被阻塞，是否等待唤醒，是否已经被取消等。变量waitStatus则表示当前被封装成Node结点的等待状态，共有4种取值CANCELLED、SIGNAL、CONDITION、PROPAGATE

**CANCELLED**：值为1，在同步队列中等待的线程等待超时或被中断，需要从同步队列中取消该Node的结点，其结点的waitStatus为CANCELLED，即结束状态，进入该状态后的结点将不会再变化。（超时或者中断了，这个结点就没用了）

**SIGNAL**：值为-1，说白了，就是处于唤醒状态，在等待前继结点释放锁，就会通知标识为SIGNAL状态的后继结点的线程执行。

**CONDITION**：值为-2，与Condition相关，该标识的结点处于等待队列中，结点的线程等待在Condition上，当其他线程调用了Condition的signal()方法后，CONDITION状态的结点将从等待队列转移到同步队列中，等待获取同步锁

**PROPAGATE**：值为-3，与共享模式相关，在共享模式中，该状态标识结点的线程处于可运行状态。0状态：值为0，代表初始化状态。AQS在判断状态时，通过用waitStatus>0表示取消状态，而waitStatus<0表示有效状态

3. unparkSuccessor(Node) 

此方法用于唤醒等待队列中下一个线程

# 16. 线程的状态及其转化

![Image](C:\Users\ADMINI~1\AppData\Local\Temp\Image.png)

# 17. hashmap

1. 根据key获取哈希桶数组索引位置：取key的hashCode值、高位运算、取模运算。对于任意给定的对象，我们首先想到的就是把hash值对数组长度取模运算，这样一来，元素的分布相对来说是比较均匀的。但是，模运算的消耗还是比较大的，在HashMap中通过h & (table.length -1)来得到该对象的保存位，而HashMap底层数组的长度总是2的n次方，h& (length-1)运算等价于对length取模，也就是h%length，但是&比%具有更高的效率。在JDK1.8的实现中，优化了高位运算的算法，通过hashCode()的高16位异或低16位实现的：(h = k.hashCode()) ^ (h >>> 16)，主要是从速度、功效、质量来考虑的，这么做可以在数组table的length比较小的时候，也能保证考虑到高低Bit都参与到Hash的计算中，同时不会有太大的开销

2. put方法：1. 判断table是否为空或者null，为空就执行resize扩容2. 通过一中的hash算法得到键值对应该存放的位置i，如果table[i]为null的话，直接新建节点添加，添加后判断实际存在的键值对个数是否超过阈值，超过的话就进行resize扩容3. 如果table[i]不为null的话，查看是否和第一个节点的key相同，相同则直接覆盖value，不相同的话判断table[i]中存储的是否为树节点，是的话就执行红黑树的插入操作，不是的话遍历链表判断长度是否大于8，大于8则把链表转换为红黑树(红黑树的平均查找长度，也就是时间复杂度是log(n)，长度为8，查找长度为log(8)=3，因为2的3次方是8，链表的平均查找长度为n/2，当长度为8时，平均查找长度为8/2=4，这才有转换成树的必要，因为log(8)比8/2小，用树结构需要的查找步数更少)，执行红黑树的插入操作，否则就执行链表的插入操作，遍历过程中如果key相同就直接覆盖value，插入成功后，判断实际存在的键值对数量size是否超多了最大容量threshold，如果超过，进行扩容

3. 扩容机制（1.8）：1.插入键值对，发现超过阈值，首先会判断是否需要初始化，不需要的话再判断数组是否大于2^30，如果是的话直接设置为Int的最大值2^31-12.新建一个新的数组，容量为原来的容量*23.保存旧数组到oldtable，遍历旧数组的元素，计算他们在新数组中的索引，并用尾插法转移到新数组中4.重新计算扩容阈值

4. get方法：1.计算key的hash值，计算key所在的索引2.如果桶为空返回null，不为空就去看桶的第一个位置的key是否相等，相等的话就返回value值3.如果不相等，判断下一个结点是红黑树结点还是链表，是树就采用树的查询方式返回，不是的话就用链表的方式遍历返回

5. 为什么table.length总是2的n次方？1.这样在上一步hashcode和table.length-1按位与，等价于hashcode对table.length取模，而取模涉及除法运算，性能比按位与差很多2.在扩容的时候对键值对重新计算索引的时候不需要再重新计算hash值，而是直接看hashcode新增的比特是0还是1，是0就是原索引，是1就是原索引+旧的容量。并且新增的bit是0还是1可以看做是随机的，这样元素都被均匀的放在新的篮子里了

6. 红黑树：

   性质1：每个节点要么是黑色，要么是红色

   性质2：根节点是黑色

   性质3：每个叶子节点是黑色

   性质4：每个红色结点的两个子结点一定都是黑色

   性质5：任意一结点到每个叶子结点的路径都包含数量相同的黑结点
   左旋：以某个结点作为支点(旋转结点)，其右子结点变为旋转结点的父结点，右子结点的左子结点变为旋转结点的右子结点，左子结点保持不变右旋：以某个结点作为支点(旋转结点)，其左子结点变为旋转结点的父结点，左子结点的右子结点变为旋转结点的左子结点，右子结点保持不变变色：结点的颜色由红变黑或由黑变红

# 18. ConcurrentHashMap：

1.7底层采用segment数组和hashEntry（其实就是hashmap）数组组成，采用了分段锁技术，segment继承了ReentrantLock，理论上可以支持并发的数量为segment数组的长度，每当一个线程占用锁访问一个segment数组时，不会影响其他segment数组的操作

**1.put方法**：首先通过key定位到具体的segment（计算过程比较复杂，本质感觉就是取hashcode的高4位），然后调用tryLock()方法尝试获取锁，如果失败则会调用scanAndLockForPut自旋获得锁，如果自旋到一定次数会阻塞该线程，等待其他线程释放锁之后获取。获得锁之后，根据key的hashcode定位到具体的hashEntry上，再遍历，如果key相同则覆盖value，不相同则新建结点添加。最后释放锁

**2.get方法：没有什么区别，不需要加锁，因为value都用volatile修饰，保证读到的都是最新值**
**1.8放弃了分段锁，采用CAS+synchronized**

1.put方法：**当添加一对键值对的时候，首先会去判断保存这些键值对的数组是不是初始化了， 如果没有的话就初始化数组。然后通过计算hashcode来确定放在数组的哪个位置，如果这个位置为空则CAS写入。如果不为空的话，则取出这个节点来，如果取出来的节点的hash值是MOVED(-1)的话，则表示当前正在对这个数组进行扩容，复制到新的数组，则当前线程也去帮助复制。如果这个节点，不为空，也不在扩容，则通过synchronized来加锁，进行添加操作，然后判断当前取出的节点位置存放的是链表还是树，如果是链表的话，则遍历整个链表，直到取出来的节点的key来个要放的key进行比较，如果key相等，并且key的hash值也相等的话，则说明是同一个key，则覆盖掉value，否则的话则添加到链表的末尾，如果是树的话，则把这个元素添加到树中去，最后在添加完成之后，会判断在该节点处共有多少个节点（注意是添加前的个数），如果达到8个以上了的话，链表转为树
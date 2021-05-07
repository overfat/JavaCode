# JAVA虚拟机知识点

本节笔记是根据黑马程序员java虚拟机教学视频学习时，所做的笔记。

## 一、什么是JVM

### 定义

java virtual machine，JAVA程序的运行环境（JAVA二进制字节码的运行环境）

### 好处

	+ 一次编写，到处运行
	+ 自动内存管理，垃圾回收机制
	+ 数组下标越界检查

### 比较

JVM JDK JRE之间的区别

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/1.png" alt="1" style="zoom:50%;" />


## 二、内存结构

### 整体架构

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/2.png" alt="2" style="zoom:50%;" />

### 1. 程序计数器

**作用**：保存JVM中下一条所要执行的指令的地址

特点：

 + 线程私有
   	+ CPU会为每个线程分配时间片，当当前线程的时间片使用完以后，CPU就会去执行另一个线程中的代码。
   	+ 程序计数器是**每个线程私有的**，当另一个线程的时间片用完，又返回来执行当前线程的代码时，通过程序计数器可以知道应该执行哪一句指令。
+ 不会存在内存溢出

### 2. 虚拟机栈

**定义**

+ 每个线程运行需要的内存空间，称为虚拟机栈
+ 每个栈由多个栈祯组成，对应着每次调用方法时所占用的内存
+ 每个线程只能有一个活动栈祯，对应着当前正在执行的方法

**演示**

```java
public class Main{
	public static void main(String[] args){
    method1();
  }
  public static void method1(){
		method2(1,2);
  }
  public static int method2(int a, int b){
    int c = a + b;
    return c;
  }
}
```

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/3.png" alt="3" style="zoom:50%;" />

在控制台中可以看到，主类中的方法在进入虚拟机栈的时候，符合栈的特点。

**问题辨析**

+ 垃圾回收是否设计栈内存？
  + **不需要**，因为虚拟机栈中是由一个个栈帧组成的，在方法执行完毕后，对应的栈帧就会被弹出栈，所以无需要通过来集回收机制去回收内存。
+ 栈内存分配的越大越好吗？
  + 不是，因为物理内存时一定的，栈内存越大， 可以支持更多的递归调用，但是可执行的线程数就会减少。
+ 方法内的局部变量是否是线程安全的？
  + 如果方法内局部变量没有逃离方法的作用范围，那么就是安全的。
  + 如果局部变量引用了对象，并逃离了方法的作用范围，则需要考虑线程安全问题。

**内存溢出**

Java.lang.stackOverflowError 栈内存溢出

**发生原因**

	+ 虚拟机栈中，栈帧过多（无限递归）
	+ 每个栈帧所占用过大

**线程运行诊断**

**CPU占用过高（本节目前不是很熟悉，需要进一步的复习巩固）**

+ Linux环境下运行某些程序的时候，可能导致CPU的占用过高，这是需要定位占用CPU过高的线程。
  + top命令，查看是哪个进程占用CPU过高
  + ps -H -eo pid,tid(线程id), %cpu | grep 刚才通过top查到的进程号 通过ps 命令进一步查看是哪个线程占用CPU过高。
  + jstack 进程id 通过查看进程中的线程nid，刚才通过ps命令看到的tid来对比定位，注意jstack查找出的线程id是**16进制的，需要转换。**

### 3. 本地方法栈

一些带有**native关键字**的方法就是需要JAVA去调用本地的C或者是C++方法，因为JAVA有时候没法直接和操作系统底层交互，所以需要用到本地方法。

### 4.堆

**定义**

通过**new关键字创建的对象**都会被放在堆内存

**特点**

	+ 所有线程共享，堆内存中的对象都需要考虑线程安全问题
	+ 有垃圾回收机制

**堆内存溢出**

java.lang.OutofMemoryError: java heap space堆内存溢出

堆内存诊断

jps

jmap

jconsole

jvirsalvm

### 5. 方法区

**结构**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/4.png" alt="4" style="zoom:50%;" />

**内存溢出**

+ 1.8之前会导致永久代内存溢出
+ 1.8之后会导致元空间内存溢出

**常量池**

二进制字节码的组成：类的基本信息、常量池、累的方法定义（包含了虚拟机指令）

**通过反编译来查看类的信息**



**运行时常量池**

+ 常量池
  + 就是一张表（如上图中的constant pool），虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量信息。
+ 运行时常量池
  + 常量池时.class文件中的，当该类被加载以后，它的常量池信息就会放入运行时常量池，并把里面的符号地址变为真实地址。

**常量池与串池的关系**

串池StringTable

特征

+ 常量池中的字符串仅仅是符号，只有在被用到时才会转化为对象
+ 利用串池的机制，来避免重复创建字符串对象。
+ 字符串变量拼接的原理是StringBuilder
+ 字符串常量拼接的原理是编译器优化
+ 可以使用intern方法，主动将串池中还没有的字符串方法串池中
+ 注意：无论是串池还是堆里面的字符串，都是对象

用来放字符串对象且里面的**元素不重复**

```java
public class StringTableStudy {
	public static void main(String[] args) {
		String a = "a"; 
		String b = "b";
		String ab = "ab";
	}
}
```

常量池中的信息，都会被加载到运行时常量池中，但是a b ab仅仅是常量池中的符号，**还没有成为java字符串**

```java
0: ldc           #2                  // String a
2: astore_1
3: ldc           #3                  // String b
5: astore_2
6: ldc           #4                  // String ab
8: astore_3
9: return
```

当执行到 ldc#2时，会把符号a变为“a”字符串对象，并放入串池中（hashtable结构，不可扩容），下面的b和ab都一样，最终**StringTable["a","b","ab"]**

**注意**字符串的创建都是懒惰的，只有当运行到那一行字符串且不在串池中的时候（如idc#2），该字符串才会被创建并放入串池中。

**使用拼接字符串变量对象创建字符串的过程**

```java
public class StringTableStudy {
	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String ab = "ab";
		//拼接字符串对象来创建新的字符串
		String ab2 = a+b; 
	}
}
```

反编译后的结果

```java
	 Code:
      stack=2, locals=5, args_size=1
         0: ldc           #2                  // String a
         2: astore_1
         3: ldc           #3                  // String b
         5: astore_2
         6: ldc           #4                  // String ab
         8: astore_3
         9: new           #5                  // class java/lang/StringBuilder
        12: dup
        13: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
        16: aload_1
        17: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        20: aload_2
        21: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        24: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/Str
ing;
        27: astore        4
        29: return
```

通过拼接的方式来创建字符串的**过程**是，StringBuilder().append("a").append("b").toString();

最后的toString()方法的返回值是一个新的字符串，但字符串的值和拼接的字符串一致，但是是两个不同的字符串，一个存在于串池之中，一个存在于堆内存之中。

```java
String ab = "ab";
String ab2 = a+b;
Systen.out.println(ab == ab2);
// 返回值是false，因为ab是存在于串池当中，但是ab2存在于堆内存当中。
```

**使用拼接字符串常量的方法创建字符串**

```java
public class StringTableStudy {
	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String ab = "ab";
		String ab2 = a+b;
		//使用拼接字符串的方法创建字符串
		String ab3 = "a" + "b";
	}
}
```

反编译后的结果

```java
 	  Code:
      stack=2, locals=6, args_size=1
         0: ldc           #2                  // String a
         2: astore_1
         3: ldc           #3                  // String b
         5: astore_2
         6: ldc           #4                  // String ab
         8: astore_3
         9: new           #5                  // class java/lang/StringBuilder
        12: dup
        13: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
        16: aload_1
        17: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        20: aload_2
        21: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String
;)Ljava/lang/StringBuilder;
        24: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/Str
ing;
        27: astore        4
        //ab3初始化时直接从串池中获取字符串
        29: ldc           #4                  // String ab
        31: astore        5
        33: return
```

+ 使用**拼接字符串常量**的方法来创建新的字符串时，因为内容是常量，javac会在编译器进行优化，结果已经在编译器确定为ab，而创建ab的时候已经在串池中放入了“ab”，所以ab3直接从串池中获取值，所以进行的操作和ab="ab"一致
+ 使用**拼接字符串变量**的方法来创建新的字符串时，因为内容是变量，只能在运行期间确定它的值，所以需要使用StringBuilder来创建。



**intern方法1.8**

调用字符串对象的intern方法，会将该字符串对象尝试放入到串池中。

+ 如果串池中没有该字符串对象，则放入成功
+ 如果有该字符串对象，则放入失败

**无论是否放入成功，都会返回串池中的字符串对象**

注意：此时如果调用intern方法成功，对内存于串池中的字符串对象则是同一个对象；如果失败，则不是同一个对象

例一

```java
public class Main {
	public static void main(String[] args) {
		//"a" "b" 被放入串池中，str则存在于堆内存之中
		String str = new String("a") + new String("b");
		//调用str的intern方法，这时串池中没有"ab"，则会将该字符串对象放入到串池中，此时堆内存与串池中的"ab"是同一个对象
		String st2 = str.intern();
		//给str3赋值，因为此时串池中已有"ab"，则直接将串池中的内容返回
		String str3 = "ab";
		//因为堆内存与串池中的"ab"是同一个对象，所以以下两条语句打印的都为true
		System.out.println(str == st2);
		System.out.println(str == str3);
	}
}
```

例二

```java
public class Main {
	public static void main(String[] args) {
        //此处创建字符串对象"ab"，因为串池中还没有"ab"，所以将其放入串池中
		String str3 = "ab";
        //"a" "b" 被放入串池中，str则存在于堆内存之中
		String str = new String("a") + new String("b");
        //此时因为在创建str3时，"ab"已存在与串池中，所以放入失败，但是会返回串池中的"ab"
		String str2 = str.intern();
        //false
		System.out.println(str == str2);
        //false
		System.out.println(str == str3);
        //true
		System.out.println(str2 == str3);
	}
}
```

**intern方法1.6**

调用字符串对象的intern方法，会讲该字符串对象尝试放入到串池中

+ 如果串池中没有该对象，会将该字符串复制一份，然后放入到串池中
+ 如果有对象了，则放入失败。

**无论成功与否，返回串池中的字符串对象**

注意：此时无论intern方法是否执行成功，串池中的字符串对象和堆内存中的字符串对象都不是同一个对象

**StringTable垃圾回收**

StringTable在内存紧张时，会发生垃圾回收

StringTable调优

+ 因为StringTable是由HashTable实现的，所以可以适当增加HashTable桶的个数，来减少字符串放入串池中所需要的时间

```java
-XX:StringTableSize=xxx
```

+ 考虑是否需要将字符串对象入池，可以通过intern方法减少重复入池。

### 6. 直接内存

+ 属于操作系统结构，常见于NIO操作时，用于数据缓冲区
+ 分配回收成本较高，但是读写性能高
+ 不受JVM内存回收管理

**文件读写流程**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/5.png" alt="5" style="zoom:50%;" />

**使用了DirectBuffer**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/6.png" alt="6" style="zoom:50%;" />

直接内存是操作系统和Java代码都可以访问的一块区域，无需将代码从系统内存复制到Java堆内存，从而提高了效率。

**释放原理**

直接内存的回收不是通过JVM垃圾回收来释放的，而是通过unsafe.freeMemory来手动释放

通过

```java
//通过ByteBuffer申请1M的直接内存
ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1M);
```

申请直接内存，但JVM并不能回收直接内存中的阵容，它是如何实现回收的呢？

allocateDirect的实现

```java
public static ByteBuffer allocateDirect(int capacity) {
    return new DirectByteBuffer(capacity);
}
```

DirectByteBuffer类

```java
DirectByteBuffer(int cap) {   // package-private
   
    super(-1, 0, cap, cap);
    boolean pa = VM.isDirectMemoryPageAligned();
    int ps = Bits.pageSize();
    long size = Math.max(1L, (long)cap + (pa ? ps : 0));
    Bits.reserveMemory(size, cap);

    long base = 0;
    try {
        base = unsafe.allocateMemory(size); //申请内存
    } catch (OutOfMemoryError x) {
        Bits.unreserveMemory(size, cap);
        throw x;
    }
    unsafe.setMemory(base, size, (byte) 0);
    if (pa && (base % ps != 0)) {
        // Round up to page boundary
        address = base + ps - (base & (ps - 1));
    } else {
        address = base;
    }
    cleaner = Cleaner.create(this, new Deallocator(base, size, cap)); //通过虚引用，来实现直接内存的释放，this为虚引用的实际对象
    att = null;
}
```

这里调用了一个Clean的create方法，且后台线程还会对虚引用的对象监测，如果虚引用的实际对象（这里指的是DirectByteBuffer）被回收以后，就会调用Cleaner的clean方法，来清楚直接内存中占用的内存

```java
public void clean() {
       if (remove(this)) {
           try {
               this.thunk.run(); //调用run方法
           } catch (final Throwable var2) {
               AccessController.doPrivileged(new PrivilegedAction<Void>() {
                   public Void run() {
                       if (System.err != null) {
                           (new Error("Cleaner terminated abnormally", var2)).printStackTrace();
                       }

                       System.exit(1);
                       return null;
                   }
               });
           }
```

对应对象的run方法

```java
public void run() {
    if (address == 0) {
        // Paranoia
        return;
    }
    unsafe.freeMemory(address); //释放直接内存中占用的内存
    address = 0;
    Bits.unreserveMemory(size, capacity);
}
```

直接内存的回收机制总结

+ 使用了Unsafe类来完成直接内存的分配回收，回收需要主动调用的freeMemory方法。
+ ByteBuffer的实现内部使用了Cleaner(虚引用)来监测ByteBuffer。一旦ByteBuffer被垃圾回收，那么会由ReferenceHandler来调用Cleaner的clean方法调用freeMemory来释放内存。

## 三、垃圾回收

### 1、如何判断对象可以回收

**引用计数法**

弊端：循环引用时，两个对象的计数都为1，导致两个对象都无法被释放

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/7.png" alt="7" style="zoom: 50%;" />

**可达性分析算法**

+ JVM中的垃圾回收器通过可达性分析来探索所有存活的对象
+ 扫描堆中的对象，看能够沿着GC ROOT对象为起点的引用链找到该对象，如果找不到，则表示该对象可以回收
+ 可以作为GC Root的对象
  + 虚拟机栈（栈帧中的本地变量表）中引用的对象。
  + 方法区中类静态属性引用的对象
  + 方法区常量引用的对象
  + 本地方法栈中JNI（即一般说的Native方法）引用的对象

**五种引用**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/8.png" alt="8" style="zoom:50%;" />

**强引用**

只有GC Root都不引用该对象时，才会回收强引用对象。

+ 如上图中B和C都不引用A1对象时，A1对象才会被回收

**弱引用**

当GC Root指向软引用对象时，在内存不足时，会回收软引用所引用的对象

+ 如上图如果B对象不再引用A2对象且内存不足时，软引用所引用的A2对象就会被回收

软引用的使用：

```java
public class Demo1 {
	public static void main(String[] args) {
		final int _4M = 4*1024*1024;
		//使用软引用对象 list和SoftReference是强引用，而SoftReference和byte数组则是软引用
		List<SoftReference<byte[]>> list = new ArrayList<>();
		SoftReference<byte[]> ref= new SoftReference<>(new byte[_4M]);
	}
}
```

如果在垃圾回收时发现内存不足，在回收软引用所指向的对象时，**软引用本身不会被清理**

如果想要清理软引用，需要使用引用队列

```java
public class Demo1 {
	public static void main(String[] args) {
		final int _4M = 4*1024*1024;
		//使用引用队列，用于移除引用为空的软引用对象
		ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
		//使用软引用对象 list和SoftReference是强引用，而SoftReference和byte数组则是软引用
		List<SoftReference<byte[]>> list = new ArrayList<>();
		SoftReference<byte[]> ref= new SoftReference<>(new byte[_4M]);

		//遍历引用队列，如果有元素，则移除
		Reference<? extends byte[]> poll = queue.poll();
		while(poll != null) {
			//引用队列不为空，则从集合中移除该元素
			list.remove(poll);
			//移动到引用队列中的下一个元素
			poll = queue.poll();
		}
	}
}
```

大概的思路是：查看引用队列中有无软引用，如果有，则将该软引用从存放它的集合中移除（这里是一个list集合）

**弱引用**

只有弱引用引用该对象时，在垃圾回收时，无论内存是否充足，都会回收弱引用所引用的对象

+ 如上图如果B对象不再引用A3对象，则A3对象会被回收

**弱引用和软引用类似，**只是将SoftReference换为了WeakReference

**虚引用**

当虚引用对象所引用的对象被回收以后，虚引用对象就会被放入引用队列中，调用虚引用的方法

+ 虚引用的一个体现是释放直接内存所分配的内存，当引用的对象ByteBuffer被垃圾回收以后，虚引用对象Cleaner就会被放入引用队列中，然后调用Cleaner的clean方法来释放直接内存
+ 如上图：B对象不再引用ByteBuffer对象，ByteBuffer就会被回收，但是直接内存的内存还未被回收。这时需要将虚引用对象CLeaner放入引用队列中，然后调用它的clean方法来释放直接内存。

**终结器引用**

所有的类都继承自Object类，Object类有一个finalize方法，当某个对象不再被其他的对象所引用时，会先将终结器引用对象放入引用队列中，然后根据终结器引用对象找到它所引用的对象，然后调用该对象的finalize方法，调用以后，该对象就可以被垃圾回收了。

+ 如上图，B对象不再引用A4对象。这时终结器对象就会被放入引用队列中，引用队列会根据它，找到它引用的对象，然后调用被引用对象的finalize方法。调用以后，该对象就可以被垃圾回收了。

**引用队列**

+ 软引用和弱引用可以配合引用队列
  + 在弱引用和虚引用所引用的对象被回收以后，会将这些引用放入引用队列中，方便一起回收这些软/弱引用对象
+ 虚引用和终结器引用必须配合引用队列
  + 虚引用和终结器引用在使用时会关联一个引用队列



### 2、垃圾回收算法

**标记-清除**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/9.png" alt="9" style="zoom:50%;" />

定义：标记清除算法顾名思义，是指虚拟机在执行垃圾回收的过程中，先采用标记算法确定可回收对象，然后垃圾收集器根据标识清除相应的内容，给堆内存中腾出相应的空间

+ 这里腾出内存空间并不是将内存空间的字节清0，而是记录下这段内存的起始结束地址，下次分配内存的时候，会直接覆盖这段内存

**缺点：**容易产生大量的内存碎片，可能无法满足大对象的内存分配，一旦导致无法分配对象，那就会导致jvm启动gc，一旦启动gc，我们的应用程序就会暂停，这就导致应用的响应速度变慢

**标记-整理**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/10.png" alt="10" style="zoom:50%;" />

标记整理会将不被GC Root引用的对象回收，清除其占用的内存空间。然后整理剩余的对象，可以有效避免因内存碎片而导致的问题，但是因为整体需要消耗一定的时间，所以效率比较低。

**复制**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150842.png" alt="20200608150842" style="zoom:50%;" />

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150856.png" alt="20200608150856" style="zoom:50%;" />

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150907.png" alt="20200608150907" style="zoom:50%;" />

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150919.png" alt="20200608150919" style="zoom:50%;" />

将内存分为等大小的两个区域，FROM和TO（TO中为空）。先将被GC Root引用的对象从FROM放入T0中，再回收不被GC Root引用的对象。然后交换FROM和To。这样也可以避免内存碎片的问题，但是会占用双倍的内存空间。

### 3、分代回收

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150931.png" alt="20200608150931" style="zoom:50%;" />

**回收流程**

新创建的对象都被放在了**新生代的伊甸园中**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150939.png" alt="20200608150939" style="zoom:50%;" />

当伊甸园中的内存不足时，就会进行一次垃圾回收，这时的回收叫做Minor GC

Minor GC会将伊甸园和幸存去FROM存活的对象先复制到幸存区To中，并让其寿命加1，再交换两个幸存区

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150946.png" alt="20200608150946" style="zoom:50%;" />

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608150955.png" alt="20200608150955" style="zoom:50%;" />

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151002.png" alt="20200608151002" style="zoom:50%;" />

再次创建对象，若新生代的伊甸园又满了，则会再次触发Minor GC（会触发stop the world，暂停其他用户线程，只让垃圾回收线程工作），这时不仅会回收伊甸园中的垃圾，还会回收幸存区中的垃圾，再将活跃对象复制到幸存区To中。回收以后会交换两个幸存区，并让幸存区中的对象寿命加1.

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151010.png" alt="20200608151010" style="zoom:50%;" />

如果幸存区中对象的寿命超过某个阈值，就会被放入老年代中。

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151018.png" alt="20200608151018" style="zoom:50%;" />

如果新生代老年代中的内存都满了，就会先触发Minor GC，再触发Full GC，扫描新生代和老年代中所有不再使用的对象并回收。

**GC分析**

大对象处理策略

当遇到一个较大的对象时，就算新生代的伊甸园为空，也无法容纳该对象时，会将该对象直接晋升为老年代。

**线程内存溢出**

某个线程的内存溢出了而抛异常，不会让其他的线程结束运行。

这时因为当一个线程抛出OOM异常后，它所占据的内存资源会全部被释放掉，从而不会影响其他线程的运行，进程依然正常。



### 4. 垃圾回收器

**相关概念**

**并行收集**：指多条垃圾收集线程并行工作，但此时用户线程仍处于等待状态。

**并发收集**：指用户线程与垃圾收集线程同时工作（不一定是并行的可能会交替执行）。用户程序在继续运行，而垃圾收集程序运行在另一个CPU上。

**吞吐量**：即CPU用于运行用户代码的时间与CPU总消耗时间的比值（吞吐量=运行用户代码时间/{运行用户代码时间+垃圾收集时间}），也就是。例如：虚拟机共运行100分钟，垃圾收集器花掉一分钟，那么吞吐量就是99%

**串行**：

+ 单线程
+ 内存较小、个人电脑（CPU核数较少）

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151027.png" alt="20200608151027" style="zoom:50%;" />

**安全点：**让其他线程都在这个点停下来，以免垃圾回收时移动对象地址，使得其他线程找不到被移动的对象。

因为是串行的，所以只有一个垃圾回收线程，且在该线程执行回收工作时，其他线程进入阻塞状态。

**Serial收集器**

Serial收集器时最基本的、发展历史最悠久的收集器

特点：单线程、简单高效（与其他收集器的单线程相比），采用复制算法。对于限定单个CPU的环境来说，Serial收集器由于没有线程交互的开销，专心做垃圾收集自然可以获得最高的单线程收集效率。收集器进行垃圾回收时，必须暂停其他所有的工作线程，直到它结束.

**ParNew收集器**

ParNew收集器其实就是Serial收集器的多线程版本

**特点：**多线程、ParNew收集器默认开启的收集器线程数与CPU的数量相同，在CPU非常多的环境中，可以使用-XX:ParallelGCThreads参数来限制垃圾收集的线程数。和Serial收集器一样存在Stop The World的问题。

**Serial Old收集器**

Serial Old收集器时Serial的老年代版本

特点：同样是单线程收集器，采用标记-整理算法

**吞吐量优先**

+ 多线程
+ 堆内存较大、多核CPU
+ 单位时间内，STW时间最短
+ JDK1.8默认使用的垃圾回收器

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151039.png" alt="20200608151039" style="zoom:50%;" />

**Paraller Scavenge收集器**

与吞吐量关系密切，故也称为吞吐量优先收集器

特点：属于新生代收集器也是采用复制算法的收集器（用到了新生代的幸存区），又是并行的多线程收集器（与ParNew类似）

该收集器的目标是达到一个可控的吞吐量。还有一个值得关注的点是：GC自适应调节策略（与ParNew收集器最重要的一个区别）

**GC自适应掉姐策略：**Parallel Scavenge收集器可设置-XX:UseAdptiveSizePolicy参数，当开关打开时不需要手动置顶新生代的大小、Eden与Survivor区的比例、晋升老年代的对象年龄等，虚拟机会根据系统的运行状况收集性能监控信息，动态设置这些参数以提供最优的停顿时间和最高的吞吐量，这种调节方式称之为GC的自适应调节策略。

Parallel Scavenge收集器使用两个参数控制吞吐量：

+ XX:MaxGCPauseMills控制最大的垃圾收集停顿时间
+ XX:GCRatio 直接设置吞吐量的大小

**Paraller Old收集器**

是Parallel Scavenge收集器的老年代版本

特点：多线程，采用**标记-整理算法**

**响应时间优先**

+ 多线程
+ 堆内存较大、多核CPU
+ 尽可能让单次STW时间变短（尽量不影响其他线程运行）

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151052.png" alt="20200608151052" style="zoom:50%;" />

**CMS收集器**

Concurrent Mark Sweep，一种以获取最短回收停顿时间为目标的老年代收集器

**特点：**基于标记-清除算法实现。并发收集、低停顿，但是会产生内存碎片

**应用场景：**适用于注重服务的响应速度，希望系统停顿时间最短，给用户带来更好的体验等场景下。如Web程序，b/s服务。

**CMS收集器分为以下四步：**

1. 初始标记：标记GC Root能直接找到的对象。速度很快但是仍存在Stop The World问题
2. 并发标记：进行GC Root Tracing的过程，找出存活对象且用户线程可并发执行
3. 重新标记：为了修正并发标记期间因为用户程序继续运行而导致标记产生变动的那一部分对象的标记记录。仍然存在Stop the world问题。
4. 并发清除：对标记的对象进行清除

CMS收集器的内存回收过程是与用户多线程一起并发执行的



**G1**

定义： Garbage First，JDK9以后默认使用，而且替代了CMS收集器

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200909201212.png" alt="20200909201212" style="zoom:50%;" />

**适用场景**

+ 同时注重吞吐量和低延迟
+ 超大堆内存，会将对内存划分为多个大小相等的区域
+ 整体上是标记-整理算法，两个区域之间是复制算法。
+ 相关参数：JDK8并不是默认开启的，所以需要参数开启

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151100.png" alt="20200608151100" style="zoom:50%;" />

**G1垃圾回收阶段**

<img src="/Users/hanfeixiang/Desktop/IDEACode/学习笔记/pictures/20200608151109.png" alt="20200608151109" style="zoom:50%;" />

新生代伊甸园垃圾回收-->内存不足，新生代回收+并发标记 --> 回收新生代伊甸园、幸存区、老年代内存-->新生代伊甸园垃圾回收（重新开始）

**Young Collection**

**分区算法region**

分代是按对象的生命周期划分，分区则是将堆空间划分连续几个不同的小区间，每一个小区间独立回收，可以控制一次回收多少个小空间，方便控制GC产生的停顿时间。

E：伊甸园区 S：幸存区 O：老年代

+ 会STW

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.28.32.png" alt="截屏2021-05-03 上午11.28.32" style="zoom:40%;" />

---



<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.29.02.png" alt="截屏2021-05-03 上午11.29.02" style="zoom:40%;" />



<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.29.29.png" alt="截屏2021-05-03 上午11.29.29" style="zoom:40%;" />

**Young Collection + CM**

CM：并发标记

+ 在Young GC时会对GC Root进行初始标记
+ 在老年代占用堆内存的比例达到阈值时，堆进行并发标记，阈值可以根据用户来进行设定

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.31.53.png" alt="截屏2021-05-03 上午11.31.53" style="zoom:50%;" />

Mixed Collection

会对E S O进行全面的回收

+ 最终标记
+ 拷贝存活

-XX:MaxGCPauseMills:xxx用于指定最长的停顿时间

问：为什么有的老年代被拷贝了，有的却没有？

因为指定了最大停顿时间，如果对所有老年代都进行回收，耗时可能过高。为了保证时间不超过设定的停顿时间，会回收最优价值的老年代（回收后，能够得到更多内存）

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.36.46.png" alt="截屏2021-05-03 上午11.36.46" style="zoom:50%;" />

**Full GC**

G1在老年代内存不足时（老年代所占内存超过阈值）

+ 如果垃圾产生速度慢于垃圾回收速度，不会触发Full GC，还是并发地进行清理
+ 如果垃圾产生速度快于垃圾回收速度，便会触发Full GC

Young Collection跨代引用

+ 新生代回收的跨代引用（老年代引用新生代）问题

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.38.59.png" alt="截屏2021-05-03 上午11.38.59" style="zoom:50%;" />

+ 卡表与Remenbered Set
  + Remember Set存在于E中，用于保存新生代对象对应的脏卡
    + 脏卡：O被划分为多个区域（一个区域512K），如果该区域引用了新生代对象，则该区域被称为脏卡
+ 在引用变更时通过post-write barried + dirty card queue
+ concurrent refinement threads 更新Remembered Set

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.42.11.png" alt="截屏2021-05-03 上午11.42.11" style="zoom:50%;" />

**remark**

重新标记阶段

在垃圾回收时，收集器处理对象的过程中

黑色：已经被处理，需要保留的 灰色：正在处理中的，白色：还未处理的

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 上午11.43.30.png" alt="截屏2021-05-03 上午11.43.30" style="zoom:50%;" />

但是在**并发标记过程中**，有可能A被吹来了以后未引用C，但是该处理过程还未结束，在处理过程结束之前引用了C，这时就会Remark

过程如下：

+ 之前C未被引用，这时A引用了C，就会给C加一个写屏障，写屏障的指令会被执行，将C放入一个队列当中，并将C变为处理中状态
+ 在并发标记阶段结束以后，重新标记阶段会STW，然后将放在该队列中的对象重新处理，发现有强引用引用它。就会处理它

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 下午12.37.45.png" alt="截屏2021-05-03 下午12.37.45" style="zoom:50%;" />

**jdk8u20字符串去重**

过程

+ 将所有新分配的字符串（底层是char[]）放入一个队列
+ 当新生代回收时，G1并发检查是否有重复的字符串
+ 如果字符串的值一样，就让他们引用同一个字符串对象
+ 注意，其与String.intern的区别
  + intern关注的是字符串对象
  + 字符串去重关注的是char[]
  + 在JAVA内部，使用了不同的字符串标

优点与缺点

+ 节省了大量的内存
+ 新生代回收时间略微增加，导致略微占用CPU

**JDK8u40并发标记类卸载**

在并发标记阶段结束以后，就能知道哪些类不再被使用，如果一个类加载器的所有类都不在使用，则卸载它所加载的所有类

**JDK 8u60回收巨型对象**

+ 一个对象大于region的一半时，就称为巨型对象
+ G1不会对巨型对象进行拷贝
+ 回收时被优先考虑
+ G1会跟踪老年代所有incoming引用，如果老年代incoming引用为0的巨型对象就可以在新生代垃圾回收时处理掉

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 下午12.44.28.png" alt="截屏2021-05-03 下午12.44.28" style="zoom:50%;" />



### 5. GC调优

查看虚拟机参数命令

```java
"F:\JAVA\JDK8.0\bin\java" -XX:+PrintFlagsFinal -version | findstr "GC"
```

可以根据参数去查询具体的信息

**调优领域**

+ 内存
+ 锁竞争
+ CPU占用
+ IO
+ GC

**确定目标**

低延迟/高吞吐量？ 选择适合的GC

+ CMS G1 ZGC
+ ParallelGC
+ Zing

**最快的GC是不发生GC**

首先排除减少因为自身编写的代码而引发的内存问题

+ 查看Full GC前后的内存占用，考虑以下几个问题
  + 数据是不是太多？
  + 是否存在内存泄漏
  + 数据表示是否太臃肿
    + 对象图
    + 对象大小

**新生代调优**

+ 新生代的特点
  + 所有的new操作分配内存都是非常廉价的
  + 死亡对象回收零代价
  + 大部分对象用过就死
  + Minor GC所用时间远远小于Full GC
+ 新生代内存是越大越好嘛？
  + 不是
    + 新生代内存太小：频繁触发Minor GC，会STW，会使吞吐量下降
    + 新生代内存太大：老年代内存占比有所降低，会更频繁地触发Full GC，而且触发Minor GC时，清理新生代所花费的时间会更长
  + 新生代内存设置为内容制[并发量*（请求-响应）]的数据为宜

**幸存区调优**

+ 幸存区需要能够保存当前活跃对象+需要晋升的对象
+ 晋升阈值配置的当，让长时间存活的对象尽快晋升

**老年代调优**



## 四、 类加载机制

### 1. 类加载过程

类的加载过程分为三个过程（五个步骤）：加载——>连接（验证、准备、解析）——>初始化——>引用——>卸用。加载、验证、准备、初始化这四个阶段发生的顺序是确定的，而解析阶段可以在初始化阶段之后发生，也称之为动态绑定或者晚期绑定。

**1. 加载**：简单来说是把class字节码文件从各个来源通过类加载器装载入内存中。

+ 通过全限定类名定位.class文件，并获取其二进制字节流
+ 把字节流所代表的静态存储结构转换为方法区的运行时数据结构
+ 在Java堆中生成一个此类的java.lang.Class对象，作为方法区中这些数据的访问入口。
+ 字节码来源：包括从本地路径下编译生成的.class文件，从jar包中的.class文件等。

**2. 连接**

1. **验证**

   1. 确保被加载的类的正确性。验证是连接阶段的第一步，用以确定Class字节流中的信息是否符合虚拟机的要求。
   2. 具体验证形式：
      1. 文件格式验证：验证字节流是否符合Class文件格式的规范，例如是否以0xCAFEBABE开头、主次版本号是否在当前虚拟机的处理范围之内、常量池中的常量是否有不被支持的类型。
      2. 元数据验证：对字节码描述的信息进行语义分析（注意：对比javac编译阶段的语义分析），以保证其描述的信息符合Java语言规范的要求；例如：这个类是否有父类，除了java.lang.Object之外。
      3. 字节码验证：通过数据流和控制流分析，确定程序语义是合法的，符合逻辑的
      4. 符号引用验证：确保解析动作能正确执行

2. **准备**

   1. 为类的静态变量分配内存，并将其初始化为默认值。准备过程通常分配一个结构用来存储类信息，这个结构中包含了类中定义的成员变量，方法和接口信息等。
   2. 具体行为：
      1. 这时候进行内存分配的仅包括类变量（static），而不包括实例变量，实例变量会在对象实例化时随着对象一块分配在Java堆中。
      2. 这里所设置的初始值通常情况下是数据类型默认的零值（如0、0L），而不是在被在java代码中被显示赋值。

3. **解析**

   1. 把类中对常量池内的符号引用转换为直接引用
   2. 解析动作主要针对类或者接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符等7类符号引用进行。

   3. **符号引用：**即一个字符串，但是这个字符串给出了一些能够唯一性识别一个方法、一个变量、一个类的相关信息。
   4. **直接引用：**可以理解为一个内存地址，或者一个便宜量。比如类方法，类变量的直接引用是指向方法区的指针；而实例方法、实例变量的直接引用则是从实例的头指针开始算起到这个实例变量位置的偏移。
   5. 举例来说：现在调用方法hello()，这个方法的地址是1234567，那么hello就是符号引用，1234567是直接引用。

4. **初始化**
   1. 初始化：对类的静态变量赋予正确的初始值（注意和连接时的解析过程是分开的）
   2. 初始化的目标：
      1. 实现对声明类静态变量时指定的初始值进行初始化
      2. 实现对使用静态代码块设置的初始化的初始化
   3. 初始化的步骤：
      1. 如果此类没被加载、连接，则先加载、连接此类
      2. 如果此类的直接父类还没有被初始化，则先初始化其直接父类
      3. 如果类中有初始化语句，则按照顺序依次执行初始化语句。
   4. 初始化的时机
      1. 创建类的实例
      2. java.lang.reflect包中的方法（Class.forName("xxx")）
      3. 对类的静态变量进行访问赋值
      4. 访问调用类的静态方法
      5. 初始化一个类的子类，父类本身也会被初始化
      6. 作为程序的启动入口，包含main方法（如SpringBoot入口类）



### 2. 类的主动引用和被动引用

### 3. 三种类加载器

java虚拟机把加载动作放到JVM外部实现，以便让应用程序决定如何获取所需要的类，这个动作的实现代码就是类加载器。

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 下午4.36.38.png" alt="截屏2021-05-03 下午4.36.38" style="zoom:50%;" />

1. 类加载器的关系
   1. Bootstrap ClassLoader是在Java虚拟机启动后初始化的
   2. Bootstrap ClassLoader负责加载ExtClassLoader，并且将ExtClassLoader的父加载器设置为Bootstrap ClassLoader
   3. Bootstrap ClassLoader加载完ExtClassLoader后，就会加载AppClassLoader，并且将AppClassLoader的父加载器指定为ExtClassLoader
2. 类加载器的作用
   1. **启动类加载器：**负责加载JAVA_HOME/lib目录中的、或者通过-Xbootclasspath参数指定路径中的、且被虚拟机认可（按文件名识别，如rt.jar）的类库.
   2. **扩展类加载器**：JAVA_HOME/jre/lib/ext路径下以及java.ext.dirs系统变量指定的路径中的类库。
   3. **应用程序类加载器：**负载加载用户路径上的类库，JVM通过双亲委派模型进行类的加载，当然我们也可以通过继承java.lang.ClassLoader实现自定义的类加载器。

3. 类加载器的特点
   1. 层级结构：Java里的类加载器被组成成了有父子关系的层级结构。BoostStrap类装载器时所有类装载器的父类。
   2. 代理模式：基于层级结构，类的代理可以在装载器之间进行代理。当装载器装载一个类时，首先会检查它在父类装载器中是够进行了装载。如果上层装载器已经装载了这个类，这个类会被直接使用。反之，类装载器会请求装载这个类。
   3. 可见行限制：一个子装载器可以查找父类装载器中的类，但是一个父装载器不能查找子装载器里的类。
   4. 不允许卸载：类装载器可以装载一个类但是不可以卸载它，不过可以删除当前的类装载器，然后创建一个新的类装载器装载。
4. 类加载器的隔离问题：
   1. 每个类装载器都有一个自己的命名空间来保存自己已经装载的类。当一个类装载器装载一个类时，它会通过保存在命名空间里面的全限定类名进行搜索来监测这个类是否已经被加载了。
   2. **隔离问题：**JVM以及Dalvik对类的唯一识别就是（ClassLoader id + PackageName + Classname），所以一个运行程序中是有可能存在两个报名和类名完全一致的类的，并且如果这两个类不是由一个ClassLoader加载，是无法将一个类的实例强转为另外一个类的，这就是ClassLoader的隔离性



### 4. 双亲委托机制

核心思想：**自底向上检查类是否已经被加载**，自顶向下尝试加载类

1. 具体加载过程
   1. 当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
   2. 当ExtClassLoader加载一个CLass时，它首先也不会自己去尝试加载这个类，而是把类加载请求委托给BoostStrap ClassLoader去完成。
   3. 如果BooststrapClassLoader加载失败，会使用ExtClassLoader来加载。
   4. 如果ExtClassLoader也加载失败，则会使用AppClassLoader来加载，如果AppClassLoader也加载失败，则会报出异常。

### 5. 类的动态加载

1. 类的几种加载方式
   1. 通过命令行启动时由JVM初始化加载
   2. 通过Class.forName()方法动态加载
   3. 通过ClassLoader.loadclass()方法动态加载
2. **Class.forName()和ClassLoader.loadClass()**
   1. Class.forName()：把类的.class文件加载到JVM中，对类进行解释的同时执行类中的static静态代码块
   2. ClassLoader.loadCLass():只是把.class文件加载到JVM中，不会执行static代码块中的内容，只有在newInstance才会去执行

### 6. 对象的初始化

1. 静态变量/静态代码块 -> 普通代码块 ->构造函数
2. 父类静态变量和静态代码块（先声明的先执行）
3. 子类静态变量和静态代码块（先声明的先执行）
4. 父类普通成员变量和普通代码块（先声明的先执行）
5. 父类的构造函数
6. 子类普通成员变量和普通代码块
7. 子类构造函数

### 7. 自定义类加载器

1. 为什么要自定义类加载器：当你的项目很大，而里面又同事依赖两个不同版本的jar包，你没有办法抛弃，那么你觉得你该怎么做呢？按照默认的话，是会先加载其中的一个jar里面的类，由于jvm默认的机制，保证一个类只能被加载一次，此时只能自己定义类加载器了。
2. 实例

Parent.java

```java
package org.ostenant.jdk8.learning.examples.classloader.custom;

public class Parent {
    protected static String CLASS_NAME;
    protected static String CLASS_LOADER_NAME;
    protected String instanceID;

    // 1.先执行静态变量和静态代码块（只在类加载期间执行一次）
    static {
        CLASS_NAME = Parent.class.getName();
        CLASS_LOADER_NAME = Parent.class.getClassLoader().toString();
        System.out.println("Step a: " + CLASS_NAME + " is loaded by " + CLASS_LOADER_NAME);
    }

    // 2.然后执行变量和普通代码块（每次创建实例都会执行）
    {
        instanceID = this.toString();
        System.out.println("Step c: Parent instance is created: " + CLASS_LOADER_NAME + " -> " + instanceID);
    }

    // 3.然后执行构造方法
    public Parent() {
        System.out.println("Step d: Parent instance：" + instanceID + ", constructor is invoked");
    }

    public void say() {
        System.out.println("My first class loader...");
    }
}
```

Children.java

```java
package org.ostenant.jdk8.learning.examples.classloader.custom;

public class Children extends Parent {
    static {
        CLASS_NAME = Children.class.getName();
        CLASS_LOADER_NAME = Children.class.getClassLoader().toString();
        System.out.println("Step b: " + CLASS_NAME + " is loaded by " + CLASS_LOADER_NAME);
    }

    {
        instanceID = this.toString();
        System.out.println("Step e: Children instance is created: " + CLASS_LOADER_NAME + " -> " + instanceID);
    }

    public Children() {
        System.out.println("Step f: Children instance：" + instanceID + ", constructor is invoked");
    }

    public void say() {
        System.out.println("My first class loader...");
    }
}
```

实现自定义的类加载器：CustomClassLoader.java

```java
public class CustomClassLoader extends ClassLoader {
    private String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name); // 可省略
        if (c == null) {
            byte[] data = loadClassData(name);
            if (data == null) {
                throw new ClassNotFoundException();
            }
            return defineClass(name, data, 0, data.length);
        }
        return null;
    }

    protected byte[] loadClassData(String name) {
        try {
            // package -> file folder
            name = name.replace(".", "//");
            FileInputStream fis = new FileInputStream(new File(classPath + "//" + name + ".class"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] b = new byte[2048];
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            fis.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

测试：CustomerClassLoaderTester.java

测试程序启动后，逐一拷贝并加载待加载的目标类源文件

```java
private static final String CHILDREN_SOURCE_CODE_NAME = SOURCE_CODE_LOCATION + "Children.java";
    private static final String PARENT_SOURCE_CODE_NAME = SOURCE_CODE_LOCATION + "Parent.java";
    private static final List<String> SOURCE_CODE = Arrays.asList(CHILDREN_SOURCE_CODE_NAME, PARENT_SOURCE_CODE_NAME);

    static {
        SOURCE_CODE.stream().map(path -> new File(path))
            // 路径转文件对象
            .filter(f -> !f.isDirectory())
            // 文件遍历
            .forEach(f -> {
            // 拷贝后源代码
            File targetFile = copySourceFile(f);
            // 编译源代码
            compileSourceFile(targetFile);
        });
    }
```

拷贝单一源文件到自定义类加载器的类加载目录

```java
protected static File copySourceFile(File f) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            // package ...;
            String firstLine = reader.readLine();

            StringTokenizer tokenizer = new StringTokenizer(firstLine, " ");
            String packageName = "";
            while (tokenizer.hasMoreElements()) {
                String e = tokenizer.nextToken();
                if (e.contains("package")) {
                    continue;
                } else {
                    packageName = e.trim().substring(0, e.trim().length() - 1);
                }
            }

            // package -> path
            String packagePath = packageName.replace(".", "//");
            // java file path
            String targetFileLocation = TARGET_CODE_LOCALTION + "//" + packagePath + "//";

            String sourceFilePath = f.getPath();
            String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("\\") + 1);

            File targetFile = new File(targetFileLocation, fileName);
            File targetFileLocationDir = new File(targetFileLocation);
            if (!targetFileLocationDir.exists()) {
                targetFileLocationDir.mkdirs();
            }
            // writer
            writer = new BufferedWriter(new FileWriter(targetFile));
            // 写入第一行
            writer.write(firstLine);
            writer.newLine();
            writer.newLine();

            String input = "";
            while ((input = reader.readLine()) != null) {
            writer.write(input);
                writer.newLine();
            }

            return targetFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
```

对拷贝后的.java源文件进行手动编译，在同级目录下生成.class文件

```java
protected static void compileSourceFile(File f) {
        try {
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(f);

            // 执行编译任务
            CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
            task.call();
            standardFileManager.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

通过自定义类加载器加载Children的java.lang.Class<?>对象，然后利用反射机制创建Children的实例对象

```java
@Test
    public void test() throws Exception {
        // 创建自定义类加载器
        CustomClassLoader classLoader = new CustomClassLoader(TARGET_CODE_LOCALTION); // E://myclassloader//classpath
        // 动态加载class文件到内存中（无连接）
        Class<?> c = classLoader.loadClass("org.ostenant.jdk8.learning.examples.classloader.custom.Children");
        // 通过反射拿到所有的方法
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if ("say".equals(method.getName())) {
                // 通过反射拿到children对象
                Object children = c.newInstance();
                // 调用children的say()方法
                method.invoke(children);
                break;
            }
        }
    }
```

**测试编写的类加载器**

**(一)测试场景**

1. 保留static代码块，把目标类Children.java和Parent.java拷贝到类加载的目录，然后进行手动编译

2. 保留测试项目目录中的目标类Children.java和Parent.java

   <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 下午6.07.05.png" alt="截屏2021-05-03 下午6.07.05" style="zoom:50%;" />

   测试结果输出：

   ![截屏2021-05-03 下午6.07.37](/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 下午6.07.37.png)

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-03 下午6.08.20.png" alt="截屏2021-05-03 下午6.08.20" style="zoom:50%;" />





# JVM面试常见问题

## 1.




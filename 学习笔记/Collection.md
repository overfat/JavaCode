# Java集合学习笔记

## 基础知识点

### 1. java集合概述

1. Java集合就像一种容器，可以**动态地** 把多个对象的引用放入容器中。
2. Java集合类可以用于存储数量不等的多个对象，还可用于保存具有映射关系的关联数组。
3. 可以分为Collection和Map两种体系
   1. Collection接口：单列数据，定义了存取一组对象的方法的集合。
      1. List：元素有序、可重复的集合
      2. Set：元素无序、不可重复的集合
   2. Map接口：双列数据，保存具有映射关系的"Key-Value"的集合。

### 2.Collection接口

1. Collection接口是List、Set和Queue接口的父接口，该接口中定义的方法既可以用于操作Set集合，也可以操作List和Queue集合。

```java
// 添加元素
add(Object Obj);
addAll(Collection coll);
// 获取元素个数
int size();
// 清空集合
void clear();
// 是否为空
boolean isEmpty();
// 是否包含某个元素
boolean contains(Object obj); //通过元素的equals方法来判断是否是同一个对象
boolean containsAll(Collection c); //也是调用元素的equals方法来比较
// 删除
boolean remove(Object obj);
boolean remove(Collection coll);
// 取两个集合的交集
boolean retainAll(Collection c); // 把交集的结果存在当前集合中，不影响c
// 集合是否相等
boolean equals(Obect obj);
// 转成对象数组
Object[] toArray();
// 获取集合对象的哈希值
int hashCode();
// 遍历
iterator()

```

### 3. Iterator迭代器接口

1. Iterator仅用于遍历集合，本身并不提供装载对象的能力，如果需要创建Iterator对象，则必须有一个被迭代的集合。

   ```java
   boolean hasNext();
   next();
   void remove();
   ```

2. 在调用it.next()方法之前必须要调用it.hasNext()方法进行检测。若不调用，且下一条记录无效，就会抛出异常。

3. Iterator接口的remve方法

   ```java
   Iterator iter = coll.iterator();
   while(iter.hasNext()){
     Object obj = iter.next();
     if(obj.equals("Tom")){
       iter.remove();
     }
   }
   ```

   Iterator可以删除集合的元素，但是是遍历过程中通过迭代器对象的remove方法，不是集合的remove方法。

   如果未调用next()或在上一次调用next方法之后已经调用了remove方法，再调用remove方法就会报IllegalStateException。

### 4.List接口

1. List集合类中的元素有序、且可重复，集合中的每个元素都有其对应的顺序索引。主要实现类：ArrayList、LinkedList和Vector

2. 除了从Collection中继承的方法，还有额外添加的方法

   ```java
   void add(int index,Object ele); //在index位置插入ele元素
   boolean addAll(int index, Collection ele); //从index位置开始将ele中的元素添加进来
   Object get(int index); //获取index位置的元素
   int indexOf(Object obj); //获取obj在集合中首次出现的位置
   int lastIndexOf(Object obj); //获取obj在集合中最后一次出现的位置
   Object remove(int index);// 删除index位置的元素，并返回此元素
   Object set(int index, Object ele); //把index位置的元素设置为ele
   List subList(int fromindex, int toindex); //返回从fromIndex到toIndex位置的子集合
   ```

3. **ArrayList** 

   1. ArrayList的JDK1.8之前和之后的区别？
      1. JDK1.7: ArrayList像是饿汉式，直接创建一个初始容量为10的数组。
      2. JDK1.8: ArrayList像是懒汉式，一开始创建一个长度为0的数组，当添加第一个元素时在创建一个初始容量为10的数组。

4. **LinkedList**

   1. 对于频繁的插入或者删除元素的操作，建议直接使用LinkedList类

   2. 新增方法：

      ```java
      void addFisrst(Object obj);
      void addLast(Object obj);
      Object getFirst();
      Object getLast();
      Object removeFirst();
      Object removeLast();
      ```

### 5. Set接口

1. Set接口是Collection的子接口，set接口没有提供额外的方法。
2. Set判断两个对象是否相同不是使用==运算符，而是使用equals方法。
3. **HashSet：**
   1. HashSet不能保证元素的排列顺序，线程不安全，集合中的元素可以是null。
   2. HashSet判断两个元素相等的标准：两个对象通过hashCode方法比较相等，并且两个相等的对象equals方法返回值也相同。
   3. 对于存放在Set容器中的对象，对应的类一定要重写equals和hashCode方法，以实现对象相等规则。即：“相等的对象必须具有相等的散列码”。
   4. 添加元素的过程：**当向 HashSet 集合中存入一个元素时，HashSet 会调用该对象的 hashCode() 方法 来得到该对象的 hashCode 值，然后根据 hashCode 值，通过某种散列函数决定该对象 在 HashSet 底层数组中的存储位置。(这个散列函数会与底层数组的长度相计算得到在 数组中的下标，并且这种散列函数计算还尽可能保证能均匀存储元素，越是散列分布， 该散列函数设计的越好)。如果两个元素的hashCode()值相等，会再继续调用equals方法，如果equals方法结果 为true，添加失败;如果为false，那么会保存该元素，但是该数组的位置已经有元素了， 那么会通过链表的方式继续链接。** 
   5. **如果两个元素的equals方法返回true，但他们的hashCode返回值不相等，hashset将会把他们存放在不同的位置，但是依然可以添加成功。** 
   6. 底层也是数组，初始容量为16，当如果使用率超过0.75，就会扩大容量为原来的2倍。
   7. 重写hashCode的原则：
      1. 在程序运行时，同一个对象多次调用 hashCode() 方法应该返回相同的值。 
      2. 当两个对象的 equals() 方法比较返回 true 时，这两个对象的 hashCode()方法的返回值也应相等。
      3. 对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。
4. **LinkedHashSet**
   1. LinkedHashSet是HashSet的子类。
   2. 根据hashCode值来决定元素的储存位置，但它同时使用双向链表维护元素的次序，这使得元素看起来是以插入顺序保存的。
   3. LinkedHashSet的插入性能略低于hashSet，但是在迭代访问Set里的全部元素时具有很好的性能。
   4. LinkedHashSet不允许集合有重复元素。
5. **TreeSet**
   1. TreeSet是SortedSet接口的实现类，TreeSet可以确保集合中的元素处于排序状态。
   2. 底层使用红黑树存储结构。
   3. 新增的方法如下：（了解）
      1. Comparator comparator()
      2. Object first()
      3. Object last()
      4. Object lower(Object e)
      5. Object higher(Object e)
      6. SortedSet subSet(fromElement,toElement)
      7. SortedSet headSet(toElement)
      8. SortedSet tailSet(fromElement)
   4. 有两种排序方法：自然排序和定制排序，默认情况下，TreeSet采取自然排序。

### 6. Map接口

1. Map中的key和value都可以是任何引用类型的数据。

2. Map中的key用Set来存放，不允许重复，即同一个Map对象所对应的类，必须重写hashCode()和equals方法。

3. 常用方法：

   ```java
   Object put(Object key, Object value); // 添加元素
   void putAll(Map m); //将m中所有的key-value添加到当前map中
   Object remove(Object key); //移除指定的key，返回value
   void clear(); 清空map中的所有数据。
   // 元素查询方法
   Object get(Object key); //获取指定的key所对应的value
   boolean containsKey(Object key); //是否包含指定的key
   boolean containsValue(Object value) //是否包含指定的value
   int size()； //返回map中key-value的个数
   boolean isEmpty(); // 判断当前map是否为空
   boolean equals(Object obj); //判断两个对象是否相等
   // 元视图操作的方法
   Set keySet(); //返回所有key构成的Set集合
   Collection values(); //返回所有value构成的Collection集合
   Set entrySet(); 返回所有key-value对构成的Set集合
   ```

4. **HashMap**

   1. 允许使用null键和null值，与HashSet一样，不保证映射的顺序。

   2. 所有的key构成的集合是Set：无序的、不可重复的，所有key所在的类要重写hashCode和equals方法。

   3. 所有的Value构成的集合是Collection：无序的，可以重复的，所有value所在的类要重写equals方法。

   4. HashMap判断两个key相等的标准是：两个key通过equasl方法返回true，hashCode值也相等。

   5. HashMap判断两个value相等的标准是：两个value通过equals方法返回true。

   6. JDK1.7以及以前版本，HashMap是数组+链表结构。JDK1.8版本发布以后，HashMap是数组+链表+红黑树实现。

   7. HashMap源码中的重要常量：

      <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-01 下午3.16.41.png" alt="截屏2021-05-01 下午3.16.41" style="zoom:50%;" />

   8. 添加元素的过程，JDK1.8之前

      1. 向HashMap中添加entry1(key, value),需要首先计算entry1中key的哈希值，此哈希值经过处理以后。得到在底层数组Entry[]数组中的要存储的位置i，如果位置i上没有元素，则entry1直接添加成功。如果已经存在了元素，则需要通过循环的方式，依次比较entry1中key和其他的entry。如果hash值不同，则直接添加成功。如果hash值相同，就比较equals值，如果equals值相同，就覆盖。如果equals不同，则可以添加成功。
      2. HashMap的扩容：当HashMap中元素越来越多的时候，hash冲突的几率就越来越高，因此需要对数组进行扩容，扩容之后，最消耗性能的点就出现了：原数组中的数据必须重新计算其在新数组中的位置，并放进去，这就是resize。
      3. HashMap什么时候进行扩容<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-01 下午8.39.00.png" alt="截屏2021-05-01 下午8.39.00" style="zoom:50%;" />

   9. 添加元素的过程，JDK1.8？

      <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-01 下午8.42.14.png" alt="截屏2021-05-01 下午8.42.14" style="zoom:50%;" />

      <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-01 下午8.44.13.png" alt="截屏2021-05-01 下午8.44.13" style="zoom:50%;" />

      <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-05-01 下午8.42.00.png" alt="截屏2021-05-01 下午8.42.00" style="zoom:50%;" />

   10. Map实现类之二：LinkedHashMap

       1. LinkedHashMap是HashMap的子类。
       2. 在HashMap存储结构的基础上，使用了一对双向链表来记录添加元素的顺序。
       3. 与LinkedHashSet类似，LinkedHashMap可以维护Map的迭代顺序；迭代顺序与key-value对的插入顺序一致。

   11. Map实现类之三：TreeMap

       1. TreeMap存储Key-Value对时，需要根据key-value对进行排序。TreeMap可以保证所有的key-value对处于有序状态。
       2. 底层使用红黑树结构存储数据。
       3. TreeMap的key的排序：
          1. 自然排序：TreeMap的所有的Key必须实现Comparable接口，而且所有的key应该时同一个类的对象，否则将会抛出ClassCastException。
          2. 定制排序：创建TreeMap时，传入一个Comparator对象，该对象负责对TreeMap中的所有key进行排序。此时不需要Map的key实现Comparator接口。
       4. TreeMap判断两个Key相等的标准：两个key通过compareTo方法或者compare方法返回0；

### 7. Collections工具类

1. Collections是一个操作Set、List和Map等集合的工具类。

2. 提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法。

3. 排序操作：

   1. ```java
      reverse(List); // 反转List中元素的顺序
      shuffle(List); //对List集合元素进行随机排序操作
      sort(List); //根据元素的自然顺序对指定List集合元素按照升序进行排序
      sort(List,Comparator); //根据指定的Comparator产生顺序对List集合元素进行排序
      swap(List, int, int); //将指定List集合中的i元素和j元素进行交换
      ```

   2. ```java
      Object max(Collection); //根据元素的自然顺序，返回给定集合中的最大元素
      Object max(Collection, Comparator); //根据Comparator指定的顺序，返回给定集合中的最大元素
      Object min(Collection);
      Object min(Collection, Comparaotr);
      int frequency(Collection, Object); //返回指定集合中指定元素的出现次数
      void copy(List dest, List src); // 将src中的内容复制到dest中
      boolean replaceAll(List list, Object oldVal, Object newVal);
      ```

   3. Collections类中提供了多个synchronizedXxx()方法，该方法可使指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题。

      1. ```java
         static<T> List<T> synchronizedList(List<T> list);
         static<K,V> Map<K,V> synchronizedMap(Map<K,V> m);
         static<T> Set<T> synchronizedSet(Set<T> s);
         static<K,V> SortedMap<K,V> synchronizedSortedMap(SortedMap<K,V> m);
         static<T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s)
         ```























## 常见面试题

### 1. List、Set、Map三者的区别

1. List储存的元素是有序的，可重复的。
2. Set中储存的元素是无序的，不可重复的。
3. Map使用键值对(Key-Value)来存储元素，Key是无序的、不可重复的，value是无序的、可重复的，每个键最多映射到一个值。

### 2. ArrayList 和LinkedList的区别

1. **是否保证线程安全：** ArrayList和LinkedList都是不同步的，不保证线程安全。

2. **底层数据结构：** ArrayList底层的数据结构是数组，LinkedList的底层数据结构是双向链表(JDK1.6版本使用的是循环链表，JDK1.7中取消的循环)。

3. **插入和删除是否受元素位置的影响： ** ArrayList采用数据进行存储，所以插入元素和删除元素的时间复杂度受元素位置的影响。LinkedList采用链表存储，所以在插入、删除元素的时候不会受到位置的影响。但是如果在指定位置插入元素的话，需要先找到这个位置。

4. **是否支持随机访问：** ArrayList支持随机访问，LinkedList不支持随机访问。

5. **内存空间占用：** ArrayList的空间浪费主要体现在list列表后面会预留一定的容量空间，LinkedList的空间花费主要体现在它的每一个元素需要消耗比ArrayList更多的空间（要存放直接前驱和后继以及数据）。

6. **补充内容：RandomAccess接口** 

   ```java
   public interface RandomAccess{}
   ```

   RandomAccess接口中什么都没有定义，更多像是一个标识。标识实现这个接口的类具有随机访问功能。

### 3. ArrayList与Vector区别呢？为什么要用ArrayList取代Vector呢？

1. ArrayList是List的主要实现类，底层使用Object[]存储，线程不安全。
2. Vactor是List的古老实现类，底层使用Object[]存储，线程安全的。
3. ArrayList每次扩容请求其大小的1.5倍空间，Vector每次扩容请求其大小的2被空间。

### 4. ArrayList的扩容机制

### 5. HashMap和HashTable的区别

1. 线程是否安全：HashMap是非线程安全的，HashTable是线程安全的，因为HashTable内部的方法都经过synchronized修饰。（如果要保证线程安全就使用**CurrentHashMap** ）
2. 效率：因为线程安全的问题，HashMap比HashTable效率高。
3. **对NULL Key和NULL Value的支持：** HashMap可以存储null的key和value，但是null作为键只能有一个，作为值可以有多个。HashTable不允许有NULL的键和值，否则就会出现**NullPointerException** .
4. **初始容量大小和每次扩容大小的不同：** 创建时如果不指定容量的初始值，HashTable默认的初始大小为11，之后每次扩充，容量为原来的2n+1。HashMap默认的初始化大小是16，之后每次扩充，容量变为原来的2倍。 创建时如果指定了大小，那么HashTable会直接使用给定的大小，HashMap会将其扩充为2的幂次方大小。也就是说HashMap总是使用2的幂次方作为Hash表的大小。
5. **底层数据结构：** JDK 1.8之后HashMap在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认是8）时（将链表转化为红黑树之前会先进行判断，如果当前数组小于64，那么会选择先进行数据扩容，而不是转化为红黑树），将链表转化为红黑树，减少搜索时间。HashTable并没有这样的机制。

### 6.HashMap和HashSet的区别

1. HashSet的底层就是HashMap实现的。

### 7. HashSet如何检查重复值

1. 当你把对象加入到HashSet时，HashSet会首先计算对象的hashcode值来判断对象加入的位置，同时也会与其他加入对象的hashcode值比较。如果没有相符的hashcode，Hashset就会假设没有重复出现。但是如果发现有相同的hashcode，那么就会调用equals方法来检查hashcode相等的对象是否真的相同。如果两者相同，就不会加入。
2. **hashcode和equals()的相关规定：**
   1. 如果两个对象相等，则hashcode一定也相等，equals()方法也会返回true。
   2. 两个对象有相同的hashcode值，但是他们也不一定相等。
   3. 综上，equals方法被覆盖时，hashcode方法也一定被覆盖。
   4. hashcode()的默认行为是对堆上的对象产生独特值。如果没有重写hashcode()，则该class的两个对象无论如何都不会相等（即使这两个对象指向同样的数据）。
3. **== 与equals的区别** 
   1. 对于基本类型的数据：==比较的就是值
   2. 对于引用类型的数据，==比较的是两个引用是否指向同一个对象地址（两者在内存中存放的地址（堆内存地址）是否指向同一个地方）
   3. 对于应用类型来说，equals方法如果没有被重写，对比的是他们的地址是否相等；如果equals方法被重写（例如String），那么比较的就是地址里面的内容。

### 8. HashMap的底层实现原理

1. **JDK1.8之前：** HashMap的底层数据结构是链表加数据构成的链表散列。HashMap通过key的hashcode经过扰动函数处理后得到的hash值，然后通过(n-1)&hash判断当前元素存放的位置（n指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入元素的hash值以及key是否相同，如果相同的话，直接覆盖。不相同就通过拉链法解决冲突。

使用扰动函数之后可以减少碰撞。

**JDK1.8 HashMap的hash方法源码：**

```java
static final int hash(Object key){
  int h;
  return (key == null)?0:(h == key.hashcode()) ^ (h>>>16);
  // ^ :按位异或
  // >>> ： 无符号右移，忽略符号位，空位都用0补齐
}
```

**JDK1.7 HashMap的hash方法源码** 

```java
static int hash(int h){
  h ^= (h >>> 20) ^ (h >>> 12);
  return h^(h>>>7)^(h>>>4);
}
```

2. **JDK1.8之后**

1. JDK 1.8之后HashMap在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认是8）时（将链表转化为红黑树之前会先进行判断，如果当前数组小于64，那么会选择先进行数据扩容，而不是转化为红黑树），将链表转化为红黑树，减少搜索时间。

2. **TreeMap、TreeSet以及JDK1.8之后的HashMap底层都用到了红黑树。红黑树就是为了解决二叉查找树的缺陷，因为二叉查找树在某些情况下会退化成一个线性结构**  

### 9. HashMap的长度为什么是2的幂次方

1. 取余（%）操作中如果除数是2的幂次方则等价于与其除数减一的与(&)操作（也就是说hash%length==hash&(length-1)的前提是length是2的n次方；）。并且采用二进制位操作&，相对于%能够提高运算效率，这就解释了HashMap的长度为什么是2的幂次方。

### 10. HashMap多线程操作导致死循环问题

1. 并发环境下建议使用**ConcurrentHashMap** 

### 11. ConcurrentHashMap和HashTable的区别

1. **底层数据结构：** JDK1.7的ConcurrentHashMap底层采用的是分段的数组+链表实现，JDK1.8采用的数据结构和HashMap1.8采用的相同。HashTable和JDK1.8之前的HashMap的底层的数据结构类似都是采用**数组+链表** 的主体，数组是主体，链表是为了解决哈希冲突而存在的。

2. **实现线程安全的方式：** JDK1.7时，ConcurrentHashMap（分段锁）对整个桶数组进行了分割分段，每一把锁只锁容器中一部分数据，多线程访问容器里不同数据段的数据，就不会存在竞争，提高了并发访问率。到了JDK1.8时摒弃了segment的概念，而是直接使用Node数组+链表+红黑树的数据结构来实现，并发控制使用synchronized和CAS操作。整个过程看起来就像优化过且线程安全的HashMap，虽然在JDK1.8中还能看到Segment数据结构，但是已经简化了属性，只是为了兼容旧版本。 HashTable使用同一把锁（synchronized）来保证线程的安全，效率非常低下。当一个线程访问同步方法时，其他的线程也会访问同步方法，这时效率就很低下。

   <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-29 下午1.02.49.png" alt="截屏2021-04-29 下午1.02.49" style="zoom:40%;" />

   <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-29 下午1.02.15.png" alt="截屏2021-04-29 下午1.02.15" style="zoom:40%;" />

 ### 12. ConcurrentHashMap线程安全的具体实现方式/底层具体实现

1. **JDK1.7** 首先将数据分为一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一段数据时，其他段的数据也能被其他线程访问。

2. **ConcurrentHashMap是由Segment数据结构和HashEntry数组结构构成**

3. Segment实现了ReentrantLock，所以Segment是一种可重入锁，扮演锁的角色。HashEntry用于存储键值对数据。

4. 一个 ConcurrentHashMap 里包含一个 Segment 数组。 Segment 的结构和 HashMap 类似，是 一种数组和链表结构，一个 Segment 包含一个 HashEntry 数组，每个 HashEntry 是一个链表 结构的元素，每个 Segment 守护着一个 HashEntry 数组里的元素，当对 HashEntry 数组的数 据进行修改时，必须首先获得对应的 Segment 的锁。

   ---

5. **JDK1.8** 

   1. ConcurrentHashMap取消了Segment分段锁，采取CAS和synchronized来保证并发安全。数据结构和HashMap1.8类似。
   2. synchronized只锁定当前链表或者红黑二叉树的首节点，这样只要hash不冲突，就不会产生并发，效率提升N倍。








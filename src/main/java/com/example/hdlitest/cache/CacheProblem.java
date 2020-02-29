package com.example.hdlitest.cache;

/**
 * 缓存穿透
 * @author 李会东
 * @version 1.0
 * @date 2019-12-12 22:01
 */
public class CacheProblem {
    /**
     * 缓存穿透
     * 缓存穿透是指查询一个一定不存在的数据，由于缓存是不命中时被动写的，并且出于容错考虑，如果从存储层查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义。在流量大时，可能DB就挂掉了，要是有人利用不存在的key频繁攻击我们的应用，这就是漏洞。
     * key不存在时，大量的数据进来查询DB
     *
     * 解决方案：有很多种方法可以有效地解决缓存穿透问题，最常见的则是采用 布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，一个一定不存在的数据会被 这个bitmap拦截掉，从而避免了对底层存储系统的查询压力。另外也有一个更为简单粗暴的方法（我们采用的就是这种），如果一个查询返回的数据为空（不管是数 据不存在，还是系统故障），
     * 我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟
     */


    /**
     * 缓存雪崩
     * 缓存雪崩是指在我们设置缓存时采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重雪崩。
     * key是多个
     *
     * 解决方案：缓存失效时的雪崩效应对底层系统的冲击非常可怕。大多数系统设计者考虑用加锁或者队列的方式保证缓存的单线 程（进程）写，从而避免失效时大量的并发请求落到底层存储系统上。这里分享一个简单方案就时讲缓存失效时间分散开，比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每一个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。
     * 关键字： 时间岔开，确保大家的key不会落在同一个expire点上
     */


    /**
     * 缓存击穿
     * 导致问题的原因是同一时间查，同一时间写缓存，导致并发下缓存也没用，所以考虑使用单线程等方法将写缓存保证只有一个去查了写，其他的使用缓存
     * （加锁）
     * 业界比较常用的做法，是使用mutex。简单地来说，就是在缓存失效的时候（判断拿出来的值为空），不是立即去load db，而是先使用缓存工具的某些带成功操作返回值的操作（比如Redis的SETNX或者Memcache的ADD）去set一个mutex key，当操作返回成功时，再进行load db的操作并回设缓存；否则，就重试整个get缓存的方法
     */

}

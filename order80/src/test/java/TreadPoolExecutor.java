import java.util.concurrent.*;

/**
 * 线程池
 */
public class TreadPoolExecutor {

    /**
     * 线程池实现
     *1、Executors.newFixedThreadPool(5)//固定数量线程的线程池（一池固定线程）
     *  LinkedBlockingQueue阻塞队列
     *
     *2、Executors.newSingleThreadExecutor()//单线程的线程池（一池单线程）
     *  LinkedBlockingQueue阻塞队列：无界缓存等待队列。当前线程数量达到核心线程数量时，任务进入到阻塞队列等待；
     *  每个线程都独立于其他线程运行。生产者和消费者都通过自己的锁保证线程数据的同步，即在并发操作时，任务可以并行的进行数据的处理
     *
     *3、Executors.newCachedThreadPool()//可扩容带缓存的线程池，（一池多线程）
     *  SynchronousQueue阻塞队列：无容量、无缓冲的等待队列，是一个不存储元素的阻塞队列，该队列会把任务直接交给消费者
     *  只有队列中添加的元素被消费了之后才能添加新的元素，使用该队列一般要求线程池最大线程数为无界，避免线程拒绝执行任务；
     *
     *  ArrayBlockingQueue阻塞队列：是一个有界缓存的等队列，可以自定义队列大小，当前执行线程数等于核心线程数量时，再有任务进来时会加入到
     *  ArrayBlockingQueue队列，等到线程池中有空闲线程中在从队列中取任务进行处理。如果当前队列已满时，任务加入队列失败后，会开启新的线程
     *  去处理任务，当线程数已经达到线程池最大线程数量时，再有新的任务尝试加入ArrayBlockingQueue时会报错。
     *
     *  工作流程：
     *  1、提交任务，首先判断核心线程是否已满，如果没满，则创建线程开始处理任务，如果满了，进入第二步；
     *  2、判断等待队列是否已满，如果没满，则任务进入等待队列等待被处理，如果满了，进入第三步；
     *  3、判断线程池是否已满，如果没满，创建线程开始处理任务，如果满了，进入第四步；
     *  4、执行设置的拒绝策略；
     *
     * 底层方法及方法参数：
     * public ThreadPoolExecutor(
     *          int corePoolSize,  线程池中的常驻核心线程数
     *          int maximumPoolSize, 线程池能够容纳同时执行的最大线程数，必须大于等于1
     *          long keepAliveTime,  多余的空闲线程的存活时间，当前线程池数量超过核心线程数时，当空闲时间达到多余线程存活时间值时，
     *                              多余空闲线程会被销毁直到剩下核心线程数个线程为止。
     *          TimeUnit unit, 多余空闲线程存活时间（keepAliveTime参数）的单位
     *          BlockingQueue<Runnable> workQueue, 任务队列，被提交但未被执行的任务
     *          ThreadFactory threadFactory, 表示生成线程池中工作线程的线程工厂，用于创建线程，一般使用默认即可
     *          RejectedExecutionHandler handler 拒绝策略，表示当队列满了并且工作线程大于等于线程池的最大线程数时，如何拒绝
     *      )
     *
     *  线程池拒绝策略：当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize时，如果还有任务到来就会采取任务拒绝策略
     *  1、AbortPolicy:（默认拒绝策略）丢弃任务并抛出RejectedExecutionException异常，会中断调用者的处理过程，一般不推荐，除非业务需求
     *  2、CallerRunsPolicy:不丢弃任务，不抛异常。在调用者线程中运行当前被丢弃的任务，任务不会进入线程池，若线程池已关闭，则丢弃任务
     *  3、DiscardOledestPolicy:丢弃队列中最老的，然后再次尝试提交新任务。
     *  4、DiscardPolicy:丢弃任务，不做任何处理，不报异常。
     *
     *
     *
     */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5); //固定数量线程的线程池（一池固定线程）

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();//单线程的线程池（一池单线程）

        ExecutorService executorService2 = Executors.newCachedThreadPool();//可扩容带缓存的线程池，（一池多线程）

        try {
            executorService.execute(() -> {
                //处理业务
                System.out.println(Thread.currentThread().getName()+", 办理 executorService 业务");
            });
            executorService1.execute(() -> {
                //处理业务
                System.out.println(Thread.currentThread().getName()+", 办理 executorService1 业务");
            });
            executorService2.execute(() -> {
                //处理业务
                System.out.println(Thread.currentThread().getName()+", 办理 executorService2 业务");
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();//关闭线程池
            executorService1.shutdown();//关闭线程池
            executorService2.shutdown();//关闭线程池
        }


    }

}

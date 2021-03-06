package com.learn.demo.design.pattern;

import java.util.concurrent.*;

/**
 * @ClassName: ExecutorsDemo
 * @Description: 线程池使用
 * @Author: 尚先生
 * @CreateDate: 2018/12/18 8:37
 * @Version: 1.0
 */
public class ExecutorsDemo {

    // Runnable Callable接口
    // 假设 Runnable 在JDK1.1时提供 throws Exception
    // 那么在JDK1.0运行时会异常
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService =
                Executors.newSingleThreadExecutor();
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello World 1 !");
//            }
//        });
//        executorService.execute(() ->{
//            System.out.println("Hello World 2 !");
//        });
//
//        executorService.execute(() ->{
//            throw new RuntimeException("手动抛出的异常...");
//        });
//
//        executorService.shutdown();
//        System.out.println("complete...");

        // Throwable ： Exeception | Error的父类
        Future<String> future = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "hello world";
        });

        // Checked 异常需要明确在方法签名出现
        //Unchecked异常 不强制在方法签名中出现，建议还是出现NullPointExeception
        try {
            // 终止当前线程操作
            future.cancel(true);
            String res = future.get();
            future.cancel(res.equals("hello world"));
            System.out.println("callable 输出结果： " + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("callable complete...");

    }


}

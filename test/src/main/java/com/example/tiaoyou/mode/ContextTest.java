package com.example.tiaoyou.mode;

import java.util.stream.IntStream;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 线程上下文设计模式，这个第一眼看就想到threadLocal
 * 这个方法每次调用方法时，都需要传入 Context 作为参数，而且影响一个方法的调用
 *
 * ThreadLocal 是线程本地变量，可以实现多线程的数据隔离。ThreadLocal 为每一个使用该变量的线程都提供
 * 一份独立的副本，线程间的数据是隔离的，每一个线程只能访问各自内部的副本变量。
 */
public class ContextTest {
    public class Context {
        private String name;
        private long id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
    // 设置上下午名字
    public class QueryNameAction {
        public void execute(Context context) {
            try {
                Thread.sleep(1000L);
                String name = Thread.currentThread().getName();
                context.setName(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class QueryIdAction{
        public void execute(Context context) {
            try {
                Thread.sleep(1000L);
                long id = Thread.currentThread().getId();
                context.setId(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class ExecutionTask implements Runnable {
        private final QueryNameAction queryNameAction = new QueryNameAction();
        private final QueryIdAction queryIdAction = new QueryIdAction();

        @Override
        public void run() {
            final Context context = new Context();
            queryNameAction.execute(context);
            System.out.println("The name query successful");
            queryIdAction.execute(context);
            System.out.println("The id query successful");
            System.out.println("The Name is " + context.getName() + " and id is " + context.getId());
        }
    }

    public void main(String[] args) {
        IntStream.range(1,5).forEach(i -> new Thread(new ExecutionTask()).start());
    }
}

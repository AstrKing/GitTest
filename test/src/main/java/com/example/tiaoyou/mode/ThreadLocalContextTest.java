package com.example.tiaoyou.mode;

import java.util.stream.IntStream;

/**
 * @author WangYH
 * @date 2022/6/10
 * <p>
 * ThreadLocal最适合在这种上下午切换中使用，这个场景一定要记得
 * 注意用新特性生成，可以用到java8，用java8的新特性的语法糖
 */
public class ThreadLocalContextTest {
    // 上下文类
    public static class ThreadLocalContext {
        private String name;
        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // 复制上下文到 ThreadLocal中
    public final static class ActionContext {
        private static final ThreadLocal<ThreadLocalContext> threadLocal = ThreadLocal.withInitial(ThreadLocalContext::new);

        public static ActionContext getActionContext() {
            return ContextHolder.actionContext;
        }

        public ThreadLocalContext getContext() {
            return threadLocal.get();
        }

        // 获取 ActionContext 单例
        public static class ContextHolder {
            //静态内部类，获取单例
            private final static ActionContext actionContext = new ActionContext();
        }
    }

    public static class QueryNameAction {
        public void execute() {
            try {
                Thread.sleep(1000L);
                String name = Thread.currentThread().getName();
                ActionContext.getActionContext().getContext().setName(name);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 设置上下文 ID
    public static class QueryIdAction {
        public void execute() {
            try {
                Thread.sleep(1000L);
                long id = Thread.currentThread().getId();
                ActionContext.getActionContext().getContext().setId(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 执行方法
    public static class ExecutionTask implements Runnable {
        private final QueryNameAction queryNameAction = new QueryNameAction();
        private final QueryIdAction queryIdAction = new QueryIdAction();
        @Override
        public void run() {
            queryNameAction.execute();// 设置线程名
            System.out.println("The name query successful");
            queryIdAction.execute();// 设置线程 ID
            System.out.println("The id query successful");
            System.out.println("The Name is " + ActionContext.getActionContext().getContext().getName()+" , Id is " + ActionContext.getActionContext().getContext().getId());
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 3).forEach(i -> new Thread(new ExecutionTask()).start());
    }
}

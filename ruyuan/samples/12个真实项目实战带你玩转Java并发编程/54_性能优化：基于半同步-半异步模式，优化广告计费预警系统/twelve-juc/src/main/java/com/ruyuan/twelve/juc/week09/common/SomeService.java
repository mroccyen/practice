package com.ruyuan.twelve.juc.week09.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 **/
public class SomeService extends AbstractSerializer<Task, String> {

    public SomeService() {
        super(new ArrayBlockingQueue<>(200),
              new TaskProcessor<Task, String>() {
                  @Override
                  public String doProcessor(Task task) {
                      return task.getContent();
                  }
              });
    }

    @Override
    protected Task makeTask(Object... params) {
        String id = ((String) params[0]);
        String content = (String) params[1];
        return new Task(id, content);
    }

    /**
     * 执行一些事情
     *
     * @param id      id
     * @param message 内容
     * @return 结果
     */
    public Future<String> doSomething(String id, String message) throws InterruptedException {
        return service(id, message);
    }
}
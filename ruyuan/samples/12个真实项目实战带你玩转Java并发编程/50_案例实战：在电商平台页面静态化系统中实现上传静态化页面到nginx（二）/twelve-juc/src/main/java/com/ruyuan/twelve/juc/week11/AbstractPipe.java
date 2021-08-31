

package com.ruyuan.twelve.juc.week11;

import java.util.concurrent.TimeUnit;

/**
 * Pipe的抽象实现类。 该类会调用其子类实现的doProcess方法对输入元素进行处理。并将相应的输出作为 下一个Pipe实例的输入。
 *
 * @param <IN>  输入类型
 * @param <OUT> 输出类型
 * @author little
 */
public abstract class AbstractPipe<IN, OUT> implements Pipe<IN, OUT> {

    /**
     * 记录当前pipe的下一个pipe对象
     */
    protected volatile Pipe<?, ?> nextPipe = null;

    protected volatile PipeContext pipeCtx;

    @Override
    public void init(PipeContext pipeCtx) {
        this.pipeCtx = pipeCtx;

    }

    @Override
    public void setNextPipe(Pipe<?, ?> nextPipe) {
        // 设置当前pipe的下一个pipe调用链
        this.nextPipe = nextPipe;

    }

    @Override
    public void shutdown(long timeout, TimeUnit unit) {
        // 什么也不做
    }

    /**
     * 留给子类实现。用于子类实现其任务处理逻辑。
     *
     * @param input 输入元素（任务）
     * @return 任务的处理结果
     * @throws PipeException
     */
    protected abstract OUT doProcess(IN input) throws PipeException;

    @Override
    @SuppressWarnings("unchecked")
    public void process(IN input) throws InterruptedException {
        try {
            // 执行当前pipe，得到执行结果
            OUT out = doProcess(input);
            if (null != nextPipe) {
                if (null != out) {
                    // 下一个pipe不为空，同时上一个pipe的执行结果不为空，调用下一个pipe讲当前pipe的输出作为输入
                    ((Pipe<OUT, ?>) nextPipe).process(out);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (PipeException e) {
            pipeCtx.handleError(e);
        } catch (Exception e1) {
            pipeCtx.handleError(new PipeException(this, input, "", e1));
        }
    }
}
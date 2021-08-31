

package com.ruyuan.twelve.juc.week11;

/**
 * 对复合Pipe的抽象。 一个Pipeline实例可包含多个Pipe实例。
 * 
 * @author little
 *
 * @param <IN>
 *          输入类型
 * @param <OUT>
 *          输出类型
 */
public interface Pipeline<IN, OUT> extends Pipe<IN, OUT> {

	/**
	 * 往该Pipeline实例中添加一个Pipe实例。
	 * 
	 * @param pipe
	 *          Pipe实例
	 */
	void addPipe(Pipe<?, ?> pipe);
}

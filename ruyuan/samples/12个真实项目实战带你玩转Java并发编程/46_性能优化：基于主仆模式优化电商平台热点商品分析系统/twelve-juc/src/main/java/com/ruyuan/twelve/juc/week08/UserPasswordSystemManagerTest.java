package com.ruyuan.twelve.juc.week08;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 **/
public class UserPasswordSystemManagerTest {

    public static void main(String[] args) throws InterruptedException {
        UserPasswordSystemManager userPasswordSystemManager = UserPasswordSystemManager.getInstance();
        userPasswordSystemManager.register("23423431");
        userPasswordSystemManager.register("23423432");
        userPasswordSystemManager.register("23423433");
        userPasswordSystemManager.register("23423434");

        Thread.sleep(100);
    }
}
package volatile_04;

import volatile_04.task.InfiniteLoop;

public class InfinityLoopAppl {
    public static void main(String[] args) throws InterruptedException {

        InfiniteLoop loop = new InfiniteLoop();
        Thread thread = new Thread(loop);
        thread.start();
        Thread.sleep(3000);
        loop.setFlag(false);
        System.out.println("flag =" + loop.isFlag());
        System.out.println(Thread.currentThread().getName()+ " finished");
    }
}

package messageBox_05;

import messageBox_05.model.MessageBox;
import messageBox_05.model.MessageBoxC;
import messageBox_05.service.Receiver;
import messageBox_05.service.Sender;

public class MessageBoxAppl {

    private static final int N_MESSAGES=20;
    private static final int N_RECEIVES=5;
    private static MessageBox messageBox = new MessageBoxC();


    public static void main(String[] args) throws InterruptedException {


        Thread sender = new Thread(new Sender(messageBox, N_MESSAGES));
        for (int i = 0; i < N_RECEIVES; i++) {
            Thread receiver = new Thread(new Receiver(messageBox));
            receiver.setDaemon(true);
            receiver.start();

        }
        Thread.sleep(100);
        sender.start();
        sender.join();
        Thread.sleep(100);

    }
}

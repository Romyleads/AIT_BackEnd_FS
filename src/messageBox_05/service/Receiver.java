package messageBox_05.service;

import messageBox_05.model.MessageBox;

public class Receiver implements Runnable {
    private MessageBox messageBox;

    public Receiver(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        while (true) {
            String message = messageBox.get();
            System.out.println(message + " - receiver " + Thread.currentThread().getName());
        }
    }
}
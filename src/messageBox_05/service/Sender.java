package messageBox_05.service;

import messageBox_05.model.MessageBox;

public class Sender implements Runnable {
    private MessageBox messageBox;
    private int nMessages;

    public Sender(MessageBox messageBox, int nMessages) {
        this.messageBox = messageBox;
        this.nMessages = nMessages;
    }

    @Override
    public void run() {
        for (int i = 0; i < nMessages; i++) {
            messageBox.post("message#" + i);
        }
    }
}
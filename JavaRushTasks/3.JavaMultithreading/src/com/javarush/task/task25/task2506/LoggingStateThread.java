package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State state = null;
        while (true){
            State stateNew = thread.getState();
            if(state != stateNew)
            {
                System.out.println(stateNew);
                state = stateNew;
            }
            if(state == State.TERMINATED)
                break;
        }
    }
}

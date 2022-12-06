package com.github.selenium.utils;

public class WaitSecond {

    public void wait(int timeout) throws InterruptedException {

        int timeValue = timeout * 1000;
        Thread.sleep(timeValue);
    }
}

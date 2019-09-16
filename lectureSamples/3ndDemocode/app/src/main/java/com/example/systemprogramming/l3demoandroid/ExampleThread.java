package com.example.systemprogramming.l3demoandroid;

import android.widget.TextView;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ExampleThread extends Thread {

  public interface ExampleThreadReporterInterface {
    void soppOpen(/* ExampleThread caller */);
    void soppClosed(/* ExampleThread caller */);
    void interupted(/* ExampleThread caller */);
  }

  public ExampleThread(ExampleThreadReporterInterface cb) {
    callBackInterface = cb;
  }

  ExampleThreadReporterInterface callBackInterface = null;

  public void run() {
    try {
        while (true) {
          LocalDateTime now = LocalDateTime.now();
          LocalDateTime SOPPOpens = LocalDateTime.of(2019, Month.AUGUST, 29, 15,00,00);

          long diff = now.until(SOPPOpens, ChronoUnit.SECONDS);
          if (diff > 0) {
            callBackInterface.soppOpen(/* this */);
          }
          else {
            callBackInterface.soppClosed(/* this */);
          }
          sleep(2000);
      }
    } catch(Exception e) {
      e.printStackTrace();
      callBackInterface.interupted(/* this */);
    }
  }
}

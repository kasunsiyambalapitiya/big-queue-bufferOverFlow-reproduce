package com.kasun.bigqueue.sample;

import com.leansoft.bigqueue.BigQueueImpl;
import com.leansoft.bigqueue.IBigQueue;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by kasun on 6/18/18.
 */
public class Demo {
    private static final Logger logger = Logger.getLogger(Demo.class);

    public static void main(String[] args) {
        try {
            IBigQueue bigQueue = new BigQueueImpl("/home/kasun/Documents/Support/FOIITFISCALPROD-17/reproducing-at-bigqueue" +
                    "", "demo2");
/*            for (int i = 0; i < Integer.MAX_VALUE; i++) {
               String sample = new String (new char [Integer.MAX_VALUE/100000]).replace('\0','x');
                logger.info("count:============= " + i);
                if (i % 8 == 4){
                    sample= new String ("");
                }

*//*                char charArray[]= {'T','E','S','T','I','N','G','W','S','O','2'};
                String sample = new String (charArray);
                logger.info("count:============= " + i);
                bigQueue.enqueue(sample.getBytes());*//*
            }*/

            for (int i = 0; i < Integer.MAX_VALUE / 2; i++) {

                char[] fetched = new char[12];
                char []fetched2 = new String(bigQueue.dequeue()).toCharArray();
                System.arraycopy(fetched2,0,fetched,0,fetched2.length);
                System.out.println(fetched);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

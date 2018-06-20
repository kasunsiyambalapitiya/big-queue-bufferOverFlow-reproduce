package com.kasun.bigqueue.bufferOverFlow;

import com.leansoft.bigqueue.BigQueueImpl;
import com.leansoft.bigqueue.IBigQueue;
import org.apache.log4j.Logger;
import utils.GenericUtils;

import java.io.IOException;

/**
 * Created by kasun on 6/20/18.
 */
public class Demo {
    private static final Logger logger = Logger.getLogger(com.kasun.bigqueue.sample.Demo.class);

    public static void main(String[] args) {
        try {
            IBigQueue bigQueue = new BigQueueImpl("/home/kasun/Documents/Support/FOIITFISCALPROD-17/reproducing-at-bigqueue" +
                    "", "demo2");
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String sample = new String(new char[Integer.MAX_VALUE / 16]).replace('\0', 'x');
                logger.info("count:============= " + i);
/*                if (i % 8 == 4){
                    sample= new String ("");
                }*/
                bigQueue.enqueue(GenericUtils.serializeObject(sample));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

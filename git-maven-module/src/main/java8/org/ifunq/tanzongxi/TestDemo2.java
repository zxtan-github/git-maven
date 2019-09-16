package org.ifunq.tanzongxi;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestDemo2 {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException {
        for (int i = 0; i < 100; i ++) {
            Random random = new Random();
            int sleep = random.nextInt(10000) + 5000;
            System.out.println(sleep);
        }

        String warehouseIds = "41564,64564163,56415416";
        List<String> warehouseIdList = Arrays.asList(warehouseIds.split(","));

        System.out.println(warehouseIdList.contains(String.valueOf(41564)));
        System.out.println(warehouseIdList.contains(56415416));
    }

}
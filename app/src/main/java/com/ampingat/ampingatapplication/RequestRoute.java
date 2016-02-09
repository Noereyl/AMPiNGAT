package com.ampingat.ampingatapplication;

import java.util.Scanner;

/**
 * Created by Joy Rivera on 2/8/2016.
 */
public class RequestRoute {

    public static void main(String[] args) {
        Scanner scan =  new Scanner(System.in);

        int[][] matrix = new int[15][15];
        int[] distance = new int[15];
        int[] visited = new int[15];
        int[] preD = new int[15];
        String room;
        int min;
        int nextnode;

        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                matrix[i][j] = scan.nextInt();
            }
        }

    }
}

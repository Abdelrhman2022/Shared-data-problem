/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PetersonSolution;

/**
 *
 * @author AbdallaEssam
 */
public class PetersonSolution implements Runnable {

    private boolean[] flag;
    private int turn;
    private int count;

    public PetersonSolution(int count) {
        flag = new boolean[2];
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public synchronized void run() {
        int threadId = Integer.parseInt(Thread.currentThread().getName());
        //entery section
        flag[threadId] = true;
        turn = 1 - threadId;

        while (flag[turn] && turn == 1 - threadId) {
            //do nothing 
            System.out.println(threadId + " do nothing");
        }
        
        //critical section      
        if (threadId == 0) {
            count = count + 1;
        } else {
            count = count - 1;
        }
        System.out.println("T" + threadId + " COUNT:" + count);
        //exit section
        flag[threadId] = false;

        //remainder section
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PetersonSolution;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AbdallaEssam
 */
public class PetersonSolutionDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int count = 5;
        int trials = 0;
        try {
            PetersonSolution sol = new PetersonSolution(count);
            while (sol.getCount() == count) {
                trials++;
                Thread t0 = new Thread(sol, "0");
                Thread t1 = new Thread(sol, "1");
                t0.start();
                t1.start();
                //wait till t0 and t1 terminate 
                t0.join();
                t1.join();
                System.out.println("Final Count: " + sol.getCount());
                System.out.println("=====================================");
            }
            System.out.println("Final Count: " + sol.getCount());
            System.out.println("Trials = " + trials);
        } catch (InterruptedException ex) {
            Logger.getLogger(PetersonSolutionDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

package Banker;

import sun.util.locale.provider.AvailableLanguageTags;

/**
 * The Bank
 */
public class BankImpl implements Bank {

    private int numOfCustomer;		// the number of Customer
    private int numOfResources;		// the number of different resources
    private int[][] maximum;		// the maximum demand of each Customer
    private int[][] need;		// The reaming need of each customer
    private int[][] Allocation;		// The reaming need of each customer
    private int[] Available;            //Available resource 
    private int safeSequence[];

    public BankImpl(int[] resources) {  

        this.numOfCustomer = Customer.COUNT;
        this.numOfResources = resources.length;
        this.maximum = new int[this.numOfCustomer][this.numOfResources];
        this.need = new int[this.numOfCustomer][this.numOfResources];
        this.Allocation = new int[this.numOfCustomer][this.numOfResources];
        this.Available = new int[this.numOfResources];
        this.safeSequence = new int[this.numOfCustomer];
        for (int i = 0; i < resources.length; i++) {
            Available[i] = resources[i];//the amount currently allocated to each customer 
        }
    }

    @Override
    public void addCustomer(int threadNum, int[] maxDemand) {

       

        System.arraycopy(maxDemand, 0, maximum[threadNum], 0, maxDemand.length);
        System.arraycopy(maxDemand, 0, need[threadNum], 0, maxDemand.length);

        for (int i = 0; i < maxDemand.length; i++) {
            this.maximum[threadNum][i] = maxDemand[i];
        }
        for (int i = 0; i < maxDemand.length; i++) {
            this.need[threadNum][i] = maxDemand[i];
        }
    }

    @Override
    public void getState() {
        System.out.print("Available = [");
        for (int i = 0; i < this.numOfResources - 1; i++) {
            System.out.print(this.Allocation[i] + " ");
        }
        System.out.println(this.Allocation[this.numOfResources - 1] + "]");
        System.out.print("\nAllocation = ");
        for (int i = 0; i < this.numOfCustomer; i++) {
            System.out.print("[");
            for (int j = 0; j < this.numOfResources - 1; j++) {
                System.out.print(this.Allocation[i][j] + " ");
            }
            System.out.print(this.Allocation[i][this.numOfResources - 1] + "]");
        }
        System.out.print("\nMax = ");
        for (int i = 0; i < this.numOfCustomer; i++) {
            System.out.print("[");
            for (int j = 0; j < this.numOfResources - 1; j++) {
                System.out.print(maximum[i][j] + " ");
            }
            System.out.print(maximum[i][this.numOfResources - 1] + "]");
        }
        System.out.print("\nNeed = ");
        for (int i = 0; i < this.numOfCustomer; i++) {
            System.out.print("[");
            for (int j = 0; j < this.numOfResources - 1; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.print(need[i][this.numOfResources - 1] + "]");
        }

        System.out.println();
    }

    @Override
    public boolean requestResources(int threadNum, int[] request) {
        if (!Safe(threadNum, request)) {
            
            return false;
        }

        
        for (int i = 0; i < this.numOfResources; i++) {
            this.Available[i] -= request[i];
            this.Allocation[threadNum][i] += request[i];
            need[threadNum][i] = maximum[threadNum][i] - this.Allocation[threadNum][i];
        }
        
        return true;
    }

    @Override
    public void releaseResources(int threadNum, int[] release) {
         System.out.print("\n Customer # " + threadNum + " releasing ");
                for (int i = 0; i < this.numOfResources; i++) System.out.print(release[i] + " ");
                 
        for (int i = 0; i < this.numOfResources; i++) {
            this.Available[i] += release[i];
            this.Allocation[threadNum][i] -= release[i];
            need[threadNum][i] = maximum[threadNum][i] + this.Allocation[threadNum][i];
        }
                 
                System.out.print("Available = ");
                for (int i = 0; i < this.numOfResources; i++)
                    System.out.print(this.Available[i] + "  ");
                     
                System.out.print("Allocated = [");
                for (int i = 0; i < this.numOfResources; i++)
                    System.out.print(this.Allocation[threadNum][i] + "  "); 
                    System.out.print("]"); 
 
        
        }
           
    
    

    private boolean Safe(int threadNum, int[] request) {
        System.out.print("\n Customer # " + threadNum + " requesting ");
        for (int i = 0; i < this.numOfResources; i++) {
            System.out.print(request[i] + " ");
        }

        System.out.print("Available = ");
        for (int i = 0; i < this.numOfResources; i++) {
            System.out.print(this.Available[i] + "  ");
        }

        
        for (int i = 0; i < this.numOfResources; i++) {
            if (request[i] > this.Available[i]) {
                System.out.println("\n\nINSUFFICIENT RESOURCES\n");
                return false;
            }
        }

        
        boolean[] canFinish = new boolean[this.numOfCustomer];
        for (int i = 0; i < this.numOfCustomer; i++) {
            canFinish[i] = false;
        }

        
        int[] available_copy = new int[this.numOfResources];
        System.arraycopy(this.Available, 0, available_copy, 0, this.Available.length);

      
        for (int i = 0; i < this.numOfResources; i++) {
            available_copy[i] -= request[i];
            need[threadNum][i] -= request[i];
            this.Allocation[threadNum][i] += request[i];
        }

        
        for (int i = 0; i < this.numOfCustomer; i++) {
            // first find a thread that can finish
            for (int j = 0; j < this.numOfCustomer; j++) {
                if (!canFinish[j]) {
                    boolean flag2 = true;
                    for (int k = 0; k < this.numOfResources; k++) {
                        if (need[j][k] > available_copy[k]) {
                            flag2 = false;
                        }
                    }
                    if (flag2) { 
                        canFinish[j] = true;
                        for (int x = 0; x < this.numOfResources; x++) {
                            available_copy[x] += this.Allocation[j][x];
                        }
                    }
                }
            }
        }

        // restore the value of need and allocation for this thread
        for (int i = 0; i < this.numOfResources; i++) {
            need[threadNum][i] += request[i];
            this.Allocation[threadNum][i] -= request[i];
        }
       // now go through the boolean array and see if all threads could complete
        boolean result = true;
        for (int i = 0; i < this.numOfCustomer; i++)
            if (!canFinish[i]) {
                result = false;
                break;
            }
 
        return result;
    }
}

import java.util.Scanner;
import java.io.*;
/**
 * This program reads from a text file a list of integers and sorts them using a Merge Sort.
 * It will print out both the sorted and unsorted versions and label them.
 * 
 * @author Grady McPeak 
 * @version Three
 */
public class Merge
{
    static int [] arr;
    static int [] temp;
    
    /**
     * Main method. Decides order of other functions in the program.
     * @param String args [] - command line arguments (not used)
     */
    public static void main (String args []) {
        arr = make();
        System.out.print("Unsorted: ");
        printArr();
        temp = new int [arr.length];
        System.out.println("");
        System.out.print ("Sorted: ");
        sort(0, arr.length-1);
        printArr();
    }
    
    /**
     * A recursive function in which the computer methodically breaks down the array into smaller bits by
     * limiting the portion of the array the computer can work with.
     * @param int lo - the lower bound of the range of the array available for sorting
     * @param int hi - the upper bound of the range of the array available for sorting
     */
    private static void sort (int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi-lo) /2;
            sort (lo, mid);
            sort (mid+1, hi);
            merge (lo, mid, hi);
        }
    }
    
    /** 
     * Merges the two havles of the designated portion of the array into a helper array, and then those
     * are moved to the real thing
     * @param int lo - the lower bound of the designated range
     * @param int hi - the upper bound of the designated range
     * @param int mid - the midpoint of the range
     */
    private static void merge (int lo, int mid, int hi) {
        for (int x = lo; x <= hi; x++) {
            temp[x] = arr[x];
        }
        
        int x = lo;
        int y = mid+1;
        int z = x;
        
        while (x <= mid && y <= hi) {
            if (temp[x] <= temp[y]) {
                arr[z] = temp[x];
                x++;
            } else {
                arr [z] = temp[y];
                y++;
            }
            z++;
        }
        
        while (x <= mid) {
            arr[z] = temp[x];
            x++;
            z++;
        }
    }
    
    /**
     * Places every integer in "nums.txt" into the array arr, unsorted
     */
    private static int [] make () {
        int x = 0;
        int y = 0;
        try {
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader("nums.txt")));
            scan.useDelimiter (",");
            
            while (scan.hasNext() == true) {
                x++;
                scan.next();
            }
            
            arr = new int [x];
            
            Scanner scan2;
            scan2 = new Scanner(new BufferedReader(new FileReader("nums.txt")));
            scan2.useDelimiter (",");
            
            while (scan2.hasNext() == true) {
                arr[y] = scan2.nextInt();
                y++;
            }
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        
        return arr;
    }
    
    /**
     * Prints every element that is currently in the main array (arr)
     */
    private static void printArr () {
        for (int x = 0; x < arr.length; x++) {
            System.out.print (arr[x] + " ");
        }
    }
}

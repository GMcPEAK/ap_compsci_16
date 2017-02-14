import java.util.*;
import java.util.Scanner;
import java.io.*;
/**
 * Reads a text file of any amount of integers and then performs a merge sort on them.
 * Prints out the numbers in it unsorted form. Then sorts them.
 * Then, it prints out the sorted list.
 * 
 * 
 * @author Grady McPeak 
 * @version Two
 */
public class MergeSort
{
    static ArrayList <Integer> list = new ArrayList <Integer> ();
    
    /**
     * main method. calls other methods and also prints out the list unsorted and sorted
     * @param String [] args - command line arguments. not used.
     */
    public static void main (String [] args) {
        list = makeList();
        System.out.println ("Unsorted: " + list);
        list = mergeSort ();
        System.out.println ("Sorted: " + list);
    }
    
    /**
     * reads from a .txt file called "nums.txt" and puts each number in an element of the ArrayList "list"
     * @return list - the new version of the list with all the unsorted integers put in
     */
    private static ArrayList makeList () {
        try {
            Scanner scan;
            scan = new Scanner(new BufferedReader(new FileReader("nums.txt")));
            scan.useDelimiter (",");
            
            while (scan.hasNext() == true) {
                list.add (scan.nextInt());
            }
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        return list;
    }
    
    /**
     * creates a helper ArrayList which will be used in the sorting. 
     * Then calls another method to do most of the heavy lifting.
     * @return nums - the helper list
     */
    private static ArrayList<Integer> mergeSort () { 
        ArrayList<Integer> nums = new ArrayList <Integer> ();
        nums = list;
        nums = helper(nums);
        return nums;
    }
    
    /**
     * Sends in chunks of the arraylist to be merged. Size of chunks gradually increase until the whole 
     * ArrayList has been sorted.
     * @param nums - a "helper" ArrayList used to sort the numbers
     * @return nums - the helper list
     */
    private static ArrayList<Integer> helper (ArrayList <Integer> nums) {
        if (nums.size() == 1) {
            return nums;
        }
        
        ArrayList<Integer> low = new ArrayList<Integer> ();
        for (int x = 0; x <nums.size()/2; x++) {
            low.add(nums.get(x));
        }
        
        ArrayList<Integer> high = new ArrayList<Integer> ();
        for (int x =nums.size()-low.size()-1; x< nums.size(); x++) {
            high.add(nums.get(x));
        }
        
        nums = merge(low, high);
        
        return nums;
    }
    
    /**
     * Merges two smaller ArrayLists into a bigger one.
     * @param ArrayList<Integer> one - the first of the two to be merged together
     * @param ArrayList<Integer> two - the second of the two to be merged
     * @return res - the merged list
     */
    private static ArrayList<Integer> merge (ArrayList<Integer> one, ArrayList<Integer> two) {
          ArrayList<Integer> res = new ArrayList<Integer> ();
          int i1 = 0;
          int i2 = 0;
          
          for (int x = one.size() + two.size(); x > 0;) {
              if (one.get(i1) < two.get(i2)) {
                  res.add(one.get(i1));
                  i1++;
                  x--;
                  if (i1 == one.size()) {
                      one.add(Integer.MAX_VALUE);
                  }
              } else if (one.get(i1) > two.get(i2)) {
                  res.add(two.get(i2));
                  i2++;
                  x--;
                  if (i2 == two.size()) {
                      two.add(Integer.MAX_VALUE);
                  }
              } else {
                  res.add(one.get(i1));
                  i1++;
                  res.add(two.get(i2));
                  i2++;
                  x = x - 2;
              }    
          }
            
          return res;
    }
    
    /**
     * Used to make edits to the "master" ArrayList, which is used to eventually reach the final sorted version.
     * @param ArrayList<Integer> fin - the "master" list
     * @param List<Integer> temp - a set of elements that need to be changed in the list
     * @param int lBound - the lower bound of the range of changes
     * @param int hbound - the upper bound of the range of the changes
     * @return fin - the new version of the master list 
     */
    private static ArrayList<Integer> change (ArrayList<Integer> fin, List<Integer> temp, int lBound, int hBound) {
        int y = 0;
        
        for (int x = lBound; x < hBound; x++) {
            fin.set(x, temp.get(y));
            y++;
        }
        
        return fin;
    }
}

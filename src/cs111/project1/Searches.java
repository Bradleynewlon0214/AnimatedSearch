//2001-111-1

/* 
 * Name: Bradley Newlon
 * Assignment: Project 1 
 * Lab: CS 111 Section 1
 * Date: 2/28/20
 * 
 */

package cs111.project1;

import java.util.HashMap; //used in runFibonacciSearch
import java.util.List; //used all over
import java.util.Random; //used in runRandomSearch


/**
 * class Searches - contains implementations for four searching algorithms (linear search, random search, binary search, fibonacci search)
 * the methods contained in this class by searching algorithm are as follows:
 * 
 * linear search: runLinearSearch
 * 
 * random search: runRandomSearch
 * 
 * binary search: runBinarySearch, binarySearch
 * 
 * fibonacci search: runFibonacciSearch, fibonacciSearch, fibNumber
 * 
 * @author Bradl
 *
 */
public class Searches extends Framework {
    
	private static HashMap<Integer, Integer> fibNumbers; 
	
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	/**
	 * void runLinearSearch - performs linear search on the given list for the given person. this implementation contains the optimization where if we've already passed where the item would be if it were in the list, we just stop.
	 * @param target - Person - the person we are searching for
	 * @param list - List<Person> - the list we are searching in
	 */
	protected void runLinearSearch(Person target, List<Person> list) {
    	for (int i = 0; i < list.size(); i++) {
    		paint(i, STYLE_EXAMINE);
    		delay();
    		if (list.get(i).equals(target)) {
    			paint(i, STYLE_FOUND);
    			return;
    		} else if(list.get(i).compareTo(target) > 0) {
    			break; //get out, we've already passed where the item would be if it were there
    		} else {
    			paint(i, STYLE_MISMATCH);
    			delay();
    		}
    	}
    	paint(list.size()-1, STYLE_NOT_FOUND);
	}

	@Override
	/**
	 * void runBinarySearch - wrapper method for binary search. runs binary search on the given list for the given person
	 * @param target - Person - the person we are searching for
	 * @param list - List<Person> - the list we are searching in
	 */
	protected void runBinarySearch(Person target, List<Person> list) {
		binarySearch(target, list, list.size() - 1, 0);	
	}

	
	/**
	 * boolean binarySearch - recursive implementation of binary search. binary search is done by taking a list of sorted items and finding the middle index using the given left and right bounds then if the item is at the middle index, stop.
	 * in the event that the item is either less than the middle item we shift the cursors left to repeat the process for the left half of the list. we do the opposite if the item is greater than the middle item
	 * @param target - Person - the person we are searching for
	 * @param list - List<Person> - the list we are searching in
	 * @param right - int - our right cursor. for our first run this will be the last index in the structure
	 * @param left - int - our left cursor. for the first runs this will be 0
	 * @return boolean - returns true if the item is found and false if not. the return value is never used, it's just easier for me to write recursive functions that return boolean (well, these searching ones)
	 */
	private boolean binarySearch(Person target, List<Person> list, int right, int left) {
		
		if(right >= left) {
			int middleIndex = (right + left) / 2;
			
			if(list.get(middleIndex).equals(target)) { 
				paint(middleIndex, STYLE_FOUND);
				delay();
				return true;
			}
			
			if(list.get(middleIndex).compareTo(target) > 0) {
				paint(middleIndex, STYLE_EXAMINE);
				delay();
				paint(middleIndex, STYLE_MISMATCH);
				return binarySearch(target, list, middleIndex - 1, left);
			}
			
			if(list.get(middleIndex).compareTo(target) < 0) {
				paint(middleIndex, STYLE_EXAMINE);
				delay();
				paint(middleIndex, STYLE_MISMATCH);
				return binarySearch(target, list, right, middleIndex + 1);
			}
		}
		
		paint(list.size() - 1, STYLE_NOT_FOUND);
		return false;
	}
	
	/**
	 * void runRandomSearch - searches the list randomly until either the target item is found or we've searched a number of times equal to or greater than the size of the list
	 * @param target - Person - the person we are searching for
	 * @param list - List<Person> - the list we are searching in 
	 */
	@Override
	protected void runRandomSearch(Person target, List<Person> list) {
		int numberOfDraws = 0;
		Random rand = new Random();
		while (numberOfDraws <= list.size() - 1) {
			int draw = rand.nextInt(list.size() - 1);
			paint(draw, STYLE_EXAMINE);
			delay();
			paint(draw, STYLE_MISMATCH);
			if(list.get(draw).equals(target)) {
				paint(draw, STYLE_FOUND);
				delay();
				numberOfDraws = list.size() - 1;
			}
			numberOfDraws++;
		}
		paint(list.size() - 1, STYLE_NOT_FOUND);
		
	}
	
	/**
	 * void runFibonacciSearch - runs the method fibonacciSearch, but before that this method computes and stores a list of fibonacci numbers such that they are less than the size of the list.
	 * the largest fibonacci number that is not bigger than the list is stored along with the two fibonacci numbers below it and passed to the fibonacciSearch method
	 * @param target - Person - the person we are searching for
	 * @param list - List<Person> - the list we are searching in
	 */
	@Override
	protected void runFibonacciSearch(Person target, List<Person> list) {
		
		fibNumbers = new HashMap<Integer, Integer>(); //  { fibIndex: fibNum }
		int m = 0;
		while(fibNumber(m, 0, 1) <= list.size()) {
			m++;
			fibNumbers.put(m, fibNumber(m, 0, 1));
		}
		if(fibNumbers.get(m) >= list.size())
			m--;
		int q = m - 2;
		int p = m - 1;
		int i = m;
		fibonacciSearch(target, list, fibNumbers.get(i), fibNumbers.get(p), fibNumbers.get(q));
	}
	
	/**
	 * boolean fibonacciSearch - the main search method for fibonacci search. this method implements the fibonacci search algorithm which is similar to binary search in that it divides up portions of the array, list, or whatever you're searching.
	 * except with fibonacci search instead of finding the mid points of the structure it begins by finding the largest fibonacci number in the structure's set of indices. (in the case of this list, it is size 48 so indices are ranged 0 - 47
	 * and the largest fibonacci number that fits inside that range is 34) then we check that index for the value we are looking for just like in binary search. in the event that the value we are looking for is less than our fibonacci number
	 * we move down to the next fibonacci number and repeat. in the event that our indices do not contain a fibonacci number we add the difference of our fibonacci number and the previous fibonacci number to our current fibonacci number and that
	 * becomes the new index to check.
	 * 
	 * In simpler terms, during this whole process we are maintaing three fibonacci values: our fibonacci number (we'll call it f), f - 1, and f - 2. So in the case that our target value is smaller than the value located at f
	 * we shift down each of our fibonacci numbers by one place (e.g we initially start with 34, 21, 13 and when we shift them down we get 21, 13, 8)
	 * 
	 * In the case that our target value is larger than f we don't shift up our fibonacci numbers. instead we search at the summation of f and f - 2 (e.g if we initally started with 34, 21, 13, we'd search at 47)
	 * 
	 * this assumes sorted data just like binary search too.
	 * 
	 * @param target - Person - person we are looking for
	 * @param list - List<Person> - the list we are searching in
	 * @param i - int - fibonacci number to check first
	 * @param p - int - f - 1
	 * @param q - int - f - 2
	 * @return - boolean - whether or not the value was found. the return value is never used in the main method, I just made it return a boolean cause its easier (for me) to make recursive methods that way.
	 */
	public boolean fibonacciSearch(Person target, List<Person> list, int i, int p, int q) {
		if(i < list.size() & i > -1) { //good job
			if(list.get(i).equals(target)) { 
				paint(i, STYLE_FOUND);
				delay();
				return true;
			}
			
			if(list.get(i).compareTo(target) > 0) { //go down
				paint(i, STYLE_EXAMINE);
				delay();
				paint(i, STYLE_MISMATCH);
				return fibonacciSearch(target, list, i - q, q, p - q);
			}
			
			if(list.get(i).compareTo(target) < 0) { //go up
				paint(i, STYLE_EXAMINE);
				delay();
				paint(i, STYLE_MISMATCH);
				return fibonacciSearch(target, list, i + q, p - q, (2*q) - p);
			}
		}
		
		paint(list.size() - 1, STYLE_NOT_FOUND);
		return false;
	}

	
	

	/**
	 * int fibNumber - Utility function for the fibonacci search methods. This method computers the n'th number in the fibonacci sequence using tail recursion. The sequence is computed essentially by juggling the given x and y params
	 * which on the initial run will be 0 and 1 because they are the first two numbers in the sequence. Also, done by counting down from the given n. Once we reach 0 x is the n'th fibonacci number
	 * @param n - int - is the n'th number to be found in the fibonacci sequence
	 * @param x - int - in the initial run of the method this is 0. when we reach our base case this is the n'th fibonacci number
	 * @param y - int - in the initial run this is 1 which is the second number in the sequence. when we reach our basec case this is the n'th - 1 fibonacci number, but we don't return it. 
	 * @return - the n'th fibonacci number
	 */
	private int fibNumber(int n, int x, int y) {
//		i've been learning haskell
//		tailFib :: Int -> Int -> Int -> Int
//		tailFib count x y
//		    | count <= 0 = x
//		    | otherwise = tailFib (count - 1) y (x+y)
//		x = [tailFib x 0 1 | x <- [1..10]]
		if (n <= 0)
			return x;
		else
			return fibNumber(n - 1, y, x+y);
	}

}

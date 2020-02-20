// Name: Supakarn Laorattanakul
// ID: 6288087
// Section: 3

import java.util.ArrayList; // import the ArrayList class

public class Kalculator {
	//******INSERT YOUR CODE HERE***********
	//Class attributes go here
	ArrayList<Double> Numbers = new ArrayList<Double>();
	//**************************************
	/**
	 * Constructor is the fist method to be call at instantiation of a Kalculator object.
	 * If you need to initialize your object, do so here. 
	 */
	Kalculator()
	{
		//******INSERT YOUR CODE HERE***********
		
		//**************************************
	}
	
	/**
	 * Add number to the list of numbers. 
	 * @param number
	 */
	public void addNumber(double number)
	{	//******INSERT YOUR CODE HERE***********
		this.Numbers.add(number);
		//**************************************
	}
	
	/**
	 * Remove the least recently added number from the list. If the list is empty, do nothing.
	 */
	public void deleteFirstNumber()
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() > 0) this.Numbers.remove(0);
		//**************************************
	}
	
	/**
	 * Remove the most recently added number from the list. If the list is empty, do nothing.
	 */
	public void deleteLastNumber()
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() == 1) this.Numbers.remove(0);
		else if (Numbers.size() > 1) this.Numbers.remove(Numbers.size() - 1);
		//**************************************
	}
	
	/**
	 * Calculate the summation of all the numbers in the list, then returns the sum. 
	 * If the list is empty, return 0.
	 * @return
	 */
	public double getSum()
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() > 0) {
			double sum = 0;
			for (double i : Numbers)
				sum += i;
			return sum;
		}
		else return 0;
		//**************************************
	}
	
	/**
	 * Calculate and return the average of all the numbers in the list.
	 * If the list is empty, return 0.
	 * @return
	 */
	public double getAvg()
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() > 0) {
			double avg = getSum() / Numbers.size();
			return avg;
		}
		return 0;
		//**************************************
	}
	
	/**
	 * Calculate and return the sample standard deviation of all the numbers in the list.
	 * If the list has fewer than 2 numbers, return 0.
	 * @return
	 */
	public double getStd()
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() > 1) {
			double sumUpper = 0;
			for (double i : Numbers)
				sumUpper += Math.pow(i - getAvg(), 2);
			double std2 = sumUpper / (Numbers.size() - 1);
			double std = Math.sqrt(std2);
			return std;
		} else
		return 0;
		//**************************************
	}
	
	/**
	 * Find and return the maximum of all the numbers in the list.
	 * If the list is empty, return 0.
	 * @return
	 */
	public double getMax()
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() > 0) {
			double max = Numbers.get(0);
			for (double i : Numbers) {
				if (i > max) max = i;
			}
			return max;
		}
		else return 0;
		//**************************************
	}
	
	/**
	 * Find and return the minimum of all the numbers in the list.
	 * If the list is empty, return 0.
	 */
	public double getMin()
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() > 0) {
			double min = Numbers.get(0);
			for (double i : Numbers) {
				if (i < min) min = i;
			}
			return min;
		}
		else return 0;
		//**************************************
	}
	
	/**
	 * Find and return the maximum k numbers of all the numbers in the list as an array of k double number.
	 * The order of the returned k numbers does not matter. (We only care if you can get the max k elements)
	 * If the list has fewer than k numbers, return null.
	 */
	public double[] getMaxK(int k)
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() >= k) {
			double[] num = orderNumber();
			double[] maxK = new double[k];
			for (int i = 0; i < k; i++) {
				maxK[i] = num[num.length - 1 - i];
			}
			return maxK;
		}
		else return null;
		//**************************************
	}
	
	/**
	 * Find and return the minimum k numbers of all the numbers in the list as an array of k double number.
	 * The order of the returned k numbers does not matter. (We only care if you can get the min k elements)
	 * If the list has fewer than k numbers, return null.
	 */
	public double[] getMinK(int k)
	{
		//******INSERT YOUR CODE HERE***********
		if (Numbers.size() >= k) {
			double[] num = orderNumber();
			double[] minK = new double[k];
			for (int i = 0; i < k; i++) {
				minK[i] = num[i];
			}
			return minK;
		}
		else return null;
		//**************************************
	}
	
	/**
	 * Print (via System.out.println()) the numbers in the list in format of:
	 * DATA[<N>]:[<n1>, <n2>, <n3>, ...]
	 * Where N is the size of the list. <ni> is the ith number in the list.
	 * E.g., "DATA[4]:[1.0, 2.0, 3.0, 4.0]"
	 */
	public void printData()
	{
		//******INSERT YOUR CODE HERE***********
		int size = Numbers.size();
		System.out.print("DATA[" + size + "]:[");
		for (int i = 0; i < size; i++) {
			System.out.print(Numbers.get(i));
			if (i != size - 1) System.out.print(", ");
		}
		System.out.println("]");
		//**************************************
	}

	public double[] orderNumber() {

		double[] numOrder = new double[Numbers.size()];
		for (int i = 0; i < Numbers.size(); i++) {
			numOrder[i] = Numbers.get(i);
		}

		// Order Min to Max
		for (int i = 0; i < numOrder.length; i++) {
			for (int j = 0; j < numOrder.length; j++) {
				if (numOrder[i] < numOrder[j]) {
					double temp = numOrder[j];
					numOrder[j] = numOrder[i];
					numOrder[i] = temp;
				}
			}
		}
		return numOrder;
	}
}

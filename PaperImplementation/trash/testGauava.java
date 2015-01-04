// http://stackoverflow.com/questions/460364/how-to-use-classes-from-jar-files
// http://stackoverflow.com/questions/2569279/how-to-flatten-2d-array-to-1d-array

import com.google.common.primitives.Ints;

class testGuava
{
	public static void main(String args[])
	{
		int originalArray[][]={{1,2},{3,4}};
		int[] all = Ints.concat(originalArray);
	}
}
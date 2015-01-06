/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml;

import com.google.common.primitives.Ints;
import cern.colt.bitvector.BitMatrix; //http://dst.lbl.gov/ACSSoftware/colt/api/index.html
import extendingLibrary.MyBitMatrix;
/**
 *
 * @author shinchan
 */
public class testGauava {
    	public static void main(String args[])
	{
		int originalArray[][]={{1,2},{3,4}};
		int[] all = Ints.concat(originalArray);
                System.out.println("This workked");
                
                MyBitMatrix p = new MyBitMatrix(3, 3);
                BitMatrix q = new BitMatrix(3, 3);
                MyBitMatrix test = new MyBitMatrix(3, 3);
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                    {
                        p.put(i, j, true);
                        test.put(i, j, true);
                        if(i==j)
                            q.put(j,i, true);
                    }
                }
                q.put(1,2,true);
               MyBitMatrix z = new MyBitMatrix(3,3);
               p.and(q);
               z.and(q);
               
               q=p.copy();
               
               test.toString();
               System.out.println(q.toString());
                
        }
        
}

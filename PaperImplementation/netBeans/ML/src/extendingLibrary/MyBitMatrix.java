package extendingLibrary;


import cern.colt.bitvector.BitMatrix;
import cern.colt.bitvector.BitVector;
import cern.colt.function.IntIntProcedure;
import cern.jet.random.engine.RandomSeedTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shinchan
 */
public class MyBitMatrix extends BitMatrix {

    public MyBitMatrix(int i, int i1) {
        super(i, i1);
    }

    @Override
    public void xor(BitMatrix bm) {
        super.xor(bm); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitVector toBitVector() {
        return super.toBitVector(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        return super.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rows() {
        return super.rows(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void replaceBoxWith(int i, int i1, int i2, int i3, boolean bln) {
        super.replaceBoxWith(i, i1, i2, i3, bln); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void replaceBoxWith(int i, int i1, int i2, int i3, BitMatrix bm, int i4, int i5) {
        super.replaceBoxWith(i, i1, i2, i3, bm, i4, i5); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putQuick(int i, int i1, boolean bln) {
        super.putQuick(i, i1, bln); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void put(int i, int i1, boolean bln) {
        super.put(i, i1, bln); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitMatrix part(int i, int i1, int i2, int i3) {
        return super.part(i, i1, i2, i3); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void or(BitMatrix bm) {
        super.or(bm); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void not() {
        super.not(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getQuick(int i, int i1) {
        return super.getQuick(i, i1); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean get(int i, int i1) {
        return super.get(i, i1); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean forEachCoordinateInState(boolean bln, IntIntProcedure iip) {
        return super.forEachCoordinateInState(bln, iip); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void elements(long[] longs, int i, int i1) {
        super.elements(longs, i, i1); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected long[] elements() {
        return super.elements(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitMatrix copy() {
        return super.copy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void containsBox(int i, int i1, int i2, int i3) {
        super.containsBox(i, i1, i2, i3); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int columns() {
        return super.columns(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object clone() {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        super.clear(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void checkDimensionCompatibility(BitMatrix bm) {
        super.checkDimensionCompatibility(bm); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int cardinality() {
        return super.cardinality(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void andNot(BitMatrix bm) {
        super.andNot(bm); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void and(BitMatrix bm) {
        super.and(bm); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {       
       String out = "";
       for(int i=0;i<rows;i++) {
           for(int j=0;  j<columns; j++) {
               out = out+ " "+this.get(i, j);
           }
           out = out +"\n";
        }
       return out;
    }
    
    public String toStringEasyCompare()
    {
        String out="";
        int count=0;
        
        for (int l = 0; l < rows; l++) 
        {
            for (int m = 0; m < columns; m++)
            {
                if ((get(l, m))) 
                {
                    out += Integer.toString(count) + "\n";
                }
                count++;
            }
        }
        
        return out;
    }
    
    public static MyBitMatrix toMyBitMatrix(BitMatrix in)
    {
        int rows=in.rows(),columns=in.columns();
        MyBitMatrix out = new MyBitMatrix(rows, columns);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                if(in.get(i,j))
                {
                    out.put(i, j, true);
                }
            }
        }
        return out;
    }
}

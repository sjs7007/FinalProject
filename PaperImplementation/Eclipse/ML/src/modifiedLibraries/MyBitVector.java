package modifiedLibraries;

import cern.colt.bitvector.BitVector;
import cern.colt.function.IntProcedure;

public class MyBitVector extends BitVector {

	public MyBitVector(int arg1) {
		super(arg1);
		// TODO Auto-generated constructor stub
	}
	public MyBitVector(long[] arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void and(BitVector arg0) {
		// TODO Auto-generated method stub
		super.and(arg0);
	}

	@Override
	public void andNot(BitVector arg0) {
		// TODO Auto-generated method stub
		super.andNot(arg0);
	}

	@Override
	public int cardinality() {
		// TODO Auto-generated method stub
		return super.cardinality();
	}

	@Override
	protected void checkSize(BitVector arg0) {
		// TODO Auto-generated method stub
		super.checkSize(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	@Override
	public void clear(int arg0) {
		// TODO Auto-generated method stub
		super.clear(arg0);
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public BitVector copy() {
		// TODO Auto-generated method stub
		return super.copy();
	}

	@Override
	public long[] elements() {
		// TODO Auto-generated method stub
		return super.elements();
	}

	@Override
	public void elements(long[] arg0, int arg1) {
		// TODO Auto-generated method stub
		super.elements(arg0, arg1);
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public boolean forEachIndexFromToInState(int arg0, int arg1, boolean arg2,
			IntProcedure arg3) {
		// TODO Auto-generated method stub
		return super.forEachIndexFromToInState(arg0, arg1, arg2, arg3);
	}

	@Override
	public boolean get(int arg0) {
		// TODO Auto-generated method stub
		return super.get(arg0);
	}

	@Override
	public long getLongFromTo(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return super.getLongFromTo(arg0, arg1);
	}

	@Override
	public boolean getQuick(int arg0) {
		// TODO Auto-generated method stub
		return super.getQuick(arg0);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public int indexOfFromTo(int arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return super.indexOfFromTo(arg0, arg1, arg2);
	}

	@Override
	public void not() {
		// TODO Auto-generated method stub
		super.not();
	}

	@Override
	protected int numberOfBitsInPartialUnit() {
		// TODO Auto-generated method stub
		return super.numberOfBitsInPartialUnit();
	}

	@Override
	protected int numberOfFullUnits() {
		// TODO Auto-generated method stub
		return super.numberOfFullUnits();
	}

	@Override
	public void or(BitVector arg0) {
		// TODO Auto-generated method stub
		super.or(arg0);
	}

	@Override
	public BitVector partFromTo(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return super.partFromTo(arg0, arg1);
	}

	@Override
	public void put(int arg0, boolean arg1) {
		// TODO Auto-generated method stub
		super.put(arg0, arg1);
	}

	@Override
	public void putLongFromTo(long arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		super.putLongFromTo(arg0, arg1, arg2);
	}

	@Override
	public void putQuick(int arg0, boolean arg1) {
		// TODO Auto-generated method stub
		super.putQuick(arg0, arg1);
	}

	@Override
	public void replaceFromToWith(int arg0, int arg1, BitVector arg2, int arg3) {
		// TODO Auto-generated method stub
		super.replaceFromToWith(arg0, arg1, arg2, arg3);
	}

	@Override
	public void replaceFromToWith(int arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		super.replaceFromToWith(arg0, arg1, arg2);
	}

	@Override
	public void set(int arg0) {
		// TODO Auto-generated method stub
		super.set(arg0);
	}

	@Override
	public void setSize(int arg0) {
		// TODO Auto-generated method stub
		super.setSize(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		String temp="";
		int count=0;
		for(int i=0;i<this.size();i++)
		{
			if(this.get(i))
			{
				temp+=Integer.toString(count)+"\n";
			}
			count++;
		}
		return temp;
	}

	@Override
	public void xor(BitVector arg0) {
		// TODO Auto-generated method stub
		super.xor(arg0);
	}

}


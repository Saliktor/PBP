package com.revature.gamelogic;

public class Square {
	private int idx;
	private int idy;
	private int value;
	
	public Square() {
		super();
	}
	
	

	public int getIdx() {
		return idx;
	}



	public void setIdx(int idx) {
		this.idx = idx;
	}



	public int getIdy() {
		return idy;
	}



	public void setIdy(int idy) {
		this.idy = idy;
	}



	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public Square(int idx, int idy, int value) {
		super();
		this.idx = idx;
		this.idy = idy;
		this.value = value;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idx;
		result = prime * result + idy;
		result = prime * result + value;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Square other = (Square) obj;
		if (idx != other.idx)
			return false;
		else if (idy != other.idy)
			return false;
		else if (value != other.value)
			return false;
		else
			return true;
	}



	@Override
	public String toString() {
		return "Square [idx=" + idx + ", idy=" + idy + ", value=" + value + "]";
	}
	
}



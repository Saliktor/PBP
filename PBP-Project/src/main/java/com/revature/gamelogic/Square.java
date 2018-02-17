package com.revature.gamelogic;

public class Square {
	public int idx;
	public int idy;
	public int value;
	
	public Square() {
		super();
	}

	public Square(int idx, int idy, int value) {
		super();
		this.idx = idx;
		this.idy = idy;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Square [idx=" + idx + ", idy=" + idy + ", value=" + value + "]";
	}
	
}



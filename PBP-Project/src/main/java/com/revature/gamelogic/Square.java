package com.revature.gamelogic;

public class Square {
	int idx;
	int idy;
	int value;
	
	Square() {
		super();
	}

	Square(int idx, int idy, int value) {
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



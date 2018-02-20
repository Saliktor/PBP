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
		if (idy != other.idy)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Square [idx=" + idx + ", idy=" + idy + ", value=" + value + "]";
	}
	
}



package com.revature.gamelogic;

import java.util.Arrays;

public class Game implements Cloneable {
		Square[][] boardstate = new Square[8][8]; 
		
		Game() {
			super();
			for (int i=0 ; i<8 ; i++) {
				for (int j=0 ; j<8 ;j++) {
					this.boardstate[i][j] = new Square(i,j,0);
				}
			}
			this.boardstate[3][3].value = 1;
			this.boardstate[4][4].value = 1;
			this.boardstate[3][4].value = 2;
			this.boardstate[4][3].value = 2;
		}
		
		Game(Square[][] b) {
//			for (int i=0 ; i<8 ; i++) {
//				for (int j=0 ; j<8 ;j++) {
//					this.boardstate[i][j] = b[i][j];
//					System.out.print(b[i][j].value);
//				}
//				System.out.println();
//			}
//			int[] arr = new int[8];
//			System.arraycopy(System.arraycopy(b, 0, arr, 0, 4), 0, this.boardstate,0,8);
//			System.arraycopy(array, 0, newArr, 0, 4);
//			this.boardstate = Arrays.copyOf(b, 8);
			this.boardstate = b.clone();
		}
		
		public void printBoard() {
			for (int i=0 ; i<8 ; i++){
				for (int j=0 ; j<8 ; j++) {
					String val = this.boardstate[i][j].value == 0 ? " " : ""+this.boardstate[i][j].value;
					System.out.print("["+val+"]");
				}
				System.out.print("\n");
			}
			return;
		}
}

package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static char[] board = new char[9];

	public static void main(String[] args) throws IOException, FileNotFoundException {

		if (args.length != 1) {
			System.out.println("Please Provide File to read to begin GOL");
			return;
		}

		String filePath = args[0];
		System.out.println("Processing: " + filePath);

		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);

		for (int i = 0; i < 3; i++) {
			String line = br.readLine();
			for (int j = 0; j < 3; j++) {
				board[i * 3 + j] = line.charAt(j);
			}
		}
		br.close();

		System.out.println("Board:");
		System.out.println();
		printBoard(board);
		System.out.println("-------------------------------------------------------------------");
		System.out.println();

		int utility = 0;

		int bestScore = Integer.MIN_VALUE;
		for (int i = 0; i < board.length; i++) {
			if (board[i] == '.') {
				board[i] = 'X';
				if (checkWin('O')) {
					utility -= 1;
				}
				if (checkWin('X')) {
					utility += 1;
				}
				// Evaluate the next board
				bestScore = Integer.MAX_VALUE;
				for (int j = 0; j <board.length; j++){
					if (board[j] == '.'){
						board[j] = 'O';
						if (checkWin('O')) {
							utility -= 1;
						}
						if (checkWin('X')) {
							utility += 1;
							break;
						}
						board[j] = '.';
					}
				}
				
				// return board to what was given in the text file
				board[i] = '.';

				System.out.println("y = " + i / 3 + ", x= " + i % 3 + ", utility= " + utility);
			}
			utility = 0;
		}

	}

	private static void printBoard(char[] board) {
		for (int i = 0; i < 9; i++) {
			System.out.print(board[i]);
			if ((i + 1) % 3 == 0) {
				System.out.println(); // New line after each row
			}
		}
	}

	private static boolean checkWin(char player) {
		int[][] winConditions = {
				{ 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
				{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
				{ 0, 4, 8 }, { 2, 4, 6 }
		};
		for (int[] condition : winConditions) {
			// board[condition[0]] is looking at the first index of each array within the
			// winCondition. etc
			// so {3,4,5}
			if (board[condition[0]] == player && board[condition[1]] == player && board[condition[2]] == player) {
				return true;
			}
		}
		return false;
	}


}

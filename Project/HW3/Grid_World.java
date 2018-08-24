package Array;

import java.util.ArrayList;
import java.util.List;

public class Grid_World {

	public void getMax2() {

		int iteration = 1000;

		int[][] grid = { { 0, 0, 2, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0 } };
		int row = grid.length, col = grid[0].length;
		double[][] termi_val = new double[row][col];
		String[][] direction = new String[row][col];
		termi_val[0][2] = 10.0;
		termi_val[2][4] = 5.0;
		double r_walk = -0.3, r_run = -0.2;
		double factor = 0.7;
		double p_walk = 0.8, p_run = 0.6;
		double walk_n_max = 0;

		for (int k = 0; k < iteration; k++) {
			for (int i = row - 1; i >= 0; i--) {
				for (int j = 0; j < col; j++) {
					if (grid[i][j] != 1 && grid[i][j] != 2) {
						// walk up
						int x = i, y = j;
						walk_n_max = 0.00;
						walk_n_max += r_walk;
						if (x - 1 >= 0 && grid[x - 1][y] != 1) {
							walk_n_max += factor * p_walk * termi_val[x - 1][y];
						} else {
							walk_n_max += factor * p_walk * termi_val[x][y];
						}
						if (y - 1 >= 0 && grid[x][y - 1] != 1) {

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y - 1];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						if (y + 1 < col && grid[x][y + 1] != 1) {

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y + 1];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "walk up";
							termi_val[x][y] = (double) walk_n_max;
						}

						// walk down
						walk_n_max = 0.00;
						walk_n_max += r_walk;
						if (x + 1 < row && grid[x + 1][y] != 1) {

							walk_n_max += factor * p_walk * termi_val[x + 1][y];

						} else {
							walk_n_max += factor * p_walk * termi_val[x][y];
						}
						if (y - 1 >= 0 && grid[x][y - 1] != 1) {
							// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y - 1];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						if (y + 1 < col && grid[x][y + 1] != 1) {
							// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y + 1];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "walk down";
							termi_val[x][y] = (double) walk_n_max;
						}
						// // walk_n_max *= Math.pow(factor, k);

						// walk west
						walk_n_max = 0.00;
						walk_n_max += r_walk;
						if (y - 1 >= 0 && grid[x][y - 1] != 1) {

							walk_n_max += factor * p_walk * termi_val[x][y - 1];
						} else {
							walk_n_max += factor * p_walk * termi_val[x][y];
						}

						if (x + 1 < row && grid[x + 1][y] != 1) {
							// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x + 1][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						if (x - 1 >= 0 && grid[x - 1][y] != 1) {
							// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x - 1][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						// walk_n_max *= Math.pow(factor, k);
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "walk left";
							termi_val[x][y] = (double) walk_n_max;
						}

						// walk east
						walk_n_max = 0.00;
						walk_n_max += r_walk;
						if (y + 1 < col && grid[x][y + 1] != 1) {

							walk_n_max += factor * p_walk * termi_val[x][y + 1];
						} else {
							walk_n_max += factor * p_walk * termi_val[x][y];
						}

						if (x + 1 < row && grid[x + 1][y] != 1) {
							// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x + 1][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						if (x - 1 >= 0 && grid[x - 1][y] != 1) {
							// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x - 1][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_walk) * termi_val[x][y];
						}
						// walk_n_max *= Math.pow(factor, k);
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "walk right";
							termi_val[x][y] = (double) walk_n_max;
						}

						// run north
						walk_n_max = 0.00;
						walk_n_max += r_run;
						if (x - 2 >= 0 && grid[x - 1][y] != 1 && grid[x - 2][y] != 1) {

							walk_n_max += factor * p_run * termi_val[x - 2][y];
						} else {
							walk_n_max += factor * p_run * termi_val[x][y];
						}

						if (y - 2 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 2] != 1) {
							// walk_n_max += 0.5 * (1 - p_run) * r_run;

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y - 2];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];
						}
						if (y + 2 < col && grid[x][y + 1] != 1 && grid[x][y + 2] != 1) {
							// walk_n_max += 0.5 * (1 - p_run) * r_run;

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y + 2];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];
						}
						// walk_n_max *= Math.pow(factor, k);
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "run up";
							termi_val[x][y] = (double) walk_n_max;
						}

						// run south
						walk_n_max = 0.00;
						walk_n_max += r_run;
						if (x + 2 < row && grid[x + 1][y] != 1 && grid[x + 2][y] != 1) {

							walk_n_max += factor * p_run * termi_val[x + 2][y];
						} else {
							walk_n_max += factor * p_run * termi_val[x][y];
						}

						if (y - 2 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 2] != 1) {
							// walk_n_max += 0.5 * (1 - p_run) * r_run;

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y - 2];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];
						}
						if (y + 2 < col && grid[x][y + 1] != 1 && grid[x][y + 2] != 1) {
							// walk_n_max += 0.5 * (1 - p_run) * r_run;

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y + 2];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];

						}
						// walk_n_max *= Math.pow(factor, k);
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "run down";
							termi_val[x][y] = (double) walk_n_max;
						}

						// run west
						walk_n_max = 0.00;
						walk_n_max += r_run;
						if (y - 2 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 2] != 1) {

							walk_n_max += factor * p_run * termi_val[x][y - 2];
						} else {
							walk_n_max += factor * p_run * termi_val[x][y];
						}

						if (x - 2 >= 0 && grid[x - 1][y] != 1 && grid[x - 2][y] != 1) {

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x - 2][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];
						}
						if (x + 2 < row && grid[x + 1][y] != 1 && grid[x + 2][y] != 1) {
							// walk_n_max += 0.5 * (1 - p_run) * r_run;

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x + 2][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];
						}
						// walk_n_max *= Math.pow(factor, k);
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "run left";
							termi_val[x][y] = (double) walk_n_max;
						}

						// run east
						walk_n_max = 0.00;
						walk_n_max += r_run;
						if (y + 2 < col && grid[x][y + 1] != 1 && grid[x][y + 2] != 1) {

							walk_n_max += factor * p_run * termi_val[x][y + 2];
						} else {
							walk_n_max += factor * p_run * termi_val[x][y];
						}

						if (x - 2 >= 0 && grid[x - 1][y] != 1 && grid[x - 2][y] != 1) {
							// walk_n_max += 0.5 * (1 - p_run) * r_run;

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x - 2][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];
						}
						if (x + 2 < row && grid[x + 1][y] != 1 && grid[x + 2][y] != 1) {
							// walk_n_max += 0.5 * (1 - p_run) * r_run;

							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x + 2][y];

						} else {
							walk_n_max += factor * 0.5 * (1 - p_run) * termi_val[x][y];
						}
						// walk_n_max *= Math.pow(factor, k);
						if (walk_n_max > (double) termi_val[x][y]) {
							direction[x][y] = "run right";
							termi_val[x][y] = (double) walk_n_max;
						}

					}
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(direction[i][j] + " ");
			}
			System.out.println();
		}

	}

	public void getMax3() {
		int tt = 0;
		int iteration = 1000;

		List<Integer> list = new ArrayList<>();
		// int[][] grid = { { 0, 0, 2, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 0, 0,
		// 0, 0, 2, 0 }, { 0, 0, 0, 0, 0, 0 },
		// { 0, 0, 0, 1, 0, 0 } };
		boolean flag = true;
		int[][] grid = new int[500][600];

		int row = grid.length, col = grid[0].length;
		int[][] check = new int[row][col];

		double[][] termi_val = new double[row][col];
		String[][] direction = new String[row][col];

		// termi_val[0][2] = 10.0;
		// termi_val[2][4] = 5.0;

		termi_val[400][99] = 10.0;
		grid[400][99] = 2;
		// double r_walk = -0.3, r_run = -0.2;
		double r_walk = 0, r_run = 0;
		double factor = 0.7;
		// double p_walk = 0.8, p_run = 0.6;
		double p_walk = 1, p_run = 1;
		double walk_n_max1 = 0;
		double walk_n_max2 = 0;
		double walk_n_max3 = 0;
		double walk_n_max4 = 0;
		double walk_n_max5 = 0;
		double walk_n_max6 = 0;
		double walk_n_max7 = 0;
		double walk_n_max8 = 0;
		// store all the node where they can move
		int[][] matrix = new int[row * col][49];
		int countTotal = 0;
		int index = 0;
		// walk up
		int x = 0, y = 2;
		// int k = i * col + j;
		if (x - 1 >= 0 && grid[x - 1][y] != 1 && grid[x - 1][y] != 2 && check[x - 1][y] == 0) {
			list.add((x - 1) * col + y);
			check[x - 1][y] = 1;
			countTotal++;
		}
		if (y - 1 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 1] != 2 && check[x][y - 1] == 0) {

			list.add(x * col + y - 1);
			check[x][y - 1] = 1;
			countTotal++;
		}
		if (y + 1 < col && grid[x][y + 1] != 1 && grid[x][y + 1] != 2 && check[x][y + 1] == 0) {

			list.add(x * col + y + 1);
			check[x][y + 1] = 1;
			countTotal++;
		}

		if (x + 1 < row && grid[x + 1][y] != 1 && grid[x + 1][y] != 2 && check[x + 1][y] == 0) {

			list.add((x + 1) * col + y);
			check[x + 1][y] = 1;
			countTotal++;
		}

		// if (y - 2 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 2] != 1 &&
		// grid[x][y - 2] != 2 && check[x][y - 2] == 0) {
		// // walk_n_max += 0.5 * (1 - p_run) * r_run;
		//
		// list.add(x * col + y - 2);
		// check[x][y - 2] = 1;
		// countTotal++;
		// }
		// if (y + 2 < col && grid[x][y + 1] != 1 && grid[x][y + 2] != 1 &&
		// grid[x][y + 2] != 2 && check[x][y + 2] == 0) {
		// // walk_n_max += 0.5 * (1 - p_run) * r_run;
		//
		// list.add(x * col + y + 2);
		// check[x][y + 2] = 1;
		// countTotal++;
		// }
		//
		// if (x + 2 < row && grid[x + 1][y] != 1 && grid[x + 2][y] != 1 &&
		// grid[x + 2][y] != 2 && check[x + 2][y] == 0) {
		//
		// list.add((x + 2) * col + y);
		// check[x + 2][y] = 1;
		// countTotal++;
		// }
		//
		// if (x - 2 >= 0 && grid[x - 1][y] != 1 && grid[x - 2][y] != 1 &&
		// grid[x - 2][y] != 2 && check[x - 2][y] == 0) {
		//
		// list.add((x - 2) * col + y);
		// check[x - 2][y] = 1;
		// countTotal++;
		// }

		while (countTotal < row * col - 1) {
			int k = list.get(index++);
			int xx = k / col, yy = k % col;
			if (xx - 1 >= 0 && grid[xx - 1][yy] != 1 && grid[xx - 1][yy] != 2 && check[xx - 1][yy] == 0) {
				list.add((xx - 1) * col + yy);
				check[xx - 1][yy] = 1;
				countTotal++;
			}
			if (yy - 1 >= 0 && grid[xx][yy - 1] != 1 && grid[xx][yy - 1] != 2 && check[xx][yy - 1] == 0) {

				list.add(xx * col + yy - 1);
				check[xx][yy - 1] = 1;
				countTotal++;
			}
			if (yy + 1 < col && grid[xx][yy + 1] != 1 && grid[xx][yy + 1] != 2 && check[xx][yy + 1] == 0) {

				list.add(xx * col + yy + 1);
				check[xx][yy + 1] = 1;
				countTotal++;
			}

			if (xx + 1 < row && grid[xx + 1][yy] != 1 && grid[xx + 1][yy] != 2 && check[xx + 1][yy] == 0) {

				list.add((xx + 1) * col + yy);
				check[xx + 1][yy] = 1;
				countTotal++;
			}

			// if (yy - 2 >= 0 && grid[xx][yy - 1] != 1 && grid[xx][yy - 2] != 1
			// && grid[xx][yy - 2] != 2
			// && check[xx][yy - 2] == 0) {
			// // walk_n_max += 0.5 * (1 - p_run) * r_run;
			//
			// list.add(xx * col + yy - 2);
			// check[xx][yy - 2] = 1;
			// countTotal++;
			// }
			// if (yy + 2 < col && grid[xx][yy + 1] != 1 && grid[xx][yy + 2] !=
			// 1 && grid[xx][yy + 2] != 2
			// && check[xx][yy + 2] == 0) {
			// // walk_n_max += 0.5 * (1 - p_run) * r_run;
			//
			// list.add(xx * col + yy + 2);
			// check[xx][yy + 2] = 1;
			// countTotal++;
			// }
			//
			// if (xx + 2 < row && grid[xx + 1][yy] != 1 && grid[xx + 2][yy] !=
			// 1 && grid[xx + 2][yy] != 2
			// && check[xx + 2][yy] == 0) {
			//
			// list.add((xx + 2) * col + yy);
			// check[xx + 2][yy] = 1;
			// countTotal++;
			// }
			//
			// if (xx - 2 >= 0 && grid[xx - 1][yy] != 1 && grid[xx - 2][yy] != 1
			// && grid[xx - 2][yy] != 2
			// && check[xx - 2][yy] == 0) {
			//
			// list.add((xx - 2) * col + yy);
			// check[xx - 2][yy] = 1;
			// countTotal++;
			// }
		}

		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] != 1 && grid[i][j] != 2) {
					// walk up
					x = i;
					y = j;
					int k = i * col + j;
					if (x - 1 >= 0 && grid[x - 1][y] != 1) {
						matrix[k][1] = x - 1;
						matrix[k][2] = y;
					} else {
						matrix[k][1] = x;
						matrix[k][2] = y;
					}
					if (y - 1 >= 0 && grid[x][y - 1] != 1) {

						matrix[k][3] = x;
						matrix[k][4] = y - 1;

					} else {
						matrix[k][3] = x;
						matrix[k][4] = y;
					}
					if (y + 1 < col && grid[x][y + 1] != 1) {

						matrix[k][5] = x;
						matrix[k][6] = y + 1;

					} else {
						matrix[k][5] = x;
						matrix[k][6] = y;
					}
					// walk down

					if (x + 1 < row && grid[x + 1][y] != 1) {

						matrix[k][7] = x + 1;
						matrix[k][8] = y;

					} else {
						matrix[k][7] = x;
						matrix[k][8] = y;
					}
					if (y - 1 >= 0 && grid[x][y - 1] != 1) {
						// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

						matrix[k][9] = x;
						matrix[k][10] = y - 1;

					} else {
						matrix[k][9] = x;
						matrix[k][10] = y;
					}
					if (y + 1 < col && grid[x][y + 1] != 1) {
						// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

						matrix[k][11] = x;
						matrix[k][12] = y + 1;

					} else {
						matrix[k][11] = x;
						matrix[k][12] = y;
					}

					// // walk_n_max *= Math.pow(factor, k);

					// walk west

					if (y - 1 >= 0 && grid[x][y - 1] != 1) {

						matrix[k][13] = x;
						matrix[k][14] = y - 1;
					} else {
						matrix[k][13] = x;
						matrix[k][14] = y;
					}

					if (x + 1 < row && grid[x + 1][y] != 1) {
						// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

						matrix[k][15] = x + 1;
						matrix[k][16] = y;

					} else {
						matrix[k][15] = x;
						matrix[k][16] = y;
					}
					if (x - 1 >= 0 && grid[x - 1][y] != 1) {
						// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

						matrix[k][17] = x - 1;
						matrix[k][18] = y;

					} else {
						matrix[k][17] = x;
						matrix[k][18] = y;
					}
					// walk_n_max *= Math.pow(factor, k);

					// walk east

					if (y + 1 < col && grid[x][y + 1] != 1) {

						matrix[k][19] = x;
						matrix[k][20] = y + 1;
					} else {
						matrix[k][19] = x;
						matrix[k][20] = y;
					}

					if (x + 1 < row && grid[x + 1][y] != 1) {
						// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

						matrix[k][21] = x + 1;
						matrix[k][22] = y;

					} else {
						matrix[k][21] = x;
						matrix[k][22] = y;
					}
					if (x - 1 >= 0 && grid[x - 1][y] != 1) {
						// walk_n_max += 0.5 * (1 - p_walk) * r_walk;

						matrix[k][23] = x - 1;
						matrix[k][24] = y;

					} else {
						matrix[k][23] = x;
						matrix[k][24] = y;
					}
					// walk_n_max *= Math.pow(factor, k);

					// run north

					if (x - 2 >= 0 && grid[x - 1][y] != 1 && grid[x - 2][y] != 1) {

						matrix[k][25] = x - 2;
						matrix[k][26] = y;
					} else {
						matrix[k][25] = x;
						matrix[k][26] = y;
					}

					if (y - 2 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 2] != 1) {
						// walk_n_max += 0.5 * (1 - p_run) * r_run;

						matrix[k][27] = x;
						matrix[k][28] = y - 2;

					} else {
						matrix[k][27] = x;
						matrix[k][28] = y;
					}
					if (y + 2 < col && grid[x][y + 1] != 1 && grid[x][y + 2] != 1) {
						// walk_n_max += 0.5 * (1 - p_run) * r_run;

						matrix[k][29] = x;
						matrix[k][30] = y + 2;

					} else {
						matrix[k][29] = x;
						matrix[k][30] = y;
					}
					// walk_n_max *= Math.pow(factor, k);

					// run south

					if (x + 2 < row && grid[x + 1][y] != 1 && grid[x + 2][y] != 1) {

						matrix[k][31] = x + 2;
						matrix[k][32] = y;
					} else {
						matrix[k][31] = x;
						matrix[k][32] = y;
					}

					if (y - 2 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 2] != 1) {
						// walk_n_max += 0.5 * (1 - p_run) * r_run;

						matrix[k][33] = x;
						matrix[k][34] = y - 2;

					} else {
						matrix[k][33] = x;
						matrix[k][34] = y;
					}
					if (y + 2 < col && grid[x][y + 1] != 1 && grid[x][y + 2] != 1) {
						// walk_n_max += 0.5 * (1 - p_run) * r_run;

						matrix[k][35] = x;
						matrix[k][36] = y + 2;

					} else {
						matrix[k][35] = x;
						matrix[k][36] = y;

					}
					// walk_n_max *= Math.pow(factor, k);

					// run west

					if (y - 2 >= 0 && grid[x][y - 1] != 1 && grid[x][y - 2] != 1) {

						matrix[k][37] = x;
						matrix[k][38] = y - 2;
					} else {
						matrix[k][37] = x;
						matrix[k][38] = y;
					}

					if (x - 2 >= 0 && grid[x - 1][y] != 1 && grid[x - 2][y] != 1) {

						matrix[k][39] = x - 2;
						matrix[k][40] = y;

					} else {
						matrix[k][39] = x;
						matrix[k][40] = y;
					}
					if (x + 2 < row && grid[x + 1][y] != 1 && grid[x + 2][y] != 1) {
						// walk_n_max += 0.5 * (1 - p_run) * r_run;

						matrix[k][41] = x + 2;
						matrix[k][42] = y;

					} else {
						matrix[k][41] = x;
						matrix[k][42] = y;
					}
					// walk_n_max *= Math.pow(factor, k);

					// run east

					if (y + 2 < col && grid[x][y + 1] != 1 && grid[x][y + 2] != 1) {

						matrix[k][43] = x;
						matrix[k][44] = y + 2;
					} else {
						matrix[k][43] = x;
						matrix[k][44] = y;
					}

					if (x - 2 >= 0 && grid[x - 1][y] != 1 && grid[x - 2][y] != 1) {
						// walk_n_max += 0.5 * (1 - p_run) * r_run;

						matrix[k][45] = x - 2;
						matrix[k][46] = y;

					} else {
						matrix[k][45] = x;
						matrix[k][46] = y;
					}
					if (x + 2 < row && grid[x + 1][y] != 1 && grid[x + 2][y] != 1) {
						// walk_n_max += 0.5 * (1 - p_run) * r_run;

						matrix[k][47] = x + 2;
						matrix[k][48] = y;

					} else {
						matrix[k][47] = x;
						matrix[k][48] = y;
					}
					// walk_n_max *= Math.pow(factor, k);

				}
			}
		}

		while (flag) {
			tt++;
			double difference = 0;
			// for (int i = row - 1; i >= 0; i--) {
			// for (int j = 0; j < col; j++) {
			for (int mm = 0; mm < list.size(); mm++) {
				int i = list.get(mm) / col;
				int j = list.get(mm) % col;
				if (grid[i][j] != 1 && grid[i][j] != 2) {
					// walk up
					walk_n_max1 = 0.00;
					walk_n_max2 = 0.00;
					walk_n_max3 = 0.00;
					walk_n_max4 = 0.00;
					walk_n_max5 = 0.00;
					walk_n_max6 = 0.00;
					walk_n_max7 = 0.00;
					walk_n_max8 = 0.00;
					int t = i * col + j;

					walk_n_max1 += r_walk + factor * p_walk * termi_val[matrix[t][1]][matrix[t][2]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][3]][matrix[t][4]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][5]][matrix[t][6]];

					walk_n_max2 = r_walk + factor * p_walk * termi_val[matrix[t][7]][matrix[t][8]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][9]][matrix[t][10]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][11]][matrix[t][12]];

					walk_n_max3 = r_walk + factor * p_walk * termi_val[matrix[t][13]][matrix[t][14]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][15]][matrix[t][16]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][17]][matrix[t][18]];

					walk_n_max4 = r_walk + factor * p_walk * termi_val[matrix[t][19]][matrix[t][20]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][21]][matrix[t][22]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][23]][matrix[t][24]];

					walk_n_max5 += r_run + factor * p_run * termi_val[matrix[t][25]][matrix[t][26]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][27]][matrix[t][28]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][29]][matrix[t][30]];

					walk_n_max6 = r_run + factor * p_run * termi_val[matrix[t][31]][matrix[t][32]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][33]][matrix[t][34]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][35]][matrix[t][36]];

					walk_n_max7 = r_run + factor * p_run * termi_val[matrix[t][37]][matrix[t][38]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][39]][matrix[t][40]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][41]][matrix[t][42]];

					walk_n_max8 += r_run + factor * p_run * termi_val[matrix[t][43]][matrix[t][44]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][45]][matrix[t][46]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][47]][matrix[t][48]];

					double rst = Math.max(
							Math.max(Math.max(walk_n_max1, walk_n_max2), Math.max(walk_n_max3, walk_n_max4)),
							Math.max(Math.max(walk_n_max5, walk_n_max6), Math.max(walk_n_max7, walk_n_max8)));
					if (rst - termi_val[i][j] > difference) {
						difference = rst - termi_val[i][j];
					}
					termi_val[i][j] = rst;

				}
				// }
				// }
			}
			System.out.println("difference :" + difference);
			if (difference == 0.0) {

				flag = false;
			}
		}

		// calculate direction
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] != 1 && grid[i][j] != 2) {
					// walk up
					walk_n_max1 = 0.00;
					walk_n_max2 = 0.00;
					walk_n_max3 = 0.00;
					walk_n_max4 = 0.00;
					walk_n_max5 = 0.00;
					walk_n_max6 = 0.00;
					walk_n_max7 = 0.00;
					walk_n_max8 = 0.00;
					int t = i * col + j;

					walk_n_max1 += r_walk + factor * p_walk * termi_val[matrix[t][1]][matrix[t][2]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][3]][matrix[t][4]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][5]][matrix[t][6]];

					walk_n_max2 = r_walk + factor * p_walk * termi_val[matrix[t][7]][matrix[t][8]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][9]][matrix[t][10]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][11]][matrix[t][12]];

					walk_n_max3 = r_walk + factor * p_walk * termi_val[matrix[t][13]][matrix[t][14]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][15]][matrix[t][16]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][17]][matrix[t][18]];

					walk_n_max4 = r_walk + factor * p_walk * termi_val[matrix[t][19]][matrix[t][20]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][21]][matrix[t][22]]

							+ factor * 0.5 * (1 - p_walk) * termi_val[matrix[t][23]][matrix[t][24]];

					walk_n_max5 += r_run + factor * p_run * termi_val[matrix[t][25]][matrix[t][26]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][27]][matrix[t][28]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][29]][matrix[t][30]];

					walk_n_max6 = r_run + factor * p_run * termi_val[matrix[t][31]][matrix[t][32]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][33]][matrix[t][34]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][35]][matrix[t][36]];

					walk_n_max7 = r_run + factor * p_run * termi_val[matrix[t][37]][matrix[t][38]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][39]][matrix[t][40]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][41]][matrix[t][42]];

					walk_n_max8 += r_run + factor * p_run * termi_val[matrix[t][43]][matrix[t][44]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][45]][matrix[t][46]]

							+ factor * 0.5 * (1 - p_run) * termi_val[matrix[t][47]][matrix[t][48]];

					double rst = Math.max(
							Math.max(Math.max(walk_n_max1, walk_n_max2), Math.max(walk_n_max3, walk_n_max4)),
							Math.max(Math.max(walk_n_max5, walk_n_max6), Math.max(walk_n_max7, walk_n_max8)));
					// walk down
					if (rst == walk_n_max1) {
						direction[i][j] = "walk up";
					} else if (rst == walk_n_max2) {
						direction[i][j] = "walk down";
					} else if (rst == walk_n_max3) {
						direction[i][j] = "walk left";
					} else if (rst == walk_n_max4) {
						direction[i][j] = "walk right";
					} else if (rst == walk_n_max5) {
						direction[i][j] = "run up";
					} else if (rst == walk_n_max6) {
						direction[i][j] = "run down";
					} else if (rst == walk_n_max7) {
						direction[i][j] = "run left";
					} else if (rst == walk_n_max8) {
						direction[i][j] = "run right";
					}

				} else if (grid[i][j] == 1) {
					direction[i][j] = "None";
				} else {
					direction[i][j] = "Exit";
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(direction[i][j] + " ");
			}
			System.out.println("第" + i + "行：");
		}
		System.out.println(" Total Iteration : " + tt);
		System.out.println("Total count: " + countTotal);
	}

	public static void main(String[] args) {
		Grid_World k = new Grid_World();
		k.getMax3();
	}
}

package Array;

public class HW1_561 {
	int totalNode = 0;

	public void minMax() {
		// use positive to represent S, use negative to represent C,
		// input all the attribute:
		int[][] input = { { 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

		int[][] input1 = { { 0, -2, 0, 0, 0, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, -1, 0 }, { 0, 0, 0, 0, 0, -1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

		int[] val = { 10, 20, 30, 40, 50, 60, 70, 80 };
		int depth = 2;
		String alg = "miniMax";
		String cur = "star";
		int k = 0;

		// select different method:
		if (cur.equals("star")) {
			if (alg.equals("miniMax")) {
				k = minMaxHelper(cur, depth, depth, val, input, false);
			} else {
				k = minMaxHelper3(cur, depth, depth, val, input, Integer.MIN_VALUE, false);
			}
		} else {
			if (alg.equals("miniMax")) {
				k = minMaxHelper2(cur, depth, depth, val, input1, false);
			} else {
				k = minMaxHelper4(cur, depth, depth, val, input1, Integer.MIN_VALUE, false);
			}
		}

		System.out.println("the Biggest is :" + k);
		// System.out.println("the Biggest from Global is :" + global);
		totalNode++;
		System.out.println("the TotalNode is :" + totalNode);
	}

	public int minMaxHelper(String str, int totalDepth, int depth, int[] val, int[][] input, boolean end) {
		int max = 0;
		int min = 0;
		String[] row = { "H", "G", "F", "E", "D", "C", "B", "A" };
		String[] column = { "1", "2", "3", "4", "5", "6", "7", "8" };

		if (str.equals("star")) {
			max = Integer.MIN_VALUE;
		} else if (str.equals("circle")) {
			min = Integer.MAX_VALUE;
		}
		if (depth == 0 || exitOne(input)) {
			return calUtility(input, val);
		} else if (str.equals("star")) {
			boolean flag = false;
			int pathX = -1, pathY = -1;
			int pathX1 = -1, pathY1 = -1;
			int firstUtilty = -1;
			for (int i = 1; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if ((input[i][j] == 1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == -1)
							&& (input[i - 2][j - 2] != -1) && (i - 2 == 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j - 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j - 1] = -1;
						Switch(input, i - 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == -1)
							&& (input[i - 2][j + 2] != -1) && (i - 2) == 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j + 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j + 1] = -1;
						Switch(input, i - 2, j + 2, i, j);
					}
					if ((input[i][j] == 1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == -1)
							&& (input[i - 2][j - 2] == 0) && (i - 2 != 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j - 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j - 1] = -1;
						Switch(input, i - 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == -1)
							&& (input[i - 2][j + 2] == 0) && (i - 2) != 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j + 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j + 1] = -1;
						Switch(input, i - 2, j + 2, i, j);
					}
					if ((input[i][j] == 1 && (i - 1 >= 0)) && (j - 1 >= 0) && (input[i - 1][j - 1] == 0)) {
						totalNode++;
						flag = true;
						Switch(input, i, j, i - 1, j - 1);

						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j - 1;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j - 1, i, j);
					}
					if ((input[i][j] == 1 && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] == 0)) {
						totalNode++;
						flag = true;
						Switch(input, i, j, i - 1, j + 1);
						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j + 1;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j + 1, i, j);
					}

					if ((input[i][j] == 1) && (i == 1) && (i - 1 >= 0) && (j - 1 >= 0) && input[i - 1][j - 1] > 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 1, j - 1);
						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j - 1;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j - 1, i, j);
					}
					if ((input[i][j] == 1) && ((i == 1) && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] > 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 1, j + 1);
						int k = minMaxHelper("circle", totalDepth, depth - 1, val, input, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j + 1;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j + 1, i, j);
					}
				}
			}
			if (!flag) {

				if (depth == totalDepth) {
					System.out.println("pass");
					System.out.println("After the first move utility: " + calUtility(input, val));
				}
				totalNode++;
				if (!end) {
					max = minMaxHelper("circle", totalDepth, depth - 1, val, input, true);
				} else {
					max = calUtility(input, val);
				}
			}
			if (depth == totalDepth && pathX1 >= 0 && pathY1 >= 0 && pathX >= 0 && pathY >= 0) {
				System.out.println("The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX]
						+ column[pathY]);
				System.out.println("After the first move utility: " + firstUtilty);
			}

		}

		else if (str.equals("circle")) {
			boolean flag = false;
			for (int i = 6; i >= 0; i--) {
				for (int j = 0; j < 8; j++) {
					if ((input[i][j] == -1 && (i + 1 >= 0)) && (j - 1 >= 0) && (input[i + 1][j - 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j - 1);
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						Switch2(input, i + 1, j - 1, i, j);
					}
					if ((input[i][j] == -1 && (i + 1 >= 0)) && (j + 1 < 8) && (input[i + 1][j + 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j + 1);
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						Switch2(input, i + 1, j + 1, i, j);
					}
					if ((input[i][j] == -1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == 1)
							&& (input[i + 2][j - 2] != 1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						input[i + 1][j - 1] = 1;
						Switch2(input, i + 2, j - 2, i, j);
					}
					if ((input[i][j] == -1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == 1)
							&& (input[i + 2][j + 2] != 1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						input[i + 1][j + 1] = 1;
						Switch2(input, i + 2, j + 2, i, j);
					}
					if ((input[i][j] == -1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == 1)
							&& (input[i + 2][j - 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						input[i + 1][j - 1] = 1;
						Switch2(input, i + 2, j - 2, i, j);
					}
					if ((input[i][j] == -1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == 1)
							&& (input[i + 2][j + 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						input[i + 1][j + 1] = 1;
						Switch2(input, i + 2, j + 2, i, j);
					}
					if ((input[i][j] == -1) && (i == 6) && (i + 1 < 8) && (j - 1 >= 0) && input[i + 1][j - 1] < 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j - 1);
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						Switch2(input, i + 1, j - 1, i, j);
					}
					if ((input[i][j] == -1) && (i == 6) && (i + 1 < 8) && (j + 1 < 8) && input[i + 1][j + 1] < 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j + 1);
						min = Math.min(min, minMaxHelper("star", totalDepth, depth - 1, val, input, false));
						Switch2(input, i + 1, j + 1, i, j);
					}
				}
			}
			if (!flag) {
				totalNode++;
				if (!end) {
					min = minMaxHelper("star", totalDepth, depth - 1, val, input, true);
				} else {
					min = calUtility(input, val);
				}

				// System.out.println("pass");
			}
		}

		if (str.equals("star")) {
			return max;
		}
		return min;
	}

	public int minMaxHelper2(String str, int totalDepth, int depth, int[] val, int[][] input, boolean end) {
		int max = 0;
		int min = 0;
		String[] row = { "H", "G", "F", "E", "D", "C", "B", "A" };
		String[] column = { "1", "2", "3", "4", "5", "6", "7", "8" };

		if (str.equals("circle")) {
			max = Integer.MIN_VALUE;
		} else if (str.equals("star")) {
			min = Integer.MAX_VALUE;
		}
		if (depth == 0 || exitOne(input)) {
			return calUtility2(input, val);
		} else if (str.equals("star")) {
			boolean flag = false;
			for (int i = 1; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((input[i][j] == -1 && (i - 1 >= 0)) && (j - 1 >= 0) && (input[i - 1][j - 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j - 1);
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						Switch2(input, i - 1, j - 1, i, j);
					}
					if ((input[i][j] == -1 && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j + 1);
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						Switch2(input, i - 1, j + 1, i, j);
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == 1)
							&& (input[i - 2][j - 2] != 1) && (i - 2 == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						input[i - 1][j - 1] = 1;
						Switch2(input, i - 2, j - 2, i, j);
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == 1)
							&& (input[i - 2][j + 2] != 1) && (i - 2) == 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						input[i - 1][j + 1] = 1;
						Switch2(input, i - 2, j + 2, i, j);
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == 1)
							&& (input[i - 2][j - 2] == 0) && (i - 2 != 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						input[i - 1][j - 1] = 1;
						Switch2(input, i - 2, j - 2, i, j);
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == 1)
							&& (input[i - 2][j + 2] == 0) && (i - 2) != 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						input[i - 1][j + 1] = 1;
						Switch2(input, i - 2, j + 2, i, j);
					}
					if ((input[i][j] == -1) && (i == 1) && (i - 1 >= 0) && (j - 1 >= 0) && input[i - 1][j - 1] < 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j - 1);
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						Switch2(input, i - 1, j - 1, i, j);
					}
					if ((input[i][j] == -1) && ((i == 1) && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] < 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j + 1);
						min = Math.min(min, minMaxHelper2("circle", totalDepth, depth - 1, val, input, false));
						Switch2(input, i - 1, j + 1, i, j);
					}
				}
			}

			if (!flag) {
				totalNode++;
				if (!end) {
					min = minMaxHelper2("circle", totalDepth, depth - 1, val, input, true);
				} else {
					min = calUtility2(input, val);
				}

				// System.out.println("pass");
			}
			// if (!flag) {
			// min = minMaxHelper2("circle", depth - 1, val, input,true);
			// }
		}

		else if (str.equals("circle")) {
			boolean flag = false;
			int pathX = -1, pathY = -1;
			int pathX1 = -1, pathY1 = -1;
			int firstUtilty = -1;
			for (int i = 6; i >= 0; i--) {
				for (int j = 0; j < 8; j++) {
					if ((input[i][j] == 1 && (i + 1 >= 0)) && (j - 1 >= 0) && (input[i + 1][j - 1] == 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j - 1);
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j - 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j - 1, i, j);
					}
					if ((input[i][j] == 1 && (i + 1 >= 0)) && (j + 1 < 8) && (input[i + 1][j + 1] == 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j + 1);
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j + 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j + 1, i, j);
					}
					if ((input[i][j] == 1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == -1)
							&& (input[i + 2][j - 2] != -1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j - 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j - 1] = -1;
						Switch(input, i + 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == -1)
							&& (input[i + 2][j + 2] != -1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j + 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j + 1] = -1;
						Switch(input, i + 2, j + 2, i, j);
					}
					if ((input[i][j] == 1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == -1)
							&& (input[i + 2][j - 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j - 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j - 1] = -1;
						Switch(input, i + 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == -1)
							&& (input[i + 2][j + 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j + 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j + 1] = -1;
						Switch(input, i + 2, j + 2, i, j);
					}
					if ((input[i][j] == 1) && (i == 6) && (i + 1 < 8) && (j - 1 >= 0) && input[i + 1][j - 1] > 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j - 1);
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j - 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j - 1, i, j);
					}
					if ((input[i][j] == 1) && (i == 6) && (i + 1 < 8) && (j + 1 < 8) && input[i + 1][j + 1] > 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j + 1);
						int k = Math.max(max, minMaxHelper2("star", totalDepth, depth - 1, val, input, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j + 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j + 1, i, j);
					}
				}
			}
			// if (!flag) {
			// max = minMaxHelper2("star", depth - 1, val, input,true);
			// }

			if (!flag) {

				if (depth == totalDepth) {
					System.out.println("pass");
					System.out.println("After the first move utility: " + calUtility2(input, val));
				}
				totalNode++;
				if (!end) {
					max = minMaxHelper2("star", totalDepth, depth - 1, val, input, true);
				} else {
					max = calUtility2(input, val);
				}
			}
			if (depth == totalDepth && pathX1 >= 0 && pathY1 >= 0 && pathX >= 0 && pathY >= 0) {
				System.out.println("The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX]
						+ column[pathY]);
				System.out.println("After the first move utility: " + firstUtilty);
			}
		}

		if (str.equals("circle")) {
			return max;
		}
		return min;
	}

	public int minMaxHelper3(String str, int totalDepth, int depth, int[] val, int[][] input, int global, boolean end) {
		int max = 0;
		int min = 0;
		String[] row = { "H", "G", "F", "E", "D", "C", "B", "A" };
		String[] column = { "1", "2", "3", "4", "5", "6", "7", "8" };

		if (str.equals("star")) {
			max = Integer.MIN_VALUE;
		} else if (str.equals("circle")) {
			min = Integer.MAX_VALUE;
		}
		if (depth == 0 || exitOne(input)) {
			return calUtility(input, val);
		} else if (str.equals("star")) {
			boolean flag = false;
			int pathX = -1, pathY = -1;
			int pathX1 = -1, pathY1 = -1;
			int firstUtilty = -1;
			for (int i = 1; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((input[i][j] == 1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == -1)
							&& (input[i - 2][j - 2] != -1) && (i - 2 == 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j - 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j - 1] = -1;
						Switch(input, i - 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == -1)
							&& (input[i - 2][j + 2] != -1) && (i - 2) == 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j + 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j + 1] = -1;
						Switch(input, i - 2, j + 2, i, j);
					}
					if ((input[i][j] == 1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == -1)
							&& (input[i - 2][j - 2] == 0) && (i - 2 != 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j - 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j - 1] = -1;
						Switch(input, i - 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == -1)
							&& (input[i - 2][j + 2] == 0) && (i - 2) != 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 2;
							pathY = j + 2;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						input[i - 1][j + 1] = -1;
						Switch(input, i - 2, j + 2, i, j);
					}
					if ((input[i][j] == 1 && (i - 1 >= 0)) && (j - 1 >= 0) && (input[i - 1][j - 1] == 0)) {
						totalNode++;
						flag = true;
						Switch(input, i, j, i - 1, j - 1);

						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j - 1;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j - 1, i, j);
					}
					if ((input[i][j] == 1 && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] == 0)) {
						totalNode++;
						flag = true;
						Switch(input, i, j, i - 1, j + 1);
						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j + 1;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j + 1, i, j);
					}

					if ((input[i][j] == 1) && (i == 1) && (i - 1 >= 0) && (j - 1 >= 0) && input[i - 1][j - 1] > 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 1, j - 1);
						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j - 1;
							firstUtilty = calUtility(input, val);
						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j - 1, i, j);
					}
					if ((input[i][j] == 1) && ((i == 1) && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] > 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i - 1, j + 1);
						int k = minMaxHelper3("circle", totalDepth, depth - 1, val, input, max, false);
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i - 1;
							pathY = j + 1;
							firstUtilty = calUtility(input, val);

						}
						// max = Math.max(max, minMaxHelper("circle", depth - 1,
						// val, input));
						Switch(input, i - 1, j + 1, i, j);
					}
				}
			}
			if (!flag) {
				// totalNode++;
				//
				// max = minMaxHelper3("circle", depth - 1, val, input, max);
				if (depth == totalDepth) {
					System.out.println("pass");
					System.out.println("After the first move utility: " + calUtility(input, val));
				}

				totalNode++;
				if (!end) {
					max = minMaxHelper3("circle", totalDepth, depth - 1, val, input, global, true);
				} else {
					max = calUtility(input, val);
				}
			}
			if (depth == totalDepth && pathX1 >= 0 && pathY1 >= 0 && pathX >= 0 && pathY >= 0) {
				System.out.println("The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX]
						+ column[pathY]);
				System.out.println("After the first move utility: " + firstUtilty);
			}

		}

		else if (str.equals("circle")) {
			boolean flag = false;
			for (int i = 6; i >= 0; i--) {
				for (int j = 0; j < 8; j++) {
					if ((input[i][j] == -1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == 1)
							&& (input[i + 2][j - 2] != 1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						input[i + 1][j - 1] = 1;
						Switch2(input, i + 2, j - 2, i, j);
						if (min < global) {
							// System.out.println("Global:" + global);
							break;
						}
					}
					if ((input[i][j] == -1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == 1)
							&& (input[i + 2][j + 2] != 1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						input[i + 1][j + 1] = 1;
						Switch2(input, i + 2, j + 2, i, j);
						if (min < global) {
							// System.out.println("Global:" + global);
							break;
						}
					}
					if ((input[i][j] == -1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == 1)
							&& (input[i + 2][j - 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						input[i + 1][j - 1] = 1;
						Switch2(input, i + 2, j - 2, i, j);
						if (min < global) {
							// System.out.println("Global:" + global);
							break;
						}
					}
					if ((input[i][j] == -1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == 1)
							&& (input[i + 2][j + 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						input[i + 1][j + 1] = 1;
						Switch2(input, i + 2, j + 2, i, j);
						if (min < global) {
							// System.out.println("Global:" + global);
							break;
						}
					}
					if ((input[i][j] == -1 && (i + 1 >= 0)) && (j - 1 >= 0) && (input[i + 1][j - 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j - 1);
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i + 1, j - 1, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1 && (i + 1 >= 0)) && (j + 1 < 8) && (input[i + 1][j + 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j + 1);
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i + 1, j + 1, i, j);
						if (min < global) {
							// System.out.println("Global:" + global);
							break;
						}
					}

					if ((input[i][j] == -1) && (i == 6) && (i + 1 < 8) && (j - 1 >= 0) && input[i + 1][j - 1] < 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j - 1);
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i + 1, j - 1, i, j);
						if (min < global) {
							// System.out.println("Global:" + global);
							break;
						}
					}
					if ((input[i][j] == -1) && (i == 6) && (i + 1 < 8) && (j + 1 < 8) && input[i + 1][j + 1] < 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i + 1, j + 1);
						min = Math.min(min, minMaxHelper3("star", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i + 1, j + 1, i, j);
						if (min < global) {
							// System.out.println("Global:" + global);
							break;
						}
					}
				}
			}
			if (!flag) {
				// totalNode++;
				// min = minMaxHelper3("star", depth - 1, val, input, global);
				// System.out.println("pass");

				totalNode++;
				if (!end) {
					min = minMaxHelper3("star", totalDepth, depth - 1, val, input, global, true);
				} else {
					min = calUtility(input, val);
				}
			}
		}

		if (str.equals("star")) {
			return max;
		}
		return min;
	}

	public int minMaxHelper4(String str, int totalDepth, int depth, int[] val, int[][] input, int global, boolean end) {
		int max = 0;
		int min = 0;
		String[] row = { "H", "G", "F", "E", "D", "C", "B", "A" };
		String[] column = { "1", "2", "3", "4", "5", "6", "7", "8" };

		if (str.equals("circle")) {
			max = Integer.MIN_VALUE;
		} else if (str.equals("star")) {
			min = Integer.MAX_VALUE;
		}
		if (depth == 0 || exitOne(input)) {
			return calUtility2(input, val);
		} else if (str.equals("star")) {
			boolean flag = false;
			for (int i = 1; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((input[i][j] == -1 && (i - 1 >= 0)) && (j - 1 >= 0) && (input[i - 1][j - 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j - 1);
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i - 1, j - 1, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1 && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j + 1);
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i - 1, j + 1, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == 1)
							&& (input[i - 2][j - 2] != 1) && (i - 2 == 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						input[i - 1][j - 1] = 1;
						Switch2(input, i - 2, j - 2, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == 1)
							&& (input[i - 2][j + 2] != 1) && (i - 2) == 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						input[i - 1][j + 1] = 1;
						Switch2(input, i - 2, j + 2, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j - 2 >= 0) && (input[i - 1][j - 1] == 1)
							&& (input[i - 2][j - 2] == 0) && (i - 2 != 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j - 2);
						input[i - 1][j - 1] = 0;
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						input[i - 1][j - 1] = 1;
						Switch2(input, i - 2, j - 2, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1) && (i - 2 >= 0) && (j + 2 < 8) && (input[i - 1][j + 1] == 1)
							&& (input[i - 2][j + 2] == 0) && (i - 2) != 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 2, j + 2);
						input[i - 1][j + 1] = 0;
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						input[i - 1][j + 1] = 1;
						Switch2(input, i - 2, j + 2, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1) && (i == 1) && (i - 1 >= 0) && (j - 1 >= 0) && input[i - 1][j - 1] < 0) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j - 1);
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i - 1, j - 1, i, j);
						if (min < global) {
							break;
						}
					}
					if ((input[i][j] == -1) && ((i == 1) && (i - 1 >= 0)) && (j + 1 < 8) && (input[i - 1][j + 1] < 0)) {
						flag = true;
						totalNode++;
						Switch2(input, i, j, i - 1, j + 1);
						min = Math.min(min, minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, false));
						Switch2(input, i - 1, j + 1, i, j);
						if (min < global) {
							break;
						}
					}
				}
			}

			if (!flag) {
				totalNode++;
				if (!end) {
					min = minMaxHelper4("circle", totalDepth, depth - 1, val, input, global, true);
				} else {
					min = calUtility2(input, val);
				}

				// System.out.println("pass");
			}
			// if (!flag) {
			// min = minMaxHelper2("circle", depth - 1, val, input,true);
			// }
		}

		else if (str.equals("circle")) {
			boolean flag = false;
			int pathX = -1, pathY = -1;
			int pathX1 = -1, pathY1 = -1;
			int firstUtilty = -1;
			for (int i = 6; i >= 0; i--) {
				for (int j = 0; j < 8; j++) {
					if ((input[i][j] == 1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == -1)
							&& (input[i + 2][j - 2] != -1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j - 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j - 1] = -1;
						Switch(input, i + 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == -1)
							&& (input[i + 2][j + 2] != -1) && (i + 2 == 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j + 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j + 1] = -1;
						Switch(input, i + 2, j + 2, i, j);
					}
					if ((input[i][j] == 1) && (i + 2 < 8) && (j - 2 >= 0) && (input[i + 1][j - 1] == -1)
							&& (input[i + 2][j - 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j - 2);
						input[i + 1][j - 1] = 0;
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j - 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j - 1] = -1;
						Switch(input, i + 2, j - 2, i, j);
					}
					if ((input[i][j] == 1) && (i + 2 < 8) && (j + 2 < 8) && (input[i + 1][j + 1] == -1)
							&& (input[i + 2][j + 2] == 0) && (i + 2 != 7)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 2, j + 2);
						input[i + 1][j + 1] = 0;
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 2;
							pathY = j + 2;
							firstUtilty = calUtility2(input, val);
						}
						input[i + 1][j + 1] = -1;
						Switch(input, i + 2, j + 2, i, j);
					}

					if ((input[i][j] == 1 && (i + 1 >= 0)) && (j - 1 >= 0) && (input[i + 1][j - 1] == 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j - 1);
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j - 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j - 1, i, j);
					}
					if ((input[i][j] == 1 && (i + 1 >= 0)) && (j + 1 < 8) && (input[i + 1][j + 1] == 0)) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j + 1);
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j + 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j + 1, i, j);
					}

					if ((input[i][j] == 1) && (i == 6) && (i + 1 < 8) && (j - 1 >= 0) && input[i + 1][j - 1] > 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j - 1);
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j - 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j - 1, i, j);
					}
					if ((input[i][j] == 1) && (i == 6) && (i + 1 < 8) && (j + 1 < 8) && input[i + 1][j + 1] > 0) {
						flag = true;
						totalNode++;
						Switch(input, i, j, i + 1, j + 1);
						int k = Math.max(max, minMaxHelper4("star", totalDepth, depth - 1, val, input, max, false));
						if (max < k) {
							max = k;
							pathX1 = i;
							pathY1 = j;
							pathX = i + 1;
							pathY = j + 1;
							firstUtilty = calUtility2(input, val);
						}
						Switch(input, i + 1, j + 1, i, j);
					}
				}
			}
			// if (!flag) {
			// max = minMaxHelper2("star", depth - 1, val, input,true);
			// }

			if (!flag) {

				if (depth == totalDepth) {
					System.out.println("pass");
					System.out.println("After the first move utility: " + calUtility2(input, val));
				}
				totalNode++;
				if (!end) {
					max = minMaxHelper4("star", totalDepth, depth - 1, val, input, global, true);
				} else {
					max = calUtility2(input, val);
				}
			}
			if (depth == totalDepth && pathX1 >= 0 && pathY1 >= 0 && pathX >= 0 && pathY >= 0) {
				System.out.println("The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX]
						+ column[pathY]);
				System.out.println("After the first move utility: " + firstUtilty);
			}
		}

		if (str.equals("circle")) {
			return max;
		}
		return min;
	}

	public boolean exitOne(int[][] input) {
		boolean one = false;
		boolean two = false;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
				if (input[i][j] > 0) {
					one = true;
				} else if (input[i][j] < 0) {
					two = true;
				}
			}
		}
		if (one ^ two) {
			return true;
		}
		return false;
	}

	public void Switch(int[][] input, int startX, int startY, int endX, int endY) {
		input[startX][startY] -= 1;
		input[endX][endY] += 1;
	}

	public void Switch2(int[][] input, int startX, int startY, int endX, int endY) {
		input[startX][startY] += 1;
		input[endX][endY] -= 1;
	}

	public int calUtility(int[][] input, int[] val) {
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (input[i][j] > 0) {
					sum += input[i][j] * val[7 - i];
				} else if (input[i][j] < 0) {
					sum += input[i][j] * val[i];
				}
			}
		}
		return sum;
	}

	public int calUtility2(int[][] input, int[] val) {
		int sum = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (input[i][j] > 0) {
					sum += input[i][j] * val[i];
				} else if (input[i][j] < 0) {
					sum += input[i][j] * val[7 - i];
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		int[][] array = new int[][] { { 1, 6, 9, 13 }, { 2, 8, 10, 14 }, { 5, 9, 12, 19 } };
		HW1_561 k = new HW1_561();
		k.minMax();
		// k.permutaion(k1);
	}
}

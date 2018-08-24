package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HW2 {
	public void divide(int numberOfGroup) {

		// initialize group
		List<List<String>> group = new ArrayList<>();
		for (int i = 0; i < numberOfGroup; i++) {
			List<String> list = new ArrayList<>();
			group.add(list);
		}
		// transform all the input data
		Map<String, Integer> mapConti = new HashMap<>();
		mapConti.put("Australia", 1);
		mapConti.put("Iran", 1);
		mapConti.put("Japan", 1);
		mapConti.put("Saudi Arabia", 1);
		mapConti.put("South Korea", 1);
		mapConti.put("Egypt", 2);
		mapConti.put("Morocco", 2);
		mapConti.put("Nigeria", 2);
		mapConti.put("Senegal", 2);
		mapConti.put("Tunisia", 2);
		mapConti.put("Costa Rica", 3);
		mapConti.put("Mexico", 3);
		mapConti.put("Panama", 3);
		mapConti.put("Argentina", 4);
		mapConti.put("Brazil", 4);
		mapConti.put("Colombia", 4);
		mapConti.put("Peru", 4);
		mapConti.put("Uruguay", 4);
		mapConti.put("Belgium", 5);
		mapConti.put("Croatia", 5);
		mapConti.put("Denmark", 5);
		mapConti.put("England", 5);
		mapConti.put("France", 5);
		mapConti.put("Germany", 5);
		mapConti.put("Iceland", 5);
		mapConti.put("Poland", 5);
		mapConti.put("Portugal", 5);
		mapConti.put("Russia", 5);
		mapConti.put("Serbia", 5);
		mapConti.put("Spain", 5);
		mapConti.put("Sweden", 5);
		mapConti.put("Switzerland", 5);

		Map<String, Integer> mapPot = new HashMap<>();
		mapPot.put("Russia", 1);
		mapPot.put("Germany", 1);
		mapPot.put("Brazil", 1);
		mapPot.put("Portugal", 1);
		mapPot.put("Argentina", 1);
		mapPot.put("Belgium", 1);
		mapPot.put("Poland", 1);
		mapPot.put("France", 1);
		mapPot.put("Spain", 2);
		mapPot.put("Peru", 2);
		mapPot.put("Switzerland", 2);
		mapPot.put("England", 2);
		mapPot.put("Colombia", 2);
		mapPot.put("Mexico", 2);
		mapPot.put("Uruguay", 2);
		mapPot.put("Croatia", 2);
		mapPot.put("Denmark", 3);
		mapPot.put("Iceland", 3);
		mapPot.put("Costa Rica", 3);
		mapPot.put("Sweden", 3);
		mapPot.put("Tunisia", 3);
		mapPot.put("Egypt", 3);
		mapPot.put("Senegal", 3);
		mapPot.put("Iran", 3);
		mapPot.put("Serbia", 4);
		mapPot.put("Nigeria", 4);
		mapPot.put("Australia", 4);
		mapPot.put("Japan", 4);
		mapPot.put("Morocco", 4);
		mapPot.put("Panama", 4);
		mapPot.put("South Korea", 4);
		mapPot.put("Saudi Arabia", 4);

		String[] country = { "Russia", "Germany", "Brazil", "Portugal", "Argentina", "Belgium", "Poland", "France",
				"Spain", "Peru", "Switzerland", "England", "Colombia", "Mexico", "Uruguay", "Croatia", "Denmark",
				"Iceland", "Costa Rica", "Sweden", "Tunisia", "Egypt", "Senegal", "Iran", "Serbia", "Nigeria",
				"Australia", "Japan", "Morocco", "Panama", "South Korea", "Saudi Arabia" };
		// run DFS

		String star = DFS(group, 0, mapConti, mapPot, country);
		if (star.equals("YES")) {
			System.out.println("divide successful");
			System.out.println("The result as follows:" + "\n");
			for (int i = 0; i < numberOfGroup; i++) {
				int t = i + 1;
				System.out.println("the " + t + " group is: ");
				for (int j = 0; j < group.get(i).size(); j++) {
					System.out.println(group.get(i).get(j) + " ");
				}
				System.out.println("" + "\n");

			}
		} else {
			System.out.println("divide fail");
		}

		// Display the result

	}

	public String DFS(List<List<String>> group, int level, Map<String, Integer> mapConti, Map<String, Integer> mapPot,
			String[] country) {
		if (level == 32) {
			return "YES";
		}
		if (level > 32) {
			return "NO";
		}
		for (int i = 0; i < group.size(); i++) {
			int[] countConti = new int[6];
			int[] countPot = new int[5];
			for (int j = 0; j < group.get(i).size(); j++) {
				countConti[mapConti.get(group.get(i).get(j))]++;
				countPot[mapPot.get(group.get(i).get(j))]++;
			}
			if ((countConti[mapConti.get(country[level])] < 1
					|| (mapConti.get(country[level]) == 5 && countConti[mapConti.get(country[level])] < 2))
					&& countPot[mapPot.get(country[level])] < 1) {
				group.get(i).add(country[level]);
				if (DFS(group, level + 1, mapConti, mapPot, country).equals("YES")) {
					return "YES";
				}
				group.get(i).remove(group.get(i).size() - 1);
			}
		}
		return "NO";
	}
}

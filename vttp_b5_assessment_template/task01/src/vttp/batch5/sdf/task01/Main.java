package vttp.batch5.sdf.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException {

		String file = args[0];
		String newDataFile = args[1];

		// Read CSV File
		FileReader fReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fReader);

		List<List<String>> testData = new ArrayList<>();

		// Read the CSV file and put that into a list
		String line = "";
		while ((line = br.readLine()) != null) {
			// There are lines being read that are not displaying properly, this removes
			// them.
			if (line.contains("season")) {
				continue;
			}
			List<String> dataHolder = new ArrayList<>();
			String[] row = line.split(",");
			for (String str : row) {
				dataHolder.add(str);
			}
			// Find out the total number of cyclists
			int totalCyclists = Integer.parseInt(dataHolder.get(8)) + Integer.parseInt(dataHolder.get(9));
			dataHolder.add(Integer.toString(totalCyclists));
			testData.add(dataHolder);

		}
		br.close();

		FileWriter fw = new FileWriter(newDataFile);
		BufferedWriter bw = new BufferedWriter(fw);

		// Sort the data of the list based on the final index of the inner list in
		// descending order
		testData.sort((a, b) -> Integer.compare(Integer.parseInt(b.get(10)), Integer.parseInt(a.get(10))));

		for (List<String> entry : testData) {
			bw.write(entry.toString().replace("[", "").replace("]", ""));
			bw.newLine();
		}
		bw.close();

		// Read the cleaned data
		String newLine = "";
		int lineCount = 0;
		FileReader reader = new FileReader(newDataFile);
		BufferedReader bReader = new BufferedReader(reader);
		while ((newLine = bReader.readLine()) != null && lineCount < 5) {
			String[] newRow = newLine.split(", ");

			newRow[0].replace("[", "");
			newRow[10].replace("]", "");

			if (newRow[0].equals("1"))
				newRow[0] = "spring";
			if (newRow[0].equals("2"))
				newRow[0] = "summer";
			if (newRow[0].equals("3"))
				newRow[0] = "fall";
			if (newRow[0].equals("4"))
				newRow[0] = "winter";

			if (newRow[1].equals("9"))
			newRow[1] = "September";
			if (newRow[1].equals("3"))
			newRow[1] = "March";
			if (newRow[1].equals("5"))
			newRow[1] = "May";

			if (newRow[2].equals("0")) {
				newRow[2] = "not a holiday";
			}

			if (newRow[3].equals("6"))
			newRow[3] = "Saturday";
			if (newRow[3] == "5")
			newRow[3] = "Friday";


			if (newRow[4].equals("1"))
			newRow[4] = "Clear, Few clouds, Partly cloudy, Partly cloudy";
			if (newRow[4].equals("2"))
			newRow[4] = "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";

			String position = "";
			if (lineCount == 0)
				position = "highest (position)";
			if (lineCount == 1)
				position = "second highest (position)";
			if (lineCount == 2)
				position = "third highest (position)";
			if (lineCount == 3)
				position = "fourth highest (position)";
			if (lineCount == 4)
				position = "fifth highest (position)";

			System.out.println("The " + position + " recorded number of cyclists was in " + newRow[0] + ", on a "
					+ newRow[3] + " in the month of " + newRow[1] + ".");
			System.out.println("There was a total of " + newRow[10] + " cyclists. The weather was " + newRow[4] + ".");
			System.out.println(newRow[3] + " was " + newRow[2] + "\n");

			lineCount++;
		}

		bReader.close();

		/*
		 * for ( int i = 0; i <5 ;i++){
		 * String[] temp = testData.get(i).toString().split(", ");
		 * temp[0].replace("[","");
		 * temp[10].replace("]","");
		 * String season = temp[0];
		 * if (season == "1") season = "spring";
		 * if (season == "2") season = "summer";
		 * if (season == "3") season = "fall";
		 * if (season == "4") season = "winter";
		 * temp[0] = season;
		 * String month = (temp[1]);
		 * if (month == "9") month = "September";
		 * if (month == "3") month = "March";
		 * if (month == "5") month = "May";
		 * temp[1] = month;
		 * String holiday = temp[2];
		 * if (holiday == "0"){
		 * holiday = "not a holiday";
		 * }
		 * temp[2] =holiday;
		 * String weekday = temp[3];
		 * if ( weekday == "6") weekday = "Saturday";
		 * if ( weekday == "5") weekday = "Friday";
		 * temp[3] = weekday;
		 * String weather = temp[4];
		 * if (weather == "1") weather=
		 * "Clear, Few clouds, Partly cloudy, Partly cloudy";
		 * if (weather == "2") weather =
		 * "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
		 * temp[4] = weather;
		 * String position ="";
		 * if (i == 0) position = "highest";
		 * if (i == 1) position = "second highest";
		 * if (i == 2) position = "third highest";
		 * if (i == 3) position = "fourth highest";
		 * if (i == 4) position = "fifth highest";
		 * 
		 * for (String str : temp){
		 * System.out.println("The " + position + " recorded number of cyclists was in "
		 * + temp[0] + ", on a " + temp[3] + " in the month of " + temp[1] + ".\n"
		 * +"There was a total of " + temp[10] + " cyclists. The weather was " + temp[4]
		 * + "\n" + temp[3] + " was " + temp[2] + "\n");
		 * }
		 * 
		 * }
		 */

	}

}

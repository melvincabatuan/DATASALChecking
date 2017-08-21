package ph.edu.dlsu.datasal.santos.namesurfer;

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String Name;
	private int[] rankings = new int[NDECADES];
	
	public NameSurferEntry(String line) {
		parseLine(line);
	}
	
	private void parseLine(String line) {
		int nameEnd = line.indexOf(" ");
		Name = line.substring(0, nameEnd);
	
		String numbers = line.substring(nameEnd + 1);
		StringTokenizer tokenizer = new StringTokenizer(numbers);
		for(int count = 0; tokenizer.hasMoreTokens(); count++) {
			int popularityRank = Integer.parseInt(tokenizer.nextToken());
			rankings[count] = popularityRank;
		}
	}

	public String getName() {
		return Name;
		}

	public int getRank(int decade) {
		return rankings[decade];
	}

	public String toString() {
		String value = "\"" + Name + " [";
		for(int i = 0; i<NDECADES; i++) {
			value += getRank(i) + " ";
		}
		value += "]\"";
		return value;
	}
}


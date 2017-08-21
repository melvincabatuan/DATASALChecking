package ph.edu.dlsu.datasal.santos.namesurfer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import acm.util.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
	private Map <String, NameSurferEntry> namesdb = new HashMap <String, NameSurferEntry>(); 
        
	public NameSurferDataBase(String filename) {
		getNameData(filename);
	}
	
	private void getNameData(String filename) {
		try{
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while(true) {
				String line = rd.readLine();
				if(line == null) break;
				NameSurferEntry nameEntry = new NameSurferEntry(line);
				namesdb.put(nameEntry.getName(), nameEntry);
			}
			rd.close();
		} catch(IOException ex) {
				throw new ErrorException(ex);
			}
		}
	
	public NameSurferEntry findEntry(String name) {
		char ch = name.charAt(0);
		if(Character.isLowerCase(ch) == true) {
			ch = Character.toUpperCase(ch);
		}
		String otherLetters = name.substring(1);
		otherLetters = otherLetters.toLowerCase();
		name = ch + otherLetters;
		if(namesdb.containsKey(name)) {
			return namesdb.get(name);
		}
		else{
			return null;
		}
	}
}


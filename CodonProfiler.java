import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>();
		int[] ret = new int[codons.length];
				for(int k=0; k < codons.length; k++) {
			if (!map.keySet().contains(codons[k])){ //if map keys doesnt contain a possible codon, add that
				map.put(codons[k], 0);
			}	
		}
		for (int k = 0; k < strand.size()-2; k+=3) { //loops through every three letters in strand
			char a = strand.charAt(k);
			char b = strand.charAt(k+1);
			char c = strand.charAt(k+2);
			String check = ""; //builds potential codon
			check = check+ a;
			check = check + b;
			check = check +c;
			if (map.containsKey(check)) { //if that codon is one of the possible ones in the map, update the instances
				map.put(check, map.get(check)+1);
			}
			}
		for (int y =0; y <codons.length; y++) { //returns the aray of frequencies
			ret[y] = map.get(codons[y]);
		}
		return ret;
	}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;
import java.util.*;

/**
 *
 * @author Dell
 */
public class Node {
     char c;
	 HashMap<Character,Node> children;
	 String meaning;
	
	 Node(){
		 
		 /*
			objective: to initialize a node to null values
			inputs:none
			output: none
			return value: none 
			*/
		 
		this.children=new HashMap<>();
		this.meaning=null;		
		
	}
	
	
	public void ifabsent(final char c) {

		/*
		 * objective: to put character c into hashmap if not present inputs:character c
		 * output: none return value: none
		 */

		children.put(c, new Node());
	}

	public Node getchild(final char c) {
		
		/*
		objective: to retrieve children associated with a character c
		inputs:character c
		output: none
		return value: none 
		*/
		
		return children.get(c);
	}

    
}

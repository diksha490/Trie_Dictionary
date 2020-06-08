/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;
import java.util.*;

import javax.net.ssl.SSLContext;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.io.*;


/**
 *
 * @author Dell
 */
public class Trie {
     
    int wordSize=40;
	 Node root;
	 HashSet<Integer> set=new HashSet<>();
	
	Trie(){
		/*
		objective: to initialize a root node to implement dictionary operations
		inputs:none
		output: none
		return value: none 
		*/

		root=new Node();

		//Building Trie using already saved words in text file

		try {
			buildTrie();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void buildTrie() throws IOException{
		/*
		objective: to build Trie using stored file
		inputs:none
		output: none
		return value: none 
		*/	
		
		FileReader file=new FileReader("Files/storedWords.txt");
		BufferedReader bf=new BufferedReader(file);
		bf.readLine();
		String reqWord=bf.readLine();
		System.out.println(reqWord.length());
		if(reqWord.length()!=0){
			while(reqWord!=null){
				System.out.println(reqWord);
				int whiteIdx=reqWord.indexOf(" ");
				String word=reqWord.substring(0,whiteIdx );
				String mean=reqWord.substring(whiteIdx+1,reqWord.indexOf(" ",whiteIdx+1));
				Node temp=root;
		
				for(char c:word.toCharArray())
				{
					if(temp.getchild(c)==null)
					{
						temp.ifabsent(c);
					}
					temp=temp.getchild(c);
				}
					temp.meaning=mean;
					reqWord=bf.readLine();
					
			}

		}
		else
			System.out.println("Trie is Empty ");
		
		bf.close();
	}
	
	public void Insert(String word,String mean) throws IOException{
		
		/*
		objective: to define a function Insert to put a word in dictionary.
		inputs:String word that is to be inserted
		output: none
		return value: none 
		*/
		
		Node temp=root;
		
		for(char c:word.toCharArray())
		{
			if(temp.getchild(c)==null)
			{
				temp.ifabsent(c);
			}
			temp=temp.getchild(c);
		}
        temp.meaning=mean;
        String storeWord=word+" "+mean;
        StringBuffer extra = new StringBuffer("");
        for(int i=0;i<(wordSize-storeWord.length());i++){
            extra.append(" ");
        }
		storeWord=word+" "+mean+extra.toString();
		System.out.println(storeWord.length());
        FileWriter file=new FileWriter("Files/storedWords.txt",true);
        
        file.write("\n"+storeWord);
        file.close();
		//return true;
	}
	
	public void Search(final String word) {
		
		/*
		objective: to define a function Search to check if a word is present in dictionary
		inputs:String word that is to be inserted
		output: none
		return value: true or false
		*/
		
		Node temp=root;
		int flag=0;
		
		for(final char c:word.toCharArray())
		{
			if(temp.getchild(c)==null)
				{
					flag=1;
					break;
				}
			temp=temp.getchild(c);
		}
		if(flag==1 || temp.meaning=="")
			System.out.println("No word exist");
		else if(temp.meaning.length()!=0)
			System.out.println("Word is Present\nIts meaning is: "+temp.meaning);

    }
    public void WordofDay() throws IOException {
		
		/*
		objective:To find word of the day using random function
		inputs: none
		output: none
		return value: none 
		*/
        FileReader file=new FileReader("Files/storedWords.txt");
        BufferedReader bf=new BufferedReader(file);
        Path path 
            = Paths.get("Files/storedWords.txt");
        long sizeFile=Files.size(path);
       // System.out.println(sizeFile/wordSize);
		int i=0;
		Random r=new Random();
		int totalWords=(int)sizeFile/wordSize+1;
		//System.out.println(sizeFile);
		int random=r.nextInt(totalWords);

		while(random==0 || set.contains(random) )
			random=r.nextInt(totalWords);

		set.add(random);
		//System.out.println(random);

		if(set.contains(random+1) && (random+1)<=totalWords)
			set.remove(random+1);
		else
			set.remove(random-1);	
			

        while(i<random)
        {
            bf.readLine();
            i++;
        }
		
		String reqWord=bf.readLine();
		int whiteIdx=reqWord.indexOf(" ");
		String word=reqWord.substring(0,whiteIdx );
		String mean=reqWord.substring(whiteIdx+1,reqWord.indexOf(" ",whiteIdx+1));
		System.out.println("Word of the day is: "+word+"\nIts meaning is: "+mean+"\n");
        
		
	}

	public void SimilarWords(String str, int index) throws IOException
	{
		
		/*
		objective: To search for similar words in the file
		inputs: -> str:word which is being searched
				-> index:pointer to specific alphabet in the word
		output: none
		return value: none 
		*/
		
		if(index>str.length()-1){
			System.out.println("Sorry Problem in input ");	
		}
		else{
			System.out.println("Similar words to "+str+" are as follows:");
			File file = new File("Files/words.txt"); 
			Scanner sc = new Scanner(file); 			 
			str=str.substring(0,index);
			System.out.println(str);
			while(sc.hasNext()!=false)
			{
				String getWord=sc.next();				 
				if(str.contentEquals(getWord.substring(0,index))==true)
				{
					System.out.println(getWord);
				}
			}
			sc.close();
		}
		
		
	}
    public static void main(String[] args) {
        Menu m=new Menu();
        m.setVisible(true);
        
       
        //Trie tree=new Trie();
        // TODO code application logic here
    }
    
}

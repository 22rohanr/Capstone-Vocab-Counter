/*
Capstone Essay Word Counter
@author Rohan Rashingkar
@version 5/9/20
 */
 
import java.io.*;
import java.util.*;

public class WordCounter
{
    public static void main(String[] args) throws IOException
    {
        //setting up the scanners
        System.out.println("This program counts the vocab words in a capstone essay.");
        System.out.println("Please enter the word count of your essay. If you enter the wrong word count, this program will not work.");
        Scanner wordCountScanner = new Scanner(System.in);
        int wordCount = wordCountScanner.nextInt();
        System.out.println("Please enter your essay. The process will start as soon as you press enter.");
        Scanner essayScanner = new Scanner(System.in);
        
        //traversing the essay
        String[] punctuation = {".", ",", "?", "!", ";", ":"};
        ArrayList<ArrayList<String>> wordsUsed = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 12; i++)
        {
            wordsUsed.add(new ArrayList<String>());
        }
       
        //truncating the punctuation
        for (int i = 0; i < wordCount; i++)
        {
            String wordToCheck = essayScanner.next();
            for (int j = 0; j < punctuation.length; j++)
            {
                int punctInd = wordToCheck.indexOf(punctuation[j]);
                if (punctInd != -1)
                    wordToCheck = wordToCheck.substring(0,punctInd);
            }
            
            //searching the word bank
            Scanner wordScanner = new Scanner(new BufferedReader(new FileReader("words")));
            for (int unit = 0; unit < 12; unit++)
            {
                for (int wordNum = 0; wordNum < 20; wordNum++)
                {
                    String vocabWord = wordScanner.next();
                    if (wordToCheck.length() < 3)
                        break;
                    int index =(wordToCheck.toLowerCase()).indexOf(vocabWord);
                    if (index == 0)
                    {
                        wordsUsed.get(unit).add(wordToCheck.toLowerCase());
                    }
                }
            }
        }

        //output
        for (int i = 0; i < 12; i++)
        {
            if (wordsUsed.get(i).size() == 0)
            {
                if (i < 9)
                    System.out.print("    Unit " + (int) (i + 1) + "    |    Word Count: 0    |    Words Used: None");
                else
                    System.out.print("    Unit " + (int) (i + 1) + "   |    Word Count: 0    |    Words Used: None");
            }
            else
            {
                String unitSpace = "   ";
                String wordCountSpace = "   ";
                if (i < 9)
                    unitSpace = "    ";
                if (wordsUsed.get(i).size() < 9)
                    wordCountSpace = "    ";

                System.out.print("    Unit " + (int)(i+1) + unitSpace + "|    Word Count: "+ wordsUsed.get(i).size() + wordCountSpace + "|    Words Used: ");
                for (int j = 0; j < wordsUsed.get(i).size()-1; j++)
                {
                    System.out.print((wordsUsed.get(i)).get(j) + ", ");
                }
                System.out.print((wordsUsed.get(i)).get(wordsUsed.get(i).size()-1));
            }
            System.out.println();
        }
        int totalWords = 0;
        for (int i = 0; i < 12; i++)
            totalWords += wordsUsed.get(i).size();    
            
        System.out.println("                Total Word Count: " + totalWords + "\n");
        System.out.println("Operation successful.");
        System.out.println("Please double check that everything is correct.");
        System.out.close();
        System.exit(0);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rango
 */
public class MyFileUtil {
    
    
    public static List<String[]> dataCsv(String nameFile) throws Exception{
        List<String[]> result = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
            String line;
            int currentLine = 1;
            int first = 1;
            while ((line = br.readLine()) != null) {
                if (first != currentLine) {
                    // Splitting the line by commas
                    String[] data = line.split(";");
                    result.add(data);
                }
                currentLine++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            return result;
        }
    }
    
    
    
    // GET SOME WORDS AFTER
      public static String getWordAfter(String text, String world, String separator) throws Exception{
        try {
             String wordAfter = "";
            int index = text.indexOf(world);
    //        System.out.println("index is "+index);
            if (index != -1) { // If the string is found
                // Find the start of the word after the search string
                int startIndex = 0;
                if(separator.equals("") == true) startIndex = index + world.length();
                else startIndex = index + world.length() + 1;

                // Find the end of the word
                int endIndex = text.indexOf(" ", startIndex);
                if (endIndex == -1) {
                    endIndex = text.length();
                }

                wordAfter = text.substring(startIndex, endIndex);
            }
                  
            return wordAfter;
        } catch (Exception e) {
//            e.getLocalizedMessage();
            throw  new Exception("Error. "+e.getMessage());
        }    
    }
      
      // LIRE UN FICHIER
      public static String getContentTexte(String filePath) throws Exception{
           try {
            // FileReader reads text files in the default encoding
               FileReader fileReader = new FileReader(filePath);

            // Wrap FileReader in BufferedReader for efficient reading
               BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read file line by line
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.append(line);
                lines.append(" ");
            }
            
//            System.out.println(lines.toString());
//            // Close resources
            bufferedReader.close();
            
            return lines.toString();
        } catch (Exception e) {
            throw new Exception("Error on reading the file. Error: "+e.getLocalizedMessage());
        }
      }
    
    
    // PRENDRE LES FICHIERS DANS UN DIRECTORY
    public static List<String> getNameFileInFolder(String folderPatch) throws Exception{
        List<String> result = new ArrayList<>();
        try {
             File folder = new File(folderPatch);
             if (folder.exists() && folder.isDirectory()) {
                File[] listOfFiles = folder.listFiles();

                if (listOfFiles != null) {
                    for (File file : listOfFiles) {
                        if (file.isFile() && file.getName().toLowerCase().endsWith(".pdf.txt")) {
                            result.add(file.getName());
                        }
                    }
                } 
             } else {
                 throw new Exception("Error on specifiing the folder");
             }
        } catch (Exception e) {
            throw new Exception("Error on getting all files in a folder. Error: "+e.getLocalizedMessage());
        } finally{
            return result;
        }
    }
}

package com.example.oop_project_real_estate.textfile;

import java.io.*;
import java.util.Scanner;

public class textfile {
    File fw;


    public textfile(String fileName){
        try{
            fw= new File(fileName + ".txt");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean writeFile(String data){
        try(FileWriter f = new FileWriter(fw, true)){
            f.write(data);
            f.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean find(String data){
        try{
            Scanner myReader = new Scanner(fw);
            while(myReader.hasNext()){
                if(myReader.next().equals(data)){
                    return true;
                }
            }
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public String readFile(){
        try{
            Scanner input = new Scanner(fw);
            return input.nextLine();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}

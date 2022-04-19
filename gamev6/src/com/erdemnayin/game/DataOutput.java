package com.erdemnayin.game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataOutput {

    private static DataOutput instance = new DataOutput();
    private String playerAtxt = "playerA_output.txt";
    private String playerBtxt = "playerB_output.txt";
    private String playerCtxt = "playerC_output.txt";
    private String playerDtxt = "playerD_output.txt";

    private DataOutput(){

    }

    public static DataOutput getInstance(){return instance;}

    //append
    public void writePlayerA(String string){


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerAtxt,true))){

            writer.write(string + "\n");

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void writePlayerB(String string){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerBtxt,true))){

            writer.write(string + "\n");

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void writePlayerC(String string){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerCtxt,true))){

            writer.write(string + "\n");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writePlayerD(String string){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerDtxt,true))){

            writer.write(string + "\n");

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //write, reset file or create it
    public void resetPlayerA(){

        try(FileWriter writer = new FileWriter(playerAtxt)){

            writer.write("");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void resetPlayerB(){

        try(FileWriter writer = new FileWriter(playerBtxt)){

            writer.write("");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void resetPlayerC(){

        try(FileWriter writer = new FileWriter(playerCtxt)){

            writer.write("");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void resetPlayerD(){

        try(FileWriter writer = new FileWriter(playerDtxt)){

            writer.write("");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

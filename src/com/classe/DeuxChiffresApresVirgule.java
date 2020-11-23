/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classe;

/**
 *
 * @author GERALD 3480
 */
public class DeuxChiffresApresVirgule {

    
   
    public static String ChiffresApresVirgule(double value){
        String valueRetour;
        int dec;
        String decimal;
        String[] a = (value + "").split("\\.");
        String entier =  a[0];
        int count = a[1].length();
        switch (count) {
            case 1:
                decimal = (String)a[1].subSequence(0, 1);
                String s = decimal + "0";
                valueRetour = entier + "." + s;
                break;
            case 2:
                valueRetour = entier + "." + a[1];
                break;
            default:
                decimal = (String)a[1].subSequence(0, 2);
                int cmd = Integer.parseInt((String)a[1].subSequence(2, 3));
                if(cmd < 5){
                    dec = Integer.parseInt(decimal);
                }
                else{
                    dec = Integer.parseInt(decimal) + 1;
                }   valueRetour = entier + "." + dec;
                break;
        }
        System.out.println(count);
        return valueRetour;
    }
    
    public static void main(String[] args) {
        String f = DeuxChiffresApresVirgule.ChiffresApresVirgule(234.5);
        System.out.println("Float Convertit: " + f);
    }
    
}

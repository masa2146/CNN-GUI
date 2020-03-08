 
package ysagui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class Read {
    
    public void oku(int OutputSayisi){
        
        Yapi yapi = new Yapi();
             try {
            BufferedReader read = new BufferedReader(new FileReader("veri.txt"));
            String temp;

            Double[] fd1;
            Double[] fd2;
            while ((temp = read.readLine()) != null) {
               
               

                //Dizinin son kaç elemanı output ise outputa kadar olana kisimlar inputtur
                int sayac = temp.length() - OutputSayisi;

                //Girdileri input ve output olarak ayırıyoruz
                String tempInput = temp.substring(0, sayac - 1);;
                System.out.println("input "+tempInput);
                String tempOutput = temp.substring(sayac - 1, temp.length());;
                
System.out.println("output "+tempOutput);
                String t = tempInput.replace(" ","");
                
                fd1 = new Double[t.length()]; 
                
              
              
                 
                for (int i = 0; i < t.length(); i++) {
                    fd1[i] = Double.valueOf(String.valueOf(t.charAt(i)));
                     
                }

                  
                
                String t2 = tempOutput.replace(" ","");
                fd2 = new Double[t2.length()];
                for (int i = 0; i < t2.length(); i++) {
                    
                  
                    fd2[i] =Double.valueOf(String.valueOf(t2.charAt(i)));
                   

                }
              

                yapi.girisler.add(fd1);
                yapi.cikislar.add(fd2);

            }

        } catch (IOException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

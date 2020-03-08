package ysagui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class YSAGui  {

    public static int hiddenSayac = 0;

    public static void main(String[] args) {

        Yapi yapi = new Yapi();
        Read r = new Read();
        r.oku(4);

       hiddenOlustur(5);
       hiddenOlustur(10);
       hiddenOlustur(20);
         

        agirlik();

        System.out.println("BEKLE");
        
        Ileri ileri = new Ileri();
        Geri geri = new Geri();
        for (int j = 0; j < 10000; j++) {
            for (int i = 0; i < Yapi.girisler.size(); i++) {
             ileri.girisHesaplama(i);
              geri.Hesapla(i);
            }
        }
while(true){
    kullanici(); 
}
      
        
        
    }
    //adam oraya her tıkladıgında hidden arraya hidden olsutursun
    public static void hiddenOlustur(int elemanSayisi) {

        Yapi.hiddenCikis.add(new ArrayList<>());
        iciniDoldur(elemanSayisi);
    }

    public static void iciniDoldur(int elemanSayisi) {

        for (int i = 0; i < elemanSayisi; i++) {
            Yapi.hiddenCikis.get(hiddenSayac).add(new Double(0));
        }
        hiddenSayac++;
    }

    public static void agirlik() {

        int ss = 0;
        Map<String, Double> agirlik1 = new HashMap<>();

        //girisler ile ilk hidden layer arasında agırlık
        for (int i = 0; i < Yapi.girisler.get(0).length; i++) {
            for (int j = 0; j < Yapi.hiddenCikis.get(0).size(); j++) {
                agirlik1.put(String.valueOf(i) + String.valueOf(j), randomDouble());

            }
        }

        Yapi.agirlik.add(agirlik1);
        ss++;
        agirlik1 = new HashMap<>();

        //Hidden layerlar arasındaki agirlik
        for (int j = 0; j < Yapi.hiddenCikis.size() - 1; j++) {
            for (int k = 0; k < Yapi.hiddenCikis.get(j).size(); k++) {
                for (int l = 0; l < Yapi.hiddenCikis.get(j + 1).size(); l++) {

                    agirlik1.put(String.valueOf(k) + String.valueOf(l), randomDouble());
                }
            }
        }

        Yapi.agirlik.add(agirlik1);
        ss++;

        agirlik1 = new HashMap<>();
        //Hidden ile cikis arasindaki agirliklar
        for (int j = Yapi.hiddenCikis.size() - 1; j < Yapi.hiddenCikis.size(); j++) {

            for (int i = 0; i < Yapi.hiddenCikis.get(j).size(); i++) {
                for (int k = 0; k < Yapi.cikislar.get(0).length; k++) {
                    agirlik1.put(String.valueOf(i) + String.valueOf(k), randomDouble());
                }
            }

        }

        Yapi.agirlik.add(agirlik1);

        ss++;
        // System.out.println("ÇIKIŞLAR");
        // System.out.println("SSS " + ss);
        for (int j = Yapi.hiddenCikis.size() - 1; j < Yapi.hiddenCikis.size(); j++) {

            for (int i = 0; i < Yapi.hiddenCikis.get(j).size(); i++) {
                for (int k = 0; k < Yapi.cikislar.get(0).length; k++) {
                    // System.out.println("["+i+"]"+"["+k+"]"+" --->> "+Yapi.agirlik.get(ss-1).get(String.valueOf(i) + String.valueOf(k))); 
                }
                // System.out.println("");
                //System.out.println("==================ZZZZZZZZZZZZZZZ===================");
            }

        }

    }

    public static double randomDouble() {
        Random r = new Random();
        double mydouble;
        if (r.nextBoolean()) {
            mydouble = r.nextDouble();
        } else {
            mydouble = ((double) -1) * r.nextDouble();
        }
        return mydouble;
    }

    public static void kullanici() {

        System.out.println("DEVAM....");
        Scanner input = new Scanner(System.in);
        ArrayList<Double> myint = new ArrayList<>();
        for (int i = 0; i < Yapi.girisler.get(0).length; i++) {
       
            myint.add(input.nextDouble());
        }

        veriIleri v = new veriIleri();
        v.girisHesaplama(myint);

    }

}

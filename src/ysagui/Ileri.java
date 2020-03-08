package ysagui;

import static ysagui.YSAGui.randomDouble;

public class Ileri {

    public static int agirlikIndex = 0;

    // Giris ile Hidden layer arasındaki toplam sembolu vs. hesaplama
    public void girisHesaplama(int index) {

        double toplam = 0;
        for (int i = 0; i < Yapi.hiddenCikis.get(0).size(); i++) {
            for (int j = 0; j < Yapi.girisler.get(index).length; j++) {

                //Giris ile Hidden old icin get(0) 
                // System.out.println("Giris AGIRLIK --> "+Yapi.agirlik.get(agirlikIndex).get(String.valueOf(j) + String.valueOf(i)));
                toplam = toplam + Yapi.agirlik.get(0).get(String.valueOf(j) + String.valueOf(i)) * Yapi.girisler.get(index)[j];

            }

            if (Yapi.hiddenCikis.get(0).size() > i) {
                double tpl = Sigmoid(toplam);
                Yapi.hiddenCikis.get(0).set(i, tpl);
               
            } else {
                double tpl = Sigmoid(toplam);
                Yapi.hiddenCikis.get(0).add(tpl);
             
            }

            toplam = 0;
        }

        hiddenHesaplama(index);
    }

    //Hiddenlar arasındaki toplam sembolu vs
    public void hiddenHesaplama(int index) {
        agirlikIndex++;
        agirlikIndex++;
        //System.out.println("AGIRLIK" + agirlikIndex);

        double toplam = 0;

        for (int j = 0; j < Yapi.hiddenCikis.size() - 1; j++) {
           // System.out.println(j + 1 + ". Katman SSAGIRLIK " + agirlikIndex);

            for (int k = 0; k < Yapi.hiddenCikis.get(j + 1).size(); k++) {
                for (int l = 0; l < Yapi.hiddenCikis.get(j).size(); l++) {

                    toplam = toplam + Yapi.agirlik.get(1).get(String.valueOf(l) + String.valueOf(k)) * Yapi.hiddenCikis.get(j).get(l);

                }

                if (Yapi.hiddenCikis.get(j + 1).size() > k) {
                    double tpl = Sigmoid(toplam);
                    Yapi.hiddenCikis.get(j + 1).set(k, tpl);
                  // System.out.println("IF2 TOPLAM " + tpl);
                } else {
                    double tpl = Sigmoid(toplam);

                    Yapi.hiddenCikis.get(j + 1).add(tpl);
                   // System.out.println("ELSE2 TOPLAM " + tpl);
                }

                toplam = 0;
            }

            //System.out.println("");
           // System.out.println("==================");
        }

        cikisHesapla(index);
    }

    public void cikisHesapla(int index) {
        agirlikIndex++;
        double toplam = 0;

        for (int j = Yapi.hiddenCikis.size() - 1; j < Yapi.hiddenCikis.size(); j++) {

            for (int i = 0; i < Yapi.cikislar.get(index).length; i++) {
                for (int k = 0; k < Yapi.hiddenCikis.get(j).size(); k++) {

                    toplam = toplam + Yapi.agirlik.get(2).get(String.valueOf(k) + String.valueOf(i)) * Yapi.hiddenCikis.get(j).get(k);
                    //System.out.println("[" + k + "]" + "[" + i + "]" + " --->> " + Yapi.agirlik.get(agirlikIndex - 1).get(String.valueOf(k) + String.valueOf(i)));
                }

                if (Yapi.cikis.size() > i) {
                    double tpl = Sigmoid(toplam);
                    Yapi.cikis.set(i, tpl);
                    //System.out.println("IF3 TOPLAM " + tpl);
                } else {
                    double tpl = Sigmoid(toplam);
                    Yapi.cikis.add(tpl);
                  //  System.out.println("ELSE3 TOPLAM " + tpl);
                }
                toplam = 0;
               // System.out.println("");
              //  System.out.println("==================ZZZZZZZZZZZZZZZ===================");
            }

        }
    }

    public double Sigmoid(double toplam) {
        return 1 / (1 + Math.pow(Math.E, (-1 * toplam)));
    }

}

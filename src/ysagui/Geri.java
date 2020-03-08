package ysagui;

import java.util.ArrayList;
import static ysagui.YSAGui.randomDouble;

public class Geri {

    private static ArrayList<Double> hatacikis;
    public static ArrayList<ArrayList<Double>> HatalarAraKatman;
    public static final double lamda = 0.1;

    public Geri() {
        hatacikis = new ArrayList<>();
        HatalarAraKatman = new ArrayList<>();
        Doldur();

    }

    public void Doldur() {
        for (int i = 0; i < Yapi.hiddenCikis.size(); i++) {
            HatalarAraKatman.add(new ArrayList<>());
            for (int j = 0; j < Yapi.hiddenCikis.get(i).size(); j++) {

                HatalarAraKatman.get(i).add(new Double(0));
            }
        }
       
        for (int i = 0; i < HatalarAraKatman.size(); i++) {

            for (int j = 0; j < HatalarAraKatman.get(i).size(); j++) {
                
            }
            System.out.println("");
        }
    }

    public void Hesapla(int index) {

        cikisHata(index);

        araKatmanHata();

        araKatmanGuncelle(index);
    }

    public void cikisHata(int index) {

        for (int i = 0; i < Yapi.cikis.size(); i++) {

            double t = Yapi.cikis.get(i) * (1 - Yapi.cikis.get(i)) * (Yapi.cikislar.get(index)[i] - Yapi.cikis.get(i));

            if (hatacikis.size() > i) {
                hatacikis.set(i, t);

            } else {

                hatacikis.add(t);
            }
        }
        cikisAgirlikGuncelle();
    }

    public void cikisAgirlikGuncelle() {
        double w = 0;

        for (int j = Yapi.hiddenCikis.size() - 1; j < Yapi.hiddenCikis.size(); j++) {

            for (int i = 0; i < Yapi.hiddenCikis.get(j).size(); i++) {
                for (int k = 0; k < Yapi.cikislar.get(0).length; k++) {
                    w = lamda * Yapi.hiddenCikis.get(j).get(i) * hatacikis.get(k);
                    double w1 = Yapi.agirlik.get(2).get(String.valueOf(i) + String.valueOf(k)) + w;
                    // System.out.println("[" + i + "]" + "[" + k + "]" + "Onceki Agirlik " + Yapi.agirlik.get(2).get(String.valueOf(i) + String.valueOf(k)) + " Sonraki " + w1);
                    Yapi.agirlik.get(2).put(String.valueOf(i) + String.valueOf(k), w1);
                }

            }

        }
    }

    //Tersten baslıyoruz ilk cikis ile ara katman sonra diğer ara katmanlara gidiyoruz
    public void araKatmanHata() {

        double t = 0, t2 = 0;
        int j = Yapi.hiddenCikis.size() - 1;

        for (int k = 0; k < Yapi.hiddenCikis.get(j).size(); k++) {

            t = Yapi.hiddenCikis.get(j).get(k) * (1 - Yapi.hiddenCikis.get(j).get(k));

            //Hidden ile cikis arasındaki hesaplama
            for (int i = 0; i < hatacikis.size(); i++) {

                t2 = t2 + hatacikis.get(i) * Yapi.agirlik.get(2).get(String.valueOf(k) + String.valueOf(i));
            }

            if (HatalarAraKatman.get(j).size() > k) {
                HatalarAraKatman.get(j).set(k, t * t2);

            } else {
                HatalarAraKatman.get(j).add(t * t2);

            }

            t2 = 0;
            t = 0;
            //Hiddenlar arasındaki hata fonk hesalama

        }

        for (int i = j; i >= 1; i--) {
            for (int k = 0; k < Yapi.hiddenCikis.get(i - 1).size(); k++) {
                t = Yapi.hiddenCikis.get(i - 1).get(k) * (1 - Yapi.hiddenCikis.get(i - 1).get(k));

                for (int l = 0; l < Yapi.hiddenCikis.get(i).size(); l++) {

                    //System.out.println("KKKKK " + Yapi.agirlik.get(1).get(String.valueOf(k) + String.valueOf(l)));
                    t2 = t2 + HatalarAraKatman.get(i).get(l) * Yapi.agirlik.get(1).get(String.valueOf(k) + String.valueOf(l));

                }

                if (HatalarAraKatman.get(i-1).size() > k) {
                    HatalarAraKatman.get(i-1).set(k, t * t2);
                    // System.out.println(" T2 " + t * t2);
                } else {
                    HatalarAraKatman.get(i-1).add(t * t2);
                    // System.out.println("ESLE T2 " + t * t2);
                }

                t2 = 0;
            }
        }
    }

    public void araKatmanGuncelle(int index) {
//        System.out.println("");
//        System.out.println("=======================");
//        System.out.println("");
        double w = 0;
        if (Yapi.hiddenCikis.size() - 1 != 0) {

            for (int i = 0; i < Yapi.hiddenCikis.size() - 1; i++) {

                for (int j = 0; j < Yapi.hiddenCikis.get(i).size(); j++) {
                    for (int k = 0; k < Yapi.hiddenCikis.get(i + 1).size(); k++) {
                        w = lamda * Yapi.hiddenCikis.get(i).get(j) * HatalarAraKatman.get(i + 1).get(k);
                        double w1 = Yapi.agirlik.get(1).get(String.valueOf(j) + String.valueOf(k)) + w;

                        //System.out.println("[" + j + "]" + "[" + k + "]" + "Onceki Agirlik " + Yapi.agirlik.get(1).get(String.valueOf(j) + String.valueOf(k)) + " Sonraki " + w);
                        Yapi.agirlik.get(1).put(String.valueOf(j) + String.valueOf(k), w1);

                    }
                }
            }
            girisAraHata(index);
        } else {
            
            girisAraHata(index);
        }

    }

    public void girisAraHata(int index) {

        double w = 0;
        
        for (int i = 0; i < Yapi.girisler.get(index).length; i++) {

            for (int j = 0; j < HatalarAraKatman.get(0).size(); j++) {

                w = lamda * Yapi.girisler.get(index)[i] * HatalarAraKatman.get(0).get(j);
                 //System.out.println("WWWWWWWWWW "+HatalarAraKatman.get(0).get(j)+" * "+Yapi.girisler.get(index)[i] );

                double w1 = Yapi.agirlik.get(0).get(String.valueOf(i) + String.valueOf(j)) + w;
                //System.out.println("[" + i + "]" + "[" + j + "]" + "Onceki Agirlik " + Yapi.agirlik.get(0).get(String.valueOf(i) + String.valueOf(j)) + " Sonraki " + w1);
                Yapi.agirlik.get(0).put(String.valueOf(i) + String.valueOf(j), w1);
            }
        }
    }
}

package ysagui;

import java.util.ArrayList;
import java.util.Map;

public class Yapi {

    public static ArrayList<Double[]> girisler;
    public static ArrayList<Double[]> cikislar;

    public static ArrayList<Map<String, Double>> agirlik;
    public static ArrayList<ArrayList<Double>> hiddenCikis;
    public static ArrayList<Double> cikis;

    public Yapi() {
        girisler = new ArrayList<>();
        cikislar = new ArrayList<>();
        cikis = new ArrayList<>();

        agirlik = new ArrayList<>();
        hiddenCikis = new ArrayList<>();
        cikis = new ArrayList<>();
    }

}

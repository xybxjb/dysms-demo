package cn.t;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author 加鑫宇
 * @date 2020-10-23 11:50
 */
public class Poker {

    public static void main(String[] args) {


        ArrayList<String> arrayList = new ArrayList<String>();
        String[] string = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K","A","2"};
        String[] color = {"♥", "♠", "♦", "♣"};
        for (String c : color) {
            for(String s : string){
                arrayList.add(c+s);
            }
        }
        arrayList.add("大王");
        arrayList.add("小王");

        //洗牌
        Collections.shuffle(arrayList);
        System.out.println(arrayList);

        ArrayList<String> zs = new ArrayList<>();
        ArrayList<String> ls = new ArrayList<>();
        ArrayList<String> ww = new ArrayList<>();

        ArrayList<String> dp = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            if(i>=arrayList.size()-3)
                dp.add(arrayList.get(i));
            else if (i % 3==0)
                zs.add(arrayList.get(i));
            else if (i% 3 ==1)
                ls.add(arrayList.get(i));
            else ww.add(arrayList.get(i));
        }
        System.out.println("zs"+zs);
        System.out.println("ls"+ls);
        System.out.println("ww"+ww);
        System.out.println("dp"+dp);
    }

}
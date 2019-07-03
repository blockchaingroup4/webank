package org.fisco.bcos.util;

import org.fisco.bcos.beans.CardInfo;

import java.util.Random;

public class CardDB {
    public static final String URL_PREFIX = "http://localhost:8080/img/";
    public static final String L1 = URL_PREFIX + "level1/";
    public static final String L2 = URL_PREFIX + "level2/";
    public static final String L3 = URL_PREFIX + "level3/";
    public static final String L4 = URL_PREFIX + "level4/";
    public static final String L5 = URL_PREFIX + "level5/";
    public static final String L6 = URL_PREFIX + "level6/";

    public static final String[][][] cards = {
            //level 1
            {
                {L1+"1_1.png", "11"},
                {L1+"1_2.png", "12"},
                {L1+"1_3.png", "13"},
                {L1+"1_4.png", "13"},
                {L1+"1_5.png", "13"},
                {L1+"1_6.png", "13"},
                {L1+"1_7.png", "13"},
                {L1+"1_8.png", "13"},
                {L1+"1_9.png", "13"},
                {L1+"1_10.png", "14"}
            },

            //level 2
            {
                {L2+"2_1.png", "21"},
                {L2+"2_2.png", "22"},
                {L2+"2_3.png", "21"},
                {L2+"2_4.png", "22"},
                {L2+"2_5.png", "21"},
                {L2+"2_6.png", "22"},
                {L2+"2_7.png", "23"}
            },

            //level 3
            {
                {L3+"3_1.png", "31"},
                {L3+"3_2.png", "32"}
            },

            //level 4
            {
                {L4+"4_1.png", "41"},
                {L4+"4_2.png", "41"},
                {L4+"4_3.png", "42"}
            },

            //level 5
            {
                {L5+"5_1.png", "51"},
            },

            //level 6
            {
                {L6+"6_1.jpg", "61"}
            }
    };
    public static void setCardUrlAndName(CardInfo info){
        Random random = new Random();
        int index = Math.abs(random.nextInt()) % cards[info.getLevel()-1].length;
        info.setUrl(cards[info.getLevel() - 1][index][0]);
        info.setName(cards[info.getLevel() - 1][index][1]);
    }
}

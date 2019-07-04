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
                {L1+"1_1.png", "柳干员"},
                {L1+"1_2.png", "夜烟"},
                {L1+"1_3.png", "信使"},
                {L1+"1_4.png", "霜叶"},
                {L1+"1_5.png", "麻雀"},
                {L1+"1_6.png", "红角"},
                {L1+"1_7.png", "兔子"},
                {L1+"1_8.png", "棉"},
                {L1+"1_9.png", "蛇屠箱"},
                {L1+"1_10.png", "风衣"}
            },

            //level 2
            {
                {L2+"2_1.png", "德克萨斯"},
                {L2+"2_2.png", "安洁莉娜"},
                {L2+"2_3.png", "红"},
                {L2+"2_4.png", "崔斯特"},
                {L2+"2_5.png", "能天使"},
                {L2+"2_6.png", "陈"},
                {L2+"2_7.png", "极光"}
            },

            //level 3
            {
                {L3+"3_1.png", "枚兰莎"},
                {L3+"3_2.png", "小绵羊"}
            },

            //level 4
            {
                {L4+"4_1.png", "阿米娅"},
                {L4+"4_2.png", "龙门"},
                {L4+"4_3.png", "盾"}
            },

            //level 5
            {
                {L5+"5_1.png", "阿米娅"},
            },

            //level 6
            {
                {L6+"6_1.jpg", "拉克丝"}
            }
    };
    public static void setCardUrlAndName(CardInfo info){
        Random random = new Random();
        int index = Math.abs(random.nextInt()) % cards[info.getLevel()-1].length;
        info.setUrl(cards[info.getLevel() - 1][index][0]);
        info.setName(cards[info.getLevel() - 1][index][1]);
    }
}

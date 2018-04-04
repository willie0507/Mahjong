package automahjongapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AutoMahjongApp {
    ArrayList<Mahjong> card ;
    ArrayList<Integer> discard ;
    int die1 ;
    int die2 ;
    int index ;
    int order ;
    boolean HuPai = false ;
    
    AutoMahjongApp() {
        discard = new ArrayList<>() ; 
        order = 1 ;
        die1 = (int)(Math.random() * 6 + 1) ;
        die2 = (int)(Math.random() * 6 + 1) ;
        index = die1 + die2 ;
        card = new ArrayList<>(136) ; // 建立牌堆
        for(int i = 1 ; i <= 9 ; i++) { // 加入萬子
            for(int count = 1 ; count <= 4 ; count++) {
                switch ((int)i % 10) {
                case 1:
                case 9:
                    card.add(new Mahjong(i, 0)) ;
                    break;
                case 2:
                case 8:
                    card.add(new Mahjong(i, 1)) ;
                    break;
                default:
                    card.add(new Mahjong(i, 2)) ;
                    break;
                }
            }
        }
        
        for(int i = 21 ; i <= 29 ; i++) { // 加入條子
            for(int count = 1 ; count <= 4 ; count++) {
                switch ((int)i % 10) {
                case 1:
                case 9:
                    card.add(new Mahjong(i, 0)) ;
                    break;
                case 2:
                case 8:
                    card.add(new Mahjong(i, 1)) ;
                    break;
                default:
                    card.add(new Mahjong(i, 2)) ;
                    break;
                }
            }
        }
        
        for(int i = 41 ; i <= 49 ; i++) { // 加入餅子
            for(int count = 1 ; count <= 4 ; count++) {
                switch ((int)i % 10) {
                case 1:
                case 9:
                    card.add(new Mahjong(i, 0)) ;
                    break;
                case 2:
                case 8:
                    card.add(new Mahjong(i, 1)) ;
                    break;
                default:
                    card.add(new Mahjong(i, 2)) ;
                    break;
                }
            }
        }
        
        for(int i = 61 ; i <= 121 ; i+=10) { // 加入字牌
            for(int count = 1 ; count <= 4 ; count++)
                card.add(new Mahjong(i, 0)) ;
        }
        Collections.shuffle(card) ;
    }
    
    public void Dealcard(AI_Player p1, AI_Player p2, AI_Player p3, AI_Player p4) {
        for(int i = 0 ; i < 13 ; i++) {
            p1.PlayerHands.add(card.get(index)) ;
            card.remove(index) ;
            p2.PlayerHands.add(card.get(index)) ;
            card.remove(index) ;
            p3.PlayerHands.add(card.get(index)) ;
            card.remove(index) ;
            p4.PlayerHands.add(card.get(index)) ;
            card.remove(index) ;
        }
    }
    
    public void MoPai(AI_Player p) {
        try {  // 正常摸牌
            p.PlayerHands.add(card.get(index)) ;
            card.remove(index) ;
            HuPai = p.checkHu() ;
        }
        catch (IndexOutOfBoundsException ex){  // 牌已經摸到底，從頭開始摸
            index = 0 ;
            p.PlayerHands.add(card.get(index)) ;
            card.remove(index) ;
            HuPai = p.checkHu() ;
        }
    }
    
    public void checkOrder(int change) {
        if(change > 0) // 遇到吃碰槓，更換打牌順序
            order = change ;
        else{ // 無吃碰槓，輪至下一家打牌
            order++ ;
            if(order > 4)
                order = 1 ;
        }
    }
    
    public boolean checkDuel() {
        return (card.size() <= 14) ;
    }
    
    public void showTable(AI_Player p1, AI_Player p2, AI_Player p3, AI_Player p4) {
        System.out.print("AI1手牌: ") ;
        for(int i = 0 ; i < p1.PlayerHands.size(); i++)
            System.out.print("" + p1.PlayerHands.get(i).code + " ") ;
        System.out.print("AI2手牌: ") ;
        for(int i = 0 ; i < p2.PlayerHands.size(); i++)
            System.out.print("" + p2.PlayerHands.get(i).code + " ") ;
        System.out.print("AI3手牌: ") ;
        for(int i = 0 ; i < p3.PlayerHands.size(); i++)
            System.out.print("" + p3.PlayerHands.get(i).code + " ") ;
        System.out.print("AI4手牌: ") ;
        for(int i = 0 ; i < p4.PlayerHands.size(); i++)
            System.out.print("" + p4.PlayerHands.get(i).code + " ") ;
        for(int i = 0 ; i < discard.size() ; i++)
            System.out.print("" + discard.get(i) + " ") ;
    }
    
    public static void main(String[] args) {
        int count ;
        int win1 = 0 ;
        int win1_self = 0 ;
        int win2 = 0 ;
        int win2_self = 0 ;
        int win3 = 0 ;
        int win3_self = 0 ;
        int win4 = 0 ;
        int win4_self = 0 ;
        int draw = 0 ;
        Scanner scanner = new Scanner(System.in) ;
        count = scanner.nextInt() ;
        for(int i = 0 ; i < count ; i++){
            AutoMahjongApp g1 = new AutoMahjongApp() ;
            g1.order = (i + 4) % 4 + 1 ;
            AI1 p1 = new AI1() ;
            AI3 p2 = new AI3() ;
            AI1 p3 = new AI1() ;
            AI2 p4 = new AI2() ;
            g1.Dealcard(p1, p2, p3, p4) ;
            while(true) {
                if(g1.order == 1) {
                    if(g1.checkDuel()) {
                        System.out.println("流局") ;
                        draw++ ;
                        break ;
                    }
                    g1.MoPai(p1) ;
                    if(g1.HuPai) {
                        System.out.println("AI1自摸了");
                        win1_self++ ;
                        win1++ ;
                        break ;
                    }
                    p1.AIChuPai(g1.discard) ;
                    if(p2.HuPai(g1.discard)) {
                        System.out.println("AI2胡牌了");
                        win2++ ;
                        break ;
                    }
                     
                    if(p3.HuPai(g1.discard)) {
                        System.out.println("AI3胡牌了");
                        win3++ ;
                        break ;
                    }
                    
                    if(p4.HuPai(g1.discard)) {
                        System.out.println("AI4胡牌了");
                        win4++ ;
                        break ;
                    }
                    g1.checkOrder(p2.AIChiPai(g1.discard)) ;
                    //g1.checkOrder(0) ;
                }
                else if(g1.order == 2) {
                    if(g1.checkDuel()) {
                        System.out.println("流局") ;
                        draw++ ;
                        break ;
                    }
                    if(!p2.jump)
                        g1.MoPai(p2) ;
                    if(g1.HuPai) {
                        System.out.println("AI2自摸了");
                        win2_self++ ;
                        win2++ ;
                        break ;
                    }
                    p2.AIChuPai(g1.discard) ;
                    if(p1.HuPai(g1.discard)) {
                        System.out.println("AI1胡牌了");
                        win1++ ;
                        break ;
                    }
                     
                    if(p3.HuPai(g1.discard)) {
                        System.out.println("AI3胡牌了");
                        win3++ ;
                        break ;
                    }
                    
                    if(p4.HuPai(g1.discard)) {
                        System.out.println("AI4胡牌了");
                        win4++ ;
                        break ;
                    }
                    g1.checkOrder(0) ;
                }
                else if(g1.order == 3) {
                    if(g1.checkDuel()) {
                        System.out.println("流局") ;
                        draw++ ;
                        break ;
                    }
                    g1.MoPai(p3) ;
                    if(g1.HuPai) {
                        System.out.println("AI3自摸了");
                        win3_self++ ;
                        win3++ ;
                        break ;
                    }
                    p3.AIChuPai(g1.discard) ;
                    if(p2.HuPai(g1.discard)) {
                        System.out.println("AI2胡牌了");
                        win2++ ;
                        break ;
                    }
                     
                    if(p1.HuPai(g1.discard)) {
                        System.out.println("AI1胡牌了");
                        win1++ ;
                        break ;
                    }
                    
                    if(p4.HuPai(g1.discard)) {
                        System.out.println("AI4胡牌了");
                        win4++ ;
                        break ;
                    }
                    g1.checkOrder(0) ;
                }
                else if(g1.order == 4) {
                    if(g1.checkDuel()) {
                        System.out.println("流局") ;
                        draw++ ;
                        break ;
                    }
                    g1.MoPai(p4) ;
                    if(g1.HuPai) {
                        System.out.println("AI4自摸了");
                        win4_self++ ;
                        win4++ ;
                        break ;
                    }
                    p4.AIChuPai(g1.discard) ;
                    if(p2.HuPai(g1.discard)) {
                        System.out.println("AI2胡牌了");
                        win2++ ;
                        break ;
                    }
                     
                    if(p3.HuPai(g1.discard)) {
                        System.out.println("AI3胡牌了");
                        win3++ ;
                        break ;
                    }
                    
                    if(p1.HuPai(g1.discard)) {
                        System.out.println("AI1胡牌了");
                        win1++ ;
                        break ;
                    }
                    g1.checkOrder(0) ;
                }
            }
            System.out.println(i + 1) ;
        } 
        System.out.println() ;
        System.out.println() ;
        System.out.println() ;
        System.out.println("AI1胡牌次數: " + win1) ;
        System.out.println("AI1自摸次數: " + win1_self);
        System.out.println("AI2胡牌次數: " + win2) ;
        System.out.println("AI2自摸次數: " + win2_self) ;
        System.out.println("AI3胡牌次數: " + win3) ;
        System.out.println("AI3自摸次數: " + win3_self) ;
        System.out.println("AI4胡牌次數: " + win4) ;
        System.out.println("AI4自摸次數: " + win4_self) ;
        System.out.println("流局: " + draw) ;
    }
}
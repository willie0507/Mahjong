package ui_learning;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AI_Player extends Player {
    public ArrayList<CardButton> table ; // 牌桌暫存
    
    AI_Player() {
        PlayerHands = new ArrayList<>() ; // AI手牌
        PlayerTable = new ArrayList<>() ; // AI鳴牌
        tempHu = new ArrayList<>() ;
        table = new ArrayList<>() ;
        
        //初始化牌桌
        for(int i = 1 ; i <= 9 ; i++){ // 添加萬子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i, 0, new ImageIcon("" + i + "Wans.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i, 1, new ImageIcon("" + i + "Wans.jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i, 2, new ImageIcon("" + i + "Wans.jpg"))) ;
                        break;
                }
            }
            
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加條子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i+20, 0, new ImageIcon("" + i + "Tiaos.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i+20, 1, new ImageIcon("" + i + "Tiaos.jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i+20, 2, new ImageIcon("" + i + "Tiaos.jpg"))) ;
                        break;
                }
            }
            
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加餅子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i+40, 0, new ImageIcon("" + i + "Bings.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i+40, 1, new ImageIcon("" + i + "Bings.jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i+40, 2, new ImageIcon("" + i + "Bings.jpg"))) ;
                        break;
                }
            }            
        }
        
        for(int i = 0 ; i < 4 ; i++) {
            table.add(new CardButton(61, 0, new ImageIcon("Dongs.jpg"))) ; 
            table.add(new CardButton(71, 0, new ImageIcon("Nans.jpg"))) ;
            table.add(new CardButton(81, 0, new ImageIcon("Xis.jpg"))) ;
            table.add(new CardButton(91, 0, new ImageIcon("Beis.jpg"))) ;
            table.add(new CardButton(101, 0, new ImageIcon("Zhongs.jpg"))) ;
            table.add(new CardButton(111, 0, new ImageIcon("Fas.jpg"))) ;
            table.add(new CardButton(121, 0, new ImageIcon("Bais.jpg"))) ;
        } 
    }
    
     AI_Player(String str) {
        PlayerHands = new ArrayList<>() ; // AI手牌
        PlayerTable = new ArrayList<>() ; // AI鳴牌
        tempHu = new ArrayList<>() ;
        table = new ArrayList<>() ;
        
        //初始化牌桌
        for(int i = 1 ; i <= 9 ; i++){ // 添加萬子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i, 0, new ImageIcon("" + i + "Wans_" + str + ".jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i, 1, new ImageIcon("" + i + "Wans_" + str + ".jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i, 2, new ImageIcon("" + i + "Wans_" + str + ".jpg"))) ;
                        break;
                }
            }
            
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加條子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i+20, 0, new ImageIcon("" + i + "Tiaos_" + str + ".jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i+20, 1, new ImageIcon("" + i + "Tiaos_" + str + ".jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i+20, 2, new ImageIcon("" + i + "Tiaos_" + str + ".jpg"))) ;
                        break;
                }
            }
            
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加餅子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i+40, 0, new ImageIcon("" + i + "Bings_" + str + ".jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i+40, 1, new ImageIcon("" + i + "Bings_" + str + ".jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i+40, 2, new ImageIcon("" + i + "Bings_" + str + ".jpg"))) ;
                        break;
                }
            }            
        }
        
        for(int i = 0 ; i < 4 ; i++) {
            table.add(new CardButton(61, 0, new ImageIcon("Dongs_" + str + ".jpg"))) ; 
            table.add(new CardButton(71, 0, new ImageIcon("Nans_" + str + ".jpg"))) ;
            table.add(new CardButton(81, 0, new ImageIcon("Xis_" + str + ".jpg"))) ;
            table.add(new CardButton(91, 0, new ImageIcon("Beis_" + str + ".jpg"))) ;
            table.add(new CardButton(101, 0, new ImageIcon("Zhongs_" + str + ".jpg"))) ;
            table.add(new CardButton(111, 0, new ImageIcon("Fas_" + str + ".jpg"))) ;
            table.add(new CardButton(121, 0, new ImageIcon("Bais_" + str + ".jpg"))) ;
        }
        
        if(str.equals("front")) {
            for(int i = 0 ; i < table.size() ; i++)
                table.get(i).setPreferredSize(new Dimension(33, 49)) ;
        }
        else {
            for(int i = 0 ; i < table.size() ; i++)
                table.get(i).setPreferredSize(new Dimension(49, 33)) ;
        }
    }
   
    //AI出牌，求出權值最小的element的index，然後打出
    public void AIChuPai(JPanel Discard) {
        ArrayList<Integer> temp = new ArrayList<>() ;
        for(int i = 0 ; i < PlayerHands.size() ; i++)
            temp.add(PlayerHands.get(i).code) ;
        int[] Weight = new int[temp.size()];
        Collections.sort(temp) ;
        for(int i = 0 ; i < Weight.length ; i++) { // 分配基礎權值
            Weight[i] = PlayerHands.get(i).weight ;
        }
        
        int tempcard ;
        for(int i = 0 ; i < temp.size() ; i++) {
            tempcard = temp.get(i) ;
            if(temp.contains(tempcard + 2)) // 相隔+1
               Weight[i]+=1 ;
            if(temp.contains(tempcard - 2)) // 相隔+1
               Weight[i]+=1 ;
            if(temp.contains(tempcard + 1)) // 相鄰+2
               Weight[i]+=2 ;
            if(temp.contains(tempcard - 1)) // 相鄰+2
               Weight[i]+=2 ;
            if(temp.remove(Integer.valueOf(tempcard))) {
                if(temp.remove(Integer.valueOf(tempcard))) {
                    if(temp.remove(Integer.valueOf(tempcard))) {
                        Weight[i]+=4 ;  // 三張相同+4
                        temp.add(tempcard) ;
                        temp.add(tempcard) ;
                        temp.add(tempcard) ;
                        Collections.sort(temp) ;
                    }
                    else {
                        Weight[i]+=2 ;  // 兩張相同+2
                        temp.add(tempcard) ;
                        temp.add(tempcard) ;
                        Collections.sort(temp) ;
                    }
                }
                else {
                    temp.add(tempcard) ;
                    Collections.sort(temp) ;
                }
            }
        }        
        
        int min = 0;
        for(int i = 1 ; i < Weight.length; i++){
            if(Weight[i] < Weight[min]){
                min = i ;
            }
        }
        
        System.out.println("Size: " + temp.size()) ;
        System.out.println("Min: " + min) ;
        
        int ChuPai = temp.get(min) ;
        for(int i = 0 ; i < table.size() ; i++){
            if(table.get(i).code == ChuPai){
                //table.get(i).setPreferredSize(new Dimension(33, 49));
                Discard.add(table.get(i)) ; // 把牌放進棄牌堆
                table.remove(i) ;
                break ;
            }
        }
        
        for(int i = 0 ; i < PlayerHands.size() ; i++) {
            if(PlayerHands.get(i).code == ChuPai){
                PlayerHands.remove(i) ; // 打出權值最小的牌
                break ;
            }
        }
        temp.clear() ;
        System.out.println("" + this.hashCode() + "電腦出牌：  " + ChuPai) ;
    }
    
    public boolean checkMien() { // 檢查面子
        int tempcard ;
        if(tempHu.isEmpty())
            return true ;
        else {
            for(int i = 0 ; i < tempHu.size() ; i++) {
                tempcard = tempHu.get(i) ;
                tempHu.remove(Integer.valueOf(tempcard)) ; // 檢查，先拿走一個
                if(tempHu.remove(Integer.valueOf(tempcard))) { // 檢查刻子，看看能不能拿走第二個，可以的話再看有沒有第三個存在
                    if(tempHu.remove(Integer.valueOf(tempcard))) { // 有第三個在的話，把它拿掉
                        return checkMien() ;
                    }
                    else { // 原本有兩個，補回一個
                        tempHu.add(tempcard) ;
                        Collections.sort(tempHu);
                    }
                }
                
                if(tempHu.contains(tempcard + 1) && tempHu.contains(tempcard + 2)) { // 檢查順子
                    tempHu.remove(Integer.valueOf(tempcard + 1)) ; // 有順子，拿掉
                    tempHu.remove(Integer.valueOf(tempcard + 2)) ;
                    Collections.sort(tempHu);
                    return checkMien() ;
                }
                tempHu.add(tempcard) ; // 沒有面子，補回剛開始刪掉的牌
                Collections.sort(tempHu);
            }
            tempHu.clear() ; // 完全沒有面子，清空暫存
            for(int i = 0 ; i < PlayerHands.size() ; i++){
                tempHu.add(PlayerHands.get(i).code) ; // 再把原先手牌放入暫存
            }
            return false ; 
        }

    }
    
    public boolean checkHu() { // 檢查眼 + 面子
        int tempcard ;
        for(int i = 0 ; i < PlayerHands.size() ; i++){
            tempHu.add(PlayerHands.get(i).code) ; // 把手牌放入暫存
        }
        boolean checkYen ;
        boolean checkMien = false ;
        for(int i = 0 ; i < tempHu.size() - 1 ; i++) { 
            checkYen = false ;
            tempcard = tempHu.get(i) ;
            tempHu.remove(Integer.valueOf(tempcard)) ; // 檢查眼，先拿掉一張
            if(tempHu.remove(Integer.valueOf(tempcard))) { // 如果有第二張，把它拿掉
                checkYen = true ; // 確定有眼
                checkMien = checkMien() ; // 檢查面子
                if(checkYen && checkMien) // 有眼有面子，胡牌
                    return true ;
            }
            if(checkYen && !checkMien) { // 有眼沒面子
                Collections.sort(tempHu);
            }
            else { // 沒眼，把一開始拿掉的補回來，找下一個
                tempHu.add(tempcard) ;
                Collections.sort(tempHu);
            }
        }
        tempHu.clear() ; // 完全沒有眼，清空暫存
        return false ;
    }
}
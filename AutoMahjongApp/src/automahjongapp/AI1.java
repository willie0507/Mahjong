package automahjongapp;

import java.util.ArrayList;
import java.util.Collections;

public class AI1 extends AI_Player {
    int sum ; // 吃碰槓判定用，計算總權值
    
    AI1() {
        sum = 0 ;
        PlayerHands = new ArrayList<>() ;
        table_hands = new ArrayList<>() ;
        tempHu = new ArrayList<>() ;
    }
    public void AIChuPai(ArrayList<Integer> discard) {
        Collections.sort(PlayerHands) ;
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
        Weight[min] = 0 ;
        for(int i = 0 ; i < Weight.length ; i++) {
            sum = sum + Weight[i] ;
        }
        
        int ChuPai = temp.get(min) ;
        
        for(int i = 0 ; i < PlayerHands.size() ; i++) {
            if(PlayerHands.get(i).code == ChuPai){
                discard.add(PlayerHands.get(i).code) ;
                PlayerHands.remove(i) ; // 打出權值最小的牌
                break ;
            }
        }
        temp.clear() ;
        Collections.sort(PlayerHands) ;
        //System.out.println("電腦出牌：  " + ChuPai) ;
    }
    
    public int AIChiPai(ArrayList<Integer> discard) {
        return 0 ;
    }
    
    public int AIPonPai(ArrayList<Integer> discard) {
        return 0 ;
    }
    
    public boolean checkMien() {
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
    
    public boolean checkHu() {
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
    
    public boolean HuPai(ArrayList<Integer> discard) {
        int source = discard.get(discard.size() - 1) ;
        Mahjong temp = new Mahjong(source) ;
        PlayerHands.add(temp) ;
        if(!checkHu()) {
            for(int i = 0 ; i < PlayerHands.size() ; i++) {
                if(PlayerHands.get(i).code == source) {
                    PlayerHands.remove(temp) ;
                    break ;
                } 
            }
            return false ;
        }
        else {
            return true ;
        }
    }
}

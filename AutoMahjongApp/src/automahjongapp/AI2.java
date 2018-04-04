package automahjongapp;

import java.util.ArrayList;
import java.util.Collections;

public class AI2 extends AI1 {
    AI2() {
        super() ;
    }
    
    public void lock() {
        Collections.sort(PlayerHands) ;
        ArrayList<Integer> temp = new ArrayList<>() ;
        for(int i = 0 ; i < PlayerHands.size() ; i++) {
            temp.add(PlayerHands.get(i).code) ;
        }
        Collections.sort(temp) ;
        int lock1 = -1 ;
        int lock2 = -1 ;
        int lock3 = -1 ;
        int tempcard ;
        for(int i = 0 ; i < temp.size() - 2 ; i++) {
            tempcard = temp.get(i) ;
            if(temp.contains(tempcard + 1) && temp.contains(tempcard + 2)) {
                for(int j = 0 ; j < temp.size() ; j++) {
                    if(temp.get(j) == tempcard) {
                        lock1 = j ;
                        break ;
                    }
                }
                for(int j = 0 ; j < temp.size() ; j++) {
                    if(temp.get(j) == tempcard + 1) {
                        lock2 = j ;
                        break ;
                    }
                }
                for(int j = 0 ; j < temp.size() ; j++) {
                    if(temp.get(j) == tempcard + 2) {
                        lock3 = j ;
                        break ;
                    }
                }
            }
            if(lock1 != -1 && lock2 != -1 && lock3 != -1 && PlayerHands.get(lock1).weight != -1 && PlayerHands.get(lock2).weight != -1 && PlayerHands.get(lock3).weight != -1) {
                PlayerHands.get(lock1).weight = -1 ;
                PlayerHands.get(lock2).weight = -1 ;
                PlayerHands.get(lock3).weight = -1 ;
                lock1 = -1 ;
                lock2 = -1 ;
                lock3 = -1 ;
                //break ;
            }                
        }
    }
    
    public void AIChuPai(ArrayList<Integer> discard) {
        lock() ;
        ArrayList<Integer> temp = new ArrayList<>() ;
        for(int i = 0 ; i < PlayerHands.size() ; i++)
            if(PlayerHands.get(i).weight != -1)
                temp.add(PlayerHands.get(i).code) ;
        int[] Weight = new int[temp.size()];
        Collections.sort(temp) ;
        for(int i = 0 ; i < Weight.length ; i++) { // 分配基礎權值
            if(PlayerHands.get(i).weight != -1)
                Weight[i] = PlayerHands.get(i).weight ;
        }
        
        for(int i = 0 ; i < PlayerHands.size() ; i++) {
            if(PlayerHands.get(i).weight == -1) {
                switch((int)(PlayerHands.get(i).code % 10)){
                    case 1:
                    case 9:
                        PlayerHands.get(i).weight = 0 ;
                        break;
                    case 2:
                    case 8:
                        PlayerHands.get(i).weight = 1 ;
                        break;
                    default:
                        PlayerHands.get(i).weight = 2 ;
                        break;
                }
            }
        }
        
        int tempcard ;
        for(int i = 0 ; i < temp.size() ; i++) {
            //if(Weight[i] != -1) {
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
            //}  
        }        
        
        int min = 0;
        for(int i = 1 ; i < Weight.length; i++){
            if(Weight[i] < Weight[min]){
                min = i ;
            }
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
}

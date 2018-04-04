package automahjongapp;

import java.util.ArrayList;

public abstract class AI_Player {
    ArrayList<Mahjong> PlayerHands ; // 手牌
    ArrayList<Mahjong> table_hands ; // 鳴牌
    ArrayList<Integer> tempHu ; // 手牌
    public abstract void AIChuPai(ArrayList<Integer> discard) ; // 權值計算
    public abstract int AIChiPai(ArrayList<Integer> discard) ; // 吃牌判定
    public abstract int AIPonPai(ArrayList<Integer> discard) ; // 碰牌判定
    public abstract boolean checkMien() ; // 面子判定
    public abstract boolean checkHu() ; // 胡牌判定
}

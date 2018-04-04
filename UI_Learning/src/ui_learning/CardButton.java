package ui_learning ;
import javax.swing.*;  //引用套件

public class CardButton extends JButton implements Comparable<CardButton> {
    public int code ; // 牌的編碼
    public int weight ; // 牌的權值
    
    CardButton(int c, int w, Icon icon){ // 建構子，設定牌的圖片和編碼以及基礎權值
        super(icon) ;
        code = c ;
        weight = w ;
    }
    
    CardButton(int c, String str) {
        super(str) ;
        code = c ;
    }
    
    public int compareTo(CardButton c){ // 讓 Collections.sort 可以對 CardButton 類別利用 code 的值的大小進行排列
        return ((Integer)this.code).compareTo(c.code) ;
    }

}
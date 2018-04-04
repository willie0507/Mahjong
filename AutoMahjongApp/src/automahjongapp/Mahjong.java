package automahjongapp;

public class Mahjong implements Comparable<Mahjong> {
    int code ;
    int weight ;
    
    Mahjong(int c, int w) {
        code = c ;
        weight = w ;
    }
    
    Mahjong(int c) {
        code = c ;
    }
    
    public int compareTo(Mahjong m){ // 讓 Collections.sort 可以對 Mahjong 類別利用 code 的值的大小進行排列
        return ((Integer)this.code).compareTo(m.code) ;
    }
}
package ui_learning;

public class Level2_AI_Player extends AI_Player {
    Level2_AI_Player() {
        super() ;
    }
    
    Level2_AI_Player(String str) {
        super(str) ;
    }
    
    public void lock() {
        
    }
    
    public void checkChi(CardButton card) {
        this.PlayerHands.add(card) ;
        
    }
}

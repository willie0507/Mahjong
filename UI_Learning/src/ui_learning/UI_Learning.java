package ui_learning;
import javax.swing.*;  //引用套件
import java.awt.*;
import java.awt.event.* ;
import java.util.ArrayList;
import java.util.Collections;

public class UI_Learning extends JFrame implements ActionListener {
    ArrayList<CardButton> card ; // 牌堆
    ArrayList<CardButton> hand ; // 手牌暫存
    ArrayList<CardButton> table ; // 小牌暫存
    public int Die, Die2 ;  // 兩個骰子
    public int index ;  // 當前發牌位置
    public boolean HuPai ;
    public boolean clickable = false ;
    ArrayList<Integer> tempHu ; // 胡牌檢測暫存
    int order ;
    boolean jump = false ;
    int mingCount ;  // 鳴牌次數
    boolean confirm_Chi = false ;
    boolean confirm_Pon = false ;
    
    JPanel south_table ; // 玩家的鳴牌區
    JPanel south ; // 玩家區塊的 Panel
    JPanel south_hand ; // 玩家全部手牌的 Panel
    JPanel south_dealcard ; // 放置玩家該回合摸到的牌的 Panel
    JPanel group1 ; // 放置玩家手牌 (不包含該回合摸到的牌) 的 Panel
    JPanel center ; // 所有玩家棄牌的 Panel
    JPanel south_discard ; // 玩家棄牌的 Panel
    JPanel east_discard ; // 右邊棄牌的 Panel
    JPanel west_discard ; // 左邊棄牌的 Panel
    JPanel north_discard ; // 上面棄牌的 Panel
    JPanel PanelBei ; // 上面的手牌的 Panel
    JPanel PanelXi ; // 左邊的手牌的 Panel
    JPanel PanelDong ; // 右邊的手牌的 Panel
        
    UI_Learning(String str) {
        super(str) ; // 設定 Frame 的標題
        mingCount = 0 ;
        order = 1 ;
        card = new ArrayList<>() ;
        hand = new ArrayList<>() ;
        table = new ArrayList<>() ;
        Die = (int)(Math.random() * 6 + 1) ;  
        Die2 = (int)(Math.random() * 6 + 1) ;  // 擲骰子
        index = Die + Die2 ;  // 骰子結果
        tempHu = new ArrayList<>() ;                
        //初始化手牌
        for(int i = 1 ; i <= 9 ; i++){ // 添加萬子
            for(int j = 1 ; j <= 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        card.add(new CardButton(i, 1, new ImageIcon("" + i + "Wan.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        card.add(new CardButton(i, 2, new ImageIcon("" + i + "Wan.jpg"))) ;
                        break;
                    default:
                        card.add(new CardButton(i, 3, new ImageIcon("" + i + "Wan.jpg"))) ;
                        break;
                }
            }   
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加條子
            for(int j = 1 ; j <= 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        card.add(new CardButton(i+20, 1, new ImageIcon("" + i + "Tiao.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        card.add(new CardButton(i+20, 2, new ImageIcon("" + i + "Tiao.jpg"))) ;
                        break;
                    default:
                        card.add(new CardButton(i+20, 3, new ImageIcon("" + i + "Tiao.jpg"))) ;
                        break;
                }
            }
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加餅子
            for(int j = 1 ; j <= 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        card.add(new CardButton(i+40, 1, new ImageIcon("" + i + "Bing.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        card.add(new CardButton(i+40, 2, new ImageIcon("" + i + "Bing.jpg"))) ;
                        break;
                    default:
                        card.add(new CardButton(i+40, 3, new ImageIcon("" + i + "Bing.jpg"))) ;
                        break;
                }
            }
        }
                
        for(int i = 1 ; i <= 4 ; i++){ // 添加字牌
            card.add(new CardButton(61, 0, new ImageIcon("Dong.jpg"))) ; 
            card.add(new CardButton(71, 0, new ImageIcon("Nan.jpg"))) ;
            card.add(new CardButton(81, 0, new ImageIcon("Xi.jpg"))) ;
            card.add(new CardButton(91, 0, new ImageIcon("Bei.jpg"))) ;
            card.add(new CardButton(101, 0, new ImageIcon("Zhong.jpg"))) ;
            card.add(new CardButton(111, 0, new ImageIcon("Fa.jpg"))) ;
            card.add(new CardButton(121, 0, new ImageIcon("Bai.jpg"))) ; 
        }
                  
        for(int i = 0 ; i < card.size() ; i++) // 對所有牌進行 Action 的註冊
            card.get(i).addActionListener(this);
                         
        //初始化牌桌
        for(int i = 1 ; i <= 9 ; i++){ // 添加萬子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i, 1, new ImageIcon("" + i + "Wans.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i, 2, new ImageIcon("" + i + "Wans.jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i, 3, new ImageIcon("" + i + "Wans.jpg"))) ;
                        break;
                }
            }
            
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加條子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i+20, 1, new ImageIcon("" + i + "Tiaos.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i+20, 2, new ImageIcon("" + i + "Tiaos.jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i+20, 3, new ImageIcon("" + i + "Tiaos.jpg"))) ;
                        break;
                }
            }
            
        }
                
        for(int i = 1 ; i <= 9 ; i++){ // 添加餅子
            for(int j = 0 ; j < 4 ; j++) {
                switch (i) {
                    case 1:
                    case 9:
                        table.add(new CardButton(i+40, 1, new ImageIcon("" + i + "Bings.jpg"))) ;
                        break;
                    case 2:
                    case 8:
                        table.add(new CardButton(i+40, 2, new ImageIcon("" + i + "Bings.jpg"))) ;
                        break;
                    default:
                        table.add(new CardButton(i+40, 3, new ImageIcon("" + i + "Bings.jpg"))) ;
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
        Dimension table_size = new Dimension(33, 49) ;
        for(int i = 0 ; i < table.size() ; i++) 
            table.get(i).setPreferredSize(table_size);
        
        Collections.sort(table) ; // 排列手牌
        Collections.shuffle(card); // 洗牌
	Container cp = getContentPane(); //取得內容面版
        south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)) ;
        south_hand = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5)) ;
        south_dealcard = new JPanel(new GridLayout(1, 1)) ;
        south_table = new JPanel() ;
                
        group1 = new JPanel() ;
        group1.setLayout(new GridLayout(1, 13));
	cp.setLayout(new BorderLayout(0, 0));
                
        Dimension dim = new Dimension(66, 98) ; // 設定牌的大小
                
        for(int i = 1 ; i <= 13 ; i++){ // 發最初的 13 張牌，放進手牌暫存區中
            hand.add(card.get(index)) ;
            card.remove(index) ;
        }
        Collections.sort(hand) ; // 排列手牌
        for(int i = 1 ; i <= 13 ; i++){
            hand.get(0).setPreferredSize(dim); 
            group1.add(hand.get(0)) ; // 從手牌暫存區依序加入進玩家的手牌 Panel 中
            hand.remove(0) ; // 清空手牌暫存區，以便下一次讀取手牌
        }

        card.get(0).setPreferredSize(dim);
        
        south_hand.add(group1) ; // 把玩家的前 13 張手牌放進玩家手牌區
        south.add(south_hand) ; 
        south.add(south_dealcard) ; // 把玩家該回合的摸牌放進手牌區
                
        ImageIcon dm = new ImageIcon("DuiMian.jpg");
        ImageIcon zb = new ImageIcon("ZuoBian.jpg");
        ImageIcon zbs = new ImageIcon("ZuoBians.jpg");
        ImageIcon yb = new ImageIcon("YouBian.jpg");
        ImageIcon ybs = new ImageIcon("YouBians.jpg");
                
        JLabel Imgdm1 = new JLabel(dm);
        Imgdm1.setSize(60,90);
        Imgdm1.setLocation(200, 10);
        JLabel Imgdm2 = new JLabel(dm);
        Imgdm2.setSize(60,90);
        Imgdm2.setLocation(262, 10);
        JLabel Imgdm3 = new JLabel(dm);
        Imgdm3.setSize(60,90);
        Imgdm3.setLocation(324, 10);
        JLabel Imgdm4 = new JLabel(dm);
        Imgdm4.setSize(60,90);
        Imgdm4.setLocation(386, 10);
        JLabel Imgdm5 = new JLabel(dm);
        Imgdm5.setSize(60,90);
        Imgdm5.setLocation(448, 10);
        JLabel Imgdm6 = new JLabel(dm);
        Imgdm6.setSize(60,90);
        Imgdm6.setLocation(510, 10);
        JLabel Imgdm7 = new JLabel(dm);
        Imgdm7.setSize(60,90);
        Imgdm7.setLocation(572, 10);
        JLabel Imgdm8 = new JLabel(dm);
        Imgdm8.setSize(60,90);
        Imgdm8.setLocation(634, 10);
        JLabel Imgdm9 = new JLabel(dm);
        Imgdm9.setSize(60,90);
        Imgdm9.setLocation(696, 10);
        JLabel Imgdm10 = new JLabel(dm);
        Imgdm10.setSize(60,90);
        Imgdm10.setLocation(758, 10);
        JLabel Imgdm11 = new JLabel(dm);
        Imgdm11.setSize(60,90);
        Imgdm11.setLocation(820, 10);
        JLabel Imgdm12 = new JLabel(dm);
        Imgdm12.setSize(60,90);
        Imgdm12.setLocation(882, 10);
        JLabel Imgdm13 = new JLabel(dm);
        Imgdm13.setSize(60,90);
        Imgdm13.setLocation(944, 10);
                
        PanelBei = new JPanel();

        PanelBei.add(Imgdm1);
        PanelBei.add(Imgdm2);
        PanelBei.add(Imgdm3);
        PanelBei.add(Imgdm4);
        PanelBei.add(Imgdm5);
        PanelBei.add(Imgdm6);
        PanelBei.add(Imgdm7);
        PanelBei.add(Imgdm8);
        PanelBei.add(Imgdm9);
        PanelBei.add(Imgdm10);
        PanelBei.add(Imgdm11);
        PanelBei.add(Imgdm12);
        PanelBei.add(Imgdm13);
        
        JLabel Imgzb1 = new JLabel(zbs);
        JLabel Imgzb2 = new JLabel(zbs);
        JLabel Imgzb3 = new JLabel(zbs);
        JLabel Imgzb4 = new JLabel(zbs);
        JLabel Imgzb5 = new JLabel(zbs);
        JLabel Imgzb6 = new JLabel(zbs);
        JLabel Imgzb7 = new JLabel(zbs);
        JLabel Imgzb8 = new JLabel(zbs);
        JLabel Imgzb9 = new JLabel(zbs);
        JLabel Imgzb10 = new JLabel(zbs);
        JLabel Imgzb11 = new JLabel(zbs);
        JLabel Imgzb12 = new JLabel(zbs);
        JLabel Imgzb13 = new JLabel(zbs);

        JLabel Imgyb1 = new JLabel(ybs);
        JLabel Imgyb2 = new JLabel(ybs);
        JLabel Imgyb3 = new JLabel(ybs);
        JLabel Imgyb4 = new JLabel(ybs);
        JLabel Imgyb5 = new JLabel(ybs);
        JLabel Imgyb6 = new JLabel(ybs);
        JLabel Imgyb7 = new JLabel(ybs);
        JLabel Imgyb8 = new JLabel(ybs);
        JLabel Imgyb9 = new JLabel(ybs);
        JLabel Imgyb10 = new JLabel(ybs);
        JLabel Imgyb11 = new JLabel(ybs);
        JLabel Imgyb12 = new JLabel(ybs);
        JLabel Imgyb13 = new JLabel(ybs);
                
        PanelXi = new JPanel();
        PanelXi.setLayout(new GridLayout(13,1,2,2));
        PanelXi.add(Imgzb1);
        PanelXi.add(Imgzb2);
        PanelXi.add(Imgzb3);
        PanelXi.add(Imgzb4);
        PanelXi.add(Imgzb5);
        PanelXi.add(Imgzb6);
        PanelXi.add(Imgzb7);
        PanelXi.add(Imgzb8);
        PanelXi.add(Imgzb9);
        PanelXi.add(Imgzb10);
        PanelXi.add(Imgzb11);
        PanelXi.add(Imgzb12);
        PanelXi.add(Imgzb13);
       
        PanelDong = new JPanel();
        PanelDong.setLayout(new GridLayout(13,1,2,2));
        PanelDong.add(Imgyb1);
        PanelDong.add(Imgyb2);
        PanelDong.add(Imgyb3);
        PanelDong.add(Imgyb4);
        PanelDong.add(Imgyb5);
        PanelDong.add(Imgyb6);
        PanelDong.add(Imgyb7);
        PanelDong.add(Imgyb8);
        PanelDong.add(Imgyb9);
        PanelDong.add(Imgyb10);
        PanelDong.add(Imgyb11);
        PanelDong.add(Imgyb12);
        PanelDong.add(Imgyb13);
                
        cp.add(PanelDong, BorderLayout.EAST);		
	cp.add(PanelXi, BorderLayout.WEST);
	
	cp.add(south, BorderLayout.SOUTH);
        cp.add(PanelBei, BorderLayout.NORTH);
        
        Dimension up_down_discard_area = new Dimension(380, 160) ;
        Dimension left_right_discard_area = new Dimension(160, 380) ;
        Color table_color = new Color(27, 142, 9) ;
        center = new JPanel() ;
        center.setLayout(null) ;
        south_discard = new JPanel(new FlowLayout(FlowLayout.LEFT)) ;
        south_discard.setSize(up_down_discard_area) ; //设置JPanel的大小
        south_discard.setBackground(table_color);
        east_discard = new JPanel(new FlowLayout(FlowLayout.LEFT)) ;
        //east_discard.setLayout(new GridBagLayout());
        east_discard.setSize(left_right_discard_area) ;
        east_discard.setBackground(table_color);
        west_discard = new JPanel(new FlowLayout(FlowLayout.LEFT)) ;
        west_discard.setSize(left_right_discard_area) ;
        west_discard.setBackground(table_color);
        //west_discard.setLayout(new FlowLayout(FlowLayout.TRAILING));
        north_discard = new JPanel(new FlowLayout(FlowLayout.RIGHT)) ;
        north_discard.setSize(up_down_discard_area);
        north_discard.setBackground(table_color);
        south_table.setBackground(table_color);
                
        center.add(south_discard) ;
        center.add(east_discard) ;
        center.add(west_discard) ;
        center.add(north_discard) ;
        center.add(south_table) ;
        center.setBackground(table_color);
	cp.add(center);
                
	//將元件加入中間區域,
	//相當於cp.add(new JButton("CENTER", BorderLayout.CENTER));
        //setExtendedState(Frame.MAXIMIZED_BOTH) ;    
        setSize(1280, 720) ;           
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
        south_discard.setLocation(center.getWidth() / 2 - 195, center.getHeight() - 160);
        north_discard.setLocation(center.getWidth() / 2 - 195, 0);
        west_discard.setLocation(210, center.getHeight() / 2 - 195);
        east_discard.setLocation(820, center.getHeight() / 2 - 195);
        south_table.setSize(420, 50);
        south_table.setLocation(0, center.getHeight() - 50);
        System.out.println(center.getWidth());
        System.out.println(center.getHeight());
	}
    
        public int checkPon(JPanel discard) {
            int change_order = 0 ;
            ArrayList<Integer> temp = new ArrayList<>() ;
            CardButton source = (CardButton) discard.getComponent(discard.getComponentCount() - 1) ;
            for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                temp.add(((CardButton) group1.getComponent(i)).code) ;
            }
            if(temp.remove(Integer.valueOf(source.code)) && temp.contains(source.code)) {
                temp.add(source.code) ;
                Collections.sort(temp) ;
                //CardButton confirm = new CardButton(200, "碰") ;
                //confirm.addActionListener(this) ;
                //center.add(confirm) ;
                //confirm.setSize(80, 80);
                //confirm.setLocation(center.getWidth() / 2 - 40, center.getHeight() / 2 - 40);
                //confirm.setVisible(true) ;
                //UI_Learning.this.validate();
                //UI_Learning.this.repaint() ;
                //UI_Learning.this.setVisible(true);
                //try{
                //    Thread.sleep(3000);
                //}
                //catch(InterruptedException ex){
                //    ex.printStackTrace() ;
                //}
                //center.remove(confirm);
                //UI_Learning.this.validate();
                //UI_Learning.this.repaint() ;
                //UI_Learning.this.setVisible(true);
                if(JOptionPane.showConfirmDialog(center,"碰","碰",
                                  JOptionPane.YES_NO_OPTION) == 0) {
                    change_order = 1 ;
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code)) {
                            //card1 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code)) {
                            //card2 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == source.code) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        }     
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == source.code) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == source.code) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }
                    discard.remove(discard.getComponentCount() - 1) ;
                    jump = true ;
                    south_dealcard.add((CardButton)group1.getComponent(group1.getComponentCount() - 1)) ;
                    UI_Learning.this.validate();
                    UI_Learning.this.repaint() ;
                    UI_Learning.this.setVisible(true);
                    confirm_Pon = false ;
                    clickable = true ;
                }
            }
            return change_order ;
        }
    
        public int checkChi(JPanel discard) {
            ArrayList<Integer> temp = new ArrayList<>() ;
            CardButton source = (CardButton) discard.getComponent(discard.getComponentCount() - 1) ;
            int change_order = 0 ;
            for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                temp.add(((CardButton) group1.getComponent(i)).code) ;
            }
            int card1 = -1 ;
            int card2 = -1 ;
            if((temp.contains(source.code + 1) && temp.contains(source.code + 2))) {
                CardButton confirm = new CardButton(300, "吃") ;
                confirm.addActionListener(this) ;
                center.add(confirm) ;
                confirm.setSize(80, 80);
                confirm.setLocation(center.getWidth() / 2 - 40, center.getHeight() / 2 - 40);
                confirm.setVisible(true) ;
                UI_Learning.this.validate();
                UI_Learning.this.repaint() ;
                UI_Learning.this.setVisible(true);
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace() ;
                }
                center.remove(confirm);
                UI_Learning.this.validate();
                UI_Learning.this.repaint() ;
                UI_Learning.this.setVisible(true);
                if(confirm_Chi) {
                    change_order = 1 ;
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code + 1)) {
                            card1 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code + 2)) {
                            card2 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == source.code) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        }     
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == card1 && card1 != -1) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == card2 && card2 != -1) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }
                    discard.remove(discard.getComponentCount() - 1) ;
                    jump = true ;
                    south_dealcard.add((CardButton)group1.getComponent(group1.getComponentCount() - 1)) ;
                    UI_Learning.this.validate();
                    UI_Learning.this.repaint() ;
                    UI_Learning.this.setVisible(true);
                    confirm_Chi = false ;
                }    
            }
              
            else if(temp.contains(source.code + 1) && temp.contains(source.code - 1)) {
                CardButton confirm = new CardButton(300, "吃") ;
                confirm.addActionListener(this) ;
                center.add(confirm) ;
                confirm.setSize(80, 80);
                confirm.setLocation(center.getWidth() / 2 - 40, center.getHeight() / 2 - 40);
                confirm.setVisible(true) ;
                UI_Learning.this.validate();
                UI_Learning.this.repaint() ;
                UI_Learning.this.setVisible(true);
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace() ;
                }
                center.remove(confirm);
                UI_Learning.this.validate();
                UI_Learning.this.repaint() ;
                UI_Learning.this.setVisible(true);
                if(confirm_Chi) {
                    change_order = 1 ;
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code + 1)) {
                            card1 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code - 1)) {
                            card2 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == source.code) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        }     
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == card1 && card1 != -1) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == card2 && card2 != -1) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }
                    discard.remove(discard.getComponentCount() - 1) ;
                    jump = true ;
                    south_dealcard.add((CardButton)group1.getComponent(group1.getComponentCount() - 1)) ;
                    UI_Learning.this.validate();
                    UI_Learning.this.repaint() ;
                    UI_Learning.this.setVisible(true);
                }
            }
                    
            else if((temp.contains(source.code - 1)) && temp.contains(source.code - 2)) {
                CardButton confirm = new CardButton(300, "吃") ;
                confirm.addActionListener(this) ;
                center.add(confirm) ;
                confirm.setSize(80, 80);
                confirm.setLocation(center.getWidth() / 2 - 40, center.getHeight() / 2 - 40);
                confirm.setVisible(true) ;
                UI_Learning.this.validate();
                UI_Learning.this.repaint() ;
                UI_Learning.this.setVisible(true);
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace() ;
                }
                center.remove(confirm);
                UI_Learning.this.validate();
                UI_Learning.this.repaint() ;
                UI_Learning.this.setVisible(true);
                if(confirm_Chi) {
                    change_order = 1 ;
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code - 1)) {
                            card1 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }
                    for(int i = 0 ; i < group1.getComponentCount() ; i++) {
                        if((((CardButton) group1.getComponent(i)).code) == (source.code -2)) {
                            card2 = ((CardButton) group1.getComponent(i)).code ;
                            group1.remove(i) ;
                            break ;
                        }
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == source.code) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        }     
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == card1 && card1 != -1) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }

                    for(int i = 0 ; i < table.size() ; i++) {
                        if(table.get(i).code == card2 && card2 != -1) {
                            south_table.add(table.get(i)) ;
                            table.remove(i) ;
                            break ;
                        } 
                    }
                    discard.remove(discard.getComponentCount() - 1) ;              
                    jump = true ;
                    south_dealcard.add((CardButton)group1.getComponent(group1.getComponentCount() - 1)) ;
                    UI_Learning.this.validate();
                    UI_Learning.this.repaint() ;
                    UI_Learning.this.setVisible(true);
                }     
            }
            UI_Learning.this.validate();
            clickable = true ;
                // 執行吃牌指令
            return change_order ;
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
                CardButton source ;
                for(int i = 0 ; i < UI_Learning.this.group1.getComponentCount() ; i++){
                    source = (CardButton)UI_Learning.this.group1.getComponent(i) ;
                    tempHu.add(source.code) ; // 把手牌放入暫存
                }
                source = (CardButton)UI_Learning.this.south_dealcard.getComponent(0) ;
                tempHu.add(source.code) ; // 把手牌放入暫存
                return false ; 
            }
        }
    
        public boolean checkHu() { // 檢查眼 + 面子
            int tempcard ;
            CardButton source ;
            for(int i = 0 ; i < UI_Learning.this.group1.getComponentCount() ; i++){
                source = (CardButton) UI_Learning.this.group1.getComponent(i) ;
                tempHu.add(source.code) ; // 把手牌放入暫存
            }
            source = (CardButton) UI_Learning.this.south_dealcard.getComponent(0) ;
            tempHu.add(source.code) ; // 把手牌放入暫存
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
    
        public void DealCard(Player p1, Player p2, Player p3) {  // 發牌至其他三家
            System.out.println("擲出來的骰子是：" + Die + " 和 "  + Die2) ;

            for(int i = 0 ; i < 13 ; i++) {
                p1.PlayerHands.add(card.get(index)) ;
                card.remove(index) ;
                p2.PlayerHands.add(card.get(index)) ;
                card.remove(index) ;
                p3.PlayerHands.add(card.get(index)) ;
                card.remove(index) ;
            }
        }
        
        public void MoPai (AI_Player p) {  // 電腦摸牌
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
        
        public void MoPai () {  // 玩家摸牌
            try{
                card.get(index).setPreferredSize(new Dimension(66, 98));
                south_dealcard.add(card.get(index)) ; // 摸牌
                card.remove(index) ;
            }
            catch (IndexOutOfBoundsException ex){
                index = 0 ;
                card.get(index).setPreferredSize(new Dimension(66, 98));
                south_dealcard.add(card.get(index)) ; // 摸牌
                card.remove(index) ;
            }
            HuPai = checkHu() ;
            UI_Learning.this.validate(); // 刷新 UI 介面
            clickable = true ;
        }

    public void actionPerformed(ActionEvent event){ // 觸發事件
        CardButton source = (CardButton)event.getSource() ; // 獲取觸發事件來源
        if(!clickable){
            UI_Learning.this.validate(); // 刷新 UI 介面
        }
        else if(source.code == 200) {
            confirm_Pon = true ;
        }
        else{
            Dimension dim = new Dimension(33, 49) ; // 設定牌的大小
                   
            for(int i = 0; i < table.size(); i++){
                if(source.code == table.get(i).code){  
                    table.get(i).setPreferredSize(dim);
                    UI_Learning.this.south_discard.add(table.get(i));
                    table.remove(i) ;
                    break;
                }
            }
        
            UI_Learning.this.group1.remove(source); // 該張牌被點擊，從手牌中移除
            
            System.out.println("這次也給我注意這裡: " + UI_Learning.this.south_dealcard.getComponentCount());    
            UI_Learning.this.group1.add((CardButton) UI_Learning.this.south_dealcard.getComponent(0)) ; // 把該回合摸到的牌加入手牌中
 
            System.out.println("這次給我注意這裡: " + UI_Learning.this.group1.getComponentCount());
            int v1 = 1 ; // 是否該捨棄最右邊那張
            if(UI_Learning.this.group1.getComponentCount() % 3 != 1)
                v1 = 2 ;
            hand.clear(); // 清空手牌暫存
            for(int i = 0 ; i <= UI_Learning.this.group1.getComponentCount() - v1 ; i++)
                hand.add((CardButton) UI_Learning.this.group1.getComponent(i)) ; // 從玩家手牌區獲得目前玩家手牌，放進手牌暫存區
            Collections.sort(hand); // 排列手牌
            System.out.println("給我注意啊啊啊!!!: " + hand.size());
            int remain = hand.size() ;
            UI_Learning.this.group1.removeAll(); // 清空玩家手牌區
            for(int i = 1 ; i <= remain ; i++){
                group1.add(hand.get(0)) ; // 從手牌暫存區加入手牌
                hand.remove(0) ;
            }
            hand.clear();
            System.out.println("小牌剩餘數量: " + table.size());

            //System.out.println(card.size()); // 追蹤牌堆數量
            clickable = false ;
            //Thread.interrupted() ;
            jump = false ;
            UI_Learning.this.validate(); // 刷新 UI 介面
            }
    }
    
    public void checkOrder(int change) {
        if(change > 0)
            order = change ;
        else{
            order++ ;
            if(order > 4)
                order = 1 ;
        }
    }

    public static void main(String args[]) {
	UI_Learning UI = new UI_Learning("天才麻將少年少女");
        System.out.println(UI.card.size()); // 原始牌堆數量       
        AI_Player AI1 = new AI_Player("right");
        AI_Player AI2 = new AI_Player("front");
        AI_Player AI3 = new AI_Player("left");
        Dimension dim = new Dimension(33, 49) ;
        
        UI.DealCard(AI1, AI2, AI3);
        Collections.sort(AI1.PlayerHands);
        Collections.sort(AI2.PlayerHands);
        Collections.sort(AI3.PlayerHands);
        
        while(true) {
            if(UI.order == 1) {
                if(UI.card.size() <= 14) { // 確認流局
                    System.out.println("流局") ;
                    UI.clickable = false ;
                    UI.PanelDong.removeAll() ;
                    UI.PanelBei.removeAll() ;
                    UI.PanelXi.removeAll() ;
                    for(int i = AI1.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI1.table.size() ; j++) {
                            if(AI1.PlayerHands.get(i).code == AI1.table.get(j).code) {
                                UI.PanelDong.add(AI1.table.get(j)) ;
                                AI1.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = AI2.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI2.table.size() ; j++) {
                            if(AI2.PlayerHands.get(i).code == AI2.table.get(j).code) {
                                UI.PanelBei.add(AI2.table.get(j)) ;
                                AI2.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = 0 ; i < AI3.PlayerHands.size() ; i++) {
                        for(int j = 0 ; j < AI3.table.size() ; j++) {
                            if(AI3.PlayerHands.get(i).code == AI3.table.get(j).code) {
                                UI.PanelXi.add(AI3.table.get(j)) ;
                                AI3.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    UI.validate() ;
                    break ;
                }
                if(!UI.jump) 
                    UI.MoPai() ;

                if(UI.HuPai) {
                    System.out.println("你胡牌了");
                    UI.clickable = false ;
                    break ;
                }
                    
                try{
                    Thread.sleep(5000) ;
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }

                if(UI.south_dealcard.getComponentCount() > 0) {
                    CardButton source = (CardButton)UI.south_dealcard.getComponent(0) ;
                    for(int i = 0 ; i < UI.table.size() ; i++) {
                        if(source.code == UI.table.get(i).code){
                            UI.table.get(i).setPreferredSize(dim);
                            UI.south_discard.add(UI.table.get(i));
                            UI.table.remove(i) ;
                            UI.south_dealcard.remove(source);
                            UI.validate(); // 刷新 UI 介面
                            try{
                                Thread.sleep(1000) ;
                            }
                            catch(InterruptedException e){
                                e.printStackTrace();
                            }
                        break;
                        }   
                    }
                    UI.jump = false ;
                }
                UI.checkOrder(0) ;
            }
            
            else if(UI.order == 2) {
                if(UI.card.size() <= 14) {
                    System.out.println("流局") ;
                    UI.clickable = false ;
                    UI.PanelDong.removeAll() ;
                    UI.PanelBei.removeAll() ;
                    UI.PanelXi.removeAll() ;
                    for(int i = AI1.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI1.table.size() ; j++) {
                            if(AI1.PlayerHands.get(i).code == AI1.table.get(j).code) {
                                UI.PanelDong.add(AI1.table.get(j)) ;
                                AI1.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = AI2.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI2.table.size() ; j++) {
                            if(AI2.PlayerHands.get(i).code == AI2.table.get(j).code) {
                                UI.PanelBei.add(AI2.table.get(j)) ;
                                AI2.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = 0 ; i < AI3.PlayerHands.size() ; i++) {
                        for(int j = 0 ; j < AI3.table.size() ; j++) {
                            if(AI3.PlayerHands.get(i).code == AI3.table.get(j).code) {
                                UI.PanelXi.add(AI3.table.get(j)) ;
                                AI3.table.remove(j) ;
                                break ;
                            }
                        }
                    }                
                    UI.validate();
                    break ;
                }
                UI.MoPai(AI1) ;
                Collections.sort(AI1.PlayerHands);
                if(UI.HuPai) {
                    System.out.println("AI1胡牌了");
                    for(int i = AI1.PlayerHands.size() ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI1.table.size() ; j++) {
                            if(AI1.PlayerHands.get(i).code == AI1.table.get(j).code) {
                                UI.PanelDong.add(AI1.table.get(j)) ;
                                AI1.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    UI.validate();
                    break ;
                }
                System.out.print("AI1手牌: ") ;
                for(int i = 0 ; i < AI1.PlayerHands.size() ; i++)
                    System.out.print(AI1.PlayerHands.get(i).code + "  ") ;
                System.out.println();  

                AI1.AIChuPai(UI.east_discard) ;
                UI.validate();
                UI.checkOrder(UI.checkPon(UI.east_discard)) ;
            }
                
            else if(UI.order == 3) {
                if(UI.card.size() <= 14) {
                    System.out.println("流局") ;
                    UI.clickable = false ;
                    UI.PanelDong.removeAll() ;
                    UI.PanelBei.removeAll() ;
                    UI.PanelXi.removeAll() ;
                    for(int i = AI1.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI1.table.size() ; j++) {
                            if(AI1.PlayerHands.get(i).code == AI1.table.get(j).code) {
                                UI.PanelDong.add(AI1.table.get(j)) ;
                                AI1.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = AI2.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI2.table.size() ; j++) {
                            if(AI2.PlayerHands.get(i).code == AI2.table.get(j).code) {
                                UI.PanelBei.add(AI2.table.get(j)) ;
                                AI2.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = 0 ; i < AI3.PlayerHands.size() ; i++) {
                        for(int j = 0 ; j < AI3.table.size() ; j++) {
                            if(AI3.PlayerHands.get(i).code == AI3.table.get(j).code) {
                                UI.PanelXi.add(AI3.table.get(j)) ;
                                AI3.table.remove(j) ;
                                break ;
                            }
                        }
                    }                
                    UI.validate();
                    break ;
                }
                UI.MoPai(AI2) ;
                Collections.sort(AI2.PlayerHands);
                if(UI.HuPai) {
                    System.out.println("AI2胡牌了");
                    for(int i = AI2.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI2.table.size() ; j++) {
                            if(AI2.PlayerHands.get(i).code == AI2.table.get(j).code) {
                                UI.PanelBei.add(AI2.table.get(j)) ;
                                AI2.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    UI.validate();
                    break ;
                }

                System.out.print("AI2手牌: ") ;
                for(int i = 0 ; i < AI2.PlayerHands.size() ; i++)
                    System.out.print(AI2.PlayerHands.get(i).code + "  ") ;
                System.out.println();
                try{
                    Thread.sleep(2000) ;
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }

                AI2.AIChuPai(UI.north_discard) ;
                UI.validate();
                UI.checkOrder(UI.checkPon(UI.north_discard)) ;
            }
                
            else if(UI.order == 4) {
                if(UI.card.size() <= 14) {
                    System.out.println("流局") ;
                    UI.clickable = false ;
                    UI.PanelDong.removeAll() ;
                    UI.PanelBei.removeAll() ;
                    UI.PanelXi.removeAll() ;
                    for(int i = AI1.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI1.table.size() ; j++) {
                            if(AI1.PlayerHands.get(i).code == AI1.table.get(j).code) {
                                UI.PanelDong.add(AI1.table.get(j)) ;
                                AI1.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = AI2.PlayerHands.size() - 1 ; i >= 0 ; i--) {
                        for(int j = 0 ; j < AI2.table.size() ; j++) {
                            if(AI2.PlayerHands.get(i).code == AI2.table.get(j).code) {
                                UI.PanelBei.add(AI2.table.get(j)) ;
                                AI2.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    for(int i = 0 ; i < AI3.PlayerHands.size() ; i++) {
                        for(int j = 0 ; j < AI3.table.size() ; j++) {
                            if(AI3.PlayerHands.get(i).code == AI3.table.get(j).code) {
                                UI.PanelXi.add(AI3.table.get(j)) ;
                                AI3.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    UI.validate();
                    break ;
                }
                UI.MoPai(AI3) ;
                Collections.sort(AI3.PlayerHands);
                if(UI.HuPai) {
                    System.out.println("AI3胡牌了");
                    for(int i = 0 ; i < AI3.PlayerHands.size() ; i++) {
                        for(int j = 0 ; j < AI3.table.size() ; j++) {
                            if(AI3.PlayerHands.get(i).code == AI3.table.get(j).code) {
                                UI.PanelXi.add(AI3.table.get(j)) ;
                                AI3.table.remove(j) ;
                                break ;
                            }
                        }
                    }
                    UI.validate();
                    break ;
                }
                System.out.print("AI3手牌: ") ;
                for(int i = 0 ; i < AI3.PlayerHands.size() ; i++)
                    System.out.print(AI3.PlayerHands.get(i).code + "  ") ;
                System.out.println();
                try{
                    Thread.sleep(2000) ;
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }

                AI3.AIChuPai(UI.west_discard) ;
                UI.validate() ;
                UI.checkOrder(UI.checkPon(UI.west_discard)) ;
            }         
        }
    }
}
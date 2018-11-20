/*
 * Java
 */
package objectserver;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Julia
 */
public class Client implements ActionListener{
    private JFrame frame = new JFrame("Quiz Nu");
    private JLabel condition = new JLabel("");
    private JLabel question = new JLabel("");
    private JButton butA = new JButton("");
    private JButton butB = new JButton("");
    private JButton butC = new JButton("");
    private JButton butD = new JButton("");
    private JButton butColor = new JButton();
    private JButton next = new JButton("next");
    private JPanel panelBtu = new JPanel();
    private JPanel panelAll = new JPanel();
    private JPanel panelExtra = new JPanel();
    private Socket socket;  
    private int butNum=1;
    private int questionNum=0;
    private String result;
    private String[] opponentInfo=null;
    private int butCount=0;
    private int poang=0;
    private int poang1;
    private int poang2;
    
    ObjectInputStream in;
    ObjectOutputStream out;
    int PORT = 12345;
    JButton[] buttons = new JButton[]{butA, butB, butC, butD};
    Object input;
    Object inputList;
    
    public Client(String adress) throws IOException{
        socket = new Socket(adress, PORT);
        panelBtu.setLayout(new GridLayout(2,2));
        panelAll.setLayout(new BorderLayout());
        panelExtra.setLayout(new BorderLayout());
        panelBtu.add(butA);
        panelBtu.add(butB);
        panelBtu.add(butC);
        panelBtu.add(butD);    
        butA.addActionListener(this);
        butB.addActionListener(this);
        butC.addActionListener(this);
        butD.addActionListener(this);        
        next.addActionListener(nextQ ->{ 
            butA.setBackground(null);
            butB.setBackground(null);
            butC.setBackground(null);
            butD.setBackground(null);     
            setQuestion(inputList, questionNum);
            butCount++;
            if(butCount == 2){
                try {
                    poang1 = poang;
                    poang=0;
                    out.writeObject("change");
                    condition.setText("change to opponent...Rond1 poang: "+ poang1);
                    panelAll.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(butCount == 4){
                try {
                    poang2 = poang;
                    out.writeObject("end" + "Rond1 poang: " + poang1 +
                                       "...Rond2 poang: " + poang2 +
                                       "...Total poang: " + (poang1+poang2));
                    condition.setText("Rond1 poang: " + poang1 +
                                       "...Rond2 poang: " + poang2 +
                                       "...Total poang: " + (poang1+poang2));
                    panelAll.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            butNum=1;
        });        

        condition.setText("waiting for opponent...");
        panelAll.add(question, BorderLayout.NORTH);
        panelAll.add(panelBtu, BorderLayout.CENTER);
        panelAll.add(next, BorderLayout.SOUTH);
        panelExtra.add(condition, BorderLayout.NORTH);
        panelExtra.add(panelAll, BorderLayout.CENTER);
        panelAll.setVisible(false);
        frame.add(panelExtra);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(240, 160);
        frame.setLocation(200, 200);
        frame.setVisible(true);        
                
    }
    public void play() throws IOException{
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());        
        try{
            while(true){
                if((input=in.readObject())!=null){
                    if(input instanceof List){
                        condition.setText("Now start...");
                        condition.repaint();
                        inputList = input;
                        setQuestion(inputList, questionNum++);
                    }
                    else if(((String) input).equals("wait")){
                        condition.setText("opponent answer now, wait!!!");
                        condition.repaint();
                    }
                    else if(((String) input).startsWith("begin")){  //p2rond2
                        condition.setText("Now start...");
                        condition.repaint();
                        setQuestion(inputList, ++questionNum);
                        questionNum++;
                    }
                    else if(((String) input).startsWith("p1rond2")){    //p1rond2
                        condition.setText("Now start...");
                        condition.repaint();
                        setQuestion(inputList, ++questionNum);
                        questionNum++;
                    }
                    else if(((String) input).startsWith("p2end")){
                        opponentInfo = ((String) input).split(",");   
                        condition.setText(opponentInfo[1]+"..."+opponentInfo[2]);
                        condition.repaint();
                    }
                }
            }            
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
    public boolean butControll(int butNum){
        if(butNum == 1){
            butNum=2;
            return true;
        }
        return false;
    }
    public JButton[] blandaBut(JButton[] buts){
        JButton temp = new JButton("");
        Random rani = new Random();
        for(int i=0; i<buts.length; i++){
            int raninum = rani.nextInt(buts.length);
            if(i==raninum)
                continue;
            temp.setText(buts[i].getText());
            buts[i].setText(buts[raninum].getText());
            buts[raninum].setText(temp.getText());
        }
        return buts;              
    }
    public void setQuestion(Object obj, int index){
        result = ((List<QandA>) obj).get(index).getResult();
        question.setText(((List<QandA>) obj).get(index).getFraga());
        butA.setText(((List<QandA>) obj).get(index).getValjningar()[0]);
        butB.setText(((List<QandA>) obj).get(index).getValjningar()[1]);
        butC.setText(((List<QandA>) obj).get(index).getValjningar()[2]);
        butD.setText(((List<QandA>) obj).get(index).getValjningar()[3]);
        buttons = blandaBut(buttons);
        question.repaint();
        butA.repaint();
        butB.repaint();
        butC.repaint();
        butD.repaint();
        panelAll.setVisible(true);
        panelAll.repaint();   
    }
    public static void main(String[] args) throws IOException{
            String serverAddress = (args.length == 0)? "localhost" : args[1];
            Client client = new Client(serverAddress);  
            client.play(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton answer = (JButton)e.getSource();
        if(butControll(butNum)){
            if(answer.getText().equals(result)){
                poang++;
            }
            for(int i=0; i<4; i++){
                if(buttons[i].getText().equals(result)){
                    buttons[i].setBackground(Color.GREEN);
                }
                if(!answer.getText().equals(result)){
                    answer.setBackground(Color.red);
                }
            }
            butNum=2;
        }        
    }
}

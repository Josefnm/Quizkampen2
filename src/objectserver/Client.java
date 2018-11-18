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
    private int butCount=0;
    
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
                    out.writeObject("change");
                    question.setText("change to opponent...");
                    panelAll.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(butCount == 4){
                try {
                    out.writeObject("end");
                    question.setText("your poang...");
                    panelAll.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            butNum=1;
        });        

        question.setText("waiting for p2...");
        panelAll.add(panelBtu, BorderLayout.CENTER);
        panelAll.add(next, BorderLayout.SOUTH);
        panelExtra.add(question, BorderLayout.NORTH);
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
                        inputList = input;
                        setQuestion(inputList, questionNum++);
                    }
                    else if(((String) input).equals("wait")){
                        question.setText("p1 answer now, wait!!!");
                        question.repaint();
                    }
                    else if(((String) input).startsWith("begin")){
                        setQuestion(inputList, ++questionNum);
                        questionNum++;
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
    public void setQuestion(Object obj, int index){
        result = ((List<QandA>) obj).get(index).getResult();
        question.setText(((List<QandA>) obj).get(index).getFraga());
        butA.setText(((List<QandA>) obj).get(index).getValjningar()[0]);
        butB.setText(((List<QandA>) obj).get(index).getValjningar()[1]);
        butC.setText(((List<QandA>) obj).get(index).getValjningar()[2]);
        butD.setText(((List<QandA>) obj).get(index).getValjningar()[3]);
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

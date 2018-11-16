package quizkampen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class Klient implements ActionListener{
    private JFrame frame = new JFrame("Quiz Nu");
    private JLabel question = new JLabel("");
    private JButton butA = new JButton("");
    private JButton butB = new JButton("");
    private JButton butC = new JButton("");
    private JButton butD = new JButton("");
//    private JButton butColor = new JButton();
    private JButton next = new JButton("next");
    private JPanel panelBtu = new JPanel();
    private JPanel panelAll = new JPanel();
    private static int PORT = 8901;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    ArrayList<JButton> buttons = new ArrayList<>();
    private int num =0;
    private int nextNum =100;

    public Klient(String serverAddress) throws IOException{
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        panelBtu.setLayout(new GridLayout(2,2));
        panelAll.setLayout(new BorderLayout());

        panelBtu.add(butA);
        panelBtu.add(butB);
        panelBtu.add(butC);
        panelBtu.add(butD);
        next.addActionListener(nextQ ->{   //Lamda
            out.println("next , " + nextNum);
            nextNum = nextNum+1;
            butA.setBackground(null);
            butB.setBackground(null);
            butC.setBackground(null);
            butD.setBackground(null);
        });
        butA.addActionListener(this);
        butB.addActionListener(this);
        butC.addActionListener(this);
        butD.addActionListener(this);
        panelAll.add(question, BorderLayout.NORTH);
        panelAll.add(panelBtu, BorderLayout.CENTER);
//        panelAll.add(butColor, BorderLayout.WEST);
        panelAll.add(next, BorderLayout.SOUTH);
        frame.add(panelAll);
        frame.pack();
        
    }
    public void play() throws Exception{
        String response;
        String[] choice;
        try{
            while((response = in.readLine()) != null){
                if((response.startsWith("yes"))){
                    System.out.println("yes");
                    System.out.println(response);
                    choice = response.split(",");
                    for(int i=0; i<4; i++){
                        if(buttons.get(i).getText().equals(choice[1])){
                            buttons.get(i).setBackground(Color.green);
                            buttons.get(i).repaint();
                        }
                    }
                }
                else if((response.startsWith("no"))){
                    System.out.println("no");
                    System.out.println(response);
                    choice = response.split(",");
                    for(int i=0; i<4; i++){
                        if(buttons.get(i).getText().equals(choice[1])){
                            buttons.get(i).setBackground(Color.green);
                            buttons.get(i).repaint();
                        }
                        if(buttons.get(i).getText().equals(choice[2])){
                            buttons.get(i).setBackground(Color.red);
                            buttons.get(i).repaint();
                        }
                    }                    
                }
                else{
                    choice = response.split(",");
                    question.setText(choice[0]);
                    int i = 1;
                    for(JButton button : buttons){
                        button.setText(choice[i]);
                        button.repaint();
                        i++;
                    }
                }
            }
        }
        finally{
            socket.close();
        }
    } 
 
    public static void main(String[] args) throws Exception{
        while(true){
            String serverAddress = (args.length == 0)? "localhost" : args[1];
            Klient client = new Klient(serverAddress);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setSize(240, 160);
            client.frame.setLocation(200, 200);
            client.frame.setVisible(true);
            client.play();
        }
    }    

    @Override
    public void actionPerformed(ActionEvent e) {//
        JButton answer = (JButton)e.getSource();
        num = num + 1;
        out.println(answer.getText()+","+num);
        if(num==2) num=0;
    }
    
}

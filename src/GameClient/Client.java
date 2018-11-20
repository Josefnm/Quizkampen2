package GameClient;

import GameServer.Question;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Client {

    int portNr = 12345;
    String hostIPAddress = "127.0.0.1";
    ObjectInputStream input;
    ObjectOutputStream output;
    
//    private JFrame frame = new JFrame("Quiz Nu");
//    private JLabel question = new JLabel("");
//    private JButton butA = new JButton("");
//    private JButton butB = new JButton("");
//    private JButton butC = new JButton("");
//    private JButton butD = new JButton("");
////    private JButton butColor = new JButton();
//    private JButton next = new JButton("next");
//    private JPanel panelBtu = new JPanel();
//    private JPanel panelAll = new JPanel();
//    private static int PORT = 8901;
//    private Socket socket;
////    private PrintWriter out;
////    private BufferedReader in;
//    private ObjectOutputStream out;
//    private ObjectInputStream in;
//    JButton[] buttons = new JButton[]{butA, butB, butC, butD};
//    private int num =0;
//    private int nextNum =100;

    public Client(String adress) throws IOException{
        
        
        socket = new Socket(adress, PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        Object input;
//        List<Object> input;
        try{
//            while(true){
                if((input=in.readObject())!=null){
                    System.out.println("yes");
                    System.out.println(((List<Question>) input).get(0).getFraga());
                    System.out.println(((List<Question>) input).get(1).getFraga());
//                        System.out.println(((Question[]) input)[0].getFraga());
//                        System.out.println(((Question[]) input)[1].getFraga());
//                    
//                    if(input instanceof GameServer.Question){
//                        System.out.println(((GameServer.Question)input).getFraga());
//                        System.out.println(((GameServer.Question)input).getAmne());
//                        System.out.println(((GameServer.Question)input).getValjningar(0));
//                        System.out.println(((GameServer.Question)input).getValjningar(1));
//                        System.out.println(((GameServer.Question)input).getValjningar(2));
//                        System.out.println(((GameServer.Question)input).getValjningar(3));
//                        System.out.println(((GameServer.Question)input).getSvar());
//                        System.out.println(((GameServer.Question)input).getResult());
//                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }        
        
        
//        socket = new Socket(serverAddress, PORT);
//        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        out = new PrintWriter(socket.getOutputStream(), true);
//        panelBtu.setLayout(new GridLayout(2,2));
//        panelAll.setLayout(new BorderLayout());
//        panelBtu.add(butA);
//        panelBtu.add(butB);
//        panelBtu.add(butC);
//        panelBtu.add(butD);
//        next.addActionListener(nextQ ->{   //Lamda
//            out.println("next , " + nextNum);
//            nextNum = nextNum+1;
//            butA.setBackground(null);
//            butB.setBackground(null);
//            butC.setBackground(null);
//            butD.setBackground(null);
//        });
//        butA.addActionListener(this);
//        butB.addActionListener(this);
//        butC.addActionListener(this);
//        butD.addActionListener(this);
//        panelAll.add(question, BorderLayout.NORTH);
//        panelAll.add(panelBtu, BorderLayout.CENTER);
////        panelAll.add(butColor, BorderLayout.WEST);
//        panelAll.add(next, BorderLayout.SOUTH);
//        frame.add(panelAll);
//        frame.pack();
//        
//    }
//    public void play() throws Exception{
//        String response;
//        String[] choice;
//        try{
//            while((response = in.readLine()) != null){
//                if((response.startsWith("yes"))){
//                    choice = response.split(",");
//                    for(int i=0; i<4; i++){
//                        if(buttons[i].getText().equals(choice[1])){
//                            buttons[i].setBackground(Color.green);
//                            buttons[i].repaint();
//                        }
//                    }
                }
        }
    }
}
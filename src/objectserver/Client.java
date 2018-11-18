/*
 * Java
 */
package objectserver;


import java.awt.BorderLayout;
import java.awt.GridLayout;
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
public class Client {
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
    ObjectInputStream in;
    ObjectOutputStream out;
    int PORT = 12345;
    JButton[] buttons = new JButton[]{butA, butB, butC, butD};
    Object input;
    
    public Client(String adress) throws IOException{
        socket = new Socket(adress, PORT);

        panelBtu.setLayout(new GridLayout(2,2));
        panelAll.setLayout(new BorderLayout());
        panelExtra.setLayout(new BorderLayout());
        panelBtu.add(butA);
        panelBtu.add(butB);
        panelBtu.add(butC);
        panelBtu.add(butD);    
        next.addActionListener(nextQ ->{ 
            
            try {
                out.writeObject("change");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            panelAll.setVisible(false);


        });        

        question.setText("waiting...");
        panelAll.add(question, BorderLayout.NORTH);
        panelAll.add(panelBtu, BorderLayout.CENTER);
        panelAll.add(next, BorderLayout.SOUTH);
        panelExtra.add(butColor, BorderLayout.WEST);
        panelExtra.add(panelAll, BorderLayout.CENTER);
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
                        butA.setText(((List<QandA>) input).get(0).getValjningar()[0]);
                        butA.repaint();
                    }
                    else if(((String) input).equals("wait")){
                        question.setText("p1 answer now, wait!!!");
                        question.repaint();
                    }
                }
            }
                
            
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
    public static void main(String[] args) throws IOException{
            String serverAddress = (args.length == 0)? "localhost" : args[1];
            Client client = new Client(serverAddress);  
//            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            client.frame.setSize(240, 160);
//            client.frame.setLocation(200, 200);
//            client.frame.setVisible(true);
//            System.out.println("play now");
            client.play(); 
//            System.out.println("play end");
    }
    
}

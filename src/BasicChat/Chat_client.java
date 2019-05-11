package BasicChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Chat_client extends JFrame {

    JTextArea jTextArea;
    JButton btnEnviar;
    JTextField txtTexto;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;


    public Chat_client(){
        init();
    }

    public void init(){
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setLocation(10,10);
        this.setTitle("Server");
        components();
    }

    public void components(){

        jTextArea = new JTextArea();
        jTextArea.setColumns(10);
        jTextArea.setRows(10);

        btnEnviar = new JButton("Send txt to server");
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   String msgout = "";
                   msgout = txtTexto.getText().trim();
                   dataOutputStream.writeUTF(msgout);
               }catch (Exception ex){
                   ex.printStackTrace();
               }
            }
        });


        txtTexto = new JTextField(20);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(btnEnviar);
        panel.add(txtTexto);

        setLayout(new BorderLayout());
        add(jTextArea, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setSize(450,450);
    }

    public static void main(String args[]){
        Chat_client chat_client = new Chat_client();
        try{
            socket = new Socket("172.0.10.1", 2204); // Server's IP with the port.
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String magin = "";
            while(!magin.equals("exit")){
                magin = dataInputStream.readUTF();
                chat_client.jTextArea.setText(chat_client.jTextArea.getText().trim()+"\n" +magin);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

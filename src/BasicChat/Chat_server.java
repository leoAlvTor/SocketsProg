package BasicChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat_server extends JFrame {

    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    public JTextArea jTextArea;
    JButton btnEnviar;
    JTextField txtTexto;

    public Chat_server(){
        init();
    }

    public void init(){
        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setLocation(450,10);
        this.setTitle("Server");
        components();
    }

    public void components(){

        jTextArea = new JTextArea();
        jTextArea.setColumns(10);
        jTextArea.setRows(10);

        btnEnviar = new JButton("Send txt to client");
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msgout = "";
                System.out.println("leo");
                msgout = txtTexto.getText();
                try {
                    dataOutputStream.writeUTF("\n" + msgout);
                } catch (IOException ex) {
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
        Chat_server chat_server = new Chat_server();
        String magin = "";
        try{

            serverSocket = new ServerSocket(2204); // Socket port.
            socket = serverSocket.accept(); // Server accept the connections.

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while(!magin.equals("exit")){
                magin = dataInputStream.readUTF();
                chat_server.jTextArea.setText(chat_server.jTextArea.getText() + "\n" + magin);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package FileTransfer;

import javax.swing.*;

public class Client extends JFrame {

    public JTextField txtAdd;
    public JButton btnSend;
    public JProgressBar progressBar;

    public Client(){
        init();
    }

    public void init(){
        this.setTitle("Client");
        this.setSize(399,399);
        this.setDefaultCloseOperation(3);
        components();
    }

    public void components(){

    }

    public static void main(String args[]){
        Client client = new Client();
    }
}


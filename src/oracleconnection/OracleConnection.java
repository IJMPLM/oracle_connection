/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oracleconnection;

/**
 *
 * @author TGG
 */
public class OracleConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectDB c = new ConnectDB();
        c.Connect();
        MainScreen ms = new MainScreen();
        ms.setVisible(true);
    }
}

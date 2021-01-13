/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainForm;

import javax.swing.ImageIcon;

/**
 *
 * @author Ahmad Maulana (201643500312)
 */
public class DistribusiPerangkatIT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        ImageIcon ico = new ImageIcon("src/Images/logom2.png");
         LoadingScreen Loading=new LoadingScreen();
         Loading.setVisible(true);
          FormLogin LG =new FormLogin();
        try {
             for (int i = 0; i <= 100; i++){
               Thread.sleep(150);
              Loading.angka.setText(Integer.toString(i)+"%");
       Loading.proses.setValue(i);
       if(i==100){
           Loading.setVisible(false);
          LG.setVisible(true);
       }
             }
        } catch (Exception e) {
        }
    }
    
}

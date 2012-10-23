/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.UI;

/**
 *
 * @author Caleb
 */
public class PlayerName extends javax.swing.JFrame {

    /**
     * Creates new form PlayerName
     */
    public PlayerName() {
        initComponents();
        txtPlayerName.setSelectionStart(0);
        txtPlayerName.setSelectionEnd(11);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPlayerName = new javax.swing.JTextField();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        txtPlayerName.setFont(new java.awt.Font("Segoe Print", 0, 76)); // NOI18N
        txtPlayerName.setForeground(new java.awt.Color(255, 204, 0));
        txtPlayerName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName.setText("Player Name");
        txtPlayerName.setCaretColor(new java.awt.Color(255, 204, 0));
        txtPlayerName.setOpaque(false);
        txtPlayerName.setSelectedTextColor(new java.awt.Color(255, 204, 0));
        txtPlayerName.setSelectionColor(new java.awt.Color(153, 153, 153));
        getContentPane().add(txtPlayerName);
        txtPlayerName.setBounds(79, 123, 648, 210);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/UI/CaveWall.png"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 800, 600);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayerName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerName().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBackground;
    private javax.swing.JTextField txtPlayerName;
    // End of variables declaration//GEN-END:variables
}

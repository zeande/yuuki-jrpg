package yuuki.gui.UI;
import sun.audio.*;
import java.io.*;
/**
 *
 * @author Caleb Smith
 */
public class MainTitle extends javax.swing.JFrame {
OptionsMenu om = new OptionsMenu();
PlayerName pn = new PlayerName();
    
    /**
     * Creates new form MainTitle
     */
    public MainTitle() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblNewGame = new javax.swing.JLabel();
        lblLoadGame = new javax.swing.JLabel();
        lblOptions = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(16, 38, 800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/MainTitleGuiAssets/Title.png"))); // NOI18N
        getContentPane().add(lblTitle);
        lblTitle.setBounds(560, 20, 220, 90);

        lblNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/MainTitleGuiAssets/New Game.png"))); // NOI18N
        lblNewGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNewGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewGameClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NewGameEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NewGameExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NewGamePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                NewGameReleased(evt);
            }
        });
        getContentPane().add(lblNewGame);
        lblNewGame.setBounds(590, 120, 180, 30);

        lblLoadGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/MainTitleGuiAssets/Load Game.png"))); // NOI18N
        lblLoadGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLoadGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoadGameClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoadGameEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoadGameExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LoadGamePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LoadGameReleased(evt);
            }
        });
        getContentPane().add(lblLoadGame);
        lblLoadGame.setBounds(586, 160, 190, 40);

        lblOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/MainTitleGuiAssets/Options.png"))); // NOI18N
        lblOptions.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OptionsClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OptionsEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OptionsExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OptionsPressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                OptionsReleased(evt);
            }
        });
        getContentPane().add(lblOptions);
        lblOptions.setBounds(607, 205, 140, 40);

        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/MainTitleGuiAssets/Exit.png"))); // NOI18N
        lblExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ExitPressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExitReleased(evt);
            }
        });
        getContentPane().add(lblExit);
        lblExit.setBounds(640, 248, 80, 40);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/MainTitleGuiAssets/titleBackground.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 800, 600);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-816)/2, (screenSize.height-638)/2, 816, 638);
    }// </editor-fold>//GEN-END:initComponents

    private void NewGameEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGameEntered
        // On Mouseover changes lblNewGame's Icon.
        lblNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("New Game Hi.png")));
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onHover.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
        }
    }//GEN-LAST:event_NewGameEntered

    private void NewGameExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGameExited
        // On Mouseover off changes lblNewGame's Icon.
        lblNewGame.setIcon( new javax.swing.ImageIcon(getClass().getResource("New Game.png")));
    }//GEN-LAST:event_NewGameExited

    private void NewGamePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGamePressed
        // On Mouse Press move lblNewGame Down and Right.
        lblNewGame.setBounds(592, 122, 180, 30);
    }//GEN-LAST:event_NewGamePressed

    private void NewGameReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGameReleased
        // On Mouse Release move lblNewGame Up and Left.
        lblNewGame.setBounds(590, 120, 180, 30);
    }//GEN-LAST:event_NewGameReleased

    private void LoadGameEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadGameEntered
        // On Mouseover changes lblLoadGame's Icon.
        lblLoadGame.setIcon( new javax.swing.ImageIcon(getClass().getResource("Load Game Hi.png")));
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onHover.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
        }
    }//GEN-LAST:event_LoadGameEntered

    private void LoadGameExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadGameExited
        // On Mouseover off changes lblLoadGames' Icon
        lblLoadGame.setIcon( new javax.swing.ImageIcon(getClass().getResource("Load Game.png")));
    }//GEN-LAST:event_LoadGameExited

    private void LoadGamePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadGamePressed
        // On Mouse Press move lblLoadGame Down and Right.
        lblLoadGame.setBounds(588, 162, 190, 40);
    }//GEN-LAST:event_LoadGamePressed

    private void LoadGameReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadGameReleased
        // On Mouse Release move lblLoadGame Up and Left.
        lblLoadGame.setBounds(586, 160, 190, 40);
    }//GEN-LAST:event_LoadGameReleased

    private void OptionsEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsEntered
        // On Mouseover change lblOptions's Icon.
        lblOptions.setIcon( new javax.swing.ImageIcon(getClass().getResource("Options Hi.png")));
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onHover.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
        }
    }//GEN-LAST:event_OptionsEntered

    private void OptionsExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsExited
        // On Mouseover off change lblOptions's Icon.
        lblOptions.setIcon( new javax.swing.ImageIcon(getClass().getResource("Options.png")));
    }//GEN-LAST:event_OptionsExited

    private void OptionsPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsPressed
        // On Mouse Press move lblOptions Down and Right.
        lblOptions.setBounds(609, 207, 140, 40);
    }//GEN-LAST:event_OptionsPressed

    private void OptionsReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsReleased
        // On Mouse Release move lblOptions Up and Left.
        lblOptions.setBounds(607, 205, 140, 40);
    }//GEN-LAST:event_OptionsReleased

    private void ExitEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitEntered
        // On Mouseover change lblExit's Icon.
        lblExit.setIcon( new javax.swing.ImageIcon(getClass().getResource("Exit Hi.png")));
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onHover.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
        }
    }//GEN-LAST:event_ExitEntered

    private void ExitExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitExited
        // On Mouseover off change lblExit's Icon.
        lblExit.setIcon( new javax.swing.ImageIcon(getClass().getResource("Exit.png")));
    }//GEN-LAST:event_ExitExited

    private void ExitPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitPressed
        // On Mouse Press move lblExit Down and Right.
        lblExit.setBounds(642, 250, 80, 40);
    }//GEN-LAST:event_ExitPressed

    private void ExitReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitReleased
        // On Mouse Release move lblExit Up and Left.
        lblExit.setBounds(640, 248, 80, 40);
    }//GEN-LAST:event_ExitReleased

    private void ExitClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitClicked
        // Close the Program.
       
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onSelect.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
        
            try
            {
                Thread.sleep(500);
            }
            catch(Exception e)
            {
                System.out.println("Couldn't Sleep.");
            }
        }
        System.exit(0);
       
    }//GEN-LAST:event_ExitClicked

    private void OptionsClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsClicked
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onSelect.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
        }
      om.setVisible(true);
     
    }//GEN-LAST:event_OptionsClicked

    private void lblNewGameClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewGameClicked
        // Handles when lblNewgame is Clicked.
        if(om.effectsOffChecked == true)
        {
            pn.blnSound = false;
        }
        else
        {
            pn.blnSound = true;
        }
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onSelect.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
            
        }
        pn.setVisible(true);
    }//GEN-LAST:event_lblNewGameClicked

    private void lblLoadGameClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoadGameClicked
        // Handles when lblLoadGame is clicked.
        if(om.effectsOffChecked == false)
        {
            AudioPlayer BGMPlayer = AudioPlayer.player;
            AudioStream BGM;
            AudioData BGMData;
            try
            {
            BGM = new AudioStream(new FileInputStream("onSelect.wav"));
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play New Game Entered went wrong.");
            }
        }
    }//GEN-LAST:event_lblLoadGameClicked

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
            java.util.logging.Logger.getLogger(MainTitle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainTitle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainTitle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainTitle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainTitle().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblLoadGame;
    private javax.swing.JLabel lblNewGame;
    private javax.swing.JLabel lblOptions;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}

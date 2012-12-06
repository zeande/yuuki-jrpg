/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yuuki.gui.UI;
import yuuki.ui.GraphicalEngineTest;

/**
 *
 * @author Caleb Smith
 * @version 11/16/12
 */
public class BattleScreen extends javax.swing.JFrame {
    public int mainMenuClicked = 0;
    public String nextGuiForm = "";
    Audio audio = new Audio();
    boolean musicON = true;
    boolean effectsON = true;
    
    public void setMusic(boolean input)
    {
        musicON = input;
    }
    public void setEffects(boolean input)
    {
        effectsON = input;
    }
    
//    public String getNextForm(String nextForm)
//    {
//        nextGuiForm = nextForm;
//        while(nextGuiForm == "next form querry")
//        {
//            try
//            {
//                Thread.sleep(1);
//            }
//            catch(Exception e)
//            {
//                System.out.println("Couldn't sleep @ MainTitle.getNextForm");
//            }
//        }
//       return nextGuiForm; 
//    }
    
    public void setPlayerHP(int hp, int hpMax)
    {
        lblPlayerHP.setText(hp + "/" + hpMax);
    }
    public void setMonsterHP(int hp, int hpMax)
    {
        lblMonsterHP.setText(hp + "/" + hpMax);
    }
    public void setText(String textUpdate)
    {
        String currentText = txtAreaMessageBox.getText();
        txtAreaMessageBox.setText(currentText + textUpdate);
    }
    public void setTextln(String textUpdate)
    {
        String currentText = txtAreaMessageBox.getText();
        txtAreaMessageBox.setText(currentText + textUpdate + "\n");
    }
    
    public void beginSequence()
    {
        setTextln("A Slime has appeared!");
        showBattleOptions();
    }
    
    public void showChoice()
    {
        lblChoiceBackground.setVisible(true);
        lblBtnChoiceYes.setVisible(true);
        lblBtnChoiceNo.setVisible(true);
    }
    
    public void showBattleOptions()
    {
        lblTextBoxBackground.setVisible(true);
        lblBtnAttack.setVisible(true);
        lblBtnDefend.setVisible(true);
        lblBtnFlee.setVisible(true);
        lblBtnAttack.setEnabled(true);
        lblBtnDefend.setEnabled(true);
        lblBtnFlee.setEnabled(true);
    }
    public void disableBattleOptions()
    {
        lblBtnAttack.setEnabled(false);
        lblBtnDefend.setEnabled(false);
        lblBtnFlee.setEnabled(false);
    }
    
    public String Nav()
    {
        nextGuiForm = "";
        if(mainMenuClicked % 2 != 0)
        {
            lblMenuDropDownBackground.setVisible(true);
            btnOptionsMenu.setVisible(true);
            btnNewGame.setVisible(true);
            btnLoadGame.setVisible(true);
            btnSaveGame.setVisible(true);
            btnExit.setVisible(true);
            disableBattleOptions();
        }
        else
        {
            resetMenu();
            showBattleOptions();
        }
        while(nextGuiForm == "")
        {
            try
            {
                Thread.sleep(1);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return nextGuiForm;
    }
    
    public void resetMenu()
    {
        lblMenuDropDownBackground.setVisible(false);
        btnOptionsMenu.setVisible(false);
        btnNewGame.setVisible(false);
        btnLoadGame.setVisible(false);
        btnSaveGame.setVisible(false);
        btnExit.setVisible(false);
        
        lblChoiceBackground.setVisible(false);
        lblBtnChoiceYes.setVisible(false);
        lblBtnChoiceNo.setVisible(false);
    }
    
    public void resetChoice()
    {
        lblChoiceBackground.setVisible(true);
        lblChoiceBackground.setEnabled(false);
        lblBtnChoiceYes.setVisible(true);
        lblBtnChoiceYes.setEnabled(false);
        lblBtnChoiceNo.setVisible(true);
        lblBtnChoiceNo.setEnabled(false);
    }
    
    /**
     * Creates new form BattleScreen
     */
    public BattleScreen() {
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

        lblBtnChoiceYes = new javax.swing.JLabel();
        lblBtnChoiceNo = new javax.swing.JLabel();
        lblChoiceBackground = new javax.swing.JLabel();
        lblBtnAttack = new javax.swing.JLabel();
        lblBtnFlee = new javax.swing.JLabel();
        lblBtnDefend = new javax.swing.JLabel();
        lblTextBoxBackground = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMessageBox = new javax.swing.JTextArea();
        btnExit = new javax.swing.JLabel();
        lblMonsterHP = new javax.swing.JLabel();
        lblPlayerHP = new javax.swing.JLabel();
        btnLoadGame = new javax.swing.JLabel();
        btnSaveGame = new javax.swing.JLabel();
        btnNewGame = new javax.swing.JLabel();
        btnOptionsMenu = new javax.swing.JLabel();
        btnMenu = new javax.swing.JLabel();
        lblMenuDropDownBackground = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                FormLoad(evt);
            }
        });
        getContentPane().setLayout(null);

        lblBtnChoiceYes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/ChoiceYes.png"))); // NOI18N
        lblBtnChoiceYes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChoiceYesClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ChoiceYesEntered(evt);
            }
        });
        getContentPane().add(lblBtnChoiceYes);
        lblBtnChoiceYes.setBounds(660, 480, 116, 47);

        lblBtnChoiceNo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/ChoiceNo.png"))); // NOI18N
        lblBtnChoiceNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChoiceNoClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ChoiceNoEntered(evt);
            }
        });
        getContentPane().add(lblBtnChoiceNo);
        lblBtnChoiceNo.setBounds(660, 530, 115, 46);

        lblChoiceBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/YesNoBackground.png"))); // NOI18N
        getContentPane().add(lblChoiceBackground);
        lblChoiceBackground.setBounds(650, 460, 140, 132);

        lblBtnAttack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/btnAttack.png"))); // NOI18N
        lblBtnAttack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAttackClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AttackEntered(evt);
            }
        });
        getContentPane().add(lblBtnAttack);
        lblBtnAttack.setBounds(20, 510, 190, 70);

        lblBtnFlee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/btnFlee.png"))); // NOI18N
        lblBtnFlee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFleeClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FleeEntered(evt);
            }
        });
        getContentPane().add(lblBtnFlee);
        lblBtnFlee.setBounds(560, 510, 190, 70);

        lblBtnDefend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/btnDefend.png"))); // NOI18N
        lblBtnDefend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDefendClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DefendEntered(evt);
            }
        });
        getContentPane().add(lblBtnDefend);
        lblBtnDefend.setBounds(300, 510, 190, 70);

        lblTextBoxBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/TextBoxBackground.png"))); // NOI18N
        getContentPane().add(lblTextBoxBackground);
        lblTextBoxBackground.setBounds(0, 490, 800, 110);

        txtAreaMessageBox.setEditable(false);
        txtAreaMessageBox.setColumns(20);
        txtAreaMessageBox.setLineWrap(true);
        txtAreaMessageBox.setRows(5);
        jScrollPane1.setViewportView(txtAreaMessageBox);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 340, 800, 140);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/MenuDropDownExit.png"))); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitEntered(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(645, 263, 60, 30);

        lblMonsterHP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMonsterHP.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(lblMonsterHP);
        lblMonsterHP.setBounds(660, 400, 130, 50);

        lblPlayerHP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPlayerHP.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(lblPlayerHP);
        lblPlayerHP.setBounds(10, 390, 130, 50);

        btnLoadGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/MenuDropDownLoadGame.png"))); // NOI18N
        btnLoadGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoadGameClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoadGameEntered(evt);
            }
        });
        getContentPane().add(btnLoadGame);
        btnLoadGame.setBounds(596, 214, 160, 30);

        btnSaveGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/MenuDropDownSaveGame.png"))); // NOI18N
        btnSaveGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveGameClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SaveGameEntered(evt);
            }
        });
        getContentPane().add(btnSaveGame);
        btnSaveGame.setBounds(598, 174, 160, 30);

        btnNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/MenuDropDownNewGame.png"))); // NOI18N
        btnNewGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewGameClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NewGameEntered(evt);
            }
        });
        getContentPane().add(btnNewGame);
        btnNewGame.setBounds(601, 127, 160, 30);

        btnOptionsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/MenuDropDownOptions.png"))); // NOI18N
        btnOptionsMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOptionsMenuClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OptionsMenuEntered(evt);
            }
        });
        getContentPane().add(btnOptionsMenu);
        btnOptionsMenu.setBounds(576, 72, 200, 50);

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/MenuIcon.png"))); // NOI18N
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MainMenuEntered(evt);
            }
        });
        getContentPane().add(btnMenu);
        btnMenu.setBounds(650, 10, 140, 60);
        btnMenu.getAccessibleContext().setAccessibleDescription("");

        lblMenuDropDownBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/MenuDropDownBackground.png"))); // NOI18N
        lblMenuDropDownBackground.setFocusable(false);
        getContentPane().add(lblMenuDropDownBackground);
        lblMenuDropDownBackground.setBounds(550, 2, 250, 370);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/yuuki/gui/UI/BattleScreenAssets/The_Field_by_Joker841.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 800, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuClicked
        // Handles when the Menu button is clicked.
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
        nextGuiForm = "BattleScreen.btnMenu";
        mainMenuClicked = mainMenuClicked + 1;
    }//GEN-LAST:event_btnMenuClicked

    private void btnOptionsMenuClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOptionsMenuClicked
        // Handles when the Option Button in the Drop Down Menu is Clicked.
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
        nextGuiForm = "BattleScreen.btnOptionsMenu";
    }//GEN-LAST:event_btnOptionsMenuClicked

    private void btnNewGameClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewGameClicked
        // Handles when the New Game button in the Drop Down Menu is Clicked.
         if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
         nextGuiForm = "BattleScreen.btnCreateNewGame";
    }//GEN-LAST:event_btnNewGameClicked

    private void btnSaveGameClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveGameClicked
        // Handles when the Save Game Button in the Drop Down Menu is Clicked.
         if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
         nextGuiForm = "BattleScreen.btnSaveGame";
    }//GEN-LAST:event_btnSaveGameClicked

    private void btnLoadGameClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadGameClicked
        // Handles when the Load Game Button in the Drop Down Menu is Clicked.
         if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
         nextGuiForm = "BattleScreen.btnLoadGame";
    }//GEN-LAST:event_btnLoadGameClicked

    private void btnExitClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitClicked
        // Handles when the Load Game Button in the Drop Down Menu is Clicked.
         if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
         nextGuiForm = "Exit";
    }//GEN-LAST:event_btnExitClicked

    private void FormLoad(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_FormLoad
        // TODO add your handling code here:
        audio.preload("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        audio.preload("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        lblMenuDropDownBackground.setVisible(false);
        btnOptionsMenu.setVisible(false);
        btnNewGame.setVisible(false);
        btnLoadGame.setVisible(false);
        btnSaveGame.setVisible(false);
        btnExit.setVisible(false);
    }//GEN-LAST:event_FormLoad

    private void ChoiceYesClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChoiceYesClicked
        // TODO add your handling code here:
        //nextAction = "Yes";
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
        System.out.println("Add return options @ BattleScreen.ChoiceYesClicked");
    }//GEN-LAST:event_ChoiceYesClicked

    private void ChoiceNoClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChoiceNoClicked
        // TODO add your handling code here:
        ///nextAction = "No";
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
        System.out.println("Add return options @ BattleScreen.ChoiceYesClicked");
    }//GEN-LAST:event_ChoiceNoClicked

    private void btnAttackClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAttackClicked
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
        nextGuiForm = "Attack";
    }//GEN-LAST:event_btnAttackClicked

    private void btnDefendClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDefendClicked
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
        nextGuiForm = "Defend";
    }//GEN-LAST:event_btnDefendClicked

    private void btnFleeClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFleeClicked
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onSelect.wav");
        }
        nextGuiForm = "Flee";
    }//GEN-LAST:event_btnFleeClicked

    private void MainMenuEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MainMenuEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_MainMenuEntered

    private void OptionsMenuEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptionsMenuEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_OptionsMenuEntered

    private void NewGameEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewGameEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_NewGameEntered

    private void SaveGameEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveGameEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_SaveGameEntered

    private void LoadGameEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadGameEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_LoadGameEntered

    private void ExitEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_ExitEntered

    private void AttackEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AttackEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_AttackEntered

    private void DefendEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DefendEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_DefendEntered

    private void FleeEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FleeEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_FleeEntered

    private void ChoiceYesEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChoiceYesEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_ChoiceYesEntered

    private void ChoiceNoEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChoiceNoEntered
        // TODO add your handling code here:
        if(effectsON)
        {
            audio.playSound("/yuuki/gui/UI/GuiSoundAssets/onHover.wav");
        }
    }//GEN-LAST:event_ChoiceNoEntered

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
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BattleScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BattleScreen().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnLoadGame;
    private javax.swing.JLabel btnMenu;
    private javax.swing.JLabel btnNewGame;
    private javax.swing.JLabel btnOptionsMenu;
    private javax.swing.JLabel btnSaveGame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBtnAttack;
    private javax.swing.JLabel lblBtnChoiceNo;
    private javax.swing.JLabel lblBtnChoiceYes;
    private javax.swing.JLabel lblBtnDefend;
    private javax.swing.JLabel lblBtnFlee;
    private javax.swing.JLabel lblChoiceBackground;
    private javax.swing.JLabel lblMenuDropDownBackground;
    private javax.swing.JLabel lblMonsterHP;
    private javax.swing.JLabel lblPlayerHP;
    private javax.swing.JLabel lblTextBoxBackground;
    private javax.swing.JTextArea txtAreaMessageBox;
    // End of variables declaration//GEN-END:variables
}

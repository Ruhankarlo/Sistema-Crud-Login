package br.com.gerlocacao.telas;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.*;

public class TelaPrincipal extends javax.swing.JFrame {

   //Tela Principal, o que vai abrir após o Login
    public TelaPrincipal() {
        initComponents();
       getContentPane().setBackground(new java.awt.Color(51, 153, 255));// cor de fundo
       
       
      
    }

  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        wallpapper = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        userlog = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        cadastroM = new javax.swing.JMenu();
        cliCM = new javax.swing.JMenuItem();
        usuCM = new javax.swing.JMenuItem();
        equipCM = new javax.swing.JMenuItem();
        relaM = new javax.swing.JMenu();
        termoRM = new javax.swing.JMenuItem();
        relRM = new javax.swing.JMenuItem();
        LocM = new javax.swing.JMenu();
        addLocM = new javax.swing.JMenuItem();
        helpM = new javax.swing.JMenu();
        sobreHM = new javax.swing.JMenuItem();
        OpitionsM = new javax.swing.JMenu();
        exitOM = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Locações");
        setBackground(new java.awt.Color(255, 255, 153));
        setForeground(new java.awt.Color(255, 255, 204));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        desktop.setBackground(new java.awt.Color(230, 240, 255));
        desktop.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED,
            new java.awt.Color(180, 200, 220), new java.awt.Color(150, 170, 190)));
    desktop.setForeground(new java.awt.Color(100, 120, 140));
    desktop.setToolTipText("");
    desktop.setMaximumSize(new java.awt.Dimension(1232, 554));
    desktop.setMinimumSize(new java.awt.Dimension(1232, 554));

    wallpapper.setBackground(new java.awt.Color(204, 255, 255));
    wallpapper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/Minimal-Wallpapers-HD-For-Desktop.jpg"))); // NOI18N
    wallpapper.setMaximumSize(new java.awt.Dimension(1232, 554));
    wallpapper.setMinimumSize(new java.awt.Dimension(1232, 554));
    wallpapper.setPreferredSize(new java.awt.Dimension(1232, 554));

    desktop.setLayer(wallpapper, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
    desktop.setLayout(desktopLayout);
    desktopLayout.setHorizontalGroup(
        desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(desktopLayout.createSequentialGroup()
            .addComponent(wallpapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    desktopLayout.setVerticalGroup(
        desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(desktopLayout.createSequentialGroup()
            .addComponent(wallpapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
    jLabel1.setText("LOGOTIPO");

    userlog.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
    userlog.setText("Usuário");

    date.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
    date.setText("Data");

    menu.setBackground(new java.awt.Color(255, 255, 204));
    menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 204), new java.awt.Color(255, 255, 153)));

    cadastroM.setText("Cadastros");

    cliCM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
    cliCM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/client.png"))); // NOI18N
    cliCM.setText("Clientes");
    cliCM.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cliCMActionPerformed(evt);
        }
    });
    cadastroM.add(cliCM);

    usuCM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
    usuCM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/user.png"))); // NOI18N
    usuCM.setText("Usuários");
    usuCM.setEnabled(false);
    usuCM.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            usuCMActionPerformed(evt);
        }
    });
    cadastroM.add(usuCM);

    equipCM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
    equipCM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/equip.png"))); // NOI18N
    equipCM.setText("Equipamentos");
    cadastroM.add(equipCM);

    menu.add(cadastroM);

    relaM.setText("Relatórios");

    termoRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/termodeuso.png"))); // NOI18N
    termoRM.setText("Termo de Uso");
    termoRM.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            termoRMActionPerformed(evt);
        }
    });
    relaM.add(termoRM);

    relRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/relatorioloc.png"))); // NOI18N
    relRM.setText("Relatório de Locações");
    relaM.add(relRM);

    menu.add(relaM);

    LocM.setText("Locações");

    addLocM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
    addLocM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/addlocacao.png"))); // NOI18N
    addLocM.setText("Adicionar");
    LocM.add(addLocM);

    menu.add(LocM);

    helpM.setText("Ajuda");

    sobreHM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
    sobreHM.setText("Sobre");
    sobreHM.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            sobreHMActionPerformed(evt);
        }
    });
    helpM.add(sobreHM);

    menu.add(helpM);

    OpitionsM.setText("Opções");

    exitOM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
    exitOM.setText("Sair");
    exitOM.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitOMActionPerformed(evt);
        }
    });
    OpitionsM.add(exitOM);

    menu.add(OpitionsM);

    setJMenuBar(menu);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(37, 37, 37)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1070, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(userlog)
                .addComponent(date))
            .addGap(32, 32, 32))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(userlog))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(date)
            .addContainerGap(46, Short.MAX_VALUE))
    );

    setSize(new java.awt.Dimension(1283, 711));
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void termoRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termoRMActionPerformed
       
    }//GEN-LAST:event_termoRMActionPerformed

    private void exitOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitOMActionPerformed
        //Sair do sistema. Mas por segurança, exibirá uma caixa.
       // Exibir uma caixa de diálogo de confirmação
    int resposta = JOptionPane.showConfirmDialog(
        null, 
        "Tem certeza que deseja sair?", 
        "Confirmação de Saída", 
        JOptionPane.YES_NO_OPTION
    );

    // Verificar a resposta do usuário
    if (resposta == JOptionPane.YES_OPTION) {
        // Se o usuário clicar em "Sim", sair do sistema
     
        this.dispose(); //fecho a janela
        System.exit(0); //encerro a aplicação
    }
    // Se o usuário clicar em "Não", nada acontece e a janela continua aberta

    }//GEN-LAST:event_exitOMActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Com objetivo de colocar a data, a data do sistema.
        Date dt = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        date.setText(sdf.format(dt));
    }//GEN-LAST:event_formWindowActivated

    private void sobreHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreHMActionPerformed
        // Tela sobre
        TelaSobre init = new TelaSobre();
        init.setVisible(true);
    }//GEN-LAST:event_sobreHMActionPerformed

    private void usuCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuCMActionPerformed
       TelaUsuarios init = new TelaUsuarios();
       
      desktop.add(init);
      init.setVisible(true);
       
        
    }//GEN-LAST:event_usuCMActionPerformed

    private void cliCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cliCMActionPerformed
        TelaClientes init = new TelaClientes();
        desktop.add(init);
        init.setVisible(true);
    }//GEN-LAST:event_cliCMActionPerformed

    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu LocM;
    private javax.swing.JMenu OpitionsM;
    private javax.swing.JMenuItem addLocM;
    private javax.swing.JMenu cadastroM;
    private javax.swing.JMenuItem cliCM;
    private javax.swing.JLabel date;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem equipCM;
    private javax.swing.JMenuItem exitOM;
    private javax.swing.JMenu helpM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem relRM;
    private javax.swing.JMenu relaM;
    private javax.swing.JMenuItem sobreHM;
    private javax.swing.JMenuItem termoRM;
    public static javax.swing.JLabel userlog;
    public static javax.swing.JMenuItem usuCM;
    private javax.swing.JLabel wallpapper;
    // End of variables declaration//GEN-END:variables
}

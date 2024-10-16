package br.com.gerlocacao.telas;

import java.sql.*;
import br.com.gerlocacao.dal.ModConexao;
import java.awt.Color;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class TelaLogin extends javax.swing.JFrame {
    //Chamando os métodos de conexão bd

    Connection conexao = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //Método de login
    public void logando() {
        String select = "select * from usuarios where login=? and senha=?;";

        try { //consultar o que foi digitado em login
            ps = conexao.prepareStatement(select);
            ps.setString(1, usuariobox.getText());
            String cap = new String(senhabox.getPassword());
            ps.setString(2, cap);
            // execultar query
            rs = ps.executeQuery();

            if (rs.next()) {
                String nvlacesso = rs.getString(6);

                if (nvlacesso.equals("Administrador")) {

                    TelaPrincipal inicio = new TelaPrincipal();
                    inicio.setVisible(true);
                    TelaPrincipal.usuCM.setEnabled(true);
                    this.dispose();
                    TelaPrincipal.userlog.setText(rs.getString(4));
                    // Criando uma borda com cantos arredondados e sombra
                    Border border = BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(30, 144, 255), 2), // Borda azul mais suave
                            BorderFactory.createEmptyBorder(5, 10, 5, 10) // Espaçamento interno
                    );
                    // Aplicando a borda ao userlog
                    TelaPrincipal.userlog.setBorder(border);
                    // Configurando a cor do texto e a fonte
                    TelaPrincipal.userlog.setForeground(new Color(25, 25, 112)); // Azul marinho               
                    // Opcional: Definir o background transparente ou uma cor de fundo suave
                    TelaPrincipal.userlog.setBackground(new Color(245, 245, 245)); // Cinza claro
                    TelaPrincipal.userlog.setOpaque(true); // Necessário se o background for definido

                } else {
                    TelaPrincipal inicio = new TelaPrincipal();
                    inicio.setVisible(true);
                    TelaPrincipal.userlog.setText(rs.getString(4));
                    // Opcional: Definir o background transparente ou uma cor de fundo suave
                    TelaPrincipal.userlog.setBackground(new Color(245, 245, 245)); // Cinza claro
                    TelaPrincipal.userlog.setOpaque(true); // Necessário se o background for definido
                    this.dispose();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Criando formulário da tela de login
    public TelaLogin() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(204, 229, 255));
        conexao = ModConexao.conect();
        if (conexao != null) {
            URL icondbURL = getClass().getResource("/br/com/gerlocacao/icones/database.png");
            if (icondbURL != null) {
                statussys.setIcon(new javax.swing.ImageIcon(icondbURL));
            } else {
                System.err.println("Imagem não encontrada");
            }
        } else {
            URL errodbURL = getClass().getResource("/br/com/gerlocacao/icones/databaseerro.png");
            if (errodbURL != null) {
                statussys.setIcon(new javax.swing.ImageIcon(errodbURL));
            } else {
                System.out.println("imagem dberro.png nao encontrada");
            }
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuariobox = new javax.swing.JTextField();
        buttonLogin = new javax.swing.JButton();
        senhabox = new javax.swing.JPasswordField();
        statussys = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ger Locação - Login");
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Usuário");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Senha");

        usuariobox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        usuariobox.setMinimumSize(new java.awt.Dimension(64, 26));
        usuariobox.setPreferredSize(new java.awt.Dimension(64, 26));

        buttonLogin.setBackground(new java.awt.Color(110, 176, 110));
        buttonLogin.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        buttonLogin.setText("Login");
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        senhabox.setMinimumSize(new java.awt.Dimension(64, 26));
        senhabox.setPreferredSize(new java.awt.Dimension(64, 26));

        statussys.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/database.png"))); // NOI18N
        statussys.setMaximumSize(new java.awt.Dimension(46, 26));
        statussys.setMinimumSize(new java.awt.Dimension(46, 26));
        statussys.setPreferredSize(new java.awt.Dimension(46, 26));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("LOGOTIPO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statussys, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(senhabox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuariobox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(usuariobox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(senhabox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statussys, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(34, 34, 34))))
        );

        setSize(new java.awt.Dimension(477, 294));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //apertar botão de login
    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        logando();
    }//GEN-LAST:event_buttonLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField senhabox;
    private javax.swing.JLabel statussys;
    private javax.swing.JTextField usuariobox;
    // End of variables declaration//GEN-END:variables
}

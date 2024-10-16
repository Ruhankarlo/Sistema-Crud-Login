package br.com.gerlocacao.telas;

import javax.swing.*;
import java.sql.*;
import br.com.gerlocacao.dal.ModConexao;
import com.mysql.cj.conf.BooleanPropertyDefinition;
import java.lang.ref.Cleaner;

public class TelaUsuarios extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public TelaUsuarios() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(204, 229, 255));
        acessobox.setSelectedItem(null);
        conexao = ModConexao.conect();

    }

    private void consultar() {

        String sql = "select * from usuarios where iduser=?;";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, idbox.getText());

            int idValue = Integer.parseInt(idbox.getText());
            if (idValue == 1) {
                acessobox.setEnabled(false);
            } else {
                acessobox.setEnabled(true);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                nomebox.setText(rs.getString(4));
                loginbox.setText(rs.getString(2));
                telbox.setText(rs.getString(5));
                senhabox.setText(rs.getString(3));
                acessobox.setSelectedItem(rs.getString(6));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário inexistente!");
                idbox.setText(null);
                nomebox.setText(null);
                loginbox.setText(null);
                telbox.setText(null);
                senhabox.setText(null);
                acessobox.setSelectedItem(null);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void adicionar() {
        String sql = "INSERT INTO usuarios(iduser, login, senha, usuario, fone, nivel_de_acesso) VALUES(?, ?, ?, ?, ?, ?);";

        try {

            String sql2 = "SELECT MAX(iduser) FROM usuarios;";

            ps = conexao.prepareStatement(sql2);
            rs = ps.executeQuery();
            int proximoId = 1;
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                proximoId = ultimoId + 1;

                idbox.setText(String.valueOf(proximoId));
            }

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, proximoId);
            ps.setString(2, loginbox.getText());
            String cap = new String(senhabox.getPassword());
            ps.setString(3, cap);
            ps.setString(4, nomebox.getText());
            ps.setString(5, telbox.getText());
            ps.setString(6, acessobox.getSelectedItem().toString());
            
            String select = (String) acessobox.getSelectedItem().toString();
            if (loginbox.getText().isEmpty() || cap.isEmpty() || nomebox.getText().isEmpty() || select.trim().isEmpty()|| select==null) {
                JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios tem que ser preenchidos!");

            } else {

                int add = ps.executeUpdate();
                
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com Sucesso!");
                }
                idbox.setText(null);
                nomebox.setText(null);
                loginbox.setText(null);
                telbox.setText(null);
                senhabox.setText(null);
                acessobox.setSelectedItem(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não é permitido cadastrar usuários com mesmo Login ou ID. Todos os campos tem que ser preenchidos. ERRO: \n" + e);
        }

    }

    private void delete() {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Você deseja realmente deletar este usuário?",
                "Confirmação de Exclusão",
                JOptionPane.YES_NO_OPTION
        );
        if (resposta == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM usuarios where iduser=?";
            try {
                ps = conexao.prepareStatement(sql);
                ps.setString(1, idbox.getText());

                if (idbox.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum ID selecionado para exclusão");
                } else {

                    
                    int exclusao = ps.executeUpdate();
                    System.out.println(exclusao);
                    if (exclusao > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");

                        idbox.setText(null);
                        nomebox.setText(null);
                        loginbox.setText(null);
                        telbox.setText(null);
                        senhabox.setText(null);
                        acessobox.setSelectedItem(null);

                    } else {
                        JOptionPane.showMessageDialog(null, "ID inválido! Não foi possível deletar");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Houve um erro na exclusão. ERRO: \n" + e);
            }

        } else {
            idbox.setText(null);
            nomebox.setText(null);
            loginbox.setText(null);
            telbox.setText(null);
            senhabox.setText(null);
            acessobox.setSelectedItem(null);
        }
    }

    private void alterar() {
        String sql = "UPDATE usuarios SET login=?, senha=?, usuario=?, fone=?, nivel_de_acesso=? where iduser=? ";
        try {

            ps = conexao.prepareStatement(sql);
            ps.setString(1, loginbox.getText());
            String cap = new String(senhabox.getPassword());
            ps.setString(2, cap);
            ps.setString(3, nomebox.getText());
            ps.setString(4, telbox.getText());
            ps.setString(5, acessobox.getSelectedItem().toString());
            String select = (String) acessobox.getSelectedItem().toString();
            ps.setString(6, idbox.getText());
            if (loginbox.getText().isEmpty() || cap.isEmpty() || nomebox.getText().isEmpty() || select.trim().isEmpty()|| select==null) { 
                JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios devem estar preenchidos!");
            } else {

                int add = ps.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Dados de Usuário alterado com Sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum ID válido seleciondo para alteração!");
                }
            }
            idbox.setText(null);
            nomebox.setText(null);
            loginbox.setText(null);
            telbox.setText(null);
            senhabox.setText(null);
            acessobox.setSelectedItem(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Todos dados devem estar preenchidos. Para apagar use o botão DELETAR. Alteração não foi feita. ERRO: \n " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idbox = new javax.swing.JTextField();
        nomebox = new javax.swing.JTextField();
        loginbox = new javax.swing.JTextField();
        telbox = new javax.swing.JTextField();
        adduserbutton = new javax.swing.JButton();
        acessobox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        deluserbutton = new javax.swing.JButton();
        upuserbutton = new javax.swing.JButton();
        readuserbutton = new javax.swing.JButton();
        senhabox = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 204));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setClosable(true);
        setForeground(new java.awt.Color(0, 0, 0));
        setIconifiable(true);
        setTitle("Usuários");
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1232, 554));
        setMinimumSize(new java.awt.Dimension(1232, 554));
        setPreferredSize(new java.awt.Dimension(1232, 554));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Nome de Usuário:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Login:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Telefone/Cel:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("ID:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Nivel de Acesso:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Senha:");

        idbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        idbox.setMaximumSize(new java.awt.Dimension(76, 28));
        idbox.setMinimumSize(new java.awt.Dimension(76, 28));
        idbox.setPreferredSize(new java.awt.Dimension(76, 28));
        idbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idboxActionPerformed(evt);
            }
        });

        nomebox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        loginbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        loginbox.setMaximumSize(new java.awt.Dimension(90, 28));
        loginbox.setMinimumSize(new java.awt.Dimension(90, 28));
        loginbox.setPreferredSize(new java.awt.Dimension(90, 28));

        telbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        adduserbutton.setBackground(new java.awt.Color(110, 176, 110));
        adduserbutton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        adduserbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/create.png"))); // NOI18N
        adduserbutton.setText("CADASTRAR");
        adduserbutton.setToolTipText("CADASTRAR USUÁRIO");
        adduserbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adduserbutton.setMaximumSize(new java.awt.Dimension(115, 40));
        adduserbutton.setMinimumSize(new java.awt.Dimension(115, 40));
        adduserbutton.setPreferredSize(new java.awt.Dimension(115, 40));
        adduserbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adduserbuttonActionPerformed(evt);
            }
        });

        acessobox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        acessobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario" }));
        acessobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acessoboxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Um cadastro de usuário só pode ser relizado por usúarios de nivel de acesso Administrador.");

        deluserbutton.setBackground(new java.awt.Color(143, 188, 143));
        deluserbutton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        deluserbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/delete.png"))); // NOI18N
        deluserbutton.setText("DELETAR");
        deluserbutton.setToolTipText("Clique para deletar o usuário");
        deluserbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deluserbutton.setMaximumSize(new java.awt.Dimension(115, 40));
        deluserbutton.setMinimumSize(new java.awt.Dimension(115, 40));
        deluserbutton.setPreferredSize(new java.awt.Dimension(115, 40));
        deluserbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deluserbuttonActionPerformed(evt);
            }
        });

        upuserbutton.setBackground(new java.awt.Color(143, 188, 143));
        upuserbutton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        upuserbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/update.png"))); // NOI18N
        upuserbutton.setText("ALTERAR");
        upuserbutton.setToolTipText("Clique para alterar o usuário");
        upuserbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        upuserbutton.setMaximumSize(new java.awt.Dimension(115, 40));
        upuserbutton.setMinimumSize(new java.awt.Dimension(115, 40));
        upuserbutton.setPreferredSize(new java.awt.Dimension(115, 40));
        upuserbutton.setRequestFocusEnabled(false);
        upuserbutton.setRolloverEnabled(false);
        upuserbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upuserbuttonActionPerformed(evt);
            }
        });

        readuserbutton.setBackground(new java.awt.Color(143, 188, 143));
        readuserbutton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        readuserbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/read.png"))); // NOI18N
        readuserbutton.setText("PROCURAR");
        readuserbutton.setToolTipText("Usuários cadastrados no sistema");
        readuserbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        readuserbutton.setMaximumSize(new java.awt.Dimension(101, 40));
        readuserbutton.setMinimumSize(new java.awt.Dimension(101, 40));
        readuserbutton.setPreferredSize(new java.awt.Dimension(115, 40));
        readuserbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readuserbuttonActionPerformed(evt);
            }
        });

        senhabox.setMaximumSize(new java.awt.Dimension(90, 28));
        senhabox.setMinimumSize(new java.awt.Dimension(90, 28));
        senhabox.setPreferredSize(new java.awt.Dimension(90, 28));

        jLabel10.setText("(Obrigatório)");

        jLabel11.setText("(Obrigatório)");

        jLabel12.setText("(Obrigatório)");

        jLabel13.setText("(Obrigatório)");

        jLabel14.setText("(?)");
        jLabel14.setToolTipText("Não é necessário preencher o ID. Será adicionado automaticamente, senqueciando o último usuário cadastrado.");

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Usuários");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("Preencha as informações");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(476, 476, 476))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(558, 558, 558))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(readuserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(upuserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(deluserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(384, 384, 384)
                .addComponent(adduserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idbox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel11)
                                                .addComponent(loginbox, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                                .addComponent(senhabox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(nomebox, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 123, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(telbox)
                                    .addComponent(acessobox, 0, 293, Short.MAX_VALUE))))))
                .addGap(32, 32, 32))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(1, 1, 1)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nomebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(loginbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(senhabox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(readuserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(upuserbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deluserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(adduserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(telbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(acessobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(207, 207, 207))))
        );

        acessobox.setBackground(new java.awt.Color(255, 255, 255)); // Cor de fundo branca
        acessobox.setForeground(new java.awt.Color(0, 51, 102)); // Cor do texto azul escuro

        // Adicionando um ActionListener para manipular a ação quando uma opção é selecionada

        setSize(new java.awt.Dimension(1232, 554));
    }// </editor-fold>//GEN-END:initComponents

    private void adduserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adduserbuttonActionPerformed
        adicionar();
    }//GEN-LAST:event_adduserbuttonActionPerformed

    private void idboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idboxActionPerformed

    private void acessoboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acessoboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acessoboxActionPerformed

    private void readuserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readuserbuttonActionPerformed
        //Botão de consultar usuário
        consultar();
    }//GEN-LAST:event_readuserbuttonActionPerformed

    private void deluserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deluserbuttonActionPerformed

        delete();

    }//GEN-LAST:event_deluserbuttonActionPerformed

    private void upuserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upuserbuttonActionPerformed
        alterar();
    }//GEN-LAST:event_upuserbuttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> acessobox;
    private javax.swing.JButton adduserbutton;
    private javax.swing.JButton deluserbutton;
    private javax.swing.JTextField idbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField loginbox;
    private javax.swing.JTextField nomebox;
    private javax.swing.JButton readuserbutton;
    private javax.swing.JPasswordField senhabox;
    private javax.swing.JTextField telbox;
    private javax.swing.JButton upuserbutton;
    // End of variables declaration//GEN-END:variables
}

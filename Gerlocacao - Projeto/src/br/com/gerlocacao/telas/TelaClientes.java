package br.com.gerlocacao.telas;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import br.com.gerlocacao.dal.ModConexao;
import java.util.logging.Level;
import java.util.logging.Logger;
//biblioteca externa
import net.proteanit.sql.DbUtils;

public class TelaClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public TelaClientes() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(204, 229, 255));

        conexao = ModConexao.conect();
        pesquisar();

    }

    private void adicionar() {
        String sql = "INSERT INTO clientes(nome_empresa, cpf_cnpj, fone, email, cidade, estado, bairro, endereco, observacoes) values( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = conexao.prepareStatement(sql);

            ps.setString(1, nomebox.getText());
            ps.setString(2, cnpjbox.getText());
            ps.setString(3, telbox.getText());
            ps.setString(4, emailbox.getText());
            ps.setString(5, cidadebox.getText());
            ps.setString(6, estadobox.getText());
            ps.setString(7, bairrobox.getText());
            ps.setString(8, enderecobox.getText());
            ps.setString(9, observacaobox.getText());

            if (nomebox.getText().isEmpty() || cnpjbox.getText().isEmpty() || cidadebox.getText().isEmpty() || estadobox.getText().isEmpty() || bairrobox.getText().isEmpty() || enderecobox.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios devem ser preenchidos para um cadastro de novo cliente!");
                return;
            }
            int add = ps.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Houve um erro ao cadastrar o cliente, verifique se está tudo correto! \n Não é possível cadastrar mais de um cliente com o mesmo CNPJ/CPF!");
            }
            anular();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não é possível cadastrar mais de um cliente com o mesmo CNPJ/CPF! ERRO: \n" + e);
        }

    }

    private void pesquisar() {
        String sql = "SELECT idclient as CLIENTE, nome_empresa as NOME, cpf_cnpj as CNPFCNPJ, fone as TELEFONE, email as EMAIL, cidade as CIDADE, estado as ESTADO, bairro as BAIRRO, endereco as ENDEREÇO, observacoes as OBSERVAÇÕES FROM clientes WHERE nome_empresa LIKE ?";

        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, pesquisaclibox.getText() + "%");

            rs = ps.executeQuery();

            listclienttable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Houve um erro na pesquisa de clientes! ERRO: \n" + e);
        }
    }

    private void alterar() {
        String sql = "UPDATE clientes SET nome_empresa=?, cpf_cnpj=?, fone=?, email=?, cidade=?, estado=?, bairro=?, endereco=?, observacoes=? where idclient=? ";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, nomebox.getText());
            ps.setString(2, cnpjbox.getText());
            ps.setString(3, telbox.getText());
            ps.setString(4, emailbox.getText());
            ps.setString(5, cidadebox.getText());
            ps.setString(6, estadobox.getText());
            ps.setString(7, bairrobox.getText());
            ps.setString(8, enderecobox.getText());
            ps.setString(9, observacaobox.getText());
            ps.setString(10, idbox.getText());
            if (nomebox.getText().isEmpty() || cnpjbox.getText().isEmpty() || cidadebox.getText().isEmpty() || estadobox.getText().isEmpty() || bairrobox.getText().isEmpty() || enderecobox.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios devem ser preenchidos para uma alteração de cliente!");
                return;
            }

            int add = ps.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Dados de Usuário alterado com Sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Houve um erro ao alterar o cliente, verifique se está tudo correto! \n Não é possível alterar mais de um cliente com o mesmo CNPJ/CPF! \n É necessário selecionar um cliente da tabela acima para alteração!");
                anular();
            }

            anular();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Todos dados devem estar preenchidos. Alteração não foi feita. ERRO: \n " + e);
        }
    }

    private void delete(){
        int remover = JOptionPane.showConfirmDialog(null,
                "Você deseja remover o cliente selecionado ?",
                "Exclusão de clientes",
                JOptionPane.YES_NO_OPTION);
        if (remover == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM clientes where idclient=?";
            try {
                ps = conexao.prepareStatement(sql);
                ps.setString(1, idbox.getText());

                if (cnpjbox.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente selecionado para exclusão. Utilize a lista de clientes acima para selecionar um cliente para deletar!");
                    anular();
                } else {

                    int exclusao = ps.executeUpdate();
                    System.out.println(exclusao);
                    if (exclusao > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");

                        anular();

                    } else {
                        JOptionPane.showMessageDialog(null, "CPF/CNPJ Inválido ou inexistente! Não foi possível deletar! \n Houve um erro na exclusão!");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Houve um erro na exclusão. ERRO: \n" + e);
                anular();
            }

        } else {
            anular();
        }
    }

    private void selectclient() {

        int select = listclienttable.getSelectedRow();
        idbox.setText(listclienttable.getModel().getValueAt(select, 0).toString());
        nomebox.setText(listclienttable.getModel().getValueAt(select, 1).toString());
        cnpjbox.setText(listclienttable.getModel().getValueAt(select, 2).toString());
        telbox.setText(listclienttable.getModel().getValueAt(select, 3).toString());
        emailbox.setText(listclienttable.getModel().getValueAt(select, 4).toString());
        cidadebox.setText(listclienttable.getModel().getValueAt(select, 5).toString());
        estadobox.setText(listclienttable.getModel().getValueAt(select, 6).toString());
        bairrobox.setText(listclienttable.getModel().getValueAt(select, 7).toString());
        enderecobox.setText(listclienttable.getModel().getValueAt(select, 8).toString());
        observacaobox.setText(listclienttable.getModel().getValueAt(select, 9).toString());
    }

    private void anular(){
        pesquisaclibox.setText("Carregando...");
        nomebox.setText(null);
        cnpjbox.setText(null);
        telbox.setText(null);
        emailbox.setText(null);
        cidadebox.setText(null);
        estadobox.setText(null);
        bairrobox.setText(null);
        enderecobox.setText(null);
        observacaobox.setText(null);
        idbox.setText(null);
        
        pesquisar();
        pesquisaclibox.setText(null);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        clientestexto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cnpjbox = new javax.swing.JTextField();
        nomebox = new javax.swing.JTextField();
        telbox = new javax.swing.JTextField();
        emailbox = new javax.swing.JTextField();
        estadobox = new javax.swing.JTextField();
        cidadebox = new javax.swing.JTextField();
        bairrobox = new javax.swing.JTextField();
        enderecobox = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacaobox = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listclienttable = new javax.swing.JTable();
        pesquisaclibox = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        adduserbutton = new javax.swing.JButton();
        deluserbutton = new javax.swing.JButton();
        upuserbutton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        idbox = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setClosable(true);
        setTitle("Clientes");
        setToolTipText("");
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1232, 554));
        setMinimumSize(new java.awt.Dimension(1232, 554));
        setPreferredSize(new java.awt.Dimension(1232, 554));

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        clientestexto.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        clientestexto.setText("Clientes");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Registre seus clientes!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(clientestexto)
                        .addGap(554, 554, 554))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(480, 480, 480))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clientestexto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Nome ou Empresa:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("CNPJ/CPF:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Endereço:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Telefone:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Cidade:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Estado:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Bairro:");

        cnpjbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cnpjbox.setMaximumSize(new java.awt.Dimension(76, 28));
        cnpjbox.setMinimumSize(new java.awt.Dimension(76, 28));
        cnpjbox.setPreferredSize(new java.awt.Dimension(76, 28));

        nomebox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        nomebox.setMaximumSize(new java.awt.Dimension(76, 28));
        nomebox.setMinimumSize(new java.awt.Dimension(76, 28));
        nomebox.setPreferredSize(new java.awt.Dimension(76, 28));

        telbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        telbox.setMaximumSize(new java.awt.Dimension(76, 28));
        telbox.setMinimumSize(new java.awt.Dimension(76, 28));
        telbox.setPreferredSize(new java.awt.Dimension(76, 28));

        emailbox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        emailbox.setMaximumSize(new java.awt.Dimension(76, 28));
        emailbox.setMinimumSize(new java.awt.Dimension(76, 28));
        emailbox.setPreferredSize(new java.awt.Dimension(76, 28));

        estadobox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        estadobox.setMaximumSize(new java.awt.Dimension(76, 28));
        estadobox.setMinimumSize(new java.awt.Dimension(76, 28));
        estadobox.setPreferredSize(new java.awt.Dimension(76, 28));

        cidadebox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cidadebox.setMaximumSize(new java.awt.Dimension(76, 28));
        cidadebox.setMinimumSize(new java.awt.Dimension(76, 28));
        cidadebox.setPreferredSize(new java.awt.Dimension(76, 28));

        bairrobox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        bairrobox.setMaximumSize(new java.awt.Dimension(76, 28));
        bairrobox.setMinimumSize(new java.awt.Dimension(76, 28));
        bairrobox.setPreferredSize(new java.awt.Dimension(76, 28));

        enderecobox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        enderecobox.setMaximumSize(new java.awt.Dimension(76, 28));
        enderecobox.setMinimumSize(new java.awt.Dimension(76, 28));
        enderecobox.setPreferredSize(new java.awt.Dimension(76, 28));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setText("Observações:");

        observacaobox.setColumns(20);
        observacaobox.setRows(5);
        jScrollPane1.setViewportView(observacaobox);

        listclienttable = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex, int colIndex){
                return false;
            }
        };
        listclienttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "CPF/CNPJ", "FONE", "EMAIL", "CIDADE", "ESTADO", "BAIRRO", "ENDEREÇO", "FONE"
            }
        ));
        listclienttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listclienttableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listclienttable);

        pesquisaclibox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        pesquisaclibox.setMaximumSize(new java.awt.Dimension(76, 28));
        pesquisaclibox.setMinimumSize(new java.awt.Dimension(76, 28));
        pesquisaclibox.setPreferredSize(new java.awt.Dimension(76, 28));
        pesquisaclibox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisacliboxKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jLabel12.setText("(Obrigatório)");

        jLabel13.setText("(Obrigatório)");

        jLabel14.setText("(Obrigatório)");

        jLabel15.setText("(Obrigatório)");

        jLabel16.setText("(Obrigatório)");

        jLabel17.setText("(Obrigatório)");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("ID:");

        idbox.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        idbox.setEnabled(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gerlocacao/icones/anular.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(cnpjbox, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idbox, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(465, 465, 465)
                                .addComponent(jLabel13))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(estadobox, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bairrobox, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cidadebox, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(enderecobox, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel15)))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(480, 480, 480))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(upuserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(deluserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(167, 167, 167)
                            .addComponent(adduserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(nomebox, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(telbox, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(emailbox, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pesquisaclibox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(idbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cnpjbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(telbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(emailbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel17))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(estadobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bairrobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(cidadebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enderecobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deluserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adduserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(upuserbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(319, 319, 319))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pesquisaclibox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setBounds(0, 0, 1232, 554);
    }// </editor-fold>//GEN-END:initComponents

    private void adduserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adduserbuttonActionPerformed
        adicionar();
    }//GEN-LAST:event_adduserbuttonActionPerformed

    private void deluserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deluserbuttonActionPerformed
       
            delete();
       
    }//GEN-LAST:event_deluserbuttonActionPerformed

    private void upuserbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upuserbuttonActionPerformed
        alterar();
    }//GEN-LAST:event_upuserbuttonActionPerformed
// Método key realeased, enquanto digitar, vai pesquisar automaticamente
    private void pesquisacliboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisacliboxKeyReleased
        pesquisar();
    }//GEN-LAST:event_pesquisacliboxKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pesquisar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listclienttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listclienttableMouseClicked
        selectclient();
    }//GEN-LAST:event_listclienttableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
            anular();
       
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adduserbutton;
    private javax.swing.JTextField bairrobox;
    private javax.swing.JTextField cidadebox;
    private javax.swing.JLabel clientestexto;
    private javax.swing.JTextField cnpjbox;
    private javax.swing.JButton deluserbutton;
    private javax.swing.JTextField emailbox;
    private javax.swing.JTextField enderecobox;
    private javax.swing.JTextField estadobox;
    private javax.swing.JTextField idbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listclienttable;
    private javax.swing.JTextField nomebox;
    private javax.swing.JTextArea observacaobox;
    private javax.swing.JTextField pesquisaclibox;
    private javax.swing.JTextField telbox;
    private javax.swing.JButton upuserbutton;
    // End of variables declaration//GEN-END:variables
}

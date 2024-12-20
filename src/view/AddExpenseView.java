/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import enums.ExpenseCategory;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.IllegalFormatException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Expense;
import model.FinancialControl;
import utils.ConverterUtils;

/**
 *
 * @author lucas
 */
public class AddExpenseView extends javax.swing.JFrame {

    public static AddExpenseView addExpenseView;

    /**
     * Creates new form AddIncomeView
     */
    public AddExpenseView() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);

        listExpenseCategory();
    }

    /**
     * Retorna uma instância da class AddExpenseView
     *
     * @return AddExpenseView
     */
    public static AddExpenseView getAddExpenseView() {
        if (addExpenseView == null) {
            addExpenseView = new AddExpenseView();
        }

        return addExpenseView;
    }
    
    /**
     * Inicializa a janela, configurando os dados iniciais de despesas para
     * exibição.
     *
     */
    public void screen() {
        listExpense();
        resetInteractions();
    }

    /**
     * Preenche a tabela com a lista de despesas cadastradas.
     *
     */
    private void listExpense() {
        try {
            DefaultTableModel tableModel = new DefaultTableModel();

            tableModel.addColumn("Valor");
            tableModel.addColumn("Data");
            tableModel.addColumn("Categoria");

            jExpenseTable.setRowHeight(35);

            List<Expense> listExpense = FinancialControl.listExpense();

            for (Expense expense : listExpense) {
                Object[] row = {
                    ConverterUtils.formatToCurrency(expense.getAmount()),
                    ConverterUtils.formatToDate(expense.getDateTime()),
                    expense.getExpenseCategory()
                };

                tableModel.addRow(row);
            }

            jExpenseTable.setModel(tableModel);
            jExpenseTable.setVisible(false);
            jExpenseTable.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Preenche o `JComboBox` com as categorias de despesas disponíveis.
     *
     */
    private void listExpenseCategory() {
        for (ExpenseCategory expenseCategory : ExpenseCategory.values()) {
            jExpenseCategory.addItem(expenseCategory);
        }
    }

    /**
     * Exibe a tela principal da aplicação e fecha a tela atual.
     *
     */
    private void showMainView() {
        MainView mainView = MainView.getMainView();
        mainView.screen();
        mainView.setVisible(true);

        dispose();
    }

    /**
     * Formata os dados do formulário e envia para a camada de controle para
     * salvar uma despesa.
     *
     */
    private void saveExpense() {
        try {
            LocalDateTime dateTime = ConverterUtils.convertToLocalDateTime(jDateTime.getText());
            ExpenseCategory expenseCategory = (ExpenseCategory) jExpenseCategory.getSelectedItem();
            ConverterUtils.validCategory(null, expenseCategory);
            BigDecimal amount = ConverterUtils.convertToAmount(jAmount.getText());

            FinancialControl.createExpense(amount, expenseCategory, dateTime);
            JOptionPane.showMessageDialog(this, "Despesa adicionada com sucesso");

            listExpense();
            resetInteractions();
        } catch (IOException | IllegalArgumentException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpa os campos do formulário de despesa.
     *
     */
    private void resetInteractions() {
        jDateTime.setText("");
        jExpenseCategory.setSelectedIndex(0);
        jAmount.setText("000000000000000000");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMain = new javax.swing.JPanel();
        jExpenseTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jExpenseTable = new javax.swing.JTable();
        jExpense = new javax.swing.JPanel();
        jExpenseForm = new javax.swing.JPanel();
        jDateTime = new javax.swing.JFormattedTextField();
        jDateTimeLabel = new javax.swing.JLabel();
        jAmountLabel = new javax.swing.JLabel();
        jExpenseCategoryLabel = new javax.swing.JLabel();
        jExpenseCategory = new javax.swing.JComboBox<>();
        jSaveExpense = new javax.swing.JToggleButton();
        jBackWindow = new javax.swing.JToggleButton();
        jAmount = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jExpenseTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jExpenseTitle.setText("Despesas");

        jExpenseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jExpenseTable);

        jExpenseForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Adicionar Despesa"));

        try {
            jDateTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jDateTime.setPreferredSize(new java.awt.Dimension(300, 40));
        jDateTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateTimeMouseClicked(evt);
            }
        });

        jDateTimeLabel.setText("Data do lançamento");

        jAmountLabel.setText("Valor");

        jExpenseCategoryLabel.setText("Tipo de despesa");

        jExpenseCategory.setPreferredSize(new java.awt.Dimension(300, 40));

        jSaveExpense.setText("Salvar Despesa");
        jSaveExpense.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSaveExpense.setPreferredSize(new java.awt.Dimension(200, 40));
        jSaveExpense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSaveExpenseMouseClicked(evt);
            }
        });

        jBackWindow.setText("Voltar");
        jBackWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBackWindow.setPreferredSize(new java.awt.Dimension(200, 40));
        jBackWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBackWindowMouseClicked(evt);
            }
        });

        try {
            jAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.###.###,##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jAmount.setPreferredSize(new java.awt.Dimension(300, 40));
        jAmount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAmountMouseClicked(evt);
            }
        });
        jAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAmountKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jExpenseFormLayout = new javax.swing.GroupLayout(jExpenseForm);
        jExpenseForm.setLayout(jExpenseFormLayout);
        jExpenseFormLayout.setHorizontalGroup(
            jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jExpenseFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAmountLabel)
                    .addGroup(jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jExpenseFormLayout.createSequentialGroup()
                            .addComponent(jBackWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jSaveExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jExpenseFormLayout.createSequentialGroup()
                            .addGroup(jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateTimeLabel)
                                .addComponent(jDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(80, 80, 80)
                            .addGroup(jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jExpenseCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jExpenseCategoryLabel))))
                    .addComponent(jAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jExpenseFormLayout.setVerticalGroup(
            jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jExpenseFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jExpenseFormLayout.createSequentialGroup()
                        .addComponent(jDateTimeLabel)
                        .addGap(5, 5, 5)
                        .addComponent(jDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jExpenseFormLayout.createSequentialGroup()
                        .addComponent(jExpenseCategoryLabel)
                        .addGap(5, 5, 5)
                        .addComponent(jExpenseCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jAmountLabel)
                .addGap(5, 5, 5)
                .addComponent(jAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jExpenseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSaveExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBackWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jExpenseLayout = new javax.swing.GroupLayout(jExpense);
        jExpense.setLayout(jExpenseLayout);
        jExpenseLayout.setHorizontalGroup(
            jExpenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jExpenseLayout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(jExpenseForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jExpenseLayout.setVerticalGroup(
            jExpenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jExpenseLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jExpenseForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jMainLayout = new javax.swing.GroupLayout(jMain);
        jMain.setLayout(jMainLayout);
        jMainLayout.setHorizontalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jMainLayout.createSequentialGroup()
                        .addComponent(jExpenseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addComponent(jExpense, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jMainLayout.setVerticalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jExpenseTitle)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBackWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBackWindowMouseClicked
        jBackWindow.setSelected(false);
        showMainView();
    }//GEN-LAST:event_jBackWindowMouseClicked

    private void jSaveExpenseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSaveExpenseMouseClicked
        jSaveExpense.setSelected(false);
        saveExpense();
    }//GEN-LAST:event_jSaveExpenseMouseClicked

    private void jDateTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateTimeMouseClicked
        if (jDateTime.getText().equals("  /  /    ")) {
            jDateTime.setCaretPosition(0);
        }
    }//GEN-LAST:event_jDateTimeMouseClicked

    private void jAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAmountKeyReleased
        try {
            if (Character.isDigit(evt.getKeyChar())) {
                String formattedText = ConverterUtils.formatAmountInput(jAmount.getText(), evt.getKeyChar());  
                
                jAmount.setText(formattedText);
                jAmount.setCaretPosition(jAmount.getText().length());
            }

            if (
                evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || 
                evt.getKeyCode() == KeyEvent.VK_DELETE || 
                evt.getKeyCode() == KeyEvent.VK_ESCAPE
            ) {
                String formattedText = ConverterUtils.formatAmountOnDelete(jAmount.getText());
                
                jAmount.setText(formattedText);
                jAmount.setCaretPosition(jAmount.getText().length());
            }
        } catch (NumberFormatException | IllegalFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar o campo de valor", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jAmountKeyReleased

    private void jAmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAmountMouseClicked
        jAmount.setCaretPosition(jAmount.getText().length());
    }//GEN-LAST:event_jAmountMouseClicked

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
            java.util.logging.Logger.getLogger(AddExpenseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddExpenseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddExpenseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddExpenseView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddExpenseView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jAmount;
    private javax.swing.JLabel jAmountLabel;
    private javax.swing.JToggleButton jBackWindow;
    private javax.swing.JFormattedTextField jDateTime;
    private javax.swing.JLabel jDateTimeLabel;
    private javax.swing.JPanel jExpense;
    private javax.swing.JComboBox<ExpenseCategory> jExpenseCategory;
    private javax.swing.JLabel jExpenseCategoryLabel;
    private javax.swing.JPanel jExpenseForm;
    private javax.swing.JTable jExpenseTable;
    private javax.swing.JLabel jExpenseTitle;
    private javax.swing.JPanel jMain;
    private javax.swing.JToggleButton jSaveExpense;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}


package todolist;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class viewtodolist extends javax.swing.JFrame {
private DefaultTableModel tableModel;

    /**
     * Creates new form viewtodolist
     */
    public viewtodolist() {
        initComponents();
        koneksi.initializeDatabase();
        loadTasks();
        
    // Inisialisasi model tabel setelah komponen GUI diinisialisasi
    tableModel = new DefaultTableModel(new String[]{"No", "Tugas", "Deadline"}, 0);
    jTable1.setModel(tableModel);
    }
    
    
    private void loadTasks() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        try (Connection conn = koneksi.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tugas")) { // Menggunakan tabel 'tugas' yang benar
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getDate("deadline").toString(),
                    rs.getBoolean("status") ? "Selesai" : "Belum Selesai"
                });
            }
        } catch (SQLException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private void addTask(String judul, String deadline) {
        try (Connection conn = koneksi.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO tugas (judul, deadline) VALUES (?, ?)")) {
            stmt.setString(1, judul);
            stmt.setString(2, deadline);               // Set tanggal deadline
            stmt.executeUpdate();
            loadTasks();                               // Refresh tabel
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    private void deleteTask(int id) {
        try (Connection conn = koneksi.connect();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tugas WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            loadTasks();
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
    
    private void updateTask(int id, String judul, String deadline){
    try (Connection conn = koneksi.connect();
         PreparedStatement stmt = conn.prepareStatement("UPDATE tugas SET judul = ?, deadline = ? WHERE id = ?")) {
        stmt.setString(1, judul);
        stmt.setString(2, deadline);
        stmt.setInt(3, id);
        stmt.executeUpdate();
        loadTasks();
    } catch (SQLException e) {
        System.out.println("Error updating task: " + e.getMessage());
    }
}

    
    private void setupTableClickListener() {
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow != -1) { 
                // Assuming ID, Title, and Deadline are in columns 0, 1, and 2, respectively
                String no = jTable1.getValueAt(selectedRow, 0).toString();
                String judul = jTable1.getValueAt(selectedRow, 1).toString();
                String deadline = jTable1.getValueAt(selectedRow, 2).toString();

                // Populate the text fields with data from the selected row
                jTextField1.setText(no);
                jTextField2.setText(judul);
                jTextField3.setText(deadline);
            }
        }
    });
}
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Aplikasi To-Do List");

        jLabel2.setText("No :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Buat tugas :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tanggal deadline : ");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "No", "Tugas", "Deadline"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jButton1.setText("SIMPAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jButton2.setText("EDIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("List Tugas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jTextField2)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(26, 26, 26)
                .addComponent(jButton2)
                .addGap(37, 37, 37)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:     
    String no = jTextField1.getText();
    String judul = jTextField2.getText();
    String deadlineInput = jTextField3.getText();

    if (!judul.isEmpty() && !deadlineInput.isEmpty()) { // Pastikan field tidak kosong
        try {
            // Format input dan pastikan output format SQL dalam bentuk 'yyyy-MM-dd'
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            // Parsing tanggal input untuk memastikan validitas format
            Date parsedDate = inputFormat.parse(deadlineInput);
            String deadline = outputFormat.format(parsedDate); // Konversi ke format database
            
            addTask(judul, deadline); // Tambahkan tugas ke database
            
            // Bersihkan input setelah menyimpan
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            
            // Tambahkan data ke dalam tableModel
            tableModel.addRow(new Object[]{no, judul, deadline});
        } catch (Exception e) { // Gunakan pengecualian umum
            System.out.println("Error parsing date or saving task: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, "Error: Pastikan tanggal dalam format MM/dd/yyyy.");
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Judul dan Deadline tidak boleh kosong.");
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
    if (selectedRow != -1) { 
        int taskId = (int) jTable1.getValueAt(selectedRow, 0); // Assuming ID is in column 0
        String judul = jTextField2.getText();
        String deadlineInput = jTextField3.getText();

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = inputFormat.parse(deadlineInput);
            String deadline = outputFormat.format(parsedDate);

            updateTask(taskId, judul, deadline); // Update the database

            javax.swing.JOptionPane.showMessageDialog(this, "Data berhasil diedit");

            // Clear input fields
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
        } catch (Exception e) {
            System.out.println("Error parsing date or updating task: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, "Error: Pastikan tanggal dalam format MM/dd/yyyy.");
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Pilih baris yang akan diedit terlebih dahulu");
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       int selectedRow = jTable1.getSelectedRow(); 
    if (selectedRow != -1) { 
        int taskId = (int) jTable1.getValueAt(selectedRow, 0); // Assuming ID is in column 0
        deleteTask(taskId); // Delete from the database
        loadTasks(); // Refresh table to reflect deletion
        javax.swing.JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus terlebih dahulu");
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewtodolist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}


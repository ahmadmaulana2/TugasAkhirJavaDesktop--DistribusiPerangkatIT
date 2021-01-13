/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainForm;

import Connection.konek;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Font;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ahmad Maulana (201643500312).
 */
public class DataPemasok extends javax.swing.JFrame {

    /**
     * Creates new form DataPemasok
     */
    
    private DefaultTableModel tabmode;
    
    public DataPemasok() {
        initComponents();
        ImageIcon ico = new ImageIcon("src/Images/logom2.png");
        setIconImage(ico.getImage());
        this.setLocationRelativeTo(null);
        autoNumber();
        non_aktif();
        tb_pemasok();
        new Thread(){
            public void run(){
                while(true){
                Calendar kal = new GregorianCalendar();
                int tahun = kal.get(Calendar.YEAR);
                int bulan = kal.get(Calendar.MONTH)+1;
                int hari = kal.get(Calendar.DAY_OF_MONTH);
                int jam = kal.get(Calendar.HOUR_OF_DAY);
                int menit = kal.get(Calendar.MINUTE);
                int detik = kal.get(Calendar.SECOND);
                String tanggal = hari + "-"+bulan+"-"+tahun;
                String waktu1 = jam + ":"+menit+":"+detik;
                String waktu2 = jam + ":"+menit+":"+detik;
                jLabel6.setText(tanggal);
                jLabel10.setText(waktu1);
                }
            }
        }.start();
        
        tb_pemasok.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tb_pemasok.getTableHeader().setOpaque(false);
        tb_pemasok.getTableHeader().setBackground(new Color(52,92,255));
        tb_pemasok.getTableHeader().setForeground(new Color(255,255,255));
        tb_pemasok.setRowHeight(25);
    }
    
    private void non_aktif() {
        txt_kdpemasok.setEnabled(false);
        txt_nmpemasok.setEnabled(false);
        txt_almt.setEnabled(false);
        txt_telp.setEnabled(false);
        txt_email.setEnabled(false);
    }
    
    private void aktif() {
        txt_nmpemasok.setEnabled(true);
        txt_almt.setEnabled(true);
        txt_telp.setEnabled(true);
        txt_email.setEnabled(true);
        txt_kdpemasok.requestFocus();
    }
    
     private void kosong() {
        txt_nmpemasok.setText("");
        txt_almt.setText("");
        txt_telp.setText("");
        txt_email.setText("");
        btn_save.setEnabled(true);
        btn_edit.setEnabled(true);
        btn_hapus.setEnabled(true);
    }
    
    private void autoNumber() {
        try {
            String sql = "SELECT * FROM tb_pemasok ORDER BY kd_sup DESC";
            PreparedStatement stat = konek.koneksiDb().prepareCall(sql);
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("kd_sup").substring(3);
                String AN =  "" + (Integer.parseInt(kd) + 1);
                String Nol = "";

                if (AN.length()==1) {
                    Nol = "000";
                } else if (AN.length()==2) {
                    Nol = "00";
                } else if (AN.length()==3) {
                    Nol = "0";
                } else if (AN.length()==4) {
                    Nol = "";
                }
                txt_kdpemasok.setText("PS" + Nol + AN);
            } else {
                txt_kdpemasok.setText("PS0001");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void tb_pemasok() {
        Object[] Baris = {"Kode Pemasok", "Nama Pemasok", "Alamat", "No.Telp", "E-Mail"};
        tabmode = new DefaultTableModel(null, Baris);
        tb_pemasok.setModel(tabmode);
        String sql = "select * from tb_pemasok";
        try {
            java.sql.Statement stat = konek.koneksiDb().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kd_sup = hasil.getString("kd_sup");
                String nm_sup = hasil.getString("nm_sup");
                String almt = hasil.getString("almt");
                String no_telp = hasil.getString("no_telp");
                String email = hasil.getString("email");
                String[] data = {kd_sup,nm_sup,almt,no_telp,email};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_kdpemasok = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_nmpemasok = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_telp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_cari = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_exit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 12), new java.awt.Dimension(0, 12), new java.awt.Dimension(32767, 12));
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_almt = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        btn_edit1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pemasok = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Karyawan");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1381, 776));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 830));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setText("DATA PEMASOK");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kode Pemasok");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nama Pemasok");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("No. Telp");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("E-Mail");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Alamat");

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_20px.png"))); // NOI18N
        btn_cari.setText("CARI");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        btn_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit_20px.png"))); // NOI18N
        btn_exit.setText("KELUAR");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete_20px.png"))); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update_file_filled_20px.png"))); // NOI18N
        btn_edit.setText("EDIT");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save_20px.png"))); // NOI18N
        btn_save.setText("TAMBAH");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logom2-.png"))); // NOI18N

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator3.setPreferredSize(new java.awt.Dimension(50, 10));

        txt_almt.setColumns(20);
        txt_almt.setRows(5);
        jScrollPane2.setViewportView(txt_almt);

        btn_edit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cancel_20px.png"))); // NOI18N
        btn_edit1.setText("CANCEL");
        btn_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit1ActionPerformed(evt);
            }
        });

        tb_pemasok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_pemasok.getTableHeader().setResizingAllowed(false);
        tb_pemasok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pemasokMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pemasok);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setText("JAM");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("TANGGAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(444, 444, 444))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btn_edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(76, 76, 76)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(83, 83, 83)
                                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_nmpemasok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_kdpemasok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8)))
                .addGap(72, 72, 72))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(566, 566, 566)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(861, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_save)
                            .addComponent(btn_edit)
                            .addComponent(btn_hapus))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_exit)
                            .addComponent(btn_edit1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_kdpemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cari)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_nmpemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel7)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(80, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(135, 135, 135)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        String tombol = btn_cari.getText();
        if (tombol.equals("Cari")){
            Object[] Baris = {"Kode Pemasok", "Nama Pemasok", "Alamat", "No.Telp", "E-Mail"};
            tabmode = new DefaultTableModel(null, Baris);
            tb_pemasok.setModel(tabmode);
            String sql = "Select * from tb_pemasok where kd_sup like '%" + txt_cari.getText() + "%'" +
            "or nm_sup like '%" + txt_cari.getText() + "%'";
            try {
                java.sql.Statement stat = konek.koneksiDb().createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()) {
                    String kd_sup = hasil.getString("kd_sup");
                    String nm_sup = hasil.getString("nm_sup");
                    String almt = hasil.getString("almt");
                    String no_telp = hasil.getString("no_telp");
                    String email = hasil.getString("email");
                    String[] data = {kd_sup,nm_sup,almt,no_telp,email};
                    tabmode.addRow(data);
                }
            } catch (Exception e) {
            }
            btn_cari.setText("Batal");
        }else{
            tb_pemasok();
            btn_cari.setText("Cari");
            txt_cari.setText("");
            btn_save.setEnabled(true);
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        dispose();
        FormAdmin fa = new FormAdmin();
        fa.show();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin Menghapus Data Ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from tb_pemasok where kd_sup='"+txt_kdpemasok.getText() + "'";
            try {
                PreparedStatement stat = konek.koneksiDb().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Pemasok Berhasil Dihapus");
                kosong();
                txt_kdpemasok.requestFocus();
                tb_pemasok();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Pemasok Gagal Di Hapus" + e);
            }
            new DataPemasok().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        if (btn_edit.getText()=="EDIT") {
            aktif();
            btn_edit.setText("UPDATE");
            btn_hapus.setEnabled(false);
            btn_save.setEnabled(false);
        } else {
            String sql = "update tb_pemasok set nm_sup=?, almt=?, no_telp=?, email=? where kd_sup='"+txt_kdpemasok.getText()+"'";
            try {
                PreparedStatement stat = konek.koneksiDb().prepareStatement(sql);
                stat.setString(1, txt_nmpemasok.getText());
                stat.setString(2, txt_almt.getText());
                stat.setString(3, txt_telp.getText());
                stat.setString(4, txt_email.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Pemasok Berhasil Di Update");
                btn_edit.setText("EDIT");
                tb_pemasok();
                autoNumber();
                kosong();
                non_aktif();
                new DataPemasok().setVisible(true);
                this.dispose();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Pemasok Gagal Di Update" + e);
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        String tombol = btn_save.getText();
        String kd_sup = txt_kdpemasok.getText();
        String nm_sup = txt_nmpemasok.getText();
        String almt = txt_almt.getText();
        String no_telp = txt_telp.getText();
        String email = txt_email.getText();
               
        if (tombol.equals("TAMBAH")) {
            aktif();
            kosong();
            btn_save.setText("SIMPAN");
            btn_edit.setEnabled(false);
            btn_hapus.setEnabled(false);
            }else {
            JOptionPane.showConfirmDialog(null, "Apakah Data anda sudah benar?", "INFORMASI",JOptionPane.YES_NO_OPTION);
                        try {
                            Connection c = konek.koneksiDb();
                            String sql = "INSERT INTO tb_pemasok VALUES (?, ?, ?, ?, ?)";
                            PreparedStatement p = c.prepareStatement(sql);
                            p.setString(1, kd_sup);
                            p.setString(2, nm_sup);
                            p.setString(3, almt);
                            p.setString(4, no_telp);
                            p.setString(5, email);
                            p.executeUpdate();
                            p.close();
                            txt_kdpemasok.requestFocus();
                            } catch (SQLException e) {
                            System.out.println("Terjadi Error");
                            }finally{
                                String   msg="<html>Kode Pemasok         = " +txt_kdpemasok.getText()+" <br>"
                                + "Nama Pemasok        = " +txt_nmpemasok.getText()+"<br>"
                                + "Alamat              = " +txt_almt.getText()+"<br>"
                                + "No.Telp              = " +txt_telp.getText()+"<br>"
                                + "E-Mail             = " +txt_email.getText()+"<html>";
                                JOptionPane optionPane=new JOptionPane();
                                optionPane.setMessage(msg);
                                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                                JDialog dialog=optionPane.createDialog(null, "DATA DISIMPAN");
                                dialog.setVisible(true);
                            }
                            new DataPemasok().setVisible(true);
                            this.dispose();
                            autoNumber();
                            tb_pemasok();
                            btn_save.setText("TAMBAH");
                            non_aktif();
            }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit1ActionPerformed
        // TODO add your handling code here:
        kosong();
        non_aktif();
    }//GEN-LAST:event_btn_edit1ActionPerformed

    private void tb_pemasokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pemasokMouseClicked
        // TODO add your handling code here:
        btn_edit.setEnabled(true);
        btn_edit.setText("EDIT");
        btn_hapus.setEnabled(true);
        int bar = tb_pemasok.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        txt_kdpemasok.setText(a);
        txt_nmpemasok.setText(b);
        txt_almt.setText(c);
        txt_telp.setText(d);
        txt_email.setText(e);
    }//GEN-LAST:event_tb_pemasokMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPemasok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPemasok().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_edit1;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_save;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tb_pemasok;
    private javax.swing.JTextArea txt_almt;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_kdpemasok;
    private javax.swing.JTextField txt_nmpemasok;
    private javax.swing.JTextField txt_telp;
    // End of variables declaration//GEN-END:variables
}

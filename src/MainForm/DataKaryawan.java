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
public class DataKaryawan extends javax.swing.JFrame {

    /**
     * Creates new form DataKaryawan
     */
    
    private DefaultTableModel tabmode;
    
    public DataKaryawan() {
        initComponents();
        ImageIcon ico = new ImageIcon("src/Images/logom2.png");
        setIconImage(ico.getImage());
        this.setLocationRelativeTo(null);
        autoNumber();
        non_aktif();
        tb_karyawan();
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
        
        tb_karyawan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tb_karyawan.getTableHeader().setOpaque(false);
        tb_karyawan.getTableHeader().setBackground(new Color(52,92,255));
        tb_karyawan.getTableHeader().setForeground(new Color(255,255,255));
        tb_karyawan.setRowHeight(25);
    }
    
    private void non_aktif() {
        txt_kdkrywn.setEnabled(false);
        txt_nmkrywn.setEnabled(false);
        cmb_jabatan.setEnabled(false);
        cmb_divisi.setEnabled(false);
        txt_almt.setEnabled(false);
        txt_telp.setEnabled(false);
        txt_pw.setEnabled(false);
    }
    
    private void aktif() {
        txt_nmkrywn.setEnabled(true);
        cmb_jabatan.setEnabled(true);
        cmb_divisi.setEnabled(true);
        txt_almt.setEnabled(true);
        txt_telp.setEnabled(true);
        txt_pw.setEnabled(true);
        txt_kdkrywn.requestFocus();
    }
    
     private void kosong() {
        txt_nmkrywn.setText("");
        cmb_jabatan.setSelectedItem("");
        cmb_divisi.setSelectedItem("");
        txt_almt.setText("");
        txt_telp.setText("");
        txt_pw.setText("");
        btn_save.setEnabled(true);
        btn_edit.setEnabled(true);
        btn_hapus.setEnabled(true);
    }
    
    private void autoNumber() {
        try {
            String sql = "SELECT * FROM tb_karyawan ORDER BY kd_karyawan DESC";
            PreparedStatement stat = konek.koneksiDb().prepareCall(sql);
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("kd_karyawan").substring(3);
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
                txt_kdkrywn.setText("IM" + Nol + AN);
            } else {
                txt_kdkrywn.setText("IM0001");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void tb_karyawan() {
            Object[] Baris = {"Kode Karyawan", "Nama Karyawan", "Jabatan", "Divisi", "Alamat", "No.Telp", "Password"};
        tabmode = new DefaultTableModel(null, Baris);
        tb_karyawan.setModel(tabmode);
        String sql = "select * from tb_karyawan";
        try {
            java.sql.Statement stat = konek.koneksiDb().createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kd_karyawan = hasil.getString("kd_karyawan");
                String nm_karyawan = hasil.getString("nm_karyawan");
                String jbtn = hasil.getString("jbtn");
                String divisi = hasil.getString("divisi");
                String almt = hasil.getString("almt");
                String no_telp = hasil.getString("no_telp");
                String password = hasil.getString("password");
                String[] data = {kd_karyawan,nm_karyawan,jbtn,divisi,almt,no_telp,password};
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
        txt_kdkrywn = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_nmkrywn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_telp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmb_jabatan = new javax.swing.JComboBox();
        txt_pw = new javax.swing.JPasswordField();
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
        jLabel11 = new javax.swing.JLabel();
        cmb_divisi = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_almt = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        btn_edit1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_karyawan = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Karyawan");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1381, 776));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 830));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setText("DATA KARYAWAN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kode Karyawan");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nama Karyawan");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("No. Telp");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Jabatan");

        cmb_jabatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jabatan :", "President Director", "Director", "Manager", "Sekretaris", "Staff Gudang", "Staff", "Admin IT", "IT Leader" }));

        txt_pw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pwActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Password");

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

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Divisi");

        cmb_divisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Divisi :", "Marketing", "IT Development", "Finance", "Sales", "Sitac", "Planning", "Customer Service", "People Management", "Corporate Service" }));

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

        tb_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_karyawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_karyawan);

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
                                .addGap(77, 77, 77)
                                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(45, 45, 45)
                                        .addComponent(txt_pw, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7))
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                            .addComponent(txt_kdkrywn)
                                            .addComponent(txt_telp)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11))
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmb_divisi, 0, 228, Short.MAX_VALUE)
                                            .addComponent(cmb_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_nmkrywn))))))
                        .addGap(167, 167, 167)
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
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(563, 563, 563)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(864, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(289, 289, 289)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_save)
                            .addComponent(btn_edit)
                            .addComponent(btn_hapus))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_exit)
                            .addComponent(btn_edit1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_kdkrywn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cari)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_nmkrywn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmb_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmb_divisi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_telp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(68, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(118, 118, 118)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        String tombol = btn_cari.getText();
        if (tombol.equals("Cari")){
            Object[] Baris = {"Kode Karyawan", "Nama Karyawan", "Jabatan", "Divisi", "Alamat", "No.Telp", "Password"};
            tabmode = new DefaultTableModel(null, Baris);
            tb_karyawan.setModel(tabmode);
            String sql = "Select * from tb_karyawan where kd_karyawan like '%" + txt_cari.getText() + "%'" +
            "or nm_karyawan like '%" + txt_cari.getText() + "%'";
            try {
                java.sql.Statement stat = konek.koneksiDb().createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()) {
                    String kd_karyawan = hasil.getString("kd_karyawan");
                    String nm_karyawan = hasil.getString("nm_karyawan");
                    String jbtn = hasil.getString("jbtn");
                    String divisi = hasil.getString("divisi");
                    String almt = hasil.getString("almt");
                    String no_telp = hasil.getString("no_telp");
                    String password = hasil.getString("password");
                    String[] data = {kd_karyawan,nm_karyawan,jbtn,divisi,almt,no_telp,password};
                    tabmode.addRow(data);
                }
            } catch (Exception e) {
            }
            btn_cari.setText("Batal");
        }else{
            tb_karyawan();
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
            String sql = "delete from tb_karyawan where kd_karyawan='"+txt_kdkrywn.getText() + "'";
            try {
                PreparedStatement stat = konek.koneksiDb().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil Dihapus");
                kosong();
                txt_kdkrywn.requestFocus();
                tb_karyawan();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Karyawan Gagal Di Hapus" + e);
            }
            new DataKaryawan().setVisible(true);
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
            String sql = "update tb_karyawan set nm_karyawan=?, jbtn=?, divisi=?, almt=?, no_telp=?, password=? where kd_karyawan='"+txt_kdkrywn.getText()+"'";
            try {
                PreparedStatement stat = konek.koneksiDb().prepareStatement(sql);
                stat.setString(1, txt_nmkrywn.getText());
                stat.setString(2, (String) cmb_jabatan.getSelectedItem());
                stat.setString(3, (String) cmb_divisi.getSelectedItem());
                stat.setString(4, txt_almt.getText());
                stat.setString(5, txt_telp.getText());
                stat.setString(6, txt_pw.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil Di Update");
                btn_edit.setText("EDIT");
                tb_karyawan();
                autoNumber();
                kosong();
                non_aktif();
                new DataKaryawan().setVisible(true);
                this.dispose();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Karyawan Gagal Di Update" + e);
            }
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        String tombol = btn_save.getText();
        String kd_karyawan = txt_kdkrywn.getText();
        String nm_karyawan = txt_nmkrywn.getText();
        String jbtn = (String) cmb_jabatan.getSelectedItem();
        String divisi = (String) cmb_divisi.getSelectedItem();
        String almt = txt_almt.getText();
        String no_telp = txt_telp.getText();
        String password = txt_pw.getText();
               
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
                            String sql = "INSERT INTO tb_karyawan VALUES (?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement p = c.prepareStatement(sql);
                            p.setString(1, kd_karyawan);
                            p.setString(2, nm_karyawan);
                            p.setString(3, jbtn);
                            p.setString(4, divisi);
                            p.setString(5, almt);
                            p.setString(6, no_telp);
                            p.setString(7, password);
                            p.executeUpdate();
                            p.close();
                            txt_kdkrywn.requestFocus();
                            } catch (SQLException e) {
                            System.out.println("Terjadi Error");
                            }finally{
                                String   msg="<html>Kode Karyawan         = " +txt_kdkrywn.getText()+" <br>"
                                + "Nama Karyawan        = " +txt_nmkrywn.getText()+"<br>"
                                + "Jabatan        = " +cmb_jabatan.getSelectedItem()+"<br>"
                                + "Divisi        = " +cmb_divisi.getSelectedItem()+"<br>"
                                + "Alamat              = " +txt_almt.getText()+"<br>"
                                + "No.Telp              = " +txt_telp.getText()+"<br>"
                                + "Password             = " +txt_pw.getText()+"<html>";
                                JOptionPane optionPane=new JOptionPane();
                                optionPane.setMessage(msg);
                                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                                JDialog dialog=optionPane.createDialog(null, "DATA DISIMPAN");
                                dialog.setVisible(true);
                            }
                            new DataKaryawan().setVisible(true);
                            this.dispose();
                            autoNumber();
                            tb_karyawan();
                            btn_save.setText("TAMBAH");
                            non_aktif();
            }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void txt_pwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pwActionPerformed

    private void btn_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit1ActionPerformed
        // TODO add your handling code here:
        kosong();
        non_aktif();
    }//GEN-LAST:event_btn_edit1ActionPerformed

    private void tb_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_karyawanMouseClicked
        // TODO add your handling code here:
        btn_edit.setEnabled(true);
        btn_edit.setText("EDIT");
        btn_hapus.setEnabled(true);
        int bar = tb_karyawan.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        txt_kdkrywn.setText(a);
        txt_nmkrywn.setText(b);
        cmb_jabatan.setSelectedItem(c);
        cmb_divisi.setSelectedItem(d);
        txt_almt.setText(e);
        txt_telp.setText(f);
        txt_pw.setText(g);
    }//GEN-LAST:event_tb_karyawanMouseClicked

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
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new DataKaryawan().setVisible(true);
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
    private javax.swing.JComboBox cmb_divisi;
    private javax.swing.JComboBox cmb_jabatan;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tb_karyawan;
    private javax.swing.JTextArea txt_almt;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kdkrywn;
    private javax.swing.JTextField txt_nmkrywn;
    private javax.swing.JPasswordField txt_pw;
    private javax.swing.JTextField txt_telp;
    // End of variables declaration//GEN-END:variables
}

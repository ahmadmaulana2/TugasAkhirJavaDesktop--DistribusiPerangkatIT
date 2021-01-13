/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainForm;

import Connection.konek;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ahmad Maulana (201643500312).
 */
public class DataDistribusi extends javax.swing.JFrame {

    /**
     * Creates new form DataDistribusi
     */
    
    private DefaultTableModel tabmode;
    
    public DataDistribusi() {
        initComponents();
        ImageIcon ico = new ImageIcon("src/Images/logom2.png");
        setIconImage(ico.getImage());
        this.setLocationRelativeTo(null);
        non_aktif();
        barangkeluar();
        waktu();
        autoNumber();
        tampilCombokry();
        tbldistribusi();
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
                jLabel11.setText(waktu1);
                }
            }
        }.start();
        
        tbldistribusi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbldistribusi.getTableHeader().setOpaque(false);
        tbldistribusi.getTableHeader().setBackground(new Color(52,92,255));
        tbldistribusi.getTableHeader().setForeground(new Color(255,255,255));
        tbldistribusi.setRowHeight(25);
    }
    
    private void non_aktif() {
        kdbarangkeluar.setEnabled(false);
        barangkeluar.setEnabled(false);
        stokawalbarangkeluar.setEnabled(false);
        hargabarangkeluartxt.setEnabled(false);
        jumlahpesanankeluar3.setEnabled(false);
        hitung.setEnabled(false);
        karyawan.setEnabled(false);
        alamat.setEnabled(false);
        tanggalmasuk.setEnabled(false);
        idtransaksi.setEnabled(false);
    }
    
    private void aktif() {
        kdbarangkeluar.setEnabled(false);
        barangkeluar.setEnabled(true);
        karyawan.setEnabled(true);
        stokawalbarangkeluar.setEnabled(false);
        hargabarangkeluartxt.setEnabled(false);
        jumlahpesanankeluar3.setEnabled(true);
        tanggalmasuk.setEnabled(true);
        hitung.setEnabled(true);
        kdbarangkeluar.requestFocus();
    }
    
     private void kosong() {
        kdbarangkeluar.setText("");
        barangkeluar.setSelectedItem("");
        stokawalbarangkeluar.setText("");
        hargabarangkeluartxt.setText("");
        jumlahpesanankeluar3.setText("");
        hitung.setText("");
        karyawan.setSelectedItem("");
        alamat.setText("");
        kdkaryawan.setText("");
        jabatan.setText("");
        telp.setText("");
        divisis.setText("");
        tanggalmasuk.setDateFormatString("");
        idtransaksi.setText("");
        btn_save.setEnabled(true);
        btn_hapus.setEnabled(true);
    }
    
    private void autoNumber() {
        try {
            String sql = "SELECT * FROM tb_distribusi ORDER BY kd_dis DESC";
            PreparedStatement stat = konek.koneksiDb().prepareCall(sql);
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("kd_dis").substring(3);
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
                idtransaksi.setText("DS" + Nol + AN);
            } else {
                idtransaksi.setText("DS0001");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public void tbldistribusi(){
        DefaultTableModel tbl=new DefaultTableModel();
        tbl.addColumn("ID");
        tbl.addColumn("Kode Karyawan");
        tbl.addColumn("Nama Karyawan");
        tbl.addColumn("Jabatan");
        tbl.addColumn("Divisi");
        tbl.addColumn("Alamat");
        tbl.addColumn("No.Telp");
        tbl.addColumn("Kode Barang");
        tbl.addColumn("Nama Barang");
        tbl.addColumn("Harga Barang");
        tbl.addColumn("Jumlah Distribusi");
        tbl.addColumn("Tanggal");
        tbl.addColumn("Sub Total");
        tbldistribusi.setModel(tbl);
        try{
            Statement Statement=(Statement)konek.koneksiDb().createStatement();
            ResultSet res = Statement.executeQuery("select * from tb_distribusi");
            while (res.next())
            {
                tbl.addRow(new Object[]{
                    res.getString("kd_dis"),
                    res.getString("kd_karyawan"),
                    res.getString("nm_karyawan"),
                    res.getString("jbtn"),
                    res.getString("divisi"),
                    res.getString("almt"),
                    res.getString("no_telp"),
                    res.getString("kd_brg"),
                    res.getString("nm_brg"),
                    res.getString("hrg_beli"),
                    res.getString("tambahpesanan"),
                    res.getString("tgl"),
                    res.getString("harga")
                });
               tbldistribusi.setModel(tbl);
              
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
    }
    
    public void waktu(){
        Date fs=new Date();tanggalmasuk.setDate(fs);
    }
    
    private void tampilCombokry(){
        String sql = "SELECT * FROM tb_karyawan";
        try {
            Connection c = konek.koneksiDb();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                karyawan.addItem(r.getString("nm_karyawan"));
            }
            r.close();
            s.close();
            } catch (SQLException ex) {
        }         
    }
    
    private void tampilkry(){ 
        try {
        String sql = "SELECT kd_karyawan, jbtn, divisi, almt, no_telp FROM tb_karyawan WHERE nm_karyawan='"+karyawan.getSelectedItem()+"'"; 
        Connection c = konek.koneksiDb();
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery(sql);
        while(r.next()){
            Object[] ob = new Object[5];
            ob[0]=  r.getString(1);
            kdkaryawan.setText((String) ob[0]);
            ob[1]=  r.getString(2);
            kdkaryawan.setText((String) ob[1]);
            ob[2]=  r.getString(3);
            kdkaryawan.setText((String) ob[2]);
            ob[3]=  r.getString(4);
            kdkaryawan.setText((String) ob[3]);
            ob[4]=  r.getString(5);
            kdkaryawan.setText((String) ob[4]);
        }
            r.close(); 
            r.close(); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
    
    private void barangkeluar() {
        try {
            Connection c = konek.koneksiDb();
            Statement s = c.createStatement();

            String sql = "SELECT nm_brg FROM tb_barang WHERE stk !=''";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                barangkeluar.addItem(r.getString("nm_brg"));
            }

            r.last();
            int jumlahdata = r.getRow();
            r.first();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void FilterAngka(KeyEvent a){
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukan Angka!", "WARNING", JOptionPane.WARNING_MESSAGE);
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

        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        idtransaksi = new javax.swing.JTextField();
        karyawan = new javax.swing.JComboBox<String>();
        kdkaryawan = new javax.swing.JTextField();
        jabatan = new javax.swing.JTextField();
        divisis = new javax.swing.JTextField();
        telp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        barangkeluar = new javax.swing.JComboBox<String>();
        kdbarangkeluar = new javax.swing.JTextField();
        stokawalbarangkeluar = new javax.swing.JTextField();
        hargabarangkeluartxt = new javax.swing.JTextField();
        jumlahpesanankeluar3 = new javax.swing.JTextField();
        tanggalmasuk = new com.toedter.calendar.JDateChooser();
        hitung = new javax.swing.JTextField();
        btn_save = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        btn_edit1 = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldistribusi = new javax.swing.JTable();
        sumtotal = new javax.swing.JTextField();
        cetakpembayaran1 = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Distribusi");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1381, 776));
        setResizable(false);

        jPanel3.setPreferredSize(new java.awt.Dimension(1381, 100));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logom2-.png"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setText("DATA DISTRIBUSI");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        idtransaksi.setEditable(false);
        idtransaksi.setBackground(new java.awt.Color(255, 255, 255));
        idtransaksi.setForeground(new java.awt.Color(162, 162, 162));
        idtransaksi.setText("Nomor Distribusi");
        idtransaksi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        idtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtransaksiActionPerformed(evt);
            }
        });
        idtransaksi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idtransaksiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                idtransaksiFocusLost(evt);
            }
        });
        idtransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idtransaksiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idtransaksiKeyTyped(evt);
            }
        });

        karyawan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Pilih Nama Karyawan-" }));
        karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                karyawanActionPerformed(evt);
            }
        });

        kdkaryawan.setEditable(false);
        kdkaryawan.setBackground(new java.awt.Color(255, 255, 255));
        kdkaryawan.setForeground(new java.awt.Color(162, 162, 162));
        kdkaryawan.setText("Kode Karyawan");
        kdkaryawan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jabatan.setEditable(false);
        jabatan.setBackground(new java.awt.Color(255, 255, 255));
        jabatan.setForeground(new java.awt.Color(162, 162, 162));
        jabatan.setText("Jabatan");
        jabatan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        divisis.setEditable(false);
        divisis.setBackground(new java.awt.Color(255, 255, 255));
        divisis.setForeground(new java.awt.Color(162, 162, 162));
        divisis.setText("Divisi");
        divisis.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        telp.setEditable(false);
        telp.setBackground(new java.awt.Color(255, 255, 255));
        telp.setForeground(new java.awt.Color(162, 162, 162));
        telp.setText("No.Telp");
        telp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        alamat.setColumns(20);
        alamat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        alamat.setForeground(new java.awt.Color(162, 162, 162));
        alamat.setRows(5);
        alamat.setText("\n\nAlamat");
        alamat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(alamat);

        barangkeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Pilih Nama Perangkat-" }));
        barangkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangkeluarActionPerformed(evt);
            }
        });

        kdbarangkeluar.setEditable(false);
        kdbarangkeluar.setBackground(new java.awt.Color(255, 255, 255));
        kdbarangkeluar.setForeground(new java.awt.Color(162, 162, 162));
        kdbarangkeluar.setText("Kode Perangkat");
        kdbarangkeluar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        stokawalbarangkeluar.setEditable(false);
        stokawalbarangkeluar.setBackground(new java.awt.Color(255, 255, 255));
        stokawalbarangkeluar.setForeground(new java.awt.Color(162, 162, 162));
        stokawalbarangkeluar.setText("Stok Perangkat");
        stokawalbarangkeluar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        hargabarangkeluartxt.setEditable(false);
        hargabarangkeluartxt.setBackground(new java.awt.Color(255, 255, 255));
        hargabarangkeluartxt.setForeground(new java.awt.Color(162, 162, 162));
        hargabarangkeluartxt.setText("Harga Perangkat");
        hargabarangkeluartxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        hargabarangkeluartxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargabarangkeluartxtActionPerformed(evt);
            }
        });

        jumlahpesanankeluar3.setForeground(new java.awt.Color(162, 162, 162));
        jumlahpesanankeluar3.setText("Jumlah Distribusi");
        jumlahpesanankeluar3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jumlahpesanankeluar3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jumlahpesanankeluar3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jumlahpesanankeluar3FocusLost(evt);
            }
        });
        jumlahpesanankeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahpesanankeluar3KeyTyped(evt);
            }
        });

        tanggalmasuk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        hitung.setForeground(new java.awt.Color(162, 162, 162));
        hitung.setText("Hitung Harga Tekan ENTER");
        hitung.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });
        hitung.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hitungFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hitungFocusLost(evt);
            }
        });
        hitung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hitungKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hitungKeyTyped(evt);
            }
        });

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save_20px.png"))); // NOI18N
        btn_save.setText("TAMBAH");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit_20px.png"))); // NOI18N
        btn_exit.setText("KELUAR");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        btn_edit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cancel_20px.png"))); // NOI18N
        btn_edit1.setText("CANCEL");
        btn_edit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit1ActionPerformed(evt);
            }
        });

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete_20px.png"))); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setText("JAM");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("TANGGAL");

        tbldistribusi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbldistribusi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldistribusiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldistribusi);

        cetakpembayaran1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cash_receipt_40px.png"))); // NOI18N
        cetakpembayaran1.setText("CETAK NOTA");
        cetakpembayaran1.setPreferredSize(new java.awt.Dimension(89, 29));
        cetakpembayaran1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakpembayaran1MouseClicked(evt);
            }
        });
        cetakpembayaran1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakpembayaran1ActionPerformed(evt);
            }
        });

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search_20px.png"))); // NOI18N
        btn_cari.setText("CARI");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(idtransaksi, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jabatan)
                        .addComponent(kdkaryawan)
                        .addComponent(karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(divisis, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(telp, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tanggalmasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stokawalbarangkeluar)
                    .addComponent(barangkeluar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kdbarangkeluar)
                    .addComponent(hargabarangkeluartxt)
                    .addComponent(jumlahpesanankeluar3)
                    .addComponent(hitung, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cetakpembayaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_edit1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 213, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(647, 647, 647)
                    .addComponent(sumtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(648, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(12, 12, 12)
                        .addComponent(tanggalmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(idtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kdkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(divisis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(barangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_save)
                            .addComponent(btn_edit1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stokawalbarangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(kdbarangkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_hapus)
                                    .addComponent(btn_exit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hargabarangkeluartxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jumlahpesanankeluar3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btn_cari)
                                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cetakpembayaran1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)))))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(328, 328, 328)
                    .addComponent(sumtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(329, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiActionPerformed

    private void idtransaksiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idtransaksiFocusGained
        if(idtransaksi.getText().trim().toLowerCase().equals("nomor distribusi")){
            idtransaksi.setText("");
            idtransaksi.setForeground(Color.BLACK);
        }     // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiFocusGained

    private void idtransaksiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idtransaksiFocusLost
        if(idtransaksi.getText().trim().equals("")|| idtransaksi.getText().trim().toLowerCase().equals("nomor pesanan")){
            idtransaksi.setText("Nomor Pesanan");
            idtransaksi.setForeground(new Color(162,162,162));
        }// TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiFocusLost

    private void idtransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idtransaksiKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {

            if(barangkeluar.getSelectedItem().equals("- Pilih Nama Perangkat -") ||kdbarangkeluar.getText().equals("Kode Barang") || hargabarangkeluartxt.getText().equals("Harga Barang")|| jumlahpesanankeluar3.getText().equals("Jumlah Distribusi")){
                JOptionPane.showMessageDialog(this, "Harap Lengkapi Data ","Data Belum Lengkap",JOptionPane.WARNING_MESSAGE);

            }else{
                String a = jumlahpesanankeluar3.getText();
                int aa = Integer.parseInt(a);

                String b = stokawalbarangkeluar.getText();
                int bb = Integer.parseInt(b);
                if(aa > bb){
                    JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "INDOSATM2", JOptionPane.INFORMATION_MESSAGE);
                    jumlahpesanankeluar3.setText("");
                }else{

                    if(jumlahpesanankeluar3.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "ISI JUMLAH DISTRIBUSI !");
                    }else{
                        int jumlah, harga, total;

                        jumlah = Integer.parseInt(jumlahpesanankeluar3.getText().toString());
                        harga = Integer.parseInt(hargabarangkeluartxt.getText().toString());
                        total = jumlah * harga;

                        idtransaksi.setText(Integer.toString(total));

                    }
                }
            }
        } // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiKeyPressed

    private void idtransaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idtransaksiKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_idtransaksiKeyTyped

    private void karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_karyawanActionPerformed
        // TODO add your handling code here:
        if (karyawan.getSelectedItem().equals("- Nama Karyawan -")){
            kdkaryawan.setText("");
        }else{
            try {
                Connection c = konek.koneksiDb();
                Statement s = c.createStatement();

                String sql = "SELECT kd_karyawan, jbtn, divisi, almt, no_telp FROM tb_karyawan WHERE nm_karyawan ='" + karyawan.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);

                while (r.next()) {
                    kdkaryawan.setText(r.getString("kd_karyawan"));
                    kdkaryawan.setForeground(Color.BLACK);
                    jabatan.setText(r.getString("jbtn"));
                    jabatan.setForeground(Color.BLACK);
                    divisis.setText(r.getString("divisi"));
                    divisis.setForeground(Color.BLACK);
                    alamat.setText(r.getString("almt"));
                    alamat.setForeground(Color.BLACK);
                    telp.setText(r.getString("no_telp"));
                    telp.setForeground(Color.BLACK);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_karyawanActionPerformed

    private void barangkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barangkeluarActionPerformed
        if (barangkeluar.getSelectedItem().equals("- Nama Perangakt -")){
            kdbarangkeluar.setText("");
        }else{
            try {
                Connection c = konek.koneksiDb();
                Statement s = c.createStatement();

                String sql = "SELECT kd_brg, stk, hrg_beli FROM tb_barang WHERE nm_brg ='" + barangkeluar.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);

                while (r.next()) {
                    kdbarangkeluar.setText(r.getString("kd_brg"));
                    kdbarangkeluar.setForeground(Color.BLACK);
                    stokawalbarangkeluar.setText(r.getString("stk"));
                    stokawalbarangkeluar.setForeground(Color.BLACK);
                    hargabarangkeluartxt.setText(r.getString("hrg_beli"));
                    hargabarangkeluartxt.setForeground(Color.BLACK);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_barangkeluarActionPerformed

    private void hargabarangkeluartxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargabarangkeluartxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargabarangkeluartxtActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hitungActionPerformed

    private void hitungFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hitungFocusGained
        if(hitung.getText().trim().toLowerCase().equals("hitung harga tekan enter")){
            hitung.setText("");
            hitung.setForeground(Color.BLACK);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_hitungFocusGained

    private void hitungFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hitungFocusLost
        if(hitung.getText().trim().equals("")|| hitung.getText().trim().toLowerCase().equals("hitung harga tekan enter")){
            hitung.setText("Hitung Harga Tekan ENTER");
            hitung.setForeground(new Color(162,162,162));
        }// TODO add your handling code here:
    }//GEN-LAST:event_hitungFocusLost

    private void hitungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hitungKeyPressed
        if(barangkeluar.getSelectedItem().equals("- Pilih Nama Perangkat -") ||kdbarangkeluar.getText().equals("Kode Sparepart") || hargabarangkeluartxt.getText().equals("Harga Barang")|| jumlahpesanankeluar3.getText().equals("Jumlah Pesanan")){
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data ","Data Belum Lengkap",JOptionPane.WARNING_MESSAGE);

        }else{
            String a = jumlahpesanankeluar3.getText();
            int aa = Integer.parseInt(a);

            String b = stokawalbarangkeluar.getText();
            int bb = Integer.parseInt(b);
            if(aa > bb){
                JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "INDOSATM2", JOptionPane.INFORMATION_MESSAGE);
                jumlahpesanankeluar3.setText("");
            }else{

                if(jumlahpesanankeluar3.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "ISI JUMLAH DISTRIBUSI !");
                }else{
                    int jumlah, harga, total;

                    jumlah = Integer.parseInt(jumlahpesanankeluar3.getText().toString());
                    harga = Integer.parseInt(hargabarangkeluartxt.getText().toString());
                    total = jumlah * harga;

                    hitung.setText(Integer.toString(total));
                    tbldistribusi();
                }
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_hitungKeyPressed

    private void hitungKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hitungKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_hitungKeyTyped

    private void jumlahpesanankeluar3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jumlahpesanankeluar3FocusGained
        if(jumlahpesanankeluar3.getText().trim().toLowerCase().equals("jumlah distribusi")){
            jumlahpesanankeluar3.setText("");
            jumlahpesanankeluar3.setForeground(Color.BLACK);
        } // TODO add your handling code here:
    }//GEN-LAST:event_jumlahpesanankeluar3FocusGained

    private void jumlahpesanankeluar3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jumlahpesanankeluar3FocusLost
        if(jumlahpesanankeluar3.getText().trim().equals("")|| jumlahpesanankeluar3.getText().trim().toLowerCase().equals("jumlah pesanan")){
            jumlahpesanankeluar3.setText("Jumlah Distribusi");
            jumlahpesanankeluar3.setForeground(new Color(162,162,162));
        } // TODO add your handling code here:
    }//GEN-LAST:event_jumlahpesanankeluar3FocusLost

    private void jumlahpesanankeluar3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahpesanankeluar3KeyTyped
        FilterAngka(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahpesanankeluar3KeyTyped

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
        String tombol = btn_save.getText();
        String kd_dis = idtransaksi.getText();
        String kd_karyawan = kdkaryawan.getText();
            String nm_karyawan = (String) karyawan.getSelectedItem();
            String jbtn = jabatan.getText();
            String divisi = divisis.getText();
            String almt = alamat.getText();
            String no_telp = telp.getText();
            String kd_brg = kdbarangkeluar.getText();
            String nm_brg = (String)barangkeluar.getSelectedItem();
            String hrg_beli = hargabarangkeluartxt.getText();
            String tambahpesanan = jumlahpesanankeluar3.getText();
            String tgl = dformat.format(tanggalmasuk.getDate());
            String harga = hitung.getText();

        if (tombol.equals("TAMBAH")) {
            aktif();
            //kosong();
            btn_save.setText("SIMPAN");
            btn_hapus.setEnabled(false);
        }else {
            JOptionPane.showConfirmDialog(null, "Apakah Data anda sudah benar?", "INFORMASI",JOptionPane.YES_NO_OPTION);
            try {
                Connection c = konek.koneksiDb();
                String sql = "INSERT INTO tb_distribusi VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, kd_dis);
                    p.setString(2, kd_karyawan);
                    p.setString(3, nm_karyawan);
                    p.setString(4, jbtn);
                    p.setString(5, divisi);
                    p.setString(6, almt);
                    p.setString(7, no_telp);
                    p.setString(8, kd_brg);
                    p.setString(9, nm_brg);
                    p.setString(10, hrg_beli);
                    p.setString(11, tambahpesanan);
                    p.setString(12, tgl);
                    p.setString(13, harga);
                p.executeUpdate();
                p.close();
                idtransaksi.requestFocus();
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            }finally{
                String   msg="<html>Kode Karyawan         = " +kdkaryawan.getText()+" <br>"
                + "Nama Karyawan       = " +karyawan.getSelectedItem()+"<br>"
                + "Jabatan              = " +jabatan.getText()+"<br>"
                + "Divisi              = " +divisis.getText()+"<br>"
                + "Alamat              = " +alamat.getText()+"<br>"
                + "No.Telp              = " +telp.getText()+"<br>"
                + "Kode Barang              = " +kdbarangkeluar.getText()+"<br>"
                + "Nama Barang             = " +barangkeluar.getSelectedItem()+"<br>"
                + "Harga Barang              = " +hargabarangkeluartxt.getText()+"<br>"
                + "Jumlah Distribusi             = " +jumlahpesanankeluar3.getText()+"<br>"
                + "Tanggal Distribusi              = " +tanggalmasuk.getDate()+"<br>"
                + "Total             = " +hitung.getText()+"<html>";
                JOptionPane optionPane=new JOptionPane();
                optionPane.setMessage(msg);
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog=optionPane.createDialog(null, "DATA DISIMPAN");
                dialog.setVisible(true);
            }
            new DataDistribusi().setVisible(true);
            this.dispose();
            autoNumber();
            tbldistribusi();
            btn_save.setText("TAMBAH");
            non_aktif();
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        dispose();
        FormAdmin fa = new FormAdmin();
        fa.show();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_edit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit1ActionPerformed
        // TODO add your handling code here:
        kosong();
        non_aktif();
    }//GEN-LAST:event_btn_edit1ActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin Menghapus Data Ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from tb_distribusi where kd_dis='"+idtransaksi.getText() + "'";
            try {
                PreparedStatement stat = konek.koneksiDb().prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Distribusi Berhasil Dihapus");
                kosong();
                idtransaksi.requestFocus();
                tbldistribusi();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Distribusi Gagal Di Hapus" + e);
            }
            new DataDistribusi().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void tbldistribusiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldistribusiMouseClicked
        // TODO add your handling code here:
        btn_hapus.setEnabled(true);
        int i =tbldistribusi.getSelectedRow();
        if (evt.getClickCount()==1){
            idtransaksi.setText(tbldistribusi.getValueAt(i, 0).toString());
            kdkaryawan.setText(tbldistribusi.getValueAt(i, 1).toString());
            karyawan.setSelectedItem(tbldistribusi.getValueAt(i, 2).toString());
            jabatan.setText(tbldistribusi.getValueAt(i, 3).toString());
            divisis.setText(tbldistribusi.getValueAt(i, 4).toString());
            alamat.setText(tbldistribusi.getValueAt(i, 5).toString());
            telp.setText(tbldistribusi.getValueAt(i, 6).toString());
            kdbarangkeluar.setText(tbldistribusi.getValueAt(i, 7).toString());
            barangkeluar.setSelectedItem(tbldistribusi.getValueAt(i, 8).toString());
            hargabarangkeluartxt.setText(tbldistribusi.getValueAt(i, 9).toString());
            jumlahpesanankeluar3.setText(tbldistribusi.getValueAt(i, 10).toString());
            tanggalmasuk.setDateFormatString(tbldistribusi.getValueAt(i, 11).toString());
            hitung.setText(tbldistribusi.getValueAt(i, 12).toString()); 
        }
    }//GEN-LAST:event_tbldistribusiMouseClicked

    private void cetakpembayaran1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakpembayaran1MouseClicked
        // TODO add your handling code here:
        new CetakNotaDistribusi().setVisible(true);
    }//GEN-LAST:event_cetakpembayaran1MouseClicked

    private void cetakpembayaran1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakpembayaran1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cetakpembayaran1ActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        String tombol = btn_cari.getText();
        if (tombol.equals("Cari")){
            Object[] Baris = {"ID", "Kode Karyawan", "Nama Karyawan", "Jabatan", "Divisi","Alamat","No.Telp","Kode Barang","Nama Barang","Harga Barang","Jumlah Distribusi","Tanggal","Sub Total"};
            tabmode = new DefaultTableModel(null, Baris);
            tbldistribusi.setModel(tabmode);
            String sql = "Select * from tb_distribusi where kd_dis like '%" + txt_cari.getText() + "%'" +
            "or nm_karyawan like '%" + txt_cari.getText() + "%'";
            try {
                java.sql.Statement stat = konek.koneksiDb().createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()) {
                    String kd_dis = hasil.getString("kd_dis");
                    String kd_karyawan = hasil.getString("kd_karyawan");
                    String nm_karyawan = hasil.getString("nm_karyawan");
                    String jbtn = hasil.getString("jbtn");
                    String divisi = hasil.getString("divisi");
                    String almt = hasil.getString("almt");
                    String no_telp = hasil.getString("no_telp");
                    String kd_brg = hasil.getString("kd_brg");
                    String nm_brg = hasil.getString("nm_brg");
                    String hrg_beli = hasil.getString("hrg_beli");
                    String tambahpesanan = hasil.getString("tambahpesanan");
                    String tgl = hasil.getString("tgl");
                    String harga = hasil.getString("harga");
                    String[] data = {kd_dis,kd_karyawan,nm_karyawan,jbtn,divisi,almt,no_telp,kd_brg,nm_brg,hrg_beli,tambahpesanan,tgl,harga};
                    tabmode.addRow(data);
                }
            } catch (Exception e) {
            }
            btn_cari.setText("Batal");
        }else{
            tbldistribusi();
            btn_cari.setText("Cari");
            txt_cari.setText("");
            btn_save.setEnabled(true);
        }
    }//GEN-LAST:event_btn_cariActionPerformed

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
            java.util.logging.Logger.getLogger(DataDistribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataDistribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataDistribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataDistribusi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataDistribusi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JComboBox<String> barangkeluar;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_edit1;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton cetakpembayaran1;
    private javax.swing.JTextField divisis;
    private javax.swing.JTextField hargabarangkeluartxt;
    private javax.swing.JTextField hitung;
    private javax.swing.JTextField idtransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jabatan;
    private javax.swing.JTextField jumlahpesanankeluar3;
    private javax.swing.JComboBox<String> karyawan;
    private javax.swing.JTextField kdbarangkeluar;
    private javax.swing.JTextField kdkaryawan;
    private javax.swing.JTextField stokawalbarangkeluar;
    private javax.swing.JTextField sumtotal;
    private com.toedter.calendar.JDateChooser tanggalmasuk;
    private javax.swing.JTable tbldistribusi;
    private javax.swing.JTextField telp;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}

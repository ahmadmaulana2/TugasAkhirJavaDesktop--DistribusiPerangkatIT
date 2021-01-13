-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 13 Agu 2020 pada 14.56
-- Versi server: 10.1.38-MariaDB
-- Versi PHP: 7.1.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `distribusi_it`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_barang`
--

CREATE TABLE `tb_barang` (
  `kd_brg` varchar(7) NOT NULL,
  `nm_brg` varchar(30) NOT NULL,
  `jns_brg` enum('Laptop','Mouse','Keyboard','Mini PC','Converter','Monitor','CPU') NOT NULL,
  `hrg_beli` bigint(20) NOT NULL,
  `stk` int(4) NOT NULL,
  `kd_sup` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_barang`
--

INSERT INTO `tb_barang` (`kd_brg`, `nm_brg`, `jns_brg`, `hrg_beli`, `stk`, `kd_sup`) VALUES
('PS0001', 'Lenovo X230', 'Laptop', 2500000, 30, '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_barangmasuk`
--

CREATE TABLE `tb_barangmasuk` (
  `id_brgmsk` int(5) NOT NULL,
  `nm_brg` varchar(30) NOT NULL,
  `kd_brg` varchar(7) NOT NULL,
  `tambahstk` int(11) NOT NULL,
  `tglmasuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_barangmasuk`
--

INSERT INTO `tb_barangmasuk` (`id_brgmsk`, `nm_brg`, `kd_brg`, `tambahstk`, `tglmasuk`) VALUES
(1, 'Lenovo X230', 'PS0001', 5, '2020-07-27'),
(2, 'Lenovo X230', 'PS0001', 5, '2020-07-27'),
(4, 'Lenovo X230', 'PS0001', 2, '2020-07-27'),
(5, 'Lenovo X230', 'PS0001', 2, '2020-07-27'),
(6, 'Lenovo X230', 'PS0001', 7, '2020-07-28');

--
-- Trigger `tb_barangmasuk`
--
DELIMITER $$
CREATE TRIGGER `tambahstk` AFTER INSERT ON `tb_barangmasuk` FOR EACH ROW BEGIN
UPDATE tb_barang SET stk = stk +NEW.tambahstk
WHERE nm_brg = NEW.nm_brg;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_distribusi`
--

CREATE TABLE `tb_distribusi` (
  `kd_dis` char(10) NOT NULL,
  `kd_karyawan` varchar(10) NOT NULL,
  `nm_karyawan` varchar(30) NOT NULL,
  `jbtn` enum('Staff Gudang','Staff','Admin IT','IT Leader','President Director','Director','General Manager','Manager','Sekretaris') NOT NULL,
  `divisi` enum('Marketing','IT Development','Finance','Sales','Sitac','Planning','Customer Service','People Management','Corporate Service') NOT NULL,
  `almt` text NOT NULL,
  `no_telp` text NOT NULL,
  `kd_brg` varchar(7) NOT NULL,
  `nm_brg` varchar(30) NOT NULL,
  `hrg_beli` bigint(20) NOT NULL,
  `tambahpesanan` int(5) NOT NULL,
  `tgl` date NOT NULL,
  `harga` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_distribusi`
--

INSERT INTO `tb_distribusi` (`kd_dis`, `kd_karyawan`, `nm_karyawan`, `jbtn`, `divisi`, `almt`, `no_telp`, `kd_brg`, `nm_brg`, `hrg_beli`, `tambahpesanan`, `tgl`, `harga`) VALUES
('DS0001', 'IM0001', 'Ahmad Maulana', 'Admin IT', 'IT Development', 'Depok', '0813-8183-6548', 'PS0001', 'Lenovo X230', 2500000, 2, '2020-07-28', 5000000);

--
-- Trigger `tb_distribusi`
--
DELIMITER $$
CREATE TRIGGER `batal` AFTER DELETE ON `tb_distribusi` FOR EACH ROW BEGIN
UPDATE tb_barang SET stk = stk + OLD.tambahpesanan
WHERE nm_brg = OLD.nm_brg;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `beli` AFTER INSERT ON `tb_distribusi` FOR EACH ROW BEGIN
UPDATE tb_barang SET stk = stk - NEW.tambahpesanan
WHERE nm_brg = NEW.nm_brg;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `kd_karyawan` varchar(10) NOT NULL,
  `nm_karyawan` varchar(30) NOT NULL,
  `jbtn` enum('Staff Gudang','Staff','Admin IT','IT Leader','President Director','Director','General Manager','Manager','Sekretaris') NOT NULL,
  `divisi` enum('Marketing','IT Development','Finance','Sales','Sitac','Planning','Customer Service','People Management','Corporate Service') NOT NULL,
  `almt` text NOT NULL,
  `no_telp` text NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_karyawan`
--

INSERT INTO `tb_karyawan` (`kd_karyawan`, `nm_karyawan`, `jbtn`, `divisi`, `almt`, `no_telp`, `password`) VALUES
('IM0001', 'Ahmad Maulana', 'Admin IT', 'IT Development', 'Depok', '0813-8183-6548', '50403823'),
('IM0002', 'Aji Sahid Saputra', 'IT Leader', 'IT Development', 'Pondok Melati, Jatiwarna-Bekasi', '0857-4898-4154', 'ajisahid99');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pemasok`
--

CREATE TABLE `tb_pemasok` (
  `kd_sup` char(10) NOT NULL,
  `nm_sup` varchar(30) NOT NULL,
  `almt` text NOT NULL,
  `no_telp` text NOT NULL,
  `email` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pemasok`
--

INSERT INTO `tb_pemasok` (`kd_sup`, `nm_sup`, `almt`, `no_telp`, `email`) VALUES
('PS0001', 'Berca', 'Abdul Muis', '021-487945', 'berca@hudayaperkasa.co.id');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`kd_brg`);

--
-- Indeks untuk tabel `tb_barangmasuk`
--
ALTER TABLE `tb_barangmasuk`
  ADD PRIMARY KEY (`id_brgmsk`),
  ADD KEY `tb_barangmasuk1` (`kd_brg`);

--
-- Indeks untuk tabel `tb_distribusi`
--
ALTER TABLE `tb_distribusi`
  ADD PRIMARY KEY (`kd_dis`),
  ADD KEY `tb_distribusi_1` (`kd_karyawan`),
  ADD KEY `tb_distribusi_2` (`kd_brg`);

--
-- Indeks untuk tabel `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD PRIMARY KEY (`kd_karyawan`);

--
-- Indeks untuk tabel `tb_pemasok`
--
ALTER TABLE `tb_pemasok`
  ADD PRIMARY KEY (`kd_sup`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_barangmasuk`
--
ALTER TABLE `tb_barangmasuk`
  MODIFY `id_brgmsk` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_barangmasuk`
--
ALTER TABLE `tb_barangmasuk`
  ADD CONSTRAINT `tb_barangmasuk1` FOREIGN KEY (`kd_brg`) REFERENCES `tb_barang` (`kd_brg`);

--
-- Ketidakleluasaan untuk tabel `tb_distribusi`
--
ALTER TABLE `tb_distribusi`
  ADD CONSTRAINT `tb_distribusi_1` FOREIGN KEY (`kd_karyawan`) REFERENCES `tb_karyawan` (`kd_karyawan`),
  ADD CONSTRAINT `tb_distribusi_2` FOREIGN KEY (`kd_brg`) REFERENCES `tb_barang` (`kd_brg`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

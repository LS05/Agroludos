-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 22, 2014 at 12:03 PM
-- Server version: 5.5.33
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `agroludos`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_competizione`
--

CREATE TABLE IF NOT EXISTS `tbl_competizione` (
  `idcompetizione` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `nmin` int(11) NOT NULL,
  `nmax` int(11) NOT NULL,
  `costo` double NOT NULL,
  `stato` int(11) NOT NULL,
  `descrizione` varchar(400) NOT NULL,
  `mancomp` int(11) NOT NULL,
  `tipocomp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_configurazione`
--

CREATE TABLE IF NOT EXISTS `tbl_configurazione` (
  `idconfigurazione` int(11) NOT NULL,
  `stato` tinyint(1) NOT NULL,
  `conf_file` varchar(255) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `server` varchar(100) NOT NULL,
  `porta` varchar(4) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_configurazione`
--

INSERT INTO `tbl_configurazione` (`idconfigurazione`, `stato`, `conf_file`, `tipo`, `nome`, `server`, `porta`, `username`, `password`) VALUES
(1, 0, 'src/main/resources/hibernate.cfg.xml', 'mysql', 'agroludos', 'localhost', '3306', 'root', 'root');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_iscrizione`
--

CREATE TABLE IF NOT EXISTS `tbl_iscrizione` (
  `idiscrizione` int(11) NOT NULL,
  `data` date NOT NULL,
  `stato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_iscr_comp`
--

CREATE TABLE IF NOT EXISTS `tbl_iscr_comp` (
  `idpart` int(11) NOT NULL,
  `idiscrizione` int(11) NOT NULL,
  `idcompetizione` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_maincomp`
--

CREATE TABLE IF NOT EXISTS `tbl_maincomp` (
  `idcompetizione` int(11) NOT NULL,
  `idoptional` int(11) NOT NULL,
  `idtipooptional` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_mancomp`
--

CREATE TABLE IF NOT EXISTS `tbl_mancomp` (
  `idtmancomp` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `stato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_mansis`
--

CREATE TABLE IF NOT EXISTS `tbl_mansis` (
  `idmansis` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_mansis`
--

INSERT INTO `tbl_mansis` (`idmansis`, `nome`, `cognome`, `username`, `password`, `email`) VALUES
(1, 'Luca', 'Suriano', 'LS05', 'd50189f2a160ab71e1a1a42082a12a53', 'lsadasda@live.it');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_optional`
--

CREATE TABLE IF NOT EXISTS `tbl_optional` (
  `idoptional` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descrizione` text NOT NULL,
  `costo` double NOT NULL,
  `stato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_opt_iscr`
--

CREATE TABLE IF NOT EXISTS `tbl_opt_iscr` (
  `idcompetizione` int(11) NOT NULL,
  `idoptional` int(11) NOT NULL,
  `idiscrizione` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_partecipante`
--

CREATE TABLE IF NOT EXISTS `tbl_partecipante` (
  `idtpartecipante` int(11) NOT NULL,
  `username` varchar(45) NOT NULL COMMENT '	',
  `password` varchar(255) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `cf` varchar(45) NOT NULL,
  `datanasc` date NOT NULL,
  `sesso` varchar(1) NOT NULL,
  `n_tessan` varchar(45) NOT NULL,
  `indirizzo` varchar(45) NOT NULL,
  `src` varchar(100) NOT NULL,
  `data_src` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tipocomp`
--

CREATE TABLE IF NOT EXISTS `tbl_tipocomp` (
  `idtbl_tipocomp` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descrizione` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tipoopt`
--

CREATE TABLE IF NOT EXISTS `tbl_tipoopt` (
  `idtipooptional` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_competizione`
--
ALTER TABLE `tbl_competizione`
 ADD PRIMARY KEY (`idcompetizione`), ADD KEY `fk_cpt_mancpt_idx` (`mancomp`), ADD KEY `fk_cpt_tipocpt_idx` (`tipocomp`);

--
-- Indexes for table `tbl_configurazione`
--
ALTER TABLE `tbl_configurazione`
 ADD PRIMARY KEY (`idconfigurazione`);

--
-- Indexes for table `tbl_iscrizione`
--
ALTER TABLE `tbl_iscrizione`
 ADD PRIMARY KEY (`idiscrizione`);

--
-- Indexes for table `tbl_iscr_comp`
--
ALTER TABLE `tbl_iscr_comp`
 ADD PRIMARY KEY (`idpart`,`idiscrizione`,`idcompetizione`), ADD KEY `fk_iscrcomp_comp_idx` (`idcompetizione`), ADD KEY `fk_iscrcomp_iscr_idx` (`idiscrizione`);

--
-- Indexes for table `tbl_maincomp`
--
ALTER TABLE `tbl_maincomp`
 ADD PRIMARY KEY (`idcompetizione`,`idoptional`), ADD KEY `fk_maincomp_opt_idx` (`idoptional`), ADD KEY `fk_maincomp_tipoopt_idx` (`idtipooptional`);

--
-- Indexes for table `tbl_mancomp`
--
ALTER TABLE `tbl_mancomp`
 ADD PRIMARY KEY (`idtmancomp`,`username`,`email`);

--
-- Indexes for table `tbl_mansis`
--
ALTER TABLE `tbl_mansis`
 ADD PRIMARY KEY (`idmansis`);

--
-- Indexes for table `tbl_optional`
--
ALTER TABLE `tbl_optional`
 ADD PRIMARY KEY (`idoptional`);

--
-- Indexes for table `tbl_opt_iscr`
--
ALTER TABLE `tbl_opt_iscr`
 ADD PRIMARY KEY (`idcompetizione`,`idoptional`,`idiscrizione`), ADD KEY `fk_opt_iscr_optional_idx` (`idoptional`), ADD KEY `fk_opt_iscr_iscrizione_idx` (`idiscrizione`);

--
-- Indexes for table `tbl_partecipante`
--
ALTER TABLE `tbl_partecipante`
 ADD PRIMARY KEY (`idtpartecipante`,`username`);

--
-- Indexes for table `tbl_tipocomp`
--
ALTER TABLE `tbl_tipocomp`
 ADD PRIMARY KEY (`idtbl_tipocomp`);

--
-- Indexes for table `tbl_tipoopt`
--
ALTER TABLE `tbl_tipoopt`
 ADD PRIMARY KEY (`idtipooptional`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_competizione`
--
ALTER TABLE `tbl_competizione`
ADD CONSTRAINT `fk_cpt_mancpt` FOREIGN KEY (`mancomp`) REFERENCES `tbl_mancomp` (`idtmancomp`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_cpt_tipocpt` FOREIGN KEY (`tipocomp`) REFERENCES `tbl_tipocomp` (`idtbl_tipocomp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_iscr_comp`
--
ALTER TABLE `tbl_iscr_comp`
ADD CONSTRAINT `fk_iscrcomp_comp` FOREIGN KEY (`idcompetizione`) REFERENCES `tbl_competizione` (`idcompetizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_iscrcomp_iscr` FOREIGN KEY (`idiscrizione`) REFERENCES `tbl_iscrizione` (`idiscrizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_iscrcomp_part` FOREIGN KEY (`idpart`) REFERENCES `tbl_partecipante` (`idtpartecipante`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_maincomp`
--
ALTER TABLE `tbl_maincomp`
ADD CONSTRAINT `fk_maincomp_comp` FOREIGN KEY (`idcompetizione`) REFERENCES `tbl_competizione` (`idcompetizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_maincomp_opt` FOREIGN KEY (`idoptional`) REFERENCES `tbl_optional` (`idoptional`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_maincomp_tipoopt` FOREIGN KEY (`idtipooptional`) REFERENCES `tbl_tipoopt` (`idtipooptional`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_opt_iscr`
--
ALTER TABLE `tbl_opt_iscr`
ADD CONSTRAINT `fk_opt_iscr_iscrizione` FOREIGN KEY (`idiscrizione`) REFERENCES `tbl_iscrizione` (`idiscrizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_opt_iscr_optional` FOREIGN KEY (`idoptional`) REFERENCES `tbl_optional` (`idoptional`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fl_opt_iscr_competizione` FOREIGN KEY (`idcompetizione`) REFERENCES `tbl_competizione` (`idcompetizione`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

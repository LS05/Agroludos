-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 03, 2014 at 12:55 PM
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
  `descrizione` varchar(400) NOT NULL,
  `mancomp` int(11) NOT NULL,
  `tipocomp` int(11) NOT NULL,
  `statocomp` int(11) NOT NULL,
  `idmdc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_configurazione`
--

CREATE TABLE IF NOT EXISTS `tbl_configurazione` (
  `idconfigurazione` int(11) NOT NULL,
  `stato` tinyint(1) NOT NULL,
  `conf_file` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `server` varchar(255) NOT NULL,
  `porta` varchar(255) NOT NULL,
  `username` longtext NOT NULL,
  `password` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_configurazione`
--

INSERT INTO `tbl_configurazione` (`idconfigurazione`, `stato`, `conf_file`, `tipo`, `nome`, `server`, `porta`, `username`, `password`) VALUES
(1, 0, 'src/main/resources/hibernate.cfg.xml', 'mysql', 'agroludos', 'localhost', '3306', 'root', 'root'),
(2, 0, 'src/main/resources/hibernate.cfg.xml', 'mysql', 'agroludos', 'localhost', '3306', 'root', 'root'),
(3, 0, 'src/main/resources/hibernate.cfg.xml', 'mysql', 'agroludos', 'localhost', '3306', 'root', 'root'),
(4, 0, 'src/main/resources/hibernate.cfg.xml', 'mysql', 'agroludos', 'localhost', '3306', 'root', 'root');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_iscrizione`
--

CREATE TABLE IF NOT EXISTS `tbl_iscrizione` (
  `idiscrizione` int(11) NOT NULL,
  `data` date NOT NULL,
  `stato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_iscr_comp`
--

CREATE TABLE IF NOT EXISTS `tbl_iscr_comp` (
  `idiscrizione` int(11) NOT NULL,
  `idcompetizione` int(11) NOT NULL,
  `idpartecipante` int(11) NOT NULL
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
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `stato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_optional`
--

CREATE TABLE IF NOT EXISTS `tbl_optional` (
  `idoptional` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descrizione` text NOT NULL,
  `costo` double NOT NULL,
  `stato` int(11) NOT NULL
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
-- Table structure for table `tbl_ruoli`
--

CREATE TABLE IF NOT EXISTS `tbl_ruoli` (
  `idruolo` int(11) NOT NULL,
  `descrizione` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_ruoli`
--

INSERT INTO `tbl_ruoli` (`idruolo`, `descrizione`) VALUES
(0, 'managerDiSistema'),
(1, 'managerDiCompetizione'),
(2, 'partecipante');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_statiiscr`
--

CREATE TABLE IF NOT EXISTS `tbl_statiiscr` (
  `idstatiiscr` int(11) NOT NULL,
  `descrizione` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_statiopt`
--

CREATE TABLE IF NOT EXISTS `tbl_statiopt` (
  `idstatiopt` int(11) NOT NULL,
  `descrizione` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_statiutente`
--

CREATE TABLE IF NOT EXISTS `tbl_statiutente` (
  `idstatiutente` int(11) NOT NULL,
  `descrizione` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_statiutente`
--

INSERT INTO `tbl_statiutente` (`idstatiutente`, `descrizione`) VALUES
(0, 'eliminato'),
(1, 'attivo');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_statocomp`
--

CREATE TABLE IF NOT EXISTS `tbl_statocomp` (
  `idstatocomp` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
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

-- --------------------------------------------------------

--
-- Table structure for table `tbl_utenti`
--

CREATE TABLE IF NOT EXISTS `tbl_utenti` (
  `idutente` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `idruolo` int(11) NOT NULL,
  `stato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_utenti`
--

INSERT INTO `tbl_utenti` (`idutente`, `username`, `password`, `nome`, `cognome`, `email`, `idruolo`, `stato`) VALUES
(0, 'LS05', 'd50189f2a160ab71e1a1a42082a12a53', 'Luca', 'Suriano', 'lucas05@live.it', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_competizione`
--
ALTER TABLE `tbl_competizione`
 ADD PRIMARY KEY (`idcompetizione`,`idmdc`), ADD KEY `fk_cpt_tipocpt_idx` (`tipocomp`), ADD KEY `fk_tbl_competizione_tbl_statocomp1_idx` (`statocomp`), ADD KEY `fk_tbl_competizione_tbl_utenti1_idx` (`idmdc`);

--
-- Indexes for table `tbl_configurazione`
--
ALTER TABLE `tbl_configurazione`
 ADD PRIMARY KEY (`idconfigurazione`);

--
-- Indexes for table `tbl_iscrizione`
--
ALTER TABLE `tbl_iscrizione`
 ADD PRIMARY KEY (`idiscrizione`), ADD KEY `fk_tbl_iscrizione_tbl_statiiscr1_idx` (`stato`);

--
-- Indexes for table `tbl_iscr_comp`
--
ALTER TABLE `tbl_iscr_comp`
 ADD PRIMARY KEY (`idiscrizione`,`idcompetizione`,`idpartecipante`), ADD KEY `fk_iscrcomp_comp_idx` (`idcompetizione`), ADD KEY `fk_iscrcomp_iscr_idx` (`idiscrizione`), ADD KEY `fk_tbl_iscr_comp_tbl_partecipante1_idx` (`idpartecipante`);

--
-- Indexes for table `tbl_maincomp`
--
ALTER TABLE `tbl_maincomp`
 ADD PRIMARY KEY (`idcompetizione`,`idoptional`), ADD KEY `fk_maincomp_opt_idx` (`idoptional`), ADD KEY `fk_maincomp_tipoopt_idx` (`idtipooptional`);

--
-- Indexes for table `tbl_mancomp`
--
ALTER TABLE `tbl_mancomp`
 ADD PRIMARY KEY (`idtmancomp`);

--
-- Indexes for table `tbl_optional`
--
ALTER TABLE `tbl_optional`
 ADD PRIMARY KEY (`idoptional`), ADD KEY `fk_tbl_optional_tbl_statiopt1_idx` (`stato`);

--
-- Indexes for table `tbl_opt_iscr`
--
ALTER TABLE `tbl_opt_iscr`
 ADD PRIMARY KEY (`idcompetizione`,`idoptional`,`idiscrizione`), ADD KEY `fk_opt_iscr_optional_idx` (`idoptional`), ADD KEY `fk_opt_iscr_iscrizione_idx` (`idiscrizione`);

--
-- Indexes for table `tbl_partecipante`
--
ALTER TABLE `tbl_partecipante`
 ADD PRIMARY KEY (`idtpartecipante`);

--
-- Indexes for table `tbl_ruoli`
--
ALTER TABLE `tbl_ruoli`
 ADD PRIMARY KEY (`idruolo`);

--
-- Indexes for table `tbl_statiiscr`
--
ALTER TABLE `tbl_statiiscr`
 ADD PRIMARY KEY (`idstatiiscr`);

--
-- Indexes for table `tbl_statiopt`
--
ALTER TABLE `tbl_statiopt`
 ADD PRIMARY KEY (`idstatiopt`);

--
-- Indexes for table `tbl_statiutente`
--
ALTER TABLE `tbl_statiutente`
 ADD PRIMARY KEY (`idstatiutente`);

--
-- Indexes for table `tbl_statocomp`
--
ALTER TABLE `tbl_statocomp`
 ADD PRIMARY KEY (`idstatocomp`);

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
-- Indexes for table `tbl_utenti`
--
ALTER TABLE `tbl_utenti`
 ADD PRIMARY KEY (`idutente`,`username`), ADD KEY `fk_tbl_utenti_tbl_ruoli1_idx` (`idruolo`), ADD KEY `fk_tbl_utenti_tbl_statiutente1_idx` (`stato`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_competizione`
--
ALTER TABLE `tbl_competizione`
ADD CONSTRAINT `fk_cpt_tipocpt` FOREIGN KEY (`tipocomp`) REFERENCES `tbl_tipocomp` (`idtbl_tipocomp`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_tbl_competizione_tbl_statocomp` FOREIGN KEY (`statocomp`) REFERENCES `tbl_statocomp` (`idstatocomp`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_tbl_competizione_tbl_utenti` FOREIGN KEY (`idmdc`) REFERENCES `tbl_utenti` (`idutente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_iscrizione`
--
ALTER TABLE `tbl_iscrizione`
ADD CONSTRAINT `fk_tbl_iscrizione_tbl_statiiscr` FOREIGN KEY (`stato`) REFERENCES `tbl_statiiscr` (`idstatiiscr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_iscr_comp`
--
ALTER TABLE `tbl_iscr_comp`
ADD CONSTRAINT `fk_iscrcomp_comp` FOREIGN KEY (`idcompetizione`) REFERENCES `tbl_competizione` (`idcompetizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_iscrcomp_iscr` FOREIGN KEY (`idiscrizione`) REFERENCES `tbl_iscrizione` (`idiscrizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_tbl_iscr_comp_tbl_partecipante1` FOREIGN KEY (`idpartecipante`) REFERENCES `tbl_partecipante` (`idtpartecipante`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tbl_maincomp`
--
ALTER TABLE `tbl_maincomp`
ADD CONSTRAINT `fk_maincomp_comp` FOREIGN KEY (`idcompetizione`) REFERENCES `tbl_competizione` (`idcompetizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_maincomp_opt` FOREIGN KEY (`idoptional`) REFERENCES `tbl_optional` (`idoptional`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_maincomp_tipoopt` FOREIGN KEY (`idtipooptional`) REFERENCES `tbl_tipoopt` (`idtipooptional`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_optional`
--
ALTER TABLE `tbl_optional`
ADD CONSTRAINT `fk_tbl_optional_tbl_statiopt` FOREIGN KEY (`stato`) REFERENCES `tbl_statiopt` (`idstatiopt`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_opt_iscr`
--
ALTER TABLE `tbl_opt_iscr`
ADD CONSTRAINT `fk_opt_iscr_iscrizione` FOREIGN KEY (`idiscrizione`) REFERENCES `tbl_iscrizione` (`idiscrizione`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_opt_iscr_optional` FOREIGN KEY (`idoptional`) REFERENCES `tbl_optional` (`idoptional`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fl_opt_iscr_competizione` FOREIGN KEY (`idcompetizione`) REFERENCES `tbl_competizione` (`idcompetizione`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_utenti`
--
ALTER TABLE `tbl_utenti`
ADD CONSTRAINT `fk_tbl_utenti_tbl_ruoli` FOREIGN KEY (`idruolo`) REFERENCES `tbl_ruoli` (`idruolo`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_tbl_utenti_tbl_statiutente` FOREIGN KEY (`stato`) REFERENCES `tbl_statiutente` (`idstatiutente`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

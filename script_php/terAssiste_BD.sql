-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 09, 2014 at 02:18 PM
-- Server version: 5.1.57
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `a3404997_terapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `agent`
--

CREATE TABLE `agent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nom` text NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `contact` varchar(200) NOT NULL,
  `gare` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `agent`
--

INSERT INTO `agent` VALUES(1, 'goku', 'azerty01', 'sangoku', 'son', 'xx xx xx xx xx', 1);
INSERT INTO `agent` VALUES(2, 'admin', 'admin', 'adminnom', 'adminprenom', 'admincontact', 1);
INSERT INTO `agent` VALUES(3, '', '', 'test', 'test', 'test', 1);
INSERT INTO `agent` VALUES(4, 'sdq', 'sqsdq', 'sdsf', 'sfdssdf', 'sfddsf', 0);
INSERT INTO `agent` VALUES(5, 'sinthu', 'sinthu', 'sinthu', 'sinthu', 'sinthu', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `arrêt`
--

CREATE TABLE `arrêt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `gare` int(11) NOT NULL,
  `positionGare` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `arrêt`
--


-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `contact_client` varchar(200) NOT NULL,
  `contact_externe_client` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Dumping data for table `client`
--

INSERT INTO `client` VALUES(1, 'vegeta', 'vegeta', 'contactvegeta', 'contact externe vegeta');
INSERT INTO `client` VALUES(2, 'bulma', 'bulma', 'contact bulma', 'contact externe bulma');
INSERT INTO `client` VALUES(3, 'x551', 'x551', '', '');
INSERT INTO `client` VALUES(4, 'PmrMan', 'PrenomPMR', '', '');
INSERT INTO `client` VALUES(23, 'trop', 'walissdbaba', '', '');
INSERT INTO `client` VALUES(22, 'trop', 'walidbaba', '', '');
INSERT INTO `client` VALUES(21, 'trop', 'walid', '', '');
INSERT INTO `client` VALUES(20, 'test', 'ds', '', '');
INSERT INTO `client` VALUES(11, 'jjdjdjd', 'hdjdkdi', '', '');
INSERT INTO `client` VALUES(12, 'udjdj', 'hxbdod', '', '');
INSERT INTO `client` VALUES(13, 'un', 'deux', '', '');
INSERT INTO `client` VALUES(14, 'marche', 'oui', '', '');
INSERT INTO `client` VALUES(15, 'marche', 'oui', '', '');
INSERT INTO `client` VALUES(16, 'jdkdk', 'hdjdjdjkdkd', '', '');
INSERT INTO `client` VALUES(17, 'marche', 'oui', '', '');
INSERT INTO `client` VALUES(18, 'jsospsl', 'jdkdosl', '', '');
INSERT INTO `client` VALUES(19, 'bou', 'walid', '', '');
INSERT INTO `client` VALUES(28, 'tropdokokma', 'walidsbaba', '', '');
INSERT INTO `client` VALUES(25, 'x551', 'x551testwa', '', '');
INSERT INTO `client` VALUES(26, 'x551', 'x551ok', '', '');
INSERT INTO `client` VALUES(27, 'x551', 'x551test', '', '');
INSERT INTO `client` VALUES(29, 'lol', 'walidsbaba', '', '');
INSERT INTO `client` VALUES(30, 'baba', 'walid', '', '');
INSERT INTO `client` VALUES(31, 'un', 'deuxtrois', '', '');
INSERT INTO `client` VALUES(32, 'udjdjtest', 'hxbdodtest', '', '');
INSERT INTO `client` VALUES(33, 'babatortue', 'walid', '', '');
INSERT INTO `client` VALUES(34, 'babatortue', 'walidninja', '', '');
INSERT INTO `client` VALUES(35, 'routortue', 'walidninja', '', '');
INSERT INTO `client` VALUES(36, 'Tortue', 'Ninja', '', '');
INSERT INTO `client` VALUES(37, 'Walid', 'Baba', '', '');
INSERT INTO `client` VALUES(38, 'Waliddsdsf', 'Babadsfdsf', '', '');
INSERT INTO `client` VALUES(39, 'dsfdsf', 'qsdq', '', '');
INSERT INTO `client` VALUES(40, 'dsfsgfdgv', 'sdfsdfttest', '', '');
INSERT INTO `client` VALUES(41, 'ceciestuntest', 'ceciestuntest', '', '');
INSERT INTO `client` VALUES(42, 'ceciestuntest', 'ceciestuntest', '', '');
INSERT INTO `client` VALUES(43, 'ceciest', 'untest', '', '');
INSERT INTO `client` VALUES(44, 'walidbaba', 'golfsix', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itineraire` int(11) NOT NULL,
  `agent` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  `train` int(11) NOT NULL,
  `plan` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` VALUES(1, 13, 1, 44, 9, 1);
INSERT INTO `evenement` VALUES(19, 4, 3, 17, 2, 22);
INSERT INTO `evenement` VALUES(20, 4, 3, 18, 1, 23);
INSERT INTO `evenement` VALUES(16, 4, 2, 4, 1, 14);
INSERT INTO `evenement` VALUES(18, 1, 2, 31, 3, 18);
INSERT INTO `evenement` VALUES(17, 1, 2, 32, 2, 17);

-- --------------------------------------------------------

--
-- Table structure for table `gare`
--

CREATE TABLE `gare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `ville` varchar(200) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `retournement` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `gare`
--

INSERT INTO `gare` VALUES(1, 'olympiade', 'paris', 'rue olympiade', 1);
INSERT INTO `gare` VALUES(2, 'gare du nord', 'paris', 'rue gare du nord', 2);
INSERT INTO `gare` VALUES(3, 'olympiade', 'paris', 'rue olympiade', 1);
INSERT INTO `gare` VALUES(4, 'gare du nord', 'paris', 'rue gare du nord', 2);
INSERT INTO `gare` VALUES(5, 'gare du sud', '', '', 0);
INSERT INTO `gare` VALUES(6, 'Gare du nord de walid', '', '', 0);
INSERT INTO `gare` VALUES(7, 'gare du sud ninja', '', '', 0);
INSERT INTO `gare` VALUES(8, 'Gareninja', '', '', 0);
INSERT INTO `gare` VALUES(9, 'gareWalid', '', '', 0);
INSERT INTO `gare` VALUES(10, 'GareWalidBaba', '', '', 0);
INSERT INTO `gare` VALUES(11, 'GareTolbiac', '', '', 0);
INSERT INTO `gare` VALUES(12, 'GareWalidBabadsf', '', '', 0);
INSERT INTO `gare` VALUES(13, 'GareTolbiacsdf', '', '', 0);
INSERT INTO `gare` VALUES(14, 'wcz', '', '', 0);
INSERT INTO `gare` VALUES(15, 'ehgff', '', '', 0);
INSERT INTO `gare` VALUES(16, 'garetestx', '', '', 0);
INSERT INTO `gare` VALUES(17, 'gareloltest', '', '', 0);
INSERT INTO `gare` VALUES(18, 'garetoulouse', '', '', 0);
INSERT INTO `gare` VALUES(19, 'gareparis', '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `itineraire`
--

CREATE TABLE `itineraire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero_voyage` varchar(11) NOT NULL,
  `gare_depart` int(11) NOT NULL,
  `gare_arrive` int(11) NOT NULL,
  `date_depart` varchar(100) NOT NULL,
  `heure_depart` varchar(11) NOT NULL,
  `date_arrive` varchar(100) NOT NULL,
  `heure_arrive` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `itineraire`
--

INSERT INTO `itineraire` VALUES(2, 'X511', 1, 2, '2014-02-02', '11:00', '2014-02-02', '11:40');
INSERT INTO `itineraire` VALUES(1, 'X788', 2, 1, '2014-03-31', '08:00', '2014-03-31', '08:40');
INSERT INTO `itineraire` VALUES(3, 'X511', 1, 2, '2014-02-02', '11:00', '2014-02-02', '11:40');
INSERT INTO `itineraire` VALUES(4, 'X788', 2, 1, '2014-03-31', '08:00', '2014-03-31', '08:40');
INSERT INTO `itineraire` VALUES(5, '', 2, 0, '', '15h00', '', 'gare du sud');
INSERT INTO `itineraire` VALUES(6, '', 2, 5, '', '15h00', '', 'gare du sud');
INSERT INTO `itineraire` VALUES(7, '', 6, 7, '', '15h00', '', 'gare du sud');
INSERT INTO `itineraire` VALUES(8, '', 8, 9, '', '15h00', '', 'gare du sud');
INSERT INTO `itineraire` VALUES(9, '', 10, 11, '', '15h00', '', '16:00');
INSERT INTO `itineraire` VALUES(10, '', 12, 13, '', '15h00', '', '16:00');
INSERT INTO `itineraire` VALUES(11, '', 14, 15, '', '15h00', '', '16:00');
INSERT INTO `itineraire` VALUES(12, '', 16, 17, '', '15h00', '', '16:00');
INSERT INTO `itineraire` VALUES(13, '', 18, 19, '', '15h00', '', '16:00');

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `x` int(10) NOT NULL,
  `y` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `plan`
--

INSERT INTO `plan` VALUES(1, 50, 30);
INSERT INTO `plan` VALUES(2, 70, 277);
INSERT INTO `plan` VALUES(3, 70, 100);
INSERT INTO `plan` VALUES(4, 70, 100);
INSERT INTO `plan` VALUES(5, 70, 100);
INSERT INTO `plan` VALUES(6, 70, 100);
INSERT INTO `plan` VALUES(7, 70, 100);
INSERT INTO `plan` VALUES(8, 70, 100);
INSERT INTO `plan` VALUES(9, 70, 100);
INSERT INTO `plan` VALUES(10, 70, 100);
INSERT INTO `plan` VALUES(11, 70, 100);
INSERT INTO `plan` VALUES(12, 70, 100);
INSERT INTO `plan` VALUES(13, 70, 100);
INSERT INTO `plan` VALUES(14, 70, 100);
INSERT INTO `plan` VALUES(15, -1, -1);
INSERT INTO `plan` VALUES(16, 268, 726);
INSERT INTO `plan` VALUES(17, 261, 734);
INSERT INTO `plan` VALUES(18, 235, 142);
INSERT INTO `plan` VALUES(19, 161, 540);
INSERT INTO `plan` VALUES(20, 154, 546);
INSERT INTO `plan` VALUES(21, -1, -1);
INSERT INTO `plan` VALUES(22, 157, 534);
INSERT INTO `plan` VALUES(23, 257, 730);

-- --------------------------------------------------------

--
-- Table structure for table `train`
--

CREATE TABLE `train` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `date_entre_service` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `train`
--

INSERT INTO `train` VALUES(1, 'T51', '2014-01-01');
INSERT INTO `train` VALUES(2, 'T52', '2014-02-01');
INSERT INTO `train` VALUES(3, 'T53', '0000-00-00');
INSERT INTO `train` VALUES(4, 'T54', '0000-00-00');
INSERT INTO `train` VALUES(5, 'T54', '0000-00-00');
INSERT INTO `train` VALUES(6, 'T58', '0000-00-00');
INSERT INTO `train` VALUES(7, 'T57', '0000-00-00');
INSERT INTO `train` VALUES(8, 'T60', '0000-00-00');
INSERT INTO `train` VALUES(9, 'T61', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `voiture`
--

CREATE TABLE `voiture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capaciteTotale` int(30) NOT NULL,
  `capacitePMR` int(30) NOT NULL,
  `TypeNumerotation` int(30) NOT NULL,
  `train` int(11) NOT NULL,
  `plan` int(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `voiture`
--


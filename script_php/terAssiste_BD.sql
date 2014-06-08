-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 08, 2014 at 11:42 AM
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
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nom` text NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `contact` varchar(200) NOT NULL,
  `gare` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `agent`
--

INSERT INTO `agent` VALUES(1, 'goku', 'azerty01', 'sangoku', 'son', 'xx xx xx xx xx', 1);
INSERT INTO `agent` VALUES(2, 'admin', 'admin', 'adminnom', 'adminprenom', 'admincontact', 1);

-- --------------------------------------------------------

--
-- Table structure for table `arrêt`
--

CREATE TABLE `arrêt` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `gare` int(30) NOT NULL,
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
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `contact_client` varchar(200) NOT NULL,
  `contact_externe_client` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `client`
--

INSERT INTO `client` VALUES(1, 'vegeta', 'vegeta', 'contactvegeta', 'contact externe vegeta');
INSERT INTO `client` VALUES(2, 'bulma', 'bulma', 'contact bulma', 'contact externe bulma');

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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` VALUES(1, 1, 1, 1, 1);
INSERT INTO `evenement` VALUES(2, 1, 1, 2, 1);
INSERT INTO `evenement` VALUES(3, 2, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `gare`
--

CREATE TABLE `gare` (
  `id` int(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `ville` varchar(200) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `retournement` int(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gare`
--

INSERT INTO `gare` VALUES(1, 'olympiade', 'paris', 'rue olympiade', 1);
INSERT INTO `gare` VALUES(2, 'gare du nord', 'paris', 'rue gare du nord', 2);
INSERT INTO `gare` VALUES(3, 'olympiade', 'paris', 'rue olympiade', 1);
INSERT INTO `gare` VALUES(4, 'gare du nord', 'paris', 'rue gare du nord', 2);

-- --------------------------------------------------------

--
-- Table structure for table `itineraire`
--

CREATE TABLE `itineraire` (
  `id` int(11) NOT NULL,
  `numero_voyage` varchar(11) NOT NULL,
  `gare_depart` int(11) NOT NULL,
  `gare_arrive` int(11) NOT NULL,
  `date_depart` date NOT NULL,
  `heure_depart` varchar(11) NOT NULL,
  `date_arrive` date NOT NULL,
  `heure_arrive` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `itineraire`
--

INSERT INTO `itineraire` VALUES(2, 'X511', 1, 2, '2014-02-02', '11:00', '2014-02-02', '11:40');
INSERT INTO `itineraire` VALUES(1, 'X788', 2, 1, '2014-03-31', '08:00', '2014-03-31', '08:40');
INSERT INTO `itineraire` VALUES(3, 'X511', 1, 2, '2014-02-02', '11:00', '2014-02-02', '11:40');
INSERT INTO `itineraire` VALUES(4, 'X788', 2, 1, '2014-03-31', '08:00', '2014-03-31', '08:40');

-- --------------------------------------------------------

--
-- Table structure for table `plan`
--

CREATE TABLE `plan` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `localisation` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `plan`
--


-- --------------------------------------------------------

--
-- Table structure for table `train`
--

CREATE TABLE `train` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `date_entre_service` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `train`
--

INSERT INTO `train` VALUES(1, 'T51', '2014-01-01');
INSERT INTO `train` VALUES(2, 'T52', '2014-02-01');

-- --------------------------------------------------------

--
-- Table structure for table `voiture`
--

CREATE TABLE `voiture` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
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


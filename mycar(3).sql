-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 02 mai 2020 à 18:47
-- Version du serveur :  5.7.24
-- Version de PHP :  7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mycar`
--

-- --------------------------------------------------------

--
-- Structure de la table `assurance`
--

DROP TABLE IF EXISTS `assurance`;
CREATE TABLE IF NOT EXISTS `assurance` (
  `idAssurance` int(11) NOT NULL AUTO_INCREMENT,
  `idtype` int(11) NOT NULL,
  `libelle` varchar(15) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  PRIMARY KEY (`idAssurance`),
  KEY `idtype` (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `assurance`
--

INSERT INTO `assurance` (`idAssurance`, `idtype`, `libelle`, `adresse`) VALUES
(1, 1, 'Matmut', '2 Rue Gambetta '),
(2, 2, 'Matmut', '2 Rue Gambetta');

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `idLocation` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idVehicule` int(11) NOT NULL,
  `dateDebut` varchar(15) DEFAULT '-',
  `dateFin` varchar(15) NOT NULL DEFAULT '-',
  `prix` double NOT NULL,
  PRIMARY KEY (`idLocation`),
  KEY `idUser` (`idUser`),
  KEY `idVehicule` (`idVehicule`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`idLocation`, `idUser`, `idVehicule`, `dateDebut`, `dateFin`, `prix`) VALUES
(1, 1, 1, '2019-01-01', '2019-02-02', 4000),
(2, 1, 2, '2019-05-15', '2019-06-15', 4650),
(3, 1, 3, '2018-04-13', '2020-04-24', 140980),
(4, 1, 1, '2020-01-07', '2020-01-21', 1750),
(5, 1, 2, '2020-04-01', '2020-04-21', 3000),
(6, 1, 1, '2020-04-01', '2020-04-30', 3625);

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

DROP TABLE IF EXISTS `marque`;
CREATE TABLE IF NOT EXISTS `marque` (
  `idMarque` int(11) NOT NULL AUTO_INCREMENT,
  `libelleM` varchar(15) NOT NULL,
  PRIMARY KEY (`idMarque`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `marque`
--

INSERT INTO `marque` (`idMarque`, `libelleM`) VALUES
(1, 'Opel'),
(2, 'Renault'),
(3, 'Kia'),
(4, 'Mercedes'),
(5, 'Citroen');

-- --------------------------------------------------------

--
-- Structure de la table `typeassurance`
--

DROP TABLE IF EXISTS `typeassurance`;
CREATE TABLE IF NOT EXISTS `typeassurance` (
  `idType` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(15) NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `typeassurance`
--

INSERT INTO `typeassurance` (`idType`, `libelle`) VALUES
(1, 'Tiers'),
(2, 'Tout Risque');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `nomUser` varchar(25) NOT NULL,
  `prenomUser` varchar(25) NOT NULL,
  `telUser` int(10) NOT NULL,
  `mailUser` varchar(30) NOT NULL,
  `valeur` int(1) NOT NULL DEFAULT '0',
  `mdp` varchar(100) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUser`, `nomUser`, `prenomUser`, `telUser`, `mailUser`, `valeur`, `mdp`) VALUES
(1, 'Jean', 'Test', 102030405, 'test', 1, 'test'),
(2, 'montest', 'montest', 222, 'montest', 1, 'montest');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `idVehicule` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idMarque` int(11) NOT NULL,
  `idAssurance` int(11) NOT NULL,
  `idVille` int(11) NOT NULL,
  `matricule` varchar(9) NOT NULL,
  `anneeMeS` varchar(15) NOT NULL,
  `kmVehicule` int(10) NOT NULL,
  `tarif` int(10) NOT NULL,
  PRIMARY KEY (`idVehicule`),
  KEY `idMarque` (`idMarque`,`idAssurance`,`idVille`),
  KEY `idVille` (`idVille`),
  KEY `idAssurance` (`idAssurance`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`idVehicule`, `idUser`, `idMarque`, `idAssurance`, `idVille`, `matricule`, `anneeMeS`, `kmVehicule`, `tarif`) VALUES
(1, 1, 1, 2, 1, 'ES-387-PC', '2020-01-01', 9500, 125),
(2, 1, 2, 1, 2, 'EF-987-PO', '2018-01-02', 2500, 150),
(3, 1, 3, 1, 1, 'RT-987-ZA', '2018-03-05', 50, 190),
(4, 1, 4, 2, 2, 'AE-456-RT', '2019-01-02', 350, 150),
(5, 1, 5, 1, 5, 'FE-386-RT', '2018-01-02', 1250, 90),
(6, 1, 1, 1, 2, 'PE-890-RT', '2016-01-02', 34000, 50),
(7, 1, 2, 1, 4, 'DE-109-LO', '2020-02-02', 100, 250),
(8, 1, 2, 2, 3, 'ER-254-TR', '2018-10-05', 2500, 110),
(9, 1, 5, 2, 3, 'OE-980-LP', '2019-08-12', 350, 110),
(13, 1, 1, 1, 3, 'Matricule', 'Annee', 222, 222),
(14, 1, 5, 1, 2, 'Matricule', 'Annee', 444, 444),
(15, 1, 2, 1, 2, 'DE-527-FT', '2018-01-08', 180, 280);

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

DROP TABLE IF EXISTS `ville`;
CREATE TABLE IF NOT EXISTS `ville` (
  `idVille` int(11) NOT NULL AUTO_INCREMENT,
  `libelleV` varchar(25) NOT NULL,
  PRIMARY KEY (`idVille`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`idVille`, `libelleV`) VALUES
(1, 'Toulouse'),
(2, 'Lyon'),
(3, 'Bordeaux'),
(4, 'Toulon'),
(5, 'Marseille');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `assurance`
--
ALTER TABLE `assurance`
  ADD CONSTRAINT `assurance_ibfk_1` FOREIGN KEY (`idtype`) REFERENCES `typeassurance` (`idType`);

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `utilisateur` (`idUser`),
  ADD CONSTRAINT `location_ibfk_2` FOREIGN KEY (`idVehicule`) REFERENCES `vehicule` (`idVehicule`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`idVille`) REFERENCES `ville` (`idVille`),
  ADD CONSTRAINT `vehicule_ibfk_2` FOREIGN KEY (`idAssurance`) REFERENCES `assurance` (`idAssurance`),
  ADD CONSTRAINT `vehicule_ibfk_3` FOREIGN KEY (`idMarque`) REFERENCES `marque` (`idMarque`),
  ADD CONSTRAINT `vehicule_ibfk_4` FOREIGN KEY (`idUser`) REFERENCES `utilisateur` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

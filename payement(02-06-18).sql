-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 16 Janvier 2018 à 07:49
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `payement`
--

-- --------------------------------------------------------

--
-- Structure de la table `config`
--

CREATE TABLE `config` (
  `id_config` int(11) NOT NULL,
  `cnaps_config` varchar(15) NOT NULL,
  `osief_config` varchar(15) NOT NULL,
  `irsa_config` varchar(15) NOT NULL,
  `supel8_config` varchar(15) NOT NULL,
  `suple_plus8_config` varchar(15) NOT NULL,
  `zaza_vola_config` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `print_bulletin`
--

CREATE TABLE `print_bulletin` (
  `id_print` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `matricule` varchar(6) NOT NULL,
  `clasification` varchar(15) NOT NULL,
  `payer_du` varchar(20) NOT NULL,
  `salaire_base` decimal(12,0) NOT NULL,
  `indice` int(6) NOT NULL,
  `vocation` float NOT NULL,
  `heure30` float NOT NULL,
  `heure50` float NOT NULL,
  `idemnite` float NOT NULL,
  `avance` float NOT NULL,
  `nouriture` float NOT NULL,
  `cession` float NOT NULL,
  `cnaps` float NOT NULL,
  `osief` float NOT NULL,
  `irsa` float NOT NULL,
  `autre_reduc` float NOT NULL,
  `totale_brute` float NOT NULL,
  `totale_reduc` float NOT NULL,
  `montantt_payer` float NOT NULL,
  `enlettres` text NOT NULL,
  `pdu` varchar(20) NOT NULL,
  `pau` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `print_bulletin`
--

INSERT INTO `print_bulletin` (`id_print`, `nom`, `matricule`, `clasification`, `payer_du`, `salaire_base`, `indice`, `vocation`, `heure30`, `heure50`, `idemnite`, `avance`, `nouriture`, `cession`, `cnaps`, `osief`, `irsa`, `autre_reduc`, `totale_brute`, `totale_reduc`, `montantt_payer`, `enlettres`, `pdu`, `pau`) VALUES
(75, 'VONINAHITRA Calvyn', '001', 'M2-1B', '16/01/2018', '750000', 1756, 0, 45000.9, 25962.4, 1756, 60000, 0, 0, 8209.63, 16419.3, 87074.2, 0, 820963, 171703, 649260, 'six cent quarante-neuf mille deux cent soixante  Ariary', '01/01/2018', '31/01/2018');

-- --------------------------------------------------------

--
-- Structure de la table `tab_av`
--

CREATE TABLE `tab_av` (
  `id_av` int(11) NOT NULL,
  `num_perso` varchar(6) NOT NULL,
  `dt_now_av` varchar(20) NOT NULL,
  `mois_av` varchar(20) NOT NULL,
  `annee_av` varchar(20) NOT NULL,
  `periode_du_av` varchar(20) NOT NULL,
  `periode_au_av` varchar(20) NOT NULL,
  `idemnite_av` float NOT NULL,
  `voca_piece_av` float NOT NULL,
  `nbrhers_sup_av` int(3) NOT NULL,
  `hrs_sup8_av` float NOT NULL,
  `hrs_sup_plus8_av` float NOT NULL,
  `totale_brute_av` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tab_av`
--

INSERT INTO `tab_av` (`id_av`, `num_perso`, `dt_now_av`, `mois_av`, `annee_av`, `periode_du_av`, `periode_au_av`, `idemnite_av`, `voca_piece_av`, `nbrhers_sup_av`, `hrs_sup8_av`, `hrs_sup_plus8_av`, `totale_brute_av`) VALUES
(46, '001', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 0, 0, 12, 45000.9, 25962.4, 820963);

-- --------------------------------------------------------

--
-- Structure de la table `tab_avance`
--

CREATE TABLE `tab_avance` (
  `id_avance` int(11) NOT NULL,
  `num_perso` varchar(6) NOT NULL,
  `nom_pre_avance` varchar(50) NOT NULL,
  `dt_now_avance` varchar(20) NOT NULL,
  `mois_avance` varchar(20) NOT NULL,
  `annee_avance` varchar(20) NOT NULL,
  `periode_du_avance` varchar(20) NOT NULL,
  `periode_au_avance` varchar(20) NOT NULL,
  `total_avance` float NOT NULL,
  `reste_salaire` float NOT NULL,
  `enlettre` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tab_avance`
--

INSERT INTO `tab_avance` (`id_avance`, `num_perso`, `nom_pre_avance`, `dt_now_avance`, `mois_avance`, `annee_avance`, `periode_du_avance`, `periode_au_avance`, `total_avance`, `reste_salaire`, `enlettre`) VALUES
(30, '001', 'VONINAHITRA Calvyn', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 60000, 690000, 'soixante mille   Ariary'),
(31, '003', 'FITAHIANTSOA arilala malalasoa fitiavana', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 120000, 230000, 'cent vingt mille   Ariary'),
(32, '005', 'RASOA Anyah', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 250000, 650000, 'deux cent cinquante mille   Ariary');

-- --------------------------------------------------------

--
-- Structure de la table `tab_contrat`
--

CREATE TABLE `tab_contrat` (
  `id_contrat` int(11) NOT NULL,
  `num_perso` varchar(6) NOT NULL,
  `nom_pre_contrat` varchar(150) NOT NULL,
  `dt_now_contrat` varchar(20) NOT NULL,
  `dt_entre_contrat` varchar(20) NOT NULL,
  `dt_sortie_contrat` varchar(20) NOT NULL,
  `clasifi_contrat` varchar(15) NOT NULL,
  `nbhrs_mois_contrat` varchar(15) NOT NULL,
  `nbhrs_jrs_contrat` int(15) NOT NULL,
  `indice_contrat` varchar(6) NOT NULL,
  `sal_base_contrat` float NOT NULL,
  `lieu_trav_contrat` varchar(45) NOT NULL,
  `type_paie_contrat` varchar(15) NOT NULL,
  `type_contrat` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tab_contrat`
--

INSERT INTO `tab_contrat` (`id_contrat`, `num_perso`, `nom_pre_contrat`, `dt_now_contrat`, `dt_entre_contrat`, `dt_sortie_contrat`, `clasifi_contrat`, `nbhrs_mois_contrat`, `nbhrs_jrs_contrat`, `indice_contrat`, `sal_base_contrat`, `lieu_trav_contrat`, `type_paie_contrat`, `type_contrat`) VALUES
(57, '001', 'VONINAHITRA Calvyn', '31/01/2018', '01/01/2018', '?', 'M2-1B', '174', 8, '1756', 750000, 'Auto-tractor FIANARANTSOA', 'Caissier', 'CDI'),
(58, '002', 'FITAHIANA Lalaina', '15/03/2018', '01/03/2018', '?', 'OS2-2B', '174', 8, '1674', 677777, 'Auto-tractor FIANARANTSOA', 'Caissier', 'CDI'),
(60, '003', 'FITAHIANTSOA arilala malalasoa fitiavana', '15/01/2018', '01/01/2018', '?', 'OS1-2A', '174', 8, '1764', 350000, 'Auto-tractor FIANARANTSOA', 'Caissier', 'CDI'),
(61, '004', 'SITRATRAKA Malala', '15/01/2018', '01/01/2018', '31/08/2018', 'OP3-5B', '8', 175, '143', 750000, 'Auto-tractor FIANARANTSOA', 'Caissier', 'CDD'),
(62, '005', 'RASOA Anyah', '15/01/2018', '16/01/2018', '?', 'OS3-3A', '8', 8756, '1765', 900000, 'Auto-tractor FIANARANTSOA', 'Caissier', 'CDI'),
(63, '006', 'Monja Abel Patrice', '16/01/2018', '01-01-2018', '?', 'M1-A1', '8', 8, '1576', 390000, 'Auto-tractor FIANARANTSOA', 'Caisse', 'CDI'),
(64, '007', 'MIARISOA Vololondravony Jeanne', '16/01/2018', '01/01/2018', '?', 'OP1B-4A', '174', 8, '1745', 200000, 'Auto-tractor FIANARANTSOA', 'Caissier', 'CDI');

-- --------------------------------------------------------

--
-- Structure de la table `tab_entrep`
--

CREATE TABLE `tab_entrep` (
  `num_entrep` varchar(6) NOT NULL,
  `desi_entrep` varchar(30) NOT NULL,
  `adrs_entrep` varchar(50) NOT NULL,
  `tel_entrep` varchar(15) NOT NULL,
  `email_entrep` varchar(30) NOT NULL,
  `form_juridique_entrep` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tab_entrep`
--

INSERT INTO `tab_entrep` (`num_entrep`, `desi_entrep`, `adrs_entrep`, `tel_entrep`, `email_entrep`, `form_juridique_entrep`) VALUES
('0106', 'Auto-tractor FIANARANTSOA', 'Zorozoroana Fianarantsoa', '0202213412', 'autotractor@madauto.org', 'SA'),
('0202', 'Auto-tractor TULEAR', 'Betania Nord Tuléar', '0203124567', 'autotractortulear@madauto.org', 'SARL');

-- --------------------------------------------------------

--
-- Structure de la table `tab_osief`
--

CREATE TABLE `tab_osief` (
  `id_osief` int(11) NOT NULL,
  `num_perso` varchar(6) NOT NULL,
  `nom_perso` varchar(50) NOT NULL,
  `avantage` float NOT NULL,
  `salaire_base` float NOT NULL,
  `date_debut` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tab_paie`
--

CREATE TABLE `tab_paie` (
  `id_bulletin` int(11) NOT NULL,
  `num_perso` varchar(6) NOT NULL,
  `nom_pre_bulletin` varchar(50) NOT NULL,
  `dt_now_bulletin` varchar(20) NOT NULL,
  `mois_bulletin` varchar(20) NOT NULL,
  `annee_bulletin` varchar(20) NOT NULL,
  `periode_du_bulletin` varchar(20) NOT NULL,
  `periode_au_bulletin` varchar(20) NOT NULL,
  `total_reduc_bulletin` float NOT NULL,
  `totale_av_bulletin` float NOT NULL,
  `totale_net_bulletin` float NOT NULL,
  `sal_base` float NOT NULL,
  `simple_av` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tab_paie`
--

INSERT INTO `tab_paie` (`id_bulletin`, `num_perso`, `nom_pre_bulletin`, `dt_now_bulletin`, `mois_bulletin`, `annee_bulletin`, `periode_du_bulletin`, `periode_au_bulletin`, `total_reduc_bulletin`, `totale_av_bulletin`, `totale_net_bulletin`, `sal_base`, `simple_av`) VALUES
(270, '001', 'VONINAHITRA Calvyn', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 171703, 820963, 649260, 750000, 70963);

-- --------------------------------------------------------

--
-- Structure de la table `tab_perso`
--

CREATE TABLE `tab_perso` (
  `num_perso` varchar(6) NOT NULL,
  `nom_pre_perso` varchar(50) NOT NULL,
  `sexe_perso` varchar(6) NOT NULL,
  `dt_emb_perso` varchar(20) NOT NULL,
  `situ_perso` varchar(15) NOT NULL,
  `nbr_enf_perso` int(2) NOT NULL,
  `adrs_perso` varchar(45) NOT NULL,
  `tel_perso` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tab_perso`
--

INSERT INTO `tab_perso` (`num_perso`, `nom_pre_perso`, `sexe_perso`, `dt_emb_perso`, `situ_perso`, `nbr_enf_perso`, `adrs_perso`, `tel_perso`) VALUES
('001', 'VONINAHITRA Calvyn', 'H', '01-12-2017', 'Marié(e)', 2, 'Talatamaty Fianarantsoa', '0343129867'),
('002', 'FITAHIANA Lalaina', 'F', '01-12-2017', 'Célibataire', 0, 'Tanambao Fianarantsoa', '0321467851'),
('003', 'FITAHIANTSOA arilala malalasoa fitiavana', 'F', '23-11-2017', 'Marié(e)', 2, 'Ambatolahikiso Fianarantsoa', '0345234327'),
('004', 'SITRATRAKA Malala', 'F', '02-12-2017', 'Célibataire', 3, 'Soanierana Fianarantsoa', '0343120849'),
('005', 'RASOA Anyah', 'F', '08-11-2017', 'Marié(e)', 1, 'Tanambao Fianarantsoa', '0342456582'),
('006', 'Monja Abel Patrice', 'H', '03-01-2017', 'Célibataire', 5, 'Amponenana Fianaratsoa', '0346504696'),
('007', 'MIARISOA Vololondravony Jeanne', 'F', '04-07-2017', 'Célibataire', 2, 'Talatamaty Fianarantsoa', '0324533278'),
('008', 'FALIMANANA Arivony jean', 'H', '08-11-2017', 'Marié(e)', 4, 'Talatamaty Fianarantsoa', '0342565431'),
('009', 'TOKY Anthony', 'H', '15-11-2017', 'Divorcé(e)', 0, 'Tanambao Fianaratsoa', '0346798745'),
('010', 'LALAINA Fianaritra', 'F', '08-03-2017', 'Marié(e)', 1, 'Tsaramandroso Fianaratsoa', '0325644321'),
('011', 'HERINIAINA Fatima', 'F', '03-11-2017', 'Célibataire', 0, 'Beravina Fianarantsoa', '0334567612'),
('012', 'SOJA SAOTSINJAZA Faralahy Jeans de Lex', 'H', '09-11-2017', 'Marié(e)', 0, 'Soanierana Fianaratsoa', '0331745498'),
('013', 'ARIVO Lalaniaina Haja', 'F', '03-11-2017', 'Marié(e)', 2, 'Ampabomena Fianaratsoa', '0325467871'),
('014', 'BIRIGITTE Lahitiana', 'F', '04-11-2017', 'Divorcé(e)', 1, 'Tsaramandroso Fianarantsoa', '0234657861'),
('015', 'HERISOLO Felaniaina', 'F', '08-11-2017', 'Célibataire', 0, 'Fianaratsoa ambony', '0321246578'),
('016', 'LALAINA Felana', 'F', '15-11-2017', 'Marié(e)', 2, 'Zorozoroana Fianaratsoa', '0345678631'),
('017', 'VELONORO Rakotomalala', 'H', '02-11-2017', 'Marié(e)', 1, 'Antsirabe I', '0345632219'),
('018', 'RIJA Solomalala', 'F', '15-03-2017', 'Célibataire', 2, 'Tanambao Fianaratsoa', '0345632189'),
('019', 'RASOA Fitia malala', 'F', '14-06-2017', 'Marié(e)', 2, 'Fianaratsoa ambony', '0345634298'),
('020', 'DIDIER Rakotomalala Rija', 'H', '08-06-2017', 'Célibataire', 3, 'Mahasoabe ', '0345687622'),
('021', 'HERY Faliana', 'F', '03-11-2017', 'Marié(e)', 0, 'Travaux Fianaratsoa', '0235487654'),
('022', 'DERAINA Fanyah', 'F', '05-11-2016', 'Marié(e)', 0, 'Ambodirano Atsimo Fianaratsoa', '0345678754'),
('023', 'RAJAO Patricia Marotia', 'F', '08-11-2017', 'Marié(e)', 2, 'Beravina Fianarantsoa', '0324576898'),
('024', 'RAKOTO Vohangy', 'F', '15-11-2017', 'Marié(e)', 2, 'Ankofafa andrefana Fianarantsoa', '0336587609'),
('025', 'BENJA Rafaralahy Christian', 'H', '09-11-2017', 'Marié(e)', 1, 'Ankofafa avaratra Fianaratsoa', '0347698753'),
('026', 'HERY Felatiana', 'F', '17-11-2017', 'Marié(e)', 2, 'BEtsorohitra Fianaratsoa', '0324532178'),
('029', 'ANDRIAMILAITSO Marie Larissa', 'F', '18/01/2018', 'Célibataire', 0, 'Andrainjato O9', '0347351538');

-- --------------------------------------------------------

--
-- Structure de la table `tab_reduc`
--

CREATE TABLE `tab_reduc` (
  `id_reduc` int(11) NOT NULL,
  `num_perso` varchar(6) NOT NULL,
  `dt_now_reduc` varchar(20) NOT NULL,
  `mois_reduc` varchar(20) NOT NULL,
  `annee_reduc` varchar(20) NOT NULL,
  `periode_du_reduc` varchar(20) NOT NULL,
  `periode_au_reduc` varchar(20) NOT NULL,
  `cnaps_reduc` float NOT NULL,
  `osief_reduc` float NOT NULL,
  `irsa_reduc` float NOT NULL,
  `cess_sal_reduc` float NOT NULL,
  `nouri_log_reduc` float NOT NULL,
  `autres_reduc` float NOT NULL,
  `avance_reduc` float NOT NULL,
  `totale_brute_reduc` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tab_reduc`
--

INSERT INTO `tab_reduc` (`id_reduc`, `num_perso`, `dt_now_reduc`, `mois_reduc`, `annee_reduc`, `periode_du_reduc`, `periode_au_reduc`, `cnaps_reduc`, `osief_reduc`, `irsa_reduc`, `cess_sal_reduc`, `nouri_log_reduc`, `autres_reduc`, `avance_reduc`, `totale_brute_reduc`) VALUES
(212, '001', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 8209.63, 16419.3, 87074.2, 0, 0, 0, 60000, 171703),
(213, '002', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 6777.77, 13555.5, 81488.7, 12000, 0, 0, 0, 113822),
(214, '003', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 3500, 7000, 9900, 0, 45000, 0, 120000, 185400),
(215, '004', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 7500, 15000, 83500, 0, 0, 0, 0, 106000),
(216, '005', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 9000, 18000, 120600, 0, 0, 0, 250000, 397600),
(217, '007', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 2000, 4000, 2000, 0, 0, 0, 0, 8000),
(218, '006', '16/01/2018', '1', '2018', '01/01/2018', '31/01/2018', 3900, 7800, 5660, 0, 0, 0, 0, 17360);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_user` int(11) NOT NULL,
  `nom_user` varchar(50) NOT NULL,
  `mot_de_passe_user` varchar(35) NOT NULL,
  `privilege` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `nom_user`, `mot_de_passe_user`, `privilege`) VALUES
(1, 'autotractor', 'adminroot', 'root'),
(4, 'vyn', 'vyn', 'simple'),
(7, 'user', 'user', 'root');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`id_config`);

--
-- Index pour la table `print_bulletin`
--
ALTER TABLE `print_bulletin`
  ADD PRIMARY KEY (`id_print`),
  ADD UNIQUE KEY `id_print` (`id_print`);

--
-- Index pour la table `tab_av`
--
ALTER TABLE `tab_av`
  ADD PRIMARY KEY (`id_av`);

--
-- Index pour la table `tab_avance`
--
ALTER TABLE `tab_avance`
  ADD PRIMARY KEY (`id_avance`);

--
-- Index pour la table `tab_contrat`
--
ALTER TABLE `tab_contrat`
  ADD PRIMARY KEY (`id_contrat`),
  ADD UNIQUE KEY `num_perso` (`num_perso`);

--
-- Index pour la table `tab_entrep`
--
ALTER TABLE `tab_entrep`
  ADD PRIMARY KEY (`num_entrep`),
  ADD UNIQUE KEY `num_entrep` (`num_entrep`);

--
-- Index pour la table `tab_osief`
--
ALTER TABLE `tab_osief`
  ADD PRIMARY KEY (`id_osief`);

--
-- Index pour la table `tab_paie`
--
ALTER TABLE `tab_paie`
  ADD PRIMARY KEY (`id_bulletin`);

--
-- Index pour la table `tab_perso`
--
ALTER TABLE `tab_perso`
  ADD PRIMARY KEY (`num_perso`),
  ADD UNIQUE KEY `num_perso` (`num_perso`);

--
-- Index pour la table `tab_reduc`
--
ALTER TABLE `tab_reduc`
  ADD PRIMARY KEY (`id_reduc`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `config`
--
ALTER TABLE `config`
  MODIFY `id_config` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `print_bulletin`
--
ALTER TABLE `print_bulletin`
  MODIFY `id_print` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
--
-- AUTO_INCREMENT pour la table `tab_av`
--
ALTER TABLE `tab_av`
  MODIFY `id_av` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;
--
-- AUTO_INCREMENT pour la table `tab_avance`
--
ALTER TABLE `tab_avance`
  MODIFY `id_avance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT pour la table `tab_contrat`
--
ALTER TABLE `tab_contrat`
  MODIFY `id_contrat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;
--
-- AUTO_INCREMENT pour la table `tab_osief`
--
ALTER TABLE `tab_osief`
  MODIFY `id_osief` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `tab_paie`
--
ALTER TABLE `tab_paie`
  MODIFY `id_bulletin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=274;
--
-- AUTO_INCREMENT pour la table `tab_reduc`
--
ALTER TABLE `tab_reduc`
  MODIFY `id_reduc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=219;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


//////////////////////////

 double aciente =Double.parseDouble(txt_anciente.getText());
    double idemnite=Double.parseDouble(txt_idemnite_sup.getText());
    double vocation =Double.parseDouble(txt_vacation_sup.getText());
    double val8 =Double.parseDouble(txt_val_8_sup.getText());
    double val_plus8 =Double.parseDouble(txt_val_8_plus_sup.getText());
    double val_40 = Double.parseDouble(txt_plus40.getText());
    double sal_base =Double.parseDouble(txt_sal_base_sup.getText());
    double sommes = Double.parseDouble(txt_totale_brute_sup.getText());
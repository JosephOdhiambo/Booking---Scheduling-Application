-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2023 at 03:24 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bus`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `from` varchar(50) NOT NULL,
  `to` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`from`, `to`) VALUES
('Barishal', 'Jessore'),
('Chittagong', 'Rajshahi'),
('Dhaka', 'Sylhet'),
('Jessore', 'Chandpur'),
('Khulna', 'Chittagong'),
('Sylhet', 'Dhaka');

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `service` varchar(45) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `seats` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`name`, `phone`, `source`, `destination`, `service`, `date`, `seats`, `amount`) VALUES
('jose', '079634949', 'Ziwani', 'CBD', 'Shaymoli Enterprise', '2023-09-24', '3', '4500'),
('jose', '9209409292', 'South B', 'Buru Buru', 'Embassava', '2023-08-19', '3', '4353'),
('jose', '0795848359', 'South B', 'CBD', 'ROG', '2023-12-16', '2', '2800'),
('jose', '0799213370', 'Ngong', 'Ziwani', 'Ena Enterprise', '2023-10-31', '3', '12177'),
('Arif', '94502499', 'Ziwani', 'CBD', 'Shaymoli Enterprise', '2023-09-24', '3', '4500'),
('arif', '0359394', 'Ngong', 'Westlands', 'Esteem', '2023-11-01', '1', '1209');

-- --------------------------------------------------------

--
-- Table structure for table `bookk`
--

CREATE TABLE `bookk` (
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookk`
--

INSERT INTO `bookk` (`location`) VALUES
('Buru Buru'),
('CBD'),
('Kangemi'),
('Kilimani'),
('Kisumu'),
('Naivasha'),
('Nakuru'),
('Ngong'),
('Rongai'),
('South B'),
('Umoja'),
('Westlands'),
('Ziwani');

-- --------------------------------------------------------

--
-- Table structure for table `search`
--

CREATE TABLE `search` (
  `service` varchar(50) NOT NULL,
  `source` varchar(45) DEFAULT NULL,
  `dest` varchar(50) DEFAULT NULL,
  `fare` int(11) DEFAULT NULL,
  `dtime` time DEFAULT NULL,
  `atime` time DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `iframe` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `search`
--

INSERT INTO `search` (`service`, `source`, `dest`, `fare`, `dtime`, `atime`, `seat`, `date`, `iframe`) VALUES
('CBET', 'Ziwani', 'Rongai', 3909, '14:23:00', '16:14:00', 40, '2023-08-31', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d127639.40901753178!2d36.71040767364765!3d-1.337344989588954!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f1132faa0b3f3%3A0xa70ce39dc0c18bb6!2sZiwani%20Estate%2C%20Kinyanjui%20Street%2C%20Nairobi!3m2!1d-1.2801532!2d36.8351201!4m5!1s0x182f05cf50f94e8d%3A0x51c29656e6fd8ca9!2sOngata%20Rongai!3m2!1d-1.3938636!2d36.7442377!5e0!3m2!1sen!2ske!4v1691290557072!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></ifra<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d127639.40901753178!2d36.71040767364765!3d-1.337344989588954!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f1132faa0b3f3%3A0xa70ce39dc0c18bb6!2sZiwani%20Estate%2C%20Kinyanjui%20Street%2C%20Nairobi!3m2!1d-1.2801532!2d36.8351201!4m5!1s0x182f05cf50f94e8d%3A0x51c29656e6fd8ca9!2sOngata%20Rongai!3m2!1d-1.3938636!2d36.7442377!5e0!3m2!1sen!2ske!4v1691290557072!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Embassava', 'South B', 'Buru Buru', 1450, '05:30:00', '07:14:00', 41, '2023-08-19', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d31910.235732240268!2d36.83481289453842!3d-1.3075181105368474!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f11a9d644a551%3A0xffc84960ca560e99!2sSouth%20B%2C%20Nairobi!3m2!1d-1.310341!2d36.8337368!4m5!1s0x182f13e9aaac2bd1%3A0xa05de4b5dda910a8!2sBuruburu%2C%20Nairobi!3m2!1d-1.2922481!2d36.8815479!5e0!3m2!1sen!2ske!4v1691290769418!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Ena Enterprise', 'Ngong', 'Ziwani', 4059, '03:23:00', '10:14:00', 10, '2023-10-31', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d127640.46133384413!2d36.6656204235069!3d-1.3169554592386006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f02e428edc063%3A0xd8c5ba0ad5a3c153!2sNgong!3m2!1d-1.3562117999999999!2d36.6687545!4m5!1s0x182f1132faa0b3f3%3A0xa70ce39dc0c18bb6!2sZiwani%20Estate%2C%20Kinyanjui%20Street%2C%20Nairobi!3m2!1d-1.2801532!2d36.8351201!5e0!3m2!1sen!2ske!4v1691290829218!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Esteem', 'Ngong', 'Westlands', 1209, '11:13:00', '11:20:00', 5, '2023-11-01', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d127640.70157789422!2d36.65630097347459!3d-1.3122561137706006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f02e428edc063%3A0xd8c5ba0ad5a3c153!2sNgong!3m2!1d-1.3562117999999999!2d36.6687545!4m5!1s0x182f173c0a1f9de7%3A0xad2c84df1f7f2ec8!2sWestlands%2C%20Nairobi!3m2!1d-1.2675001!2d36.812022!5e0!3m2!1sen!2ske!4v1691290868530!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Gor', 'Umoja', 'Rongai', 2817, '08:27:00', '09:00:00', 26, '2023-08-23', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d127640.23923248702!2d36.744143023536736!3d-1.3212850550642425!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f138c569e12fd%3A0x62504850cd217879!2sUmoja%2C%20Nairobi!3m2!1d-1.2743571!2d36.9057288!4m5!1s0x182f05cf50f94e8d%3A0x51c29656e6fd8ca9!2sOngata%20Rongai!3m2!1d-1.3938636!2d36.7442377!5e0!3m2!1sen!2ske!4v1691341964654!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Nyumbani Sacco', 'Kangemi', 'Rongai', 1249, '07:26:00', '10:31:00', 50, '2023-11-09', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d127639.6746021961!2d36.63548127361226!3d-1.3322284945176919!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f199c5c5b282b%3A0x80e9e2429e405793!2sKangemi%2C%20Nairobi!3m2!1d-1.2711928!2d36.7394386!4m5!1s0x182f05cf50f94e8d%3A0x51c29656e6fd8ca9!2sOngata%20Rongai!3m2!1d-1.3938636!2d36.7442377!5e0!3m2!1sen!2ske!4v1692430960626!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('ROG', 'South B', 'CBD', 1400, '03:23:00', '06:14:00', 41, '2023-12-16', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d15955.138937373025!2d36.8159632198829!3d-1.3041986766176838!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f11a9d644a551%3A0xffc84960ca560e99!2sSouth%20B%2C%20Nairobi!3m2!1d-1.310341!2d36.8337368!4m5!1s0x182f11bb686b1b43%3A0xab3fb2ddbf719d70!2sNairobi%20C%20B%20D%2C%20Nairobi!3m2!1d-1.2997041999999999!2d36.8187794!5e0!3m2!1sen!2ske!4v1691290910817!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Shaymoli Enterprise', 'Ziwani', 'CBD', 1500, '03:23:00', '10:14:00', 42, '2023-09-24', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d31910.460788157903!2d36.80669649453085!3d-1.2896921613851862!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f1132faa0b3f3%3A0xa70ce39dc0c18bb6!2sZiwani%20Estate%2C%20Kinyanjui%20Street%2C%20Nairobi!3m2!1d-1.2801532!2d36.8351201!4m5!1s0x182f11bb686b1b43%3A0xab3fb2ddbf719d70!2sNairobi%20C%20B%20D%2C%20Nairobi!3m2!1d-1.2997041999999999!2d36.8187794!5e0!3m2!1sen!2ske!4v1691290952675!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Sheba Greenline', 'Rongai', 'Buru Buru', 1400, '03:23:00', '06:22:00', 10, '2023-09-28', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d127639.04954455321!2d36.730489073695416!3d-1.3442392329499686!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182f05cf50f94e8d%3A0x51c29656e6fd8ca9!2sOngata%20Rongai!3m2!1d-1.3938636!2d36.7442377!4m5!1s0x182f13e9aaac2bd1%3A0xa05de4b5dda910a8!2sBuruburu%2C%20Nairobi!3m2!1d-1.2922481!2d36.8815479!5e0!3m2!1sen!2ske!4v1691291001989!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>'),
('Sherehe sacco', 'Kisumu', 'Naivasha', 1637, '09:25:00', '19:33:00', 23, '2023-11-08', '<iframe src=\"https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d1021369.3200947986!2d34.940056727286404!3d-0.39416629748407134!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e6!4m5!1s0x182aa437ad4ac81d%3A0x2012a439d6248dd2!2sKisumu!3m2!1d-0.0917016!2d34.7679568!4m5!1s0x1829178acc9ab163%3A0x674c5cf58d8b055d!2sNaivasha%20Town!3m2!1d-0.7057165999999999!2d36.425613399999996!5e0!3m2!1sen!2ske!4v1692430734547!5m2!1sen!2ske\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>');

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `seatname` varchar(50) NOT NULL,
  `uname` varchar(50) NOT NULL,
  `service` varchar(50) NOT NULL,
  `count` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seats`
--

INSERT INTO `seats` (`seatname`, `uname`, `service`, `count`, `id`) VALUES
('A1', 'Neaz', 'Ena Enterprise', 0, 1),
('A2', 'Neaz', 'Ena Enterprise', 0, 2),
('A1', 'Neaz', 'Shaymoli Enterprise', 1, 3),
('A2', 'Neaz', 'Shaymoli Enterprise', 1, 4),
('A3', 'Neaz', 'Ena Enterprise', 0, 5),
('A4', 'Neaz', 'Ena Enterprise', 0, 6),
('B1', 'Neaz', 'Ena Enterprise', 0, 7),
('B2', 'Neaz', 'Ena Enterprise', 0, 8),
('B3', 'Neaz', 'Ena Enterprise', 0, 9),
('B4', 'Neaz', 'Ena Enterprise', 1, 10),
('C1', 'Neaz', 'Ena Enterprise', 1, 11),
('C2', 'Neaz', 'Ena Enterprise', 1, 12),
('C3', 'Neaz', 'Ena Enterprise', 1, 13),
('C4', 'Neaz', 'Ena Enterprise', 1, 14),
('D1', 'Neaz', 'Ena Enterprise', 1, 15),
('D2', 'Neaz', 'Ena Enterprise', 0, 16),
('D3', 'Neaz', 'Ena Enterprise', 0, 17),
('D4', 'Neaz', 'Ena Enterprise', 0, 18),
('A3', 'Neaz', 'Shaymoli Enterprise', 1, 19),
('A4', 'Neaz', 'Shaymoli Enterprise', 0, 20),
('B1', 'Neaz', 'Shaymoli Enterprise', 1, 21),
('B2', 'Neaz', 'Shaymoli Enterprise', 0, 22),
('B3', 'Neaz', 'Shaymoli Enterprise', 0, 23),
('B4', 'Neaz', 'Shaymoli Enterprise', 0, 24),
('C1', 'Neaz', 'Shaymoli Enterprise', 0, 25),
('C2', 'Neaz', 'Shaymoli Enterprise', 0, 26),
('C3', 'Neaz', 'Shaymoli Enterprise', 0, 27),
('C4', 'Neaz', 'Shaymoli Enterprise', 0, 28),
('D1', 'Neaz', 'Shaymoli Enterprise', 0, 29),
('D2', 'Neaz', 'Shaymoli Enterprise', 0, 30),
('D3', 'Neaz', 'Shaymoli Enterprise', 0, 31),
('D4', 'Neaz', 'Shaymoli Enterprise', 0, 32),
('A1', 'Neaz', 'Sheba Greenline', 0, 33),
('A2', 'Neaz', 'Sheba Greenline', 0, 34),
('A3', 'Neaz', 'Sheba Greenline', 0, 35),
('A4', 'Neaz', 'Sheba Greenline', 0, 36),
('B1', 'Neaz', 'Sheba Greenline', 0, 37),
('B2', 'Neaz', 'Sheba Greenline', 0, 38),
('B3', 'Neaz', 'Sheba Greenline', 0, 39),
('B4', 'Neaz', 'Sheba Greenline', 0, 40),
('C1', 'Neaz', 'Sheba Greenline', 0, 41),
('C2', 'Neaz', 'Sheba Greenline', 0, 42),
('C3', 'Neaz', 'Sheba Greenline', 0, 50),
('C4', 'Neaz', 'Sheba Greenline', 0, 51),
('D1', 'Neaz', 'Sheba Greenline', 0, 54),
('D2', 'Neaz', 'Sheba Greenline', 0, 55),
('D3', 'Neaz', 'Sheba Greenline', 0, 56),
('D4', 'Neaz', 'Sheba Greenline', 0, 57);

-- --------------------------------------------------------

--
-- Table structure for table `seat_names`
--

CREATE TABLE `seat_names` (
  `seatname` varchar(50) NOT NULL,
  `picked` int(11) NOT NULL DEFAULT 0,
  `service` varchar(50) NOT NULL,
  `uname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seat_names`
--

INSERT INTO `seat_names` (`seatname`, `picked`, `service`, `uname`) VALUES
('1DJG2', 0, 'CBET', ''),
('686B8', 0, 'Sheba Greenline', ''),
('8BTQ5', 0, 'Gor', ''),
('A190', 1, 'Ena Enterprise', ''),
('A2', 0, 'Ena Enterprise', ''),
('A3', 1, 'Ena Enterprise', 'jose'),
('A4', 0, 'Ena Enterprise', ''),
('B1', 0, 'Ena Enterprise', ''),
('B2', 0, 'Ena Enterprise', ''),
('B3', 1, 'Ena Enterprise', 'jose'),
('B4', 0, 'Ena Enterprise', ''),
('C1', 1, 'Ena Enterprise', 'jose'),
('C2', 0, 'Ena Enterprise', ''),
('C3', 1, 'Shaymoli Enterprise', 'Arif'),
('C4', 0, 'Shaymoli Enterprise', ''),
('CZ6SJ', 0, 'Shaymoli Enterprise', ''),
('D1', 0, 'Shaymoli Enterprise', ''),
('D2', 1, 'Shaymoli Enterprise', 'jose'),
('D3', 1, 'Shaymoli Enterprise', 'Arif'),
('D4', 1, 'Shaymoli Enterprise', 'jose'),
('E1', 1, 'Shaymoli Enterprise', 'jose'),
('E2', 0, 'Shaymoli Enterprise', ''),
('E3', 0, 'Shaymoli Enterprise', ''),
('E4', 1, 'Sheba Greenline', ''),
('EXLMW', 1, 'Shaymoli Enterprise', 'Arif'),
('F2', 0, 'Sheba Greenline', ''),
('F3', 0, 'Sheba Greenline', ''),
('F4', 0, 'Sheba Greenline', ''),
('G1', 0, 'Sheba Greenline', ''),
('G2', 0, 'Sheba Greenline', ''),
('G3', 0, 'Sheba Greenline', ''),
('G4', 0, 'Sheba Greenline', ''),
('H1', 0, 'Sheba Greenline', ''),
('H2', 1, 'Sheba Greenline', ''),
('H3', 0, 'Sheba Greenline', ''),
('H4', 0, 'Sheba Greenline', ''),
('HIS183', 0, 'Embassava', ''),
('I1', 1, 'Embassava', 'jose'),
('I2', 0, 'Embassava', ''),
('I3', 1, 'Embassava', ''),
('I4', 1, 'Embassava', 'jose'),
('I5', 0, 'Embassava', ''),
('J59', 0, 'ROG', ''),
('J7', 1, 'ROG', 'jose'),
('JTS2Y', 1, 'ROG', 'jose'),
('K6', 0, 'Sheba Greenline', ''),
('K9EQ4', 0, 'CBET', ''),
('LNAHE', 0, 'Embassava', ''),
('Q9FSG', 1, 'CBET', ''),
('T6', 0, 'Sheba Greenline', ''),
('WZDRD', 1, 'Esteem', 'arif'),
('YIMDP', 1, 'Embassava', 'jose');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uname` varchar(50) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `gender` varchar(23) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uname`, `password`, `fname`, `lname`, `phone`, `age`, `state`, `city`, `gender`, `email`) VALUES
('Arif', '1234', 'Arif', 'Hasan', '01658421', '25', 'Dhaka', 'Dhaka', 'Male', 'arif@gmail.com'),
('ian', '1234', 'Ian', 'Mwai', '079492342', '25', 'Kenya', 'Nairobi', 'Male', 'ian@email.com'),
('jose', '1234', 'Joseph', 'Odhiambo', '0799213371', '26', 'Kenya', 'Nairobi', 'Male', 'jose@email.com'),
('lil', '1234', 'Lil', 'wayne', '03530', '26', 'Kenya', 'Nairobi', 'Male', 'lil@email.com'),
('mcenzie', '1234', 'Mackensie', 'Mwai', '07923994', '48', 'Kenya', 'Nairobi', 'Male', 'kenzie@email.com'),
('Modi', '1234', 'Joseph', 'Odhiambo', '0359249', '26', 'Kenya', 'Nairobi', 'Male', 'jose@email.com'),
('Neaz', '1234', 'Neaz', 'Mahmud', '02164654', '22', 'Dhaka', 'Dhaka', 'Male', 'neaz@gmail.com'),
('sharp', '1234', 'Sharp', 'Shooter', '04920422', '24', 'Canada', 'toronto', 'Male', 'shoot@email.com'),
('Wahome', '1234', 'Joseph', 'Odhiambo', '0359249', '26', 'Kenya', 'Nairobi', 'Male', 'jose@email.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`from`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD KEY `bookings_ibfk_1` (`name`);

--
-- Indexes for table `bookk`
--
ALTER TABLE `bookk`
  ADD PRIMARY KEY (`location`);

--
-- Indexes for table `search`
--
ALTER TABLE `search`
  ADD PRIMARY KEY (`service`);

--
-- Indexes for table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seatname` (`seatname`),
  ADD KEY `uname` (`uname`),
  ADD KEY `service` (`service`);

--
-- Indexes for table `seat_names`
--
ALTER TABLE `seat_names`
  ADD PRIMARY KEY (`seatname`),
  ADD KEY `service` (`service`),
  ADD KEY `uname` (`uname`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uname`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`name`) REFERENCES `user` (`uname`);

--
-- Constraints for table `seats`
--
ALTER TABLE `seats`
  ADD CONSTRAINT `seats_ibfk_2` FOREIGN KEY (`uname`) REFERENCES `user` (`uname`),
  ADD CONSTRAINT `seats_ibfk_3` FOREIGN KEY (`service`) REFERENCES `search` (`service`);

--
-- Constraints for table `seat_names`
--
ALTER TABLE `seat_names`
  ADD CONSTRAINT `seat_names_ibfk_1` FOREIGN KEY (`service`) REFERENCES `search` (`service`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

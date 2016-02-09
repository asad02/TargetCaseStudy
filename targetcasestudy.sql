-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 09, 2016 at 12:08 AM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `targetcasestudy`
--

-- --------------------------------------------------------

--
-- Table structure for table `Product`
--

CREATE TABLE IF NOT EXISTS `Product` (
  `ID` int(10) NOT NULL,
  `SKU` varchar(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `CATEGORY` varchar(20) NOT NULL,
  `LAST_UPDATED` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Product`
--

INSERT INTO `Product` (`ID`, `SKU`, `NAME`, `CATEGORY`, `LAST_UPDATED`) VALUES
(5555, 'AEX143', 'Stroller', 'baby', '2016-02-01'),
(5556, 'IoL123', 'Optimus Prime', 'toys', '2015-05-01'),
(5557, 'XYZ904', 'Sega Genesis', 'toys', '2014-03-01'),
(5558, 'ASD123', 'Baby Shampoo', 'Baby', '2016-02-06'),
(5559, 'ASD123', 'Baby Shampoo', 'Baby', '2016-02-06'),
(5560, 'ASD123', 'Baby Shampoo', 'Baby', '2016-02-06'),
(5561, 'ASD123', 'Baby Shampoo', 'Baby', '2016-02-06');

-- --------------------------------------------------------

--
-- Table structure for table `productprice`
--

CREATE TABLE IF NOT EXISTS `productprice` (
  `ID` int(10) NOT NULL,
  `PRICE` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productprice`
--

INSERT INTO `productprice` (`ID`, `PRICE`) VALUES
(5555, '120.99'),
(5556, '20.99'),
(5557, '10.99'),
(5558, '12.20'),
(5559, '12.20'),
(5560, '12.20'),
(5561, '12.20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

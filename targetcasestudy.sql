-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 19, 2016 at 06:52 PM
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
  `LAST_UPDATED` date NOT NULL,
  `ACTIVE` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Product`
--

INSERT INTO `Product` (`ID`, `SKU`, `NAME`, `CATEGORY`, `LAST_UPDATED`, `ACTIVE`) VALUES
(1232, 'test', '123', '123', '2016-06-18', 'X'),
(5556, 'IoL123', 'Optimus Prime', 'toys', '2016-06-18', 'X'),
(5557, 'XYZ904', 'Sega Genesis', 'toys', '2016-06-18', 'X'),
(5558, 'ASD123', 'Baby Shampoo', 'Baby1', '2016-06-18', 'X'),
(5559, 'ASD123', 'Baby Shampoo', 'Baby', '2016-06-18', 'X'),
(5560, 'ASD123', 'Baby Shampoo', 'Baby', '2016-06-18', 'X'),
(5561, 'dsf', 'rwr', 'dsf', '2016-06-18', 'X'),
(5562, 'KJH332', 'New product', 'adult', '2016-06-18', 'X'),
(5563, 'HKJ232', 'New shirt', 'men', '2016-06-18', 'X'),
(5567, 'KJH332', 'New product', 'adult', '2016-06-19', 'X'),
(12334, 'SKU123', 'test product12', 'baby 23', '2016-06-18', 'X'),
(13860428, 'AEX143', 'Stroller', 'baby', '2016-06-18', 'X');

-- --------------------------------------------------------

--
-- Table structure for table `productprice`
--

CREATE TABLE IF NOT EXISTS `productprice` (
  `ID` int(10) NOT NULL,
  `PRICE` decimal(10,2) NOT NULL,
  `CURRENCY` varchar(10) NOT NULL,
  `PRICEACTIVE` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productprice`
--

INSERT INTO `productprice` (`ID`, `PRICE`, `CURRENCY`, `PRICEACTIVE`) VALUES
(1232, '23.23', '123', 'X'),
(5556, '20.90', 'CAD', 'X'),
(5557, '10.99', 'CAD', 'X'),
(5558, '12.20', 'USD', 'X'),
(5559, '12.20', 'USD', 'X'),
(5560, '12.23', 'USD', 'X'),
(5561, '23.23', 'CD', 'X'),
(5562, '34.98', 'CAD', 'X'),
(5563, '23.56', 'CAD', 'X'),
(5567, '34.98', 'CAD', 'X'),
(12334, '12.33', 'CAD', 'X'),
(13860428, '120.99', 'USD', 'X');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `productprice`
--
ALTER TABLE `productprice`
  ADD PRIMARY KEY (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

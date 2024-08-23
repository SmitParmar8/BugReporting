-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2024 at 11:33 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `assignbug` (IN `in_developer_id` INT, IN `in_task_id` INT)   BEGIN
insert into task_assigned (Developer_id,task_id) values(in_developer_id,in_task_id);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertintotask` (IN `in_detail` VARCHAR(50), IN `in_priority` VARCHAR(50), IN `in_date` DATE)   BEGIN
insert into task(Task_detail,Task_priority,Task_creation_date) values (in_detail,in_priority,in_date);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `completed_bugs`
--

CREATE TABLE `completed_bugs` (
  `completion_id` int(11) NOT NULL,
  `developer_id` int(11) NOT NULL,
  `task_detail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `completed_bugs`
--

INSERT INTO `completed_bugs` (`completion_id`, `developer_id`, `task_detail`) VALUES
(1, 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `deleted_task`
--

CREATE TABLE `deleted_task` (
  `deleted_Task_Id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `task_detail` varchar(50) NOT NULL,
  `Task_priority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `deleted_task`
--

INSERT INTO `deleted_task` (`deleted_Task_Id`, `task_id`, `task_detail`, `Task_priority`) VALUES
(1, 4, 'its 3 am im not sleeping', 'High');

-- --------------------------------------------------------

--
-- Table structure for table `developer`
--

CREATE TABLE `developer` (
  `developer_id` int(11) NOT NULL,
  `Developer_name` varchar(50) NOT NULL,
  `Developer_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `developer`
--

INSERT INTO `developer` (`developer_id`, `Developer_name`, `Developer_password`) VALUES
(1, 'dev_1', 'dfgh'),
(2, 'hail_2', 'hitler'),
(3, 'jack', 'hjkl'),
(4, 'doom', 'downey'),
(5, 'bruce', 'hulkisfav'),
(6, 'nick', 'furry'),
(7, 'tony', 'stark'),
(8, 'bruce', 'banner');

-- --------------------------------------------------------

--
-- Table structure for table `senior`
--

CREATE TABLE `senior` (
  `s_id` int(11) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `s_pass` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `senior`
--

INSERT INTO `senior` (`s_id`, `s_name`, `s_pass`) VALUES
(1, 'stop', 'rukja');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `Task_id` int(11) NOT NULL,
  `Task_Detail` varchar(50) NOT NULL,
  `Task_priority` varchar(50) NOT NULL,
  `Task_creation_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`Task_id`, `Task_Detail`, `Task_priority`, `Task_creation_date`) VALUES
(1, 'all is well', 'High', '2024-08-21'),
(2, 'this needs to fix later', 'Low', '2024-08-21'),
(3, 'delevering food map bug', 'Medium', '2024-08-21');

--
-- Triggers `task`
--
DELIMITER $$
CREATE TRIGGER `deletedtask` AFTER DELETE ON `task` FOR EACH ROW BEGIN
    -- Insert the deleted developer_id and task_detail into the completedbugs table
    INSERT INTO deleted_task (task_id, task_detail,Task_priority)
    VALUES (OLD.task_id, OLD.task_detail,OLD.task_priority);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `task_assigned`
--

CREATE TABLE `task_assigned` (
  `task_assign_id` int(11) NOT NULL,
  `Developer_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `task_detail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task_assigned`
--

INSERT INTO `task_assigned` (`task_assign_id`, `Developer_id`, `task_id`, `task_detail`) VALUES
(2, 2, 3, 'delevering food map bug'),
(3, 6, 2, 'this needs to fix later');

--
-- Triggers `task_assigned`
--
DELIMITER $$
CREATE TRIGGER `addetail` BEFORE INSERT ON `task_assigned` FOR EACH ROW BEGIN
    DECLARE task_detail_value VARCHAR(255);
    
    SELECT task_detail INTO task_detail_value
    FROM task
    WHERE task_id = NEW.task_id;

    -- Set the fetched task_detail into the task_assign table
    SET NEW.task_detail = task_detail_value;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `completebugs` AFTER DELETE ON `task_assigned` FOR EACH ROW BEGIN
    INSERT INTO completed_bugs (developer_id, task_detail)
    VALUES (OLD.developer_id, OLD.task_detail);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tester`
--

CREATE TABLE `tester` (
  `tester_id` int(11) NOT NULL,
  `tester_name` varchar(50) NOT NULL,
  `tester_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tester`
--

INSERT INTO `tester` (`tester_id`, `tester_name`, `tester_password`) VALUES
(1, 'smit', 'sdfg'),
(2, 'jigo', 'jigo'),
(3, 'khush', 'rajaji'),
(6, 'ayush', 'dale'),
(7, 'arush', 'noyka'),
(8, 'karan', 'sehgal'),
(9, 'kaalu', 'bahu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `completed_bugs`
--
ALTER TABLE `completed_bugs`
  ADD PRIMARY KEY (`completion_id`);

--
-- Indexes for table `deleted_task`
--
ALTER TABLE `deleted_task`
  ADD PRIMARY KEY (`deleted_Task_Id`);

--
-- Indexes for table `developer`
--
ALTER TABLE `developer`
  ADD PRIMARY KEY (`developer_id`);

--
-- Indexes for table `senior`
--
ALTER TABLE `senior`
  ADD PRIMARY KEY (`s_id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`Task_id`);

--
-- Indexes for table `task_assigned`
--
ALTER TABLE `task_assigned`
  ADD PRIMARY KEY (`task_assign_id`);

--
-- Indexes for table `tester`
--
ALTER TABLE `tester`
  ADD PRIMARY KEY (`tester_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `completed_bugs`
--
ALTER TABLE `completed_bugs`
  MODIFY `completion_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `deleted_task`
--
ALTER TABLE `deleted_task`
  MODIFY `deleted_Task_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `developer`
--
ALTER TABLE `developer`
  MODIFY `developer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `senior`
--
ALTER TABLE `senior`
  MODIFY `s_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `Task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `task_assigned`
--
ALTER TABLE `task_assigned`
  MODIFY `task_assign_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tester`
--
ALTER TABLE `tester`
  MODIFY `tester_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.4.0-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- db_library 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `db_library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `db_library`;

-- 테이블 db_library.tbl_admin_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_admin_member` (
  `a_m_no` int(11) NOT NULL AUTO_INCREMENT,
  `a_m_approval` int(11) NOT NULL DEFAULT 0,
  `a_m_id` varchar(20) NOT NULL,
  `a_m_pw` varchar(100) NOT NULL,
  `a_m_name` varchar(20) NOT NULL,
  `a_m_gender` char(1) NOT NULL,
  `a_m_part` varchar(20) NOT NULL,
  `a_m_position` varchar(20) NOT NULL,
  `a_m_mail` varchar(50) NOT NULL,
  `a_m_phone` varchar(20) NOT NULL,
  `a_m_reg_date` datetime DEFAULT NULL,
  `a_m_mod_date` datetime DEFAULT NULL,
  PRIMARY KEY (`a_m_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 db_library.tbl_admin_member:~0 rows (대략적) 내보내기
INSERT IGNORE INTO `tbl_admin_member` (`a_m_no`, `a_m_approval`, `a_m_id`, `a_m_pw`, `a_m_name`, `a_m_gender`, `a_m_part`, `a_m_position`, `a_m_mail`, `a_m_phone`, `a_m_reg_date`, `a_m_mod_date`) VALUES
	(1, 1, 'super admin', '1234', 'SuperAdmin', 'M', 'book management', 'book manger', 'super.admin@gmail.com', '010-1234-5675', '2024-02-21 13:54:10', '2024-02-21 15:13:36');

-- 테이블 db_library.tbl_book 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_book` (
  `b_no` int(11) NOT NULL AUTO_INCREMENT,
  `b_thumbnail` varchar(100) DEFAULT NULL,
  `b_name` varchar(30) NOT NULL,
  `b_author` varchar(20) NOT NULL,
  `b_publisher` varchar(20) NOT NULL,
  `b_publish_year` char(4) NOT NULL,
  `b_isbn` varchar(30) NOT NULL,
  `b_call_number` varchar(30) NOT NULL,
  `b_rantal_able` tinyint(4) NOT NULL DEFAULT 1,
  `b_reg_date` datetime DEFAULT NULL,
  `b_mod_date` datetime DEFAULT NULL,
  PRIMARY KEY (`b_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 db_library.tbl_book:~0 rows (대략적) 내보내기

-- 테이블 db_library.tbl_rental_book 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_rental_book` (
  `rb_no` int(11) NOT NULL AUTO_INCREMENT,
  `b_no` int(11) DEFAULT NULL,
  `u_m_no` int(11) DEFAULT NULL,
  `rb_start_date` datetime DEFAULT NULL,
  `rb_end_date` datetime DEFAULT '1000-01-01 00:00:00',
  `rb_reg_date` datetime DEFAULT NULL,
  `rb_mod_date` datetime DEFAULT NULL,
  PRIMARY KEY (`rb_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 db_library.tbl_rental_book:~0 rows (대략적) 내보내기

-- 테이블 db_library.tbl_user_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_user_member` (
  `u_m_no` int(11) NOT NULL AUTO_INCREMENT,
  `u_m_id` varchar(20) NOT NULL,
  `u_m_pw` varchar(100) NOT NULL,
  `u_m_name` varchar(20) NOT NULL,
  `u_m_gender` char(1) NOT NULL,
  `u_m_mail` varchar(50) NOT NULL,
  `u_m_phone` varchar(20) NOT NULL,
  `u_m_reg_date` datetime DEFAULT NULL,
  `u_m_mod_date` datetime DEFAULT NULL,
  PRIMARY KEY (`u_m_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 db_library.tbl_user_member:~0 rows (대략적) 내보내기

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

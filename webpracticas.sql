-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-09-2023 a las 22:22:15
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `practicaescuela`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `id_alumno` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(50) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `h_cumplidas` int(11) NOT NULL,
  `h_incumplidas` int(3) NOT NULL,
  `faltas` int(11) NOT NULL,
  `curso_id` int(11) NOT NULL,
  `fk_domicilio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id_alumno`, `nombre`, `apellido`, `dni`, `h_cumplidas`, `h_incumplidas`, `faltas`, `curso_id`, `fk_domicilio`) VALUES
(6, 'Ignacio', 'Benitez', '46928483', 350, -150, 7, 2, NULL),
(8, 'Emiliano', 'Escobar', '48376273', 40, 160, 10, 2, NULL),
(9, 'danidan', 'sergan', '32358841', 100, 100, 3, 2, NULL),
(13, 'Nehuel', 'Alfonso', '46289837', 60, 140, 5, 1, NULL),
(14, 'Emiliano', 'Benitez', '47987263', 50, 150, 1, 2, NULL),
(15, 'Antonio', 'Gonzales', '47839267', 0, 200, 0, 2, NULL),
(16, 'Mariano', 'Mrreno', '47263738', 0, 200, 0, 2, NULL),
(17, 'Maria', 'Angeles', '48783736', 0, 200, 0, 2, NULL),
(18, 'Joako', 'Perez', '46987278', 0, 200, 0, 2, NULL),
(19, 'Tomas', 'Amanecer', '46567398', 0, 200, 1, 2, NULL),
(20, 'Celeste', 'Cabrera', '47567987', 0, 200, 0, 2, NULL),
(21, 'Nahuel', 'plate paz', '46013822', 0, 200, 0, 2, NULL),
(22, 'Mariela', 'Hernandez', '47687567', 200, 0, 0, 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL,
  `curso` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`id_curso`, `curso`) VALUES
(1, '7°3'),
(2, '7°5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `id_profesor` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`id_profesor`, `nombre`, `password`) VALUES
(5, 'franco plate', '$argon2id$v=19$m=1024,t=1,p=1$kJTKYsq3kx9ZBm8ahLVF+A$hfAy/PSzdegXVxf/fNAfo4USl7/zHB+/53BG9tI9jR4'),
(6, 'daniprofe', '$argon2id$v=19$m=1024,t=1,p=1$4LkBn/xNCy+gGOPQgjhBtA$lzZ5imCBcMW7WoBlq1yaOKgMe1IYh/lGs31WGu0EWw0'),
(7, 'daniprofe', '$argon2id$v=19$m=1024,t=1,p=1$D8B39jIQuvq5jOJPDDYF2w$k9qpOYRAVJOGUz3fYXMQ2QG2SvcqvQ4W1p8eUo1v3/c'),
(8, 'franco plate', '$argon2id$v=19$m=1024,t=1,p=1$cuAbMmSNB0/Ic8hwsiiAzQ$bX04IFkxebjP0gdgQe6JvBfWsebEpLZkRPJaJS35FCg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id_alumno`),
  ADD KEY `curso_id` (`curso_id`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id_curso`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`id_profesor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `id_alumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `id_curso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `profesor`
--
ALTER TABLE `profesor`
  MODIFY `id_profesor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `FK7edsj2vrlmd6jft8djvxjil8y` FOREIGN KEY (`fk_domicilio`) REFERENCES `domicilio` (`id`),
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`curso_id`) REFERENCES `cursos` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

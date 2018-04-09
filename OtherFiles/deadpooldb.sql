-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Апр 05 2018 г., 12:20
-- Версия сервера: 5.7.17-log
-- Версия PHP: 7.1.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `deadpooldb`
--

-- --------------------------------------------------------

--
-- Структура таблицы `crime`
--

CREATE TABLE `crime` (
  `crime_id` int(10) UNSIGNED NOT NULL,
  `criminal_case_id` int(10) UNSIGNED NOT NULL,
  `crime_type` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `crime_date` date NOT NULL,
  `crime_time` time DEFAULT NULL,
  `crime_place` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `crime`
--

INSERT INTO `crime` (`crime_id`, `criminal_case_id`, `crime_type`, `description`, `crime_date`, `crime_time`, `crime_place`) VALUES
(2, 2, 'ARSON', '', '2018-02-06', '18:41:14', ''),
(3, 2, 'ROBBERY', 'gfb', '2018-02-08', NULL, ''),
(4, 7, 'MURDER', 'Some decription', '2015-02-28', '15:15:00', 'This is place'),
(5, 7, 'MURDER', 'Some decription2', '2015-02-28', NULL, 'This is place');

-- --------------------------------------------------------

--
-- Структура таблицы `criminal_case`
--

CREATE TABLE `criminal_case` (
  `criminal_case_id` int(10) UNSIGNED NOT NULL,
  `detective_id` int(10) UNSIGNED NOT NULL,
  `criminal_case_number` varchar(50) NOT NULL,
  `create_date` date NOT NULL,
  `close_date` date DEFAULT NULL,
  `closed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `criminal_case`
--

INSERT INTO `criminal_case` (`criminal_case_id`, `detective_id`, `criminal_case_number`, `create_date`, `close_date`, `closed`) VALUES
(2, 1, 'hndth', '2012-02-02', NULL, 0),
(3, 1, 'new criminal case number', '2018-02-26', '2018-02-08', 1),
(5, 1, 'NoCriminalCaseNumber', '2018-02-12', NULL, 0),
(6, 1, 'Абракадабра', '2015-02-24', NULL, 1),
(7, 1, 'Это успех, my dear!', '2015-03-24', NULL, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `detective`
--

CREATE TABLE `detective` (
  `detective_id` int(10) UNSIGNED NOT NULL,
  `login` varchar(32) NOT NULL,
  `hash_of_password` char(32) NOT NULL,
  `email` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `detective`
--

INSERT INTO `detective` (`detective_id`, `login`, `hash_of_password`, `email`) VALUES
(1, 'bartimeyse', '098f6bcd4621d373cade4e832627b4f6', 'bartimeyse@yandex.by'),
(9, 'password_test', '098f6bcd4621d373cade4e832627b4f6', 'some@gmail.com'),
(49, 'weg', '793805d61d24ed4ade56d8ba1a808dbf', 'some@gmail.com');

-- --------------------------------------------------------

--
-- Структура таблицы `evidence`
--

CREATE TABLE `evidence` (
  `evidence_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `evidence`
--

INSERT INTO `evidence` (`evidence_id`, `name`, `description`) VALUES
(1, 'Оренбургский пуховый платок', 'Произведено в СССР, 1979 г., Оренбургская фабрика');

-- --------------------------------------------------------

--
-- Структура таблицы `evidence_of_crime`
--

CREATE TABLE `evidence_of_crime` (
  `evidence_id` int(10) UNSIGNED NOT NULL,
  `crime_id` int(10) UNSIGNED NOT NULL,
  `evidence_type` varchar(50) NOT NULL,
  `date_added` datetime NOT NULL,
  `details` text,
  `photo_path` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `evidence_of_crime`
--

INSERT INTO `evidence_of_crime` (`evidence_id`, `crime_id`, `evidence_type`, `date_added`, `details`, `photo_path`) VALUES
(1, 3, 'CRIME_INSTRUMENT', '2018-03-18 10:33:15', 'Этим платком была, предположительно, задушена потерпевшая. Найден рядом с трупом. Отпечатков пальцев нет.', 'а_я_не_знаю_путь_к_картинке_этой.png');

-- --------------------------------------------------------

--
-- Структура таблицы `man`
--

CREATE TABLE `man` (
  `man_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(40) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `birthday` date DEFAULT NULL,
  `home_address` text,
  `photo_path` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `man`
--

INSERT INTO `man` (`man_id`, `name`, `surname`, `birthday`, `home_address`, `photo_path`) VALUES
(1, 'NoName', 'NoSurname', '1998-09-08', 'NoHomeAddress', NULL),
(2, 'name', 'surname', NULL, 'addr', 'photo'),
(3, 'Sherlock', 'Holms', '2012-02-15', 'some_address in Minsk for Andrew', NULL),
(8, 'Sherlock', 'Holms', '2012-02-15', 'some_address in Minsk for Andrew', NULL),
(9, 'Testes', 'Person', NULL, NULL, NULL),
(49, 'weg', 'wegew', NULL, NULL, NULL),
(319, 'name', 'surname', '2012-12-02', 'addr', 'photo'),
(320, 'name', 'surname', '2012-12-02', 'addr', 'photo'),
(329, 'name', 'surname', '2012-12-02', 'addr', 'photo'),
(333, 'name', 'surname', '2012-12-02', 'addr', 'photo'),
(334, 'name', 'surname', '2012-12-02', 'addr', 'photo');

-- --------------------------------------------------------

--
-- Структура таблицы `participant`
--

CREATE TABLE `participant` (
  `man_id` int(10) UNSIGNED NOT NULL,
  `crime_id` int(10) UNSIGNED NOT NULL,
  `participant_status` varchar(50) NOT NULL,
  `date_added` datetime NOT NULL,
  `alibi` text,
  `witness_report` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `participant`
--

INSERT INTO `participant` (`man_id`, `crime_id`, `participant_status`, `date_added`, `alibi`, `witness_report`) VALUES
(1, 2, 'SUSPECTED', '2018-02-25 12:24:45', NULL, 'witness reportnewnewnew'),
(1, 3, 'SUSPECTED', '2018-02-26 13:24:45', NULL, 'witness reportnewnewnew'),
(2, 2, 'WITNESS', '2018-02-27 14:24:45', NULL, NULL),
(2, 3, 'SUSPECTED', '2018-02-28 15:24:45', NULL, 'SomeReportInfo');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `crime`
--
ALTER TABLE `crime`
  ADD PRIMARY KEY (`crime_id`),
  ADD KEY `IXFK_crime_criminal_case` (`criminal_case_id`);

--
-- Индексы таблицы `criminal_case`
--
ALTER TABLE `criminal_case`
  ADD PRIMARY KEY (`criminal_case_id`),
  ADD KEY `IXFK_criminal_case_detective` (`detective_id`);

--
-- Индексы таблицы `detective`
--
ALTER TABLE `detective`
  ADD PRIMARY KEY (`detective_id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `IXFK_detective_man` (`detective_id`);

--
-- Индексы таблицы `evidence`
--
ALTER TABLE `evidence`
  ADD PRIMARY KEY (`evidence_id`);

--
-- Индексы таблицы `evidence_of_crime`
--
ALTER TABLE `evidence_of_crime`
  ADD PRIMARY KEY (`evidence_id`,`crime_id`),
  ADD KEY `IXFK_evidence_of_crime_crime` (`crime_id`),
  ADD KEY `IXFK_evidence_evidence_type` (`evidence_type`),
  ADD KEY `IXFK_evidence_of_crime_evidence` (`evidence_id`);

--
-- Индексы таблицы `man`
--
ALTER TABLE `man`
  ADD PRIMARY KEY (`man_id`);

--
-- Индексы таблицы `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`man_id`,`crime_id`),
  ADD KEY `IXFK_participant_crime` (`crime_id`),
  ADD KEY `IXFK_participant_man` (`man_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `crime`
--
ALTER TABLE `crime`
  MODIFY `crime_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT для таблицы `criminal_case`
--
ALTER TABLE `criminal_case`
  MODIFY `criminal_case_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT для таблицы `evidence`
--
ALTER TABLE `evidence`
  MODIFY `evidence_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `man`
--
ALTER TABLE `man`
  MODIFY `man_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=342;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `crime`
--
ALTER TABLE `crime`
  ADD CONSTRAINT `FK_crime_criminal_case` FOREIGN KEY (`criminal_case_id`) REFERENCES `criminal_case` (`criminal_case_id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `criminal_case`
--
ALTER TABLE `criminal_case`
  ADD CONSTRAINT `FK_criminal_case_detective` FOREIGN KEY (`detective_id`) REFERENCES `detective` (`detective_id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `detective`
--
ALTER TABLE `detective`
  ADD CONSTRAINT `FK_detective_man` FOREIGN KEY (`detective_id`) REFERENCES `man` (`man_id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `evidence_of_crime`
--
ALTER TABLE `evidence_of_crime`
  ADD CONSTRAINT `FK_evidence_of_crime_crime` FOREIGN KEY (`crime_id`) REFERENCES `crime` (`crime_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_evidence_of_crime_evidence` FOREIGN KEY (`evidence_id`) REFERENCES `evidence` (`evidence_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `FK_participant_crime` FOREIGN KEY (`crime_id`) REFERENCES `crime` (`crime_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_participant_man` FOREIGN KEY (`man_id`) REFERENCES `man` (`man_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

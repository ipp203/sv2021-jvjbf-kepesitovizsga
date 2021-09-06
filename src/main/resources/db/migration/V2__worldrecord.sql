CREATE TABLE `world_record` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `date_of_record` DATE,
 `description` VARCHAR(255),
 `unit_of_measure` VARCHAR(255),
 `value` DOUBLE PRECISION NOT NULL,
 `recorder_id` BIGINT,
 CONSTRAINT FOREIGN KEY(`recorder_id`) REFERENCES recorder(`id`));
CREATE TABLE `key_value_store` (
                                   `key_string` varchar(400) NOT NULL,
                                   `value` text,
                                   `expires_at` datetime DEFAULT NULL,
                                   PRIMARY KEY (`key_string`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

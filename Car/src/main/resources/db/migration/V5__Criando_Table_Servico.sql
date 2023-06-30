DROP TABLE IF EXISTS `tb_servico`;
CREATE TABLE `tb_servico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2aew4rgd8u5tbpjd9elny62kx` (`cliente_id`),
  CONSTRAINT `FK2aew4rgd8u5tbpjd9elny62kx` FOREIGN KEY (`cliente_id`) REFERENCES `tb_cliente` (`id`)
);





DROP TABLE IF EXISTS `peca_carro`;
CREATE TABLE `peca_carro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `garantia` varchar(255) DEFAULT NULL,
  `nome_peca` varchar(255) DEFAULT NULL,
  `valor_peca` double DEFAULT NULL,
  PRIMARY KEY (`id`)
);







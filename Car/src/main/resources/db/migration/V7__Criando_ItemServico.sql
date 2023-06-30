
DROP TABLE IF EXISTS `item_servico`;
CREATE TABLE `item_servico` (
  `quantidade` int DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `peca_carro_id` bigint NOT NULL,
  `servico_id` bigint NOT NULL,
  PRIMARY KEY (`peca_carro_id`,`servico_id`),
  KEY `FKk76eocv3jyfs7k67n7ac9jesn` (`servico_id`),
  CONSTRAINT `FK4lioexg40h26ihroptqwet8s8` FOREIGN KEY (`peca_carro_id`) REFERENCES `peca_carro` (`id`),
  CONSTRAINT `FKk76eocv3jyfs7k67n7ac9jesn` FOREIGN KEY (`servico_id`) REFERENCES `tb_servico` (`id`)
);





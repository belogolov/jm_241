$2a$11$cYjriu266u/.GdaL7oKXD.1K8uqMUfxfmCmti4AzEK8gY2sRjB6kK


INSERT INTO `spring_security`.`roles` (`id`, `role`) VALUES ('1', 'ADMIN');
INSERT INTO `spring_security`.`roles` (`id`, `role`) VALUES ('2', 'USER');
INSERT INTO `spring_security`.`users_roles` (`role_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `spring_security`.`users_roles` (`role_id`, `user_id`) VALUES ('2', '1');
INSERT INTO `spring_security`.`users_roles` (`role_id`, `user_id`) VALUES ('2', '2');
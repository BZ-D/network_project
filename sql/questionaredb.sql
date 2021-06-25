SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `user_intel`;

CREATE TABLE `user_intel`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `ac_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `num_of_filling` int(11) NOT NULL DEFAULT 0,
    `num_of_release` int(11) NOT NULL DEFAULT 0,
    `num_of_draft` int(11) NOT NULL DEFAULT 0,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `user_intel` VALUES (1, '12345678','Mr学长',1,1,0,  '123456abc_', '2021-6-3 16:00:00');
INSERT INTO `user_intel` VALUES (2,'12345679', 'ad',1,0,0,  '123456abc_', '2021-6-3 16:00:00');

DROP TABLE IF EXISTS `questionare`;
CREATE TABLE `questionare`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `create_user_id` int(11) NOT NULL,
    `title_of_qn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `is_draft` tinyint(1) NOT NULL DEFAULT 1,
    `num_of_questions` int(11) NOT NULL,
    `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_user_qn`(`create_user_id`) USING BTREE
    /*CONSTRAINT `fk_user_qn` FOREIGN KEY (`create_user_id`) REFERENCES `user_intel` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE

     */
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `questionare` VALUES (1, 1,'调查网站用户基本信息', 0, '4', '2021-6-3 16:00:00');

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `qn_id` int(11) NOT NULL,
    `question_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `is_must` tinyint(1) NOT NULL DEFAULT 1,
    `option_a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '无',
    `option_b` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '无',
    `option_c` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '无',
    `option_d` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '无',
    `option_e` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '无',
    `option_f` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '无',
    `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_qn_question`(`qn_id`) USING BTREE
    /*CONSTRAINT `fk_qn_question` FOREIGN KEY (`qn_id`) REFERENCES `questionare` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE

     */
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `question` VALUES (1,1, '您的性别？', 'single', 1,'man','woman','无','无','无','无', '2021-6-3 16:00:00');
INSERT INTO `question` VALUES (2,1, '您的名字？', 'gap-fill', 1, '无','无','无','无','无','无','2021-6-3 16:00:00');
INSERT INTO `question` VALUES (3,1, '您的特长？', 'multiple', 1, 'football','painting','writing','singing','无','无','2021-6-3 16:00:00');
INSERT INTO `question` VALUES (4,1, '您的银行卡密码？', 'gap-fill', 1, '无','无','无','无','无','无','2021-6-3 16:00:00');

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `answer_user_id` int(11) NOT NULL,
    `answer_qn_id` int(11) NOT NULL,
    `question_id` int(11) NOT NULL,

    `gap_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '无',
    `single_answer` int(11) NOT NULL DEFAULT 0,
    `multi_one` tinyint(1) NOT NULL DEFAULT 0,
    `multi_two` tinyint(1) NOT NULL DEFAULT 0,
    `multi_three` tinyint(1) NOT NULL DEFAULT 0,
    `multi_four` tinyint(1) NOT NULL DEFAULT 0,
    `multi_five` tinyint(1) NOT NULL DEFAULT 0,
    `multi_six` tinyint(1) NOT NULL DEFAULT 0,

    `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_question_answer`(`question_id`) USING BTREE,
    INDEX `fk_user_answer`(`answer_user_id`) USING BTREE,
    INDEX `fk_qn_answer`(`answer_qn_id`) USING BTREE
    /*CONSTRAINT `fk_question_answer` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_answer` FOREIGN KEY (`answer_user_id`) REFERENCES `user_intel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_qn_answer` FOREIGN KEY (`answer_qn_id`) REFERENCES `questionare` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

     */
) ENGINE = InnoDB AUTO_INCREMENT = 198 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `answer` VALUES (1, 1, 1,1, '无',1,0,0,0,0,0,0,'2020-12-18 10:00:00');
INSERT INTO `answer` VALUES (2, 1, 1,2, '学长',0,0,0,0,0,0,0, '2020-12-18 10:00:00');
INSERT INTO `answer` VALUES (3, 1, 1,3, '无', 0,1,1,0,0,0,0, '2020-12-18 10:00:00');
INSERT INTO `answer` VALUES (4, 1, 1,4, '？？？为什么给你说',0,0,0,0,0,0,0,'2020-12-18 10:00:00');
INSERT INTO `answer` VALUES (5, 2, 1,1, '无', 1,0,0,0,0,0,0, '2020-12-18 10:00:00');
INSERT INTO `answer` VALUES (6, 2, 1,2, '管理员',0,0,0,0,0,0,0,'2020-12-18 10:00:00');
INSERT INTO `answer` VALUES (7, 2, 1,3, '无',0,0,1,1,0,0,0,'2020-12-18 10:00:00');
INSERT INTO `answer` VALUES (8, 2, 1,4, '我生日',0,0,0,0,0,0,0,'2020-12-18 10:00:00');
SET FOREIGN_KEY_CHECKS = 1;

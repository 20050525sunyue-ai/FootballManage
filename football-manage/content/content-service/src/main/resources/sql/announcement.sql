-- 公告表（MySQL 8+，与实体 com.sky.content.model.po.Announcement 对应）
CREATE TABLE IF NOT EXISTS `announcement` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` VARCHAR(256) NOT NULL COMMENT '公告标题',
  `content` TEXT NOT NULL COMMENT '公告正文',
  `publisher` VARCHAR(64) NOT NULL COMMENT '发布人',
  `publish_time` DATETIME NOT NULL COMMENT '发布时间',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_publish_time` (`publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告表';

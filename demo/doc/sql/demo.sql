-- ------ --
-- 待办事项 --
-- ------ --
DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo`
(
    `id`          bigint(64)    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(256)  NOT NULL DEFAULT '' COMMENT '名称',
    `status`      bigint(64)    NOT NULL DEFAULT '1' COMMENT '角色id',
    `remark`      varchar(256)  NOT NULL DEFAULT '' COMMENT '备注',
    `tenant_id`   bigint(64)    NOT NULL DEFAULT '2' COMMENT '租户ID',
    `create_by`   bigint(64)    NOT NULL DEFAULT '2' COMMENT '创建者',
    `create_date` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(64)    NOT NULL DEFAULT '2' COMMENT '更新者',
    `update_date` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `del_flag`    tinyint(4)    NOT NULL DEFAULT '0' COMMENT '删除标记0:保留,1:删除',
    `org_id`      bigint(64)    NOT NULL DEFAULT '-1' COMMENT '冗余字段，用于存储机构角色与用户的关系，非机构角色使用默认值-1',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='todo表';

-- todo菜单
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (146, '待办', 1, 1, '/todo/index.vue', 'index', '/todo', 0, 0, 1000, '', '', '_self', 1, 0, '{}', '', '', '', '', 2, 2, '2022-01-04 09:49:31', 2, '2022-01-05 16:33:53', 0, '', 'todo', 0, 0, 1, 3, '', '');
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (147, '待办查询：仅自己', 0, 3, '', '', '', 146, 0, 1000, '', '', '1', 0, 0, '', 'todo:page', 'create_by', 'eq', '#{currentUser.id}', 2, 2, '2022-01-05 13:58:49', 2, '2022-02-08 14:51:10', 0, '', '', 0, 0, 1, 3, ' ', '');
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (148, '更新待办：仅自己', 0, 3, '', '', '', 146, 0, 1000, '', '', '1', 0, 0, '', 'todo:update', 'create_by', 'eq', '#{currentUser.id}', 2, 2, '2022-01-05 17:38:12', 2, '2022-02-08 14:51:22', 0, '', '', 0, 0, 1, 3, ' ', '');
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (149, '删除待办：仅自己', 0, 3, '', '', '', 146, 0, 1000, '', '', '1', 0, 0, '', 'todo:delete', 'create_by', 'eq', '#{currentUser.id}', 2, 2, '2022-01-05 17:39:26', 2, '2022-02-08 14:51:33', 0, '', '', 0, 0, 1, 3, ' ', '');
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (150, '新增', 0, 2, '', '', '', 146, 0, 1000, 'todo:add', '', '1', 0, 0, '', '', '', '', '', 1, 1, '2022-01-05 17:43:33', 1, '2022-01-05 17:43:33', 0,'', '', 0, 0, 1, 3, ' ', '');
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (151, '更新', 0, 2, '', '', '', 146, 0, 1000, 'todo:update', '', '1', 0, 0, '', '', '', '', '', 2, 2, '2022-01-05 17:45:04', 2, '2022-01-05 17:45:04', 0, '', '', 0, 0, 1, 3, ' ', '');
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (152, '删除', 0, 2, '', '', '', 146, 0, 1000, 'todo:delete', '', '1', 0, 0, '', '', '', '', '', 2, 2, '2022-01-05 17:45:23', 2, '2022-01-05 17:45:23', 0, '', '', 0, 0, 1, 3, ' ', '');
INSERT INTO gc_menu (id, name, has_children, type, component, wrap_component, path, parent_id, disabled, order_num, permissions, icon, target, keep_alive, hide, params, rule_code, rule_column, rule_condition, rule_value, tenant_id, create_by, create_date, update_by, update_date, del_flag, iframe_url, code, home, top, tip_disabled, tip_from, tip_script, remark) VALUES (153, '仪表盘', 0, 1, '/dashboard/index.vue', 'index', '/dashboard', 0, 0, 9, '', '', '_self', 1, 0, '{}', '', '', '', '', 2, 2, '2022-01-07 11:09:18', 2, '2022-01-07 11:09:54', 0, '', 'dashboard', 1, 0, 1, 3, '', '');

-- todo角色
INSERT INTO gc_role (id, code, name, weight, type, order_num, remark, create_date, create_by, update_by, update_date, org_id, tenant_id, extend_obj, del_flag) VALUES (4, 'todoRole', '待办角色', 999, '2', 999, '', '2022-01-07 10:56:03', 2, 2, '2022-01-07 10:56:03', -1, 2, '', 0);

-- todo用户 默认密码为 R8if^T7hoX8sSVwP
INSERT INTO gc_user (id, pwd_expire_date, company, responsibility, address, job_number, username, password, real_name, id_card, nickname, avatar, gender, email, phone, qq, wx, status, weight, office_tel, last_pwd_update_date, last_login_date, last_login_ip, company_id, username_updated, org_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (3, '2023-02-06 10:55:39', '', '', '', '', 'todoUser', 'ee61d5f6a79b18d29392b61053ddd6c43942078576a604cdfbc146103b17bace', '', '', '', '', 0, '', '', '', '', 1, 0, '', '2022-01-07 10:55:37', '2022-01-07 10:57:31', '192.168.212.46|192.168.212.46', 0, 1, 1, 2, 2, '2022-01-07 10:55:39', 2, '2022-01-07 10:55:39', 0);
-- todo用户角色
INSERT INTO gc_user_role (id, user_id, role_id, tenant_id, create_by, create_date, update_by, update_date, del_flag, org_id) VALUES (5, 3, 4, 2, 2, '2022-01-07 10:56:29', 2, '2022-01-07 10:56:28', 0, -1);

-- todo角色菜单
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (628, 4, 146, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (629, 4, 147, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (630, 4, 148, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (631, 4, 149, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (632, 4, 150, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (633, 4, 151, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (634, 4, 152, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (635, 4, 153, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (636, 2, 146, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (637, 2, 147, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (638, 2, 148, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (639, 2, 149, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (640, 2, 150, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (641, 2, 151, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (642, 2, 152, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);
INSERT INTO gc_role_menu (id, role_id, menu_id, tenant_id, create_by, create_date, update_by, update_date, del_flag) VALUES (643, 2, 153, 2, 2, '2022-01-07 10:56:17', 2, '2022-01-07 10:56:16', 0);

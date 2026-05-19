-- Community Board Seed Data

-- Mock Users
INSERT INTO mock_users (id, nickname, avatar_url) VALUES
(1, '张医生', 'https://api.dicebear.com/7.x/avataaars/svg?seed=zhang'),
(2, '李患者', 'https://api.dicebear.com/7.x/avataaars/svg?seed=li'),
(3, '王术后恢复', 'https://api.dicebear.com/7.x/avataaars/svg?seed=wang'),
(4, '赵看病日记', 'https://api.dicebear.com/7.x/avataaars/svg?seed=zhao'),
(5, '孙陪诊员', 'https://api.dicebear.com/7.x/avataaars/svg?seed=sun'),
(6, '刘康复中', 'https://api.dicebear.com/7.x/avataaars/svg?seed=liu');

-- Sample Posts
INSERT INTO posts (id, user_id, title, content, like_count, favorite_count, comment_count, created_at) VALUES
(1, 2, '北京协和医院骨科就诊经历分享', '上个月因为膝盖疼去了协和医院骨科，挂号虽然难但医生很专业。先做了X光和核磁，医生耐心解释了半月板损伤的情况，建议保守治疗。整体体验非常好，推荐给大家。', 12, 5, 3, TIMESTAMP '2026-05-01 10:00:00'),
(2, 3, '心脏搭桥术后恢复日记', '术后第三周了，分享一下恢复经验。前两周主要是卧床休息，第三周开始在病房走廊慢走。饮食上要清淡，少盐少油。医生说恢复情况不错，再观察一周可以出院。', 25, 12, 5, TIMESTAMP '2026-05-05 14:30:00'),
(3, 4, '带孩子看儿科的注意事项', '总结了几次带娃看病的经验：1. 提前在网上预约挂号 2. 带好以往的病历和检查报告 3. 准备好要问医生的问题清单 4. 带些零食和玩具安抚孩子。希望对新手爸妈有帮助。', 18, 8, 4, TIMESTAMP '2026-05-10 09:15:00'),
(4, 5, '陪诊员眼中的好医院标准', '做了三年陪诊员，见过很多医院。我觉得好医院的标准是：导诊清晰、等待时间合理、医生愿意花时间沟通、检查结果出得快。北京几家三甲医院整体都不错。', 30, 15, 2, TIMESTAMP '2026-05-12 16:45:00'),
(5, 1, '甲状腺结节不要慌，分享我的诊疗过程', '体检发现甲状腺结节后很紧张，去了专科医院做了详细检查。医生说大部分结节是良性的，定期复查即可。分享这个经历希望能帮助有同样困扰的朋友。', 15, 6, 3, TIMESTAMP '2026-05-15 11:20:00');

-- Post-Hospital Tags (some posts linked to hospitals)
INSERT INTO post_hospital_tags (post_id, hospital_id) VALUES
(1, 1),  -- Post 1 linked to Peking Union (id=1)
(4, 1),  -- Post 4 linked to Peking Union
(4, 2);  -- Post 4 also linked to another hospital

-- Post-Specialty Tags (some posts linked to specialties)
INSERT INTO post_specialty_tags (post_id, specialty_id) VALUES
(1, 1),  -- Post 1 linked to first specialty
(2, 2),  -- Post 2 linked to second specialty
(3, 3),  -- Post 3 linked to third specialty
(5, 1);  -- Post 5 linked to first specialty

-- Sample Comments
INSERT INTO comments (id, post_id, user_id, parent_id, content, like_count, created_at) VALUES
(1, 1, 3, NULL, '协和确实不错，我也在那看过骨科，医生很负责。', 5, TIMESTAMP '2026-05-01 12:00:00'),
(2, 1, 4, NULL, '请问挂号是用的什么平台？', 2, TIMESTAMP '2026-05-01 14:00:00'),
(3, 1, 2, 2, '我用的是京医通小程序，提前一周放号。', 3, TIMESTAMP '2026-05-01 15:00:00'),
(4, 2, 6, NULL, '加油！术后恢复要有耐心，我去年也做过，现在恢复得很好。', 8, TIMESTAMP '2026-05-06 09:00:00'),
(5, 2, 4, NULL, '饮食方面可以多吃鱼和蔬菜，对恢复有帮助。', 4, TIMESTAMP '2026-05-06 10:30:00'),
(6, 2, 3, 4, '谢谢鼓励！现在每天都在进步。', 2, TIMESTAMP '2026-05-06 11:00:00'),
(7, 3, 5, NULL, '确实，提前准备问题清单很重要，不然到了诊室容易忘。', 6, TIMESTAMP '2026-05-10 11:00:00'),
(8, 3, 1, NULL, '作为医生也建议家长这样做，沟通效率会高很多。', 10, TIMESTAMP '2026-05-10 13:00:00'),
(9, 4, 2, NULL, '说得太对了，等待时间是最影响体验的。', 3, TIMESTAMP '2026-05-13 09:00:00'),
(10, 5, 6, NULL, '我也是甲状腺结节，定期复查两年了，一直没变化，放心了很多。', 4, TIMESTAMP '2026-05-15 14:00:00');

-- Sample Interactions
INSERT INTO user_interactions (user_id, target_type, target_id, type) VALUES
(3, 'POST', 1, 'LIKE'),
(4, 'POST', 1, 'LIKE'),
(5, 'POST', 2, 'LIKE'),
(6, 'POST', 2, 'FAVORITE'),
(1, 'COMMENT', 1, 'LIKE'),
(2, 'COMMENT', 4, 'LIKE');

-- Reset auto-increment sequences to avoid ID collisions
ALTER TABLE mock_users ALTER COLUMN id RESTART WITH 100;
ALTER TABLE posts ALTER COLUMN id RESTART WITH 100;
ALTER TABLE comments ALTER COLUMN id RESTART WITH 100;
ALTER TABLE post_hospital_tags ALTER COLUMN id RESTART WITH 100;
ALTER TABLE post_specialty_tags ALTER COLUMN id RESTART WITH 100;
ALTER TABLE user_interactions ALTER COLUMN id RESTART WITH 100;

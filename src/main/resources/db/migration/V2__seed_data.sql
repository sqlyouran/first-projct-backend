-- Seed data based on Fudan Hospital Rankings (复旦版医院排行榜) 2023
-- Mock data for MVP demonstration purposes

-- ==================== SPECIALTIES ====================
INSERT INTO specialties (id, name, name_cn, description, icon) VALUES
(1, 'Orthopedics', '骨科', 'Treatment of musculoskeletal system disorders including bones, joints, ligaments, tendons, and muscles.', '🦴'),
(2, 'Cardiology', '心内科', 'Diagnosis and treatment of heart and cardiovascular system diseases.', '❤️'),
(3, 'Oncology', '肿瘤科', 'Prevention, diagnosis, and treatment of cancer.', '🎗️'),
(4, 'Neurology', '神经内科', 'Treatment of disorders of the nervous system including brain and spinal cord.', '🧠'),
(5, 'Ophthalmology', '眼科', 'Medical and surgical treatment of eye disorders.', '👁️'),
(6, 'Gastroenterology', '消化内科', 'Treatment of digestive system disorders including stomach, intestines, and liver.', '🫁'),
(7, 'Respiratory Medicine', '呼吸内科', 'Diagnosis and treatment of lung and respiratory tract diseases.', '🫁'),
(8, 'Urology', '泌尿外科', 'Treatment of urinary tract and male reproductive system disorders.', '🏥'),
(9, 'Endocrinology', '内分泌科', 'Treatment of hormone-related disorders including diabetes and thyroid diseases.', '⚕️'),
(10, 'General Surgery', '普通外科', 'Surgical treatment of abdominal organs and other common surgical conditions.', '🔪'),
(11, 'Pediatrics', '儿科', 'Medical care for infants, children, and adolescents.', '👶'),
(12, 'Dermatology', '皮肤科', 'Treatment of skin, hair, and nail conditions.', '🧴');

-- ==================== HOSPITALS ====================
INSERT INTO hospitals (id, name, name_cn, city, province, address, phone, website, description, has_international, image_url) VALUES
(1, 'Peking Union Medical College Hospital', '北京协和医院', 'Beijing', 'Beijing', '1 Shuaifuyuan, Dongcheng District, Beijing', '+86-10-69156114', 'https://www.pumch.cn', 'One of China''s most prestigious hospitals, renowned for complex and rare disease diagnosis. Founded in 1921.', true, null),
(2, 'West China Hospital of Sichuan University', '四川大学华西医院', 'Chengdu', 'Sichuan', '37 Guoxue Lane, Wuhou District, Chengdu', '+86-28-85422114', 'https://www.wchscu.cn', 'One of the largest single-site hospitals in the world with over 4000 beds. Leading in multiple specialties.', true, null),
(3, 'Chinese PLA General Hospital (301 Hospital)', '中国人民解放军总医院', 'Beijing', 'Beijing', '28 Fuxing Road, Haidian District, Beijing', '+86-10-66887329', 'https://www.301hospital.com.cn', 'China''s top military hospital with comprehensive medical capabilities and VIP international services.', true, null),
(4, 'Ruijin Hospital, Shanghai Jiao Tong University', '上海交通大学医学院附属瑞金医院', 'Shanghai', 'Shanghai', '197 Ruijin Er Road, Huangpu District, Shanghai', '+86-21-64370045', 'https://www.rjh.com.cn', 'A leading teaching hospital in Shanghai, particularly renowned for hematology and endocrinology.', true, null),
(5, 'Zhongshan Hospital, Fudan University', '复旦大学附属中山医院', 'Shanghai', 'Shanghai', '180 Fenglin Road, Xuhui District, Shanghai', '+86-21-64041990', 'https://www.zs-hospital.sh.cn', 'Top-tier comprehensive hospital in Shanghai known for cardiology and liver surgery.', true, null),
(6, 'Peking University Third Hospital', '北京大学第三医院', 'Beijing', 'Beijing', '49 North Garden Road, Haidian District, Beijing', '+86-10-82266699', 'https://www.puh3.net.cn', 'Leading hospital for orthopedics and reproductive medicine in China.', true, null),
(7, 'Tongji Hospital, Huazhong University of Science and Technology', '华中科技大学同济医学院附属同济医院', 'Wuhan', 'Hubei', '1095 Jiefang Avenue, Qiaokou District, Wuhan', '+86-27-83662688', 'https://www.tjh.com.cn', 'Major comprehensive hospital in Central China with strong surgical departments.', true, null),
(8, 'Huashan Hospital, Fudan University', '复旦大学附属华山医院', 'Shanghai', 'Shanghai', '12 Wulumuqi Zhong Road, Jing''an District, Shanghai', '+86-21-52889999', 'https://www.huashan.org.cn', 'Renowned for neurology, neurosurgery, and dermatology. One of Shanghai''s oldest hospitals.', true, null),
(9, 'The First Affiliated Hospital of Zhejiang University', '浙江大学医学院附属第一医院', 'Hangzhou', 'Zhejiang', '79 Qingchun Road, Shangcheng District, Hangzhou', '+86-571-87236114', 'https://www.zy91.com', 'Top hospital in Zhejiang Province, known for liver transplantation and infectious diseases.', true, null),
(10, 'Nanfang Hospital, Southern Medical University', '南方医科大学南方医院', 'Guangzhou', 'Guangdong', '1838 Guangzhou Avenue North, Baiyun District, Guangzhou', '+86-20-61641888', 'https://www.nfyy.com', 'Leading hospital in South China with strengths in gastroenterology and nephrology.', false, null),
(11, 'Xijing Hospital, Fourth Military Medical University', '空军军医大学西京医院', 'Xi''an', 'Shaanxi', '127 Changle West Road, Xincheng District, Xi''an', '+86-29-84775507', 'https://www.xjhos.cn', 'Top military hospital in Northwest China, renowned for digestive surgery and plastic surgery.', false, null),
(12, 'The Second Affiliated Hospital of Zhejiang University', '浙江大学医学院附属第二医院', 'Hangzhou', 'Zhejiang', '88 Jiefang Road, Shangcheng District, Hangzhou', '+86-571-87783777', 'https://www.z2hospital.com', 'A leading hospital in East China with strong emergency medicine and ophthalmology.', true, null),
(13, 'Fuwai Hospital, Chinese Academy of Medical Sciences', '中国医学科学院阜外医院', 'Beijing', 'Beijing', '167 North Lishi Road, Xicheng District, Beijing', '+86-10-88396666', 'https://www.fuwai.com', 'China''s top cardiovascular specialty hospital, leading in heart surgery and interventional cardiology.', true, null),
(14, 'Zhongnan Hospital of Wuhan University', '武汉大学中南医院', 'Wuhan', 'Hubei', '169 Donghu Road, Wuchang District, Wuhan', '+86-27-67813167', 'https://www.znhospital.cn', 'Major teaching hospital in Wuhan with comprehensive departments.', false, null),
(15, 'Sun Yat-sen University Cancer Center', '中山大学肿瘤防治中心', 'Guangzhou', 'Guangdong', '651 Dongfeng East Road, Yuexiu District, Guangzhou', '+86-20-87343088', 'https://www.sysucc.org.cn', 'China''s leading cancer treatment and research center in South China.', true, null),
(16, 'Fudan University Shanghai Cancer Center', '复旦大学附属肿瘤医院', 'Shanghai', 'Shanghai', '270 Dongan Road, Xuhui District, Shanghai', '+86-21-64175590', 'https://www.shca.org.cn', 'Top cancer hospital in Eastern China with cutting-edge oncology research.', true, null),
(17, 'Peking University People''s Hospital', '北京大学人民医院', 'Beijing', 'Beijing', '11 Xizhimen South Street, Xicheng District, Beijing', '+86-10-88326666', 'https://www.pkuph.cn', 'Comprehensive hospital with strong hematology and orthopedics departments.', true, null),
(18, 'Guangdong Provincial People''s Hospital', '广东省人民医院', 'Guangzhou', 'Guangdong', '106 Zhongshan Er Road, Yuexiu District, Guangzhou', '+86-20-83827812', 'https://www.gdghospital.org.cn', 'Largest comprehensive hospital in Guangdong, strong in cardiology and emergency medicine.', true, null),
(19, 'Xiangya Hospital of Central South University', '中南大学湘雅医院', 'Changsha', 'Hunan', '87 Xiangya Road, Kaifu District, Changsha', '+86-731-84328888', 'https://www.xiangya.com.cn', 'Historic hospital (founded 1906) with excellent general surgery and neurology.', false, null),
(20, 'Zhongshan Ophthalmic Center, Sun Yat-sen University', '中山大学中山眼科中心', 'Guangzhou', 'Guangdong', '7 Jinsui Road, Tianhe District, Guangzhou', '+86-20-66618899', 'https://www.gzzoc.com', 'China''s top ophthalmology hospital and research center.', true, null),
(21, 'Beijing Tongren Hospital', '首都医科大学附属北京同仁医院', 'Beijing', 'Beijing', '1 Dongjiaominxiang, Dongcheng District, Beijing', '+86-10-58269911', 'https://www.trhos.com', 'National leader in ophthalmology and otolaryngology.', true, null),
(22, 'The First Affiliated Hospital of Sun Yat-sen University', '中山大学附属第一医院', 'Guangzhou', 'Guangdong', '58 Zhongshan Er Road, Yuexiu District, Guangzhou', '+86-20-87755766', 'https://www.fahsysu.org.cn', 'Top comprehensive hospital in South China with excellent liver and kidney transplant programs.', true, null),
(23, 'Renji Hospital, Shanghai Jiao Tong University', '上海交通大学医学院附属仁济医院', 'Shanghai', 'Shanghai', '160 Pujian Road, Pudong District, Shanghai', '+86-21-68383204', 'https://www.renji.com', 'Founded in 1844, Shanghai''s oldest hospital. Strong in gastroenterology and rheumatology.', true, null),
(24, 'Beijing Children''s Hospital', '首都医科大学附属北京儿童医院', 'Beijing', 'Beijing', '56 Nanlishi Road, Xicheng District, Beijing', '+86-10-59616161', 'https://www.bch.com.cn', 'China''s top pediatric hospital with comprehensive children''s healthcare services.', true, null),
(25, 'Children''s Hospital of Fudan University', '复旦大学附属儿科医院', 'Shanghai', 'Shanghai', '399 Wanyuan Road, Minhang District, Shanghai', '+86-21-64931990', 'https://www.ch.fudan.edu.cn', 'Leading pediatric hospital in East China.', false, null);

-- ==================== SPECIALTY RANKINGS (Fudan 2023 Mock) ====================

-- Orthopedics (骨科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(1, 6, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 3, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 1, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 2, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 7, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 17, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 19, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 11, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 22, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(1, 9, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Cardiology (心内科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(2, 13, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 5, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 1, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 18, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 2, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 4, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 3, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 7, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 19, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(2, 17, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Oncology (肿瘤科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(3, 15, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 16, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 1, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 2, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 7, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 22, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 3, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 9, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 19, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(3, 10, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Neurology (神经内科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(4, 1, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 8, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 3, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 2, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 19, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 7, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 4, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 22, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 9, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(4, 17, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Ophthalmology (眼科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(5, 20, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 21, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 8, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 12, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 1, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 2, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 9, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 22, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 3, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(5, 7, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Gastroenterology (消化内科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(6, 11, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 23, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 1, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 10, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 2, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 5, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 4, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 3, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 9, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(6, 7, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Respiratory Medicine (呼吸内科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(7, 2, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 1, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 3, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 5, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 7, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 19, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 4, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 22, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 9, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(7, 10, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Urology (泌尿外科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(8, 6, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 3, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 1, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 2, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 22, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 7, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 11, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 9, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 4, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(8, 19, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Endocrinology (内分泌科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(9, 4, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 1, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 2, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 3, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 19, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 5, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 22, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 7, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 17, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(9, 9, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- General Surgery (普通外科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(10, 1, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 2, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 5, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 3, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 19, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 7, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 4, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 22, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 9, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(10, 11, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Pediatrics (儿科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(11, 24, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 25, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 1, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 2, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 22, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 3, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 7, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 4, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 9, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(11, 19, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

-- Dermatology (皮肤科) Rankings
INSERT INTO specialty_rankings (specialty_id, hospital_id, rank_position, tier, ranking_year, source_name) VALUES
(12, 8, 1, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 1, 2, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 2, 3, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 3, 4, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 22, 5, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 11, 6, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 19, 7, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 9, 8, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 7, 9, 'Top 10', 2023, 'Fudan Hospital Rankings'),
(12, 4, 10, 'Top 10', 2023, 'Fudan Hospital Rankings');

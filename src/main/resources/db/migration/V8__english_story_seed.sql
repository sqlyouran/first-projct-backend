-- English Patient Story seed data (5 stories + 3 discussions)
-- These are community-shared experiences, not medical advice.

-- Patient Stories (type = STORY)
INSERT INTO posts (user_id, title, content, type, condition_name, treatment_type, cost_range, timeline_days, outcome, nationality, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'My ACL Reconstruction Journey at Peking University Third Hospital',
 'I tore my ACL playing basketball in Beijing. After researching options, I chose Peking University Third Hospital (北医三院) for their sports medicine reputation. The whole process from initial consultation to full recovery took about 4 months. The surgical team was incredibly professional, and the international department helped with all paperwork. Post-op rehab was thorough with weekly checkups. Total cost including surgery, 3-night stay, and follow-up was very reasonable compared to back home. Highly recommend for any sports injury.',
 'STORY', 'ACL Tear', 'Arthroscopic Surgery', '10K_25K', 120, 'EXCELLENT', 'American', 12, 3, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (user_id, title, content, type, condition_name, treatment_type, cost_range, timeline_days, outcome, nationality, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'Heart Valve Replacement at Fuwai Hospital - A Lifesaver',
 'At 55, I was diagnosed with severe aortic stenosis while living in Shanghai. My cardiologist recommended Fuwai Hospital in Beijing - China''s top cardiac center. The surgery was minimally invasive (TAVR procedure). I stayed 5 days post-op. The cardiac team was world-class, several doctors had trained abroad and spoke English well. Recovery took about 6 weeks before I could return to normal activities. The cost was a fraction of what it would have been in the UK.',
 'STORY', 'Aortic Stenosis', 'TAVR (Heart Valve)', '25K_50K', 42, 'EXCELLENT', 'British', 24, 8, 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (user_id, title, content, type, condition_name, treatment_type, cost_range, timeline_days, outcome, nationality, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'Thyroid Cancer Treatment at West China Hospital',
 'Diagnosed with papillary thyroid cancer during a routine checkup in Chengdu. West China Hospital (华西医院) has one of China''s best oncology departments. Had a total thyroidectomy followed by radioactive iodine therapy. The whole treatment from diagnosis to being cancer-free took about 3 months. Now on daily levothyroxine but otherwise back to normal life. The doctors were thorough in explaining everything, though I needed a translator for some consultations.',
 'STORY', 'Thyroid Cancer', 'Surgery + Radioiodine', '5K_10K', 90, 'GOOD', 'German', 18, 5, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (user_id, title, content, type, condition_name, treatment_type, cost_range, timeline_days, outcome, nationality, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'Retinal Detachment Emergency at Zhongshan Ophthalmic Center',
 'Woke up with sudden floaters and a dark shadow in my peripheral vision. Rushed to Zhongshan Ophthalmic Center in Guangzhou. They diagnosed retinal detachment and performed emergency vitrectomy surgery the same day. Amazing how fast they acted - saved my vision. Two weeks of face-down recovery was tough but worth it. Vision recovered to about 85% which the doctor said was a great outcome given the severity. The whole experience from ER to final checkup was under $3000.',
 'STORY', 'Retinal Detachment', 'Vitrectomy Surgery', 'UNDER_5K', 14, 'GOOD', 'Australian', 15, 4, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (user_id, title, content, type, condition_name, treatment_type, cost_range, timeline_days, outcome, nationality, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'Neurosurgery for Trigeminal Neuralgia at Xuanwu Hospital',
 'Suffered from trigeminal neuralgia for 2 years - the most excruciating pain imaginable. Medications stopped working. Chose Xuanwu Hospital in Beijing for microvascular decompression surgery. Dr. Chen''s team performed the 4-hour procedure perfectly. Pain was completely gone when I woke up. I cried with relief. Hospital stay was 7 days, full recovery in 6 weeks. Cost was about $15K all-in, which my international insurance covered partially. Changed my life.',
 'STORY', 'Trigeminal Neuralgia', 'Microvascular Decompression', '10K_25K', 49, 'EXCELLENT', 'Canadian', 31, 11, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- English Discussion posts (type = DISCUSSION)
INSERT INTO posts (user_id, title, content, type, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'Tips for navigating Chinese hospitals as a foreigner',
 'After 5 years in China and multiple hospital visits, here are my top tips: 1) Download the hospital''s WeChat mini-program for appointment booking, 2) Bring your passport every time, 3) International departments exist at major hospitals but cost 2-3x more, 4) Learn basic medical Chinese or bring a friend who can translate, 5) Mornings are less crowded, 6) Keep all receipts for insurance reimbursement, 7) Don''t be afraid to ask for a second opinion. What tips would you add?',
 'DISCUSSION', 22, 15, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (user_id, title, content, type, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'Health insurance options for expats in China 2024',
 'Exploring health insurance options as my company plan is changing. Currently looking at: Cigna Global, Bupa International, AXA, and local Chinese insurance (Ping An). For those working here long-term, what has your experience been? Particularly interested in: coverage for pre-existing conditions, direct billing at international departments, and whether local plans are worth considering if you speak some Chinese.',
 'DISCUSSION', 16, 9, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (user_id, title, content, type, like_count, comment_count, favorite_count, created_at, updated_at) VALUES
(1, 'Best hospitals with English-speaking staff in Shanghai?',
 'Just relocated to Shanghai for work. Can anyone recommend hospitals with reliable English-speaking staff? Not just international clinics (which are very expensive) but actual hospitals where you can see specialists. Interested in: general practice, dental, and dermatology. Budget is moderate - willing to pay more for English service but not international clinic prices.',
 'DISCUSSION', 11, 7, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

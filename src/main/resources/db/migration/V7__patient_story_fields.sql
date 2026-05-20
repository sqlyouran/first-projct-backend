-- Add structured Patient Story fields to posts table
ALTER TABLE posts ADD COLUMN type VARCHAR(20) DEFAULT 'DISCUSSION';
ALTER TABLE posts ADD COLUMN condition_name VARCHAR(255);
ALTER TABLE posts ADD COLUMN treatment_type VARCHAR(255);
ALTER TABLE posts ADD COLUMN cost_range VARCHAR(20);
ALTER TABLE posts ADD COLUMN timeline_days INTEGER;
ALTER TABLE posts ADD COLUMN outcome VARCHAR(20);
ALTER TABLE posts ADD COLUMN nationality VARCHAR(100);

-- Update existing posts to have explicit DISCUSSION type
UPDATE posts SET type = 'DISCUSSION' WHERE type IS NULL;

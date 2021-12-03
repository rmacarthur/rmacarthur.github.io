-- Reading activities for family --
SELECT u.username, b.title, ra.date_entered, ra.time_in_mins, ra.reading_type FROM reading_activities ra
        JOIN users u ON ra.reader_id = u.user_id
        JOIN books b ON ra.book_id = b.book_id
        WHERE ra.reader_id IN (SELECT user_id FROM users WHERE family_id = 3);
        
-- Reading Activities for User --
SELECT u.username, b.title, ra.date_entered, ra.time_in_mins, ra.reading_type FROM reading_activities ra
        JOIN users u ON ra.reader_id = u.user_id
        JOIN books b ON ra.book_id = b.book_id
        WHERE ra.reader_id = 4;
        
-- Total reading minutes for reader --
SELECT SUM(time_in_mins) FROM reading_activities WHERE reader_id = 3;
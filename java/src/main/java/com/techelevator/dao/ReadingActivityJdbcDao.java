package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.ReadingActivity;
import com.techelevator.model.ReadingActivityAdd;
import com.techelevator.model.ReadingActivityDetailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadingActivityJdbcDao implements ReadingActivityDao {

    private JdbcTemplate template;

    @Autowired
    BookDao bookDao;

    public ReadingActivityJdbcDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ReadingActivity> getAllReadingActivities() {
        List<ReadingActivity> activityList = new ArrayList<>();
        String sql = "SELECT * FROM reading_activities";

        SqlRowSet result = template.queryForRowSet(sql);
        while (result.next()) {
            activityList.add(mapResultToReadingActivity(result));
        }

        return activityList;
    }

    @Override
    public ReadingActivityDetailed getActivityByActivityId(long activityId) {
        ReadingActivityDetailed activity = null;
        String sql = "SELECT * FROM reading_activities WHERE reading_activity_id = ?";

        SqlRowSet result = template.queryForRowSet(sql, activityId);
        if (result.next()) {
            activity = mapResultToDetailedReadingActivity(result);
        }

        return activity;
    }

    @Override
    public List<ReadingActivityDetailed> getActivitiesByReaderId(long readerId) {
        List<ReadingActivityDetailed> activityList = new ArrayList<>();
        String sql = "SELECT u.username, b.title, ra.date_entered, ra.time_in_mins, ra.reading_type, " +
                "ra.book_is_completed, ra.notes " +
                "FROM reading_activities ra " +
                "JOIN users u ON ra.reader_id = u.user_id " +
                "JOIN books b ON ra.book_id = b.book_id " +
                "WHERE ra.reader_id = ?";

        SqlRowSet result = template.queryForRowSet(sql, readerId);
        while (result.next()) {
            activityList.add(mapResultToDetailedReadingActivity(result));
        }

        return activityList;
    }

    @Override
    public List<ReadingActivityDetailed> getActivitiesByFamilyId(long familyId) {
        List<ReadingActivityDetailed> activityList = new ArrayList<>();
        String sql = "SELECT u.username, b.title, ra.date_entered, ra.time_in_mins, ra.reading_type, " +
                "ra.book_is_completed, ra.notes " +
                "FROM reading_activities ra " +
                "JOIN users u ON ra.reader_id = u.user_id " +
                "JOIN books b ON ra.book_id = b.book_id " +
                "WHERE ra.reader_id IN (SELECT user_id FROM users WHERE family_id = ?)";

        SqlRowSet result = template.queryForRowSet(sql, familyId);
        while (result.next()) {
            activityList.add(mapResultToDetailedReadingActivity(result));  //map result to family reading activity
        }

        return activityList;
    }

    @Override
    public int getTotalReadingMinsByReaderId(long readerId) {
        String sql = "SELECT SUM(time_in_mins) FROM reading_activities " +
                "WHERE reader_id = ?";

        SqlRowSet result = template.queryForRowSet(sql, readerId);
        if (result.next()) {

            return result.getInt("sum");
        }
        return 0;
    }

    @Override
    public void addActivity(ReadingActivityAdd activityToAdd) {

        Book book = bookDao.getBookByTitle(activityToAdd.getBookTitle());
        int bookId = -1;

        if (book == null) {
            String sqlNoBook = "INSERT INTO books (title, author) " +
                    "VALUES (?, ?)";
            template.update(sqlNoBook, activityToAdd.getBookTitle(), activityToAdd.getAuthor());
            book = bookDao.getBookByTitle(activityToAdd.getBookTitle());
        }

        String sqlBookId = "SELECT book_id FROM books WHERE title = ? AND author = ?";
        SqlRowSet result = template.queryForRowSet(sqlBookId, activityToAdd.getBookTitle(), activityToAdd.getAuthor());
        System.out.println(result);


        if(result.next()) {
            bookId = result.getInt("book_id");
        }

        String sql = "INSERT INTO reading_activities (book_id, " +
            "reader_id, time_in_mins, reading_type, book_is_completed, notes) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        template.update(sql, bookId, activityToAdd.getReaderId(),
                activityToAdd.getReaderId(), activityToAdd.getTimeInMinutes(),
                activityToAdd.getReadingType(), activityToAdd.getNotes());
    }

    @Override
    public void updateActivity(ReadingActivity activityToUpdate, long activityId) {
        String sql = "UPDATE reading_activities SET book_id = ?, time_in_mins = ?, " +
                "reading_type = ?, book_is_completed = ?, notes = ?";

        template.update(sql, activityToUpdate.getBookId(), activityToUpdate.getTimeInMinutes(),
                activityToUpdate.getReadingType(), activityToUpdate.isCompleted(), activityToUpdate.getNotes());
    }

    @Override
    public void deleteActivity(long activityId) {
        String sql = "DELETE FROM reading_activities WHERE id = ?";

        template.update(sql, activityId);
    }

    private ReadingActivity mapResultToReadingActivity(SqlRowSet result){

        long activityId = result.getLong("reading_activity_id");
        long bookId = result.getLong("book_id");
        long readerId = result.getLong("reader_id");
        Date date = result.getDate("date_entered");
        int time = result.getInt("time_in_mins");
        String readingType = result.getString("reading_type");
        boolean isCompleted = result.getBoolean("book_is_completed");
        String notes = result.getString("notes");

        ReadingActivity activity = new ReadingActivity(activityId, bookId, readerId, date, time, readingType,
                isCompleted, notes);

        return activity;
    }

    private ReadingActivityDetailed mapResultToDetailedReadingActivity(SqlRowSet result){

        String readerName = result.getString("username");
        String bookTitle = result.getString("title");
        Date date = result.getDate("date_entered");
        int time = result.getInt("time_in_mins");
        String readingType = result.getString("reading_type");
        boolean isCompleted = result.getBoolean("book_is_completed");
        String notes = result.getString("notes");

        ReadingActivityDetailed activity = new ReadingActivityDetailed(readerName, bookTitle, date,
                time, readingType, isCompleted, notes);

        return activity;
    }

}

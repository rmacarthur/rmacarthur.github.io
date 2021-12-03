package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class FamilyAccountJdbcDao extends User implements FamilyAccountDao{

    private JdbcTemplate template;


    public FamilyAccountJdbcDao(DataSource dataSource){template = new JdbcTemplate(dataSource);}

    @Override
    public void addFamilyAccount(FamilyAccount familyToAdd){
        String sql = "INSERT INTO families (family_name) values (?)";
        template.update(sql, familyToAdd.getFamilyName());
    }

    @Override
    public void removeFamilyAccount(Long familyId){
        String sql = "DELETE FROM families WHERE family_id = ?";
        template.update(sql, familyId);
    }

    @Override
    public List<FamilyAccountDetailed> getFamilyList(Long familyId) {
        List<FamilyAccountDetailed> familyList = new ArrayList<>();
        String sql = "SELECT users.username, users.user_id, families.family_id FROM users " +
                "JOIN families on users.family_id = families.family_id " +
                "WHERE users.family_id = ?";
        SqlRowSet result = template.queryForRowSet(sql, familyId);
            while(result.next()) {
                familyList.add(mapResultToFamilyAccountDetailed((result)));
            }
        return familyList;
        }


    @Override
    public Long getFamilyId(Long userId) {
        String sql = "Select family_id from users where user_id = ?";
        SqlRowSet result = template.queryForRowSet(sql, userId);

        Long id = -2L;
        if(result.next()) {
            id = result.getLong("family_id");
        }

        return id;
    }

    @Override
    public List<FamilyAccountDetailed> findAllUsers() {
        List<FamilyAccountDetailed> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet result = template.queryForRowSet(sql);
        while(result.next()) {
            users.add(mapResultToFamilyAccountDetailed(result));
        }

        return users;
    }

    public void addFamilyMember(Long familyId, Long userId) {
        String sql = "UPDATE users SET family_id = ? WHERE user_id = ?";
        template.update(sql, familyId, userId);


    }

    private FamilyAccount mapResultToFamilyAccount(SqlRowSet result){
        String familyName = result.getString("family_name");
        Long familyId = result.getLong("family_id");
        return new FamilyAccount(familyName, familyId);
    }

    private FamilyAccountDetailed mapResultToFamilyAccountDetailed(SqlRowSet result){
        String username = result.getString("username");
        Long familyId = result.getLong("family_id");
        Long userId = result.getLong("user_id");

        return new FamilyAccountDetailed(username, familyId, userId);
    }

}

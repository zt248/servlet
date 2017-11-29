package dao.impl;

import dao.AddressDao;
import entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository(value = "addressImplDao")
public class AddressImplDao implements AddressDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void add(Address address) {
        String sql = "INSERT INTO ADDRESS (COUNTRY, CITY, STREET, POST_CODE) VALUES (?,?,?,?);";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,address.getCountry());
                preparedStatement.setString(2,address.getCity());
                preparedStatement.setString(3,address.getStreet());
                preparedStatement.setString(4,address.getPostCode());
                return preparedStatement;
            }
        };
        jdbcTemplate.update(preparedStatementCreator,generatedKeyHolder);
        long id = generatedKeyHolder.getKey().longValue();
        address.setId(id);
//        String sql = "INSERT INTO ADDRESS (ID,COUNTRY, CITY, STREET, POST_CODE) VALUES (?,?,?,?,?);";
//        jdbcTemplate.update(sql, address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getPostCode());
    }

    @Override
    public List<Address> getAll() {
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS";
        List<Address> addressList = jdbcTemplate.query(sql, new AddressMapper());
        return addressList;
    }

    @Override
    public Address getById(Long id) {
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID = ?";
        jdbcTemplate.queryForList(sql,new Object[]{id});
        Address address = jdbcTemplate.queryForObject(sql, new Object[]{id}, new AddressMapper());
        return address;
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";
        jdbcTemplate.update(sql, address.getCountry(), address.getCity(), address.getStreet(), address.getPostCode(), address.getId());
    }

    @Override
    public void remove(Address address) {
        String sql = "DELETE FROM ADDRESS WHERE ID=?";
        jdbcTemplate.update(sql, address.getId());
    }
}

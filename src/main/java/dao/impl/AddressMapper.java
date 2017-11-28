package dao.impl;

import entity.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper<Address> {



    @Override
    public Address mapRow(ResultSet resultSet, int i) throws SQLException {

        Address address = new Address();
        address.setId(resultSet.getLong("id"));
        address.setCountry(resultSet.getString("country"));
        address.setCity(resultSet.getString("city"));
        address.setStreet(resultSet.getString("street"));
        address.setPostCode(resultSet.getString("post_Code"));
        return address;
    }
}

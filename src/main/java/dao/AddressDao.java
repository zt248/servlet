package dao;

import entity.Address;

import javax.sql.DataSource;
import java.util.List;

public interface AddressDao {

    public void setDataSource(DataSource dataSource);

    //create
    void add(Address address) throws DaoException;

    //read
    List<Address> getAll() throws DaoException;

    Address getById(Long id) throws DaoException;

    //update
    void update(Address address) throws DaoException;

    //delete

    void remove(Address address) throws DaoException;

}

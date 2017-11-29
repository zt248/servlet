import dao.impl.AddressImplDao;
import entity.Address;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Domain {
    public static void main(String[] args)  {
//        Utill utill = new Utill();
//        utill.getConnection();

        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");

        AddressImplDao addressImplDao = (AddressImplDao) context.getBean("addressImplDao");

//        AddressImplDao addressImplDao = new AddressImplDao();

        Address address = new Address();
//        address.setId(1L);
        address.setCountry("DCTest");
        address.setCity("asdGotham City");
        address.setStreet("3fsdfArkham street");
        address.setPostCode("12211");


//        try {
            addressImplDao.add(address);
//            addressImplDao.update(new Address(2L,"new Addres","new Cuty","new Street","new PostCode"));
          //  addressImplDao.remove(address);
           // System.out.println(addressImplDao.getById(1L));


            List<Address> addressList = addressImplDao.getAll();
            for (Address a : addressList) {
                System.out.println(a);
            }
//            addressImplDao.close();

//        } catch (Exception e) {
//            throw new DaoException();
//        }


    }
}

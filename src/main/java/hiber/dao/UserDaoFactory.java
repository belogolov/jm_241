package hiber.dao;


public class UserDaoFactory {

    public UserDao createFactory() {
        return new UserDaoImp();
    }

}

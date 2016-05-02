package edu.umsl.final1.dao;

import edu.umsl.final1.domain.UserLoginInfo;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by genebrowder on 5/1/16.
 */
public class UserLoginInfoDao {

    private static SessionFactory factory;

    public static boolean isValiduser(UserLoginInfo UserLoginInfo){
        List<UserLoginInfo> alluserLoginInfoList =   getAllUserLoginInfo();

        for(UserLoginInfo userLogininfo: alluserLoginInfoList)   {

            if ((UserLoginInfo.getUserName().equals(userLogininfo.getUserName()))  &&
                    (UserLoginInfo.getPassword().equals(userLogininfo.getPassword()))  ){

                return true;
            }
        }

        return false;

    }
    private static List getAllUserLoginInfo(){

        Session session = getSession();


        Transaction tx = null;

        List allUserLoginInfoList = new ArrayList();
        try{
            tx = session.beginTransaction();

            Query queryResult = session.createQuery("from UserLoginInfo");


            allUserLoginInfoList = queryResult.list();
            for (int i = 0; i < allUserLoginInfoList.size(); i++) {
                UserLoginInfo userLoginInfo = (UserLoginInfo) allUserLoginInfoList.get(i);
                System.out.println("userLoginInfo["+userLoginInfo.getId()+"] userName ="+userLoginInfo.getUserName());
                //System.out.println("userLoginInfo["+userLoginInfo.getId()+"] password ="+userLoginInfo.getPassword());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return allUserLoginInfoList;
    }

    public static long saveUserLoginInfo(UserLoginInfo UserLoginInfo){

        Session session = getSession();
        Transaction tx = null;
        long userLoginInfoID = 0;
        try{
            tx = session.beginTransaction();

            //This allows a user and a password to be added only once
            if(!isValiduser(UserLoginInfo)) {
                userLoginInfoID = (long) session.save(UserLoginInfo);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return userLoginInfoID;
    }





    private static Session getSession() {
        //Hibernate Code  ----------------------------------------------------------

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());

        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        //Hibernate Code  ----------------------------------------------------------

        factory= new Configuration().configure().buildSessionFactory(serviceRegistry);

        //Hibernate Code  ----------------------------------------------------------

        return factory.openSession();
    }
}

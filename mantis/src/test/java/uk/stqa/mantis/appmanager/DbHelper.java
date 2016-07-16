package uk.stqa.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import uk.stqa.mantis.model.Users;
import uk.stqa.mantis.model.UserData;

/**
 * Created by natla on 05/07/2016.
 */
public class DbHelper {
    private final ApplicationManager app;
    private final SessionFactory sessionFactory;

    public DbHelper(ApplicationManager app){
        this.app = app;

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Users users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}

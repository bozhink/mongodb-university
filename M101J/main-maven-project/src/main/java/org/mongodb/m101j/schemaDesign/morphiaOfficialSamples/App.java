package org.mongodb.m101j.schemaDesign.morphiaOfficialSamples;

import com.mongodb.MongoClient;
import org.mongodb.m101j.schemaDesign.morphiaOfficialSamples.models.Employee;
import org.mongodb.m101j.utils.Helpers;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by Bozhin Karaivanov on 10-Jun-16.
 */
public class App {
    public static void main(String[] args) {
        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("org.mongodb.m101j.schemaDesign.morphiaOfficialSamples.models");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
        datastore.ensureIndexes();

        // saving data
        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        datastore.save(elmer);

        final Employee daffy = new Employee("Daffy Duck", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepé Le Pew", 25000.0);
        datastore.save(pepe);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);

        datastore.save(elmer);

        final Query<Employee> query = datastore.createQuery(Employee.class);
        final List<Employee> employeeList  = query.asList();

        for (Employee employee: employeeList) {
            System.out.println(employee);
        }
    }
}

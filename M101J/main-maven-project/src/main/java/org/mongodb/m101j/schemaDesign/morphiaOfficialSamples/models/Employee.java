package org.mongodb.m101j.schemaDesign.morphiaOfficialSamples.models;

import javafx.beans.property.ReadOnlyListPropertyBase;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bozhin Karaivanov on 10-Jun-16.
 */
@Entity("employees")
@Indexes(
        @Index(value = "salary", fields = @Field("salary"))
)
public class Employee {
    public Employee() {
        this.setDirectReports(new ArrayList<Employee>());
    }

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
        this.setDirectReports(new ArrayList<Employee>());
    }

    @Id
    private ObjectId id;

    private String name;

    @Reference
    private Employee manager;

    public List<Employee> getDirectReports() {
        return directReports;
    }

    private void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    @Reference
    private List<Employee> directReports;

    @Property("wage")
    private Double salary;


}

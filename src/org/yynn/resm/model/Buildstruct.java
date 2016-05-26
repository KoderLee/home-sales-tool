package org.yynn.resm.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Buildstruct generated by MyEclipse - Hibernate Tools
 */

public class Buildstruct extends org.yynn.resm.model.BaseModel implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String description;
     private Set buildingprojects = new HashSet(0);


    // Constructors

    /** default constructor */
    public Buildstruct() {
    }

	/** minimal constructor */
    public Buildstruct(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Buildstruct(String name, String description, Set buildingprojects) {
        this.name = name;
        this.description = description;
        this.buildingprojects = buildingprojects;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Set getBuildingprojects() {
        return this.buildingprojects;
    }
    
    public void setBuildingprojects(Set buildingprojects) {
        this.buildingprojects = buildingprojects;
    }
   








}
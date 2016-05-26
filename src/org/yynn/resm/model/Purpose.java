package org.yynn.resm.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Purpose generated by MyEclipse - Hibernate Tools
 */

public class Purpose extends org.yynn.resm.model.BaseModel implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String description;
     private Set callcustomers = new HashSet(0);
     private Set visitcustomers = new HashSet(0);


    // Constructors

    /** default constructor */
    public Purpose() {
    }

	/** minimal constructor */
    public Purpose(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Purpose(String name, String description, Set callcustomers, Set visitcustomers) {
        this.name = name;
        this.description = description;
        this.callcustomers = callcustomers;
        this.visitcustomers = visitcustomers;
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

    public Set getCallcustomers() {
        return this.callcustomers;
    }
    
    public void setCallcustomers(Set callcustomers) {
        this.callcustomers = callcustomers;
    }

    public Set getVisitcustomers() {
        return this.visitcustomers;
    }
    
    public void setVisitcustomers(Set visitcustomers) {
        this.visitcustomers = visitcustomers;
    }
   








}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;

/**
 *
 * @author monge55
 */
public class Student extends Person implements Serializable {
    //private String name;
    private String career;
    //private String lastName;
    private int year;
    private String carnet;

    public Student() {
        super();
        this.career = "";
        this.year = 0;
        this.carnet = "";
    }

   

    public Student(String name, String lastName, String career, int year, String carnet) {
       super(name,lastName);
        this.career = career;
        this.year = year;
        this.carnet = carnet;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    @Override
    public String toString() {
        return super.toString() + "career=" + career + ", year=" + year + ", carnet=" + carnet + '.';
    }
    
    

    
     public int sizeInBytes() {
        //long necesita dos bytes
        //String 2 bytes
        return this.getName().length() * 2
                + this.getCareer().length() *2+ this.getLastName().length() * 2  +8+ this.getCarnet().length() * 2 ;
    }
    
    
    
}

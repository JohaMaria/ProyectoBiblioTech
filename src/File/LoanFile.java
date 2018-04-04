/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Domain.Audiovisual;
import Domain.Loan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo
 */
public class LoanFile {

    private String path;//para darle nombre y extension al archivo

    public LoanFile() {
        this.path = "loanFile.dat";
    }//constructor

    public void saveLoan(Loan loan) throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);
        List<Loan> loanList = new ArrayList<Loan>();

        if (myFile.exists()) {
            ObjectInputStream objectinputStream = new ObjectInputStream(new FileInputStream(myFile));
            Object aux = objectinputStream.readObject();//readObject(); jala todo lo que este en el archivo
            loanList = (List<Loan>) aux;//castin
            objectinputStream.close();
        }// if(myFile.exists())

        loanList.add(loan);
        ObjectOutputStream outPut = new ObjectOutputStream(new FileOutputStream(myFile));
        outPut.writeUnshared(loanList);
        outPut.close();
    }//guardarPersona

    public Loan getByCode(String carnet) throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);

        List<Loan> loanList = new ArrayList<Loan>();
        if (myFile.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(myFile));
            Object aux = objectInputStream.readObject();
            loanList = (List<Loan>) aux;
            objectInputStream.close();
        }//if

        Loan loan = null;

        for (int i = 0; i < loanList.size(); i++) {
            if (loanList.get(i).getStudent().getCarnet().equals(carnet)){
            loan = loanList.get(i);
            break;//romper la ejecucion y seguir con lo que está abajo

        }//if
        }

        // }//for i
        return loan;
    }//obtener persona

    public boolean getByCodeBoolean(int code) throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);

        List<Loan> loanList = new ArrayList<Loan>();
        if (myFile.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(myFile));
            Object aux = objectInputStream.readObject();
            loanList = (List<Loan>) aux;
            objectInputStream.close();
        }//if(myFile.exists())

//        Audiovisual audiovisual = null;
//        for (int i = 0; i < loanList.size(); i++) {
//            if (loanList.get(i).getCode() == code) {
//                loan = loanList.get(i);
//                return true;//rompe el for, aquí se pone porque sino retornaría null, porq asi fue inicializado antes
//            }//if(personaList.get(i).getCedula().equals(cedula))
//        }//for i
        return false;
    }//obtenerLibro

    public List<Loan> arrays() throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);

        List<Loan> loanList = new ArrayList<Loan>();
        if (myFile.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(myFile));
            Object aux = objectInputStream.readObject();
            loanList = (List<Loan>) aux;

            objectInputStream.close();

        }//if(myFile.exists())

        return loanList;
    }//obtenerPersona
     public Loan verification(String carnets) throws IOException, ClassNotFoundException {
        File myFile = new File(this.path);

        ArrayList<Loan> loanList = new ArrayList<>();
        if (myFile.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(myFile));
            Object aux = objectInputStream.readObject();
            loanList = (ArrayList<Loan>) aux;
            objectInputStream.close();
        }//if(myFile.exists())

        Loan loan= new Loan();
        for (int i = 0; i < loanList.size(); i++) {
            if (loanList.get(i).getStudent().getCarnet().equals(carnets)) {
                
               
                loan= loanList.remove(i);
               // books.setCount(count);

                break;//rompe el for, aquí se pone porque sino retornaría null, porq asi fue inicializado antes
            }//if(personaList.get(i).getCedula().equals(cedula))
        }//for i
        ObjectOutputStream outPut = new ObjectOutputStream(new FileOutputStream(myFile));
        outPut.writeUnshared(loanList);
        outPut.close();
        return loan;
    }//obtenerLibro
}
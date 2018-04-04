/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Domain.Student;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class StudentFile {

    public RandomAccessFile random;
    private int regsQuantity;// cantidad de resgistros en el archivo
    private int regSize;//tama;o del registro
    private String myFilePath;//ruta

    public StudentFile(File file) throws IOException {

        myFilePath = file.getPath();

        this.regSize = 140;

        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + "is an invalid file");
        } else {
            random = new RandomAccessFile(file, "rw");
            // necesitamos indicar cuantos registros hay
            this.regsQuantity = (int) Math.ceil((double) random.length() / (double) regSize);
        }
    }//muy importante cerrar nuestros archivos

    public void close() throws IOException {
        random.close();
    }//indicar la cantidad de registro de nuestros archivos

    public int fileSize() {
        return this.regsQuantity;
    }
    //insertar un nuevo registro en una posición específica

    public boolean putValues(int position, Student student) throws IOException {
        //primero verificar que sea valida la inserción

        if (position < 0 && position > this.regsQuantity) {
            System.err.println("1001- Record position is out of bounds");
            return false;
        } else {
            if (student.sizeInBytes() > this.regSize) {
                System.err.println("1002- Record size is out of bounds");
                return false;
            } else {
                //bingo
                random.seek(position * this.regSize);
                random.writeUTF(student.getName());
                random.writeUTF(student.getLastName());
                random.writeUTF(student.getCareer());
                random.writeInt(student.getYear());
                random.writeUTF(student.getCarnet());

                return true;
            }
        }
    }// end method
    // insertar al final del archivo

    public boolean addEndRecord(Student student) throws IOException {
        boolean success = putValues(this.regsQuantity, student);
        if (success) {
            ++this.regsQuantity;
        }//if
        return success;
    }//addEndRecord

    //obtener un estudiante
    public Student getStudent(int position) throws IOException {
        //validar
        int i = 0;
        Student studentTemp = new Student();
        for (i = 0; i < this.regsQuantity; i++) {
            // colocamos el brazo en el brazo en lugar
            for (i = 0; i < this.regsQuantity; i++) {
                random.seek(position * this.regSize);
            }
            //hacemos la lectura

            studentTemp.setName(random.readUTF());
            studentTemp.setLastName(random.readUTF());
            studentTemp.setCareer(random.readUTF());
            studentTemp.setYear(random.readInt());
            studentTemp.setCarnet(random.readUTF());

        }//for

        return studentTemp;
    }//getVehicle

    public int createID(String career, int lastNumber) throws IOException {
        Student myStudent;

        int count = 1;
        //buscar
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myStudent = this.getStudent(i);
            //preguntar si es el estudiante que deseo eliminar
            if (myStudent.getCareer().equals(career)) {
                count++;

//                myStudent.setCarnet("deleted");
            }//if

        }//for
        lastNumber += count;
        System.out.print(lastNumber);
        return lastNumber;
    }//delete

    //retornar una lista de estudiante
    public ArrayList<Student> getAllStudent() throws IOException {
        ArrayList<Student> studentArray = new ArrayList<Student>();
        for (int i = 0; i < this.regsQuantity; i++) {
            Student sTemp = this.getStudent(i);

            if (sTemp != null) {
                if (!sTemp.getName().equalsIgnoreCase("deleted")) {
                    studentArray.add(sTemp);
                }//if interno
            }//if externo
        }//end for
        return studentArray;

    }//getAllCar

    public boolean devolver(Student student) throws IOException {
        return this.putValues(this.regsQuantity, student);
    }//devolver

    //método verifica que la serie se encuentre en el archivo para poder actualizar
    public boolean verification(String carnet) throws IOException {
        Student myStudent;
        //buscar
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myStudent = this.getStudent(i);
            //preguntar si es el estudiante que deseo eliminar
            if (myStudent.getCarnet().equals(carnet)) {
                // marcar como eliminado
                System.out.print("Bienvenido");
                return true;
            }//if

        }//for
        System.err.print("carnet no encontrado");
        return false;

    }//update
    public Student buscar(String carnet) throws IOException{
        Student myStudent;
        //buscar
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myStudent = this.getStudent(i);
            //preguntar si es el estudiante que deseo eliminar
            if (myStudent.getCarnet().equals(carnet)) {
                // marcar como eliminado
                
                return myStudent;
            }//if

        }//for
        System.err.print("carnet no encontrado");
        return null;
       
    }
}
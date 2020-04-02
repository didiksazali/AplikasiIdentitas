package com.kuliah.pm2aplikasiidentitas;

public class Identitas {
	// variables
    int _noktp;
    String _nama;
    String _gender;
    String _status;
    String _tanggallahir;

    // Konstruktor
    public Identitas(){
         
    }
    // constructor
    public Identitas(int noktp, String nama, String gender, String status, String tanggallahir){
        this._noktp= noktp;
        this._nama = nama;
        this._gender = gender;
        this._status = status;
        this._tanggallahir = tanggallahir;
    }
     
    // constructor
    public Identitas(String nama, String gender, String status, String tanggallahir){
        this._nama = nama;
        this._gender = gender;
        this._status = status;
        this._tanggallahir = tanggallahir;
    }
    // getting noktp
    public int getNOKTP(){
        return this._noktp;
    }
     
    // setting noktp
    public void setNOKTP(int noktp){
        this._noktp = noktp;
    }
     
    // getting nama
    public String getNama(){
        return this._nama;
    }
     
    // setting nama
    public void setNama(String nama){
        this._nama = nama;
    }
     
    // getting gender
    public String getGender(){
        return this._gender;
    }
     
    // setting gender
    public void setGender(String gender){
        this._gender = gender;
    }
    // getting status
    public String getStatus(){

    	return this._status;
    }
     
    // setting status
    public void setStatus(String status){
        this._status = status;
    }
    
 // getting tanggallahir
    public String getTanggallahir(){

    	return this._tanggallahir;
    }
     
    // setting tanggallahir
    public void setTanggallahir(String tanggallahir){
        this._tanggallahir = tanggallahir;
    }
    
    
}

package com.kuliah.pm2aplikasiidentitas;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

	public class OperasiDatabase extends SQLiteOpenHelper {

		// Versi Database
		private static final int DATABASE_VERSION = 1;

		// Nama Database
		private static final String DATABASE_NAME = "identitas";

		// Nama table Identitas
		private static final String TABLE_DATA = "data";

		// Nama atribut
		private static final String KEY_NOKTP = "noktp";
		private static final String KEY_NAMA = "nama";
		private static final String KEY_GENDER = "gender";
		private static final String KEY_STATUS = "status";
		private static final String KEY_TANGGALLAHIR = "tanggallahir";

		public OperasiDatabase(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// Buat Table
		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_data_TABLE = "CREATE TABLE " + TABLE_DATA + "("
				+ KEY_NOKTP + " TEXT PRIMARY KEY," + KEY_NAMA + " TEXT,"
			+ KEY_GENDER + " TEXT, " + KEY_STATUS + " TEXT," + KEY_TANGGALLAHIR
				+ " TEXT)";
			db.execSQL(CREATE_data_TABLE);
		}

		// Upgrade database
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Hapus table lama yang sudah ada
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

			// Buat table
			onCreate(db);
		}

		// Tambahkan data baru
		void addData(Identitas ktp) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_NOKTP, ktp.getNOKTP());
			values.put(KEY_NAMA, ktp.getNama());
			values.put(KEY_GENDER, ktp.getGender());
			values.put(KEY_STATUS, ktp.getStatus());
			values.put(KEY_TANGGALLAHIR, ktp.getTanggallahir());

			// Insert Record
			db.insert(TABLE_DATA, null, values);
			db.close(); // Tutup koneksi databsae
		}

		// Mengambil satu data
		Identitas getIdentitas(String noktp) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_DATA, new String[] { KEY_NOKTP,
					KEY_NAMA, KEY_GENDER, KEY_STATUS, KEY_TANGGALLAHIR }, KEY_NOKTP + "=?",
					new String[] { String.valueOf(noktp) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			Identitas hasil = new Identitas(Integer.parseInt(cursor.getString(0)),
			cursor.getString(1),cursor.getString(2),cursor.getString(3),
			cursor.getString(4));

			// return hasil
			return hasil;
		}

		// Ambil seluruh data
		public List<Identitas> getAllIdentitas() {
			List<Identitas> listIdentitas = new ArrayList<Identitas>();
			// Query untuk seluruh rekaman
			String selectQuery = "SELECT  * FROM " + TABLE_DATA;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// Iterasi untuk memasukkan ke dalam contactlist
			if (cursor.moveToFirst()) {
				do {
					Identitas contact = new Identitas();
					contact.setNOKTP(Integer.parseInt(cursor.getString(0)));
					contact.setNama(cursor.getString(1));
					contact.setGender(cursor.getString(2));
					contact.setStatus(cursor.getString(3));
					contact.setTanggallahir(cursor.getString(4));
					// Muat ke dalam contact
					listIdentitas.add(contact);
				} while (cursor.moveToNext());
			}

			// kkembalian
			return listIdentitas;
		}

		// Update satu rekaman
		public int updateIdentitas(String fs, Identitas ktp) {
			
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			Log.d("Update", fs + " " + ktp.getNama() + " " + ktp.getGender() + " " + ktp.getStatus() + " " + ktp.getTanggallahir());
			values.put(KEY_NAMA, ktp.getNama());
			values.put(KEY_GENDER, ktp.getGender());
			values.put(KEY_STATUS, ktp.getStatus());
			values.put(KEY_TANGGALLAHIR, ktp.getTanggallahir());

			// update row
			return db.update(TABLE_DATA, values, KEY_NOKTP + " = ?",
			new String[] { String.valueOf(fs) });

		}

		// Delete satu rekaman
		public int deleteIdentitas(String fs) {
			SQLiteDatabase db = this.getWritableDatabase();
			int hasil=db.delete(TABLE_DATA, KEY_NOKTP + " = ?",
					new String[] { String.valueOf(fs) });
			db.close();
			return hasil;
		}

		// Hitung rekaman
		public int getContactsCount() {
			String countQuery = "SELECT  * FROM " + TABLE_DATA;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			cursor.close();

			// hasil perhitungan
			return cursor.getCount();
		}

		public ArrayList<HashMap<String, String>> UpdateLihatSeluruh() {
			try {
				ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map;
				SQLiteDatabase db;
				db = this.getReadableDatabase();
				String strSQL = "SELECT * FROM " + TABLE_DATA;
				Cursor cursor = db.rawQuery(strSQL, null);
				if (cursor != null) {
					if (cursor.moveToFirst()) {
						do {
							map = new HashMap<String, String>();
							map.put("noktp", cursor.getString(0));
						map.put("nama", cursor.getString(1));
						map.put("gender", cursor.getString(2));
						map.put("status", cursor.getString(3));
						map.put("tanggallahir", cursor.getString(4));
							myArrList.add(map);
						} while (cursor.moveToNext());
					}
				}
				cursor.close();
				db.close();
				return myArrList;
			} catch (Exception e) {
				return null;

			}
		}
		
		public String[] CariDataUpdate(String strMemberID){
			try{
				String arrData[]=null;
				SQLiteDatabase db;
				db=this.getReadableDatabase();
				Cursor cursor = db.query(TABLE_DATA, new String[] { "*" },"noktp=?", new String[] { String.valueOf(strMemberID)},null,null,null,null);
				if (cursor!=null){
					if(cursor.moveToFirst()){
						arrData = new String[cursor.getColumnCount()];
						arrData[0]=cursor.getString(1);
						arrData[1]=cursor.getString(2);
						arrData[2]=cursor.getString(3);
						arrData[3]=cursor.getString(4);
						arrData[4]=cursor.getString(5);
					}
				}
				cursor.close();
				db.close();
				return arrData;
				}catch(Exception e){
					return null;
				}
		}
	}

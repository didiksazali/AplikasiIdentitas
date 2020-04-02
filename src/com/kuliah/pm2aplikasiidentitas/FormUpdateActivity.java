package com.kuliah.pm2aplikasiidentitas;

import java.util.List;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FormUpdateActivity extends Activity {
	EditText _nokt, _nam, _gende, _statu, _tanggallahi;
	RadioGroup radioGroup1, radioGroup2;
	RadioButton radioLk, radioPr,radioKawin, radioTidakKawin;
	Button btnSave;
	
	String MemID;
	
	protected Cursor cursor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formupdate);
		
		final Button cancel2 = (Button) findViewById(R.id.btnCancel2);
		cancel2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		

		// Read var from Intent
		Intent intent = getIntent();
		MemID = intent.getStringExtra("MemID");
		
		ShowData();
	}

	public void ShowData() {
		
		final EditText _nokt = (EditText) findViewById(R.id.edtNoktp);
		final EditText _nam = (EditText) findViewById(R.id.edtNama);
		radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
		final RadioGroup radioGroup2 = (RadioGroup)findViewById(R.id.radioGroup2);
		final EditText _tanggallahi = (EditText) findViewById(R.id.edtTanggallahir);

		// new Class DB
		final OperasiDatabase myDb = new OperasiDatabase(this);
		
		SQLiteDatabase db = myDb.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM data WHERE noktp = '" +getIntent().getStringExtra("MemID")+ "'", null);
		cursor.moveToFirst();
		if (cursor.getCount()>0) {
			cursor.moveToPosition(0);
		
			_nokt.setText(cursor.getString(0).toString());
			_nam.setText(cursor.getString(1).toString());
			radioGroup1.getCheckedRadioButtonId();
			radioGroup2.getCheckedRadioButtonId();
			
			String gender=(cursor.getString(2).toString());
			if (gender.toString().equals("Laki-laki")) {
				radioLk = (RadioButton)findViewById(R.id.radioLk);
               radioLk.setChecked(true);
			}
			else if (gender.toString().equals("Perempuan")) {
				radioPr = (RadioButton)findViewById(R.id.radioPr);
                radioPr.setChecked(true);
			}
		      
			String status=(cursor.getString(3).toString());
			if (status.toString().equals("Kawin")) {
				radioKawin = (RadioButton)findViewById(R.id.radioKawin);
                radioKawin.setChecked(true);
			}
			else if (status.toString().equals("Tidak Kawin")) {
				radioTidakKawin = (RadioButton)findViewById(R.id.radioTidakKawin);
               radioTidakKawin.setChecked(true);
			}
			
			_tanggallahi.setText(cursor.getString(4).toString());
		}
	
		
	}

	public boolean UpdateData() {
		final EditText _nokt = (EditText) findViewById(R.id.edtNoktp);
		final EditText _nam = (EditText) findViewById(R.id.edtNama);
		radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
		final RadioGroup radioGroup2 = (RadioGroup)findViewById(R.id.radioGroup2);
		final EditText _tanggallahi = (EditText) findViewById(R.id.edtTanggallahir);
		
		
		String gender=null;
	      
	      switch (radioGroup1.getCheckedRadioButtonId()) {
	        case R.id.radioLk:
	          gender="Laki-laki";
	          break;
	        case R.id.radioPr:
	          gender="Perempuan";
	          break;
	      }
	      
	      String status=null;
	      
	      switch (radioGroup2.getCheckedRadioButtonId()) {
	        case R.id.radioKawin:
	          status="Kawin";
	          break;
	        case R.id.radioTidakKawin:
	          status="Tidak Kawin";
	          break;
	      }
		
		
		// Dialog
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();
		// periksa no ktp
		if (_nokt.getText().length() == 0) {
			ad.setMessage("No KTP harus diisi !");
			ad.show();
			_nokt.requestFocus();
			return false;
		}
		// periksa nama
		if (_nam.getText().length() == 0) {
			ad.setMessage("Nama harus diisi !");
			ad.show();
			_nam.requestFocus();
			return false;
		}
		// new Class DB
		final OperasiDatabase myDb = new OperasiDatabase(this);
		Log.d("MemID", "adalah " + MemID);
		// Save Data
		long saveStatus = myDb.updateIdentitas(MemID, new Identitas( _nam.getText().toString(), gender, status, _tanggallahi.getText()
				.toString()));
		if (saveStatus <= 0) {
			ad.setMessage("Ada kesalahan.....!! " + saveStatus);
			ad.show();

			return false;
		}

		Toast.makeText(FormUpdateActivity.this, "Update Data sukses dilakukan.",
				Toast.LENGTH_LONG).show();
		return true;
	}

	public void update(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah Anda ingin mengupdate data tersebut?")
				.setCancelable(false)
				.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					UpdateData();
					finish();
					}
				})
				.setNegativeButton("Tidak",
						new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						}).show();
	
	}	
}
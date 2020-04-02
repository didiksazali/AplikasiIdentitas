package com.kuliah.pm2aplikasiidentitas;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class InsertActivity extends Activity {
	EditText _nokt, _nam, _gende, _statu, _tanggallahi;
	RadioGroup radioGroup1, radioGroup2;
	RadioButton radioLK, radioPr, radioKawin, radioTidakKawin;
	Button btnSave;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);
		
		_nokt = (EditText) findViewById(R.id.edtNoktp);
		_nam = (EditText) findViewById(R.id.edtNama);
		_tanggallahi = (EditText) findViewById(R.id.edtTanggallahir);
		radioLK = (RadioButton)findViewById(R.id.radioLk);
		radioPr = (RadioButton)findViewById(R.id.radioPr);
		radioKawin = (RadioButton)findViewById(R.id.radioKawin);
		radioTidakKawin = (RadioButton)findViewById(R.id.radioTidakKawin);
		
		final Button reset = (Button) findViewById(R.id.btnReset);
		reset.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				_nokt.setText("");
				_nam.setText("");
				_tanggallahi.setText("");
				
				radioLK.setChecked(false);
				radioPr.setChecked(false);
				radioKawin.setChecked(false);
				radioTidakKawin.setChecked(false);
				
			}
		});
		
	}

	public void save(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah Anda ingin menyimpan data?")
				.setCancelable(false)
				.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
						insertData();
					}
				})
				.setNegativeButton("Tidak",
						new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						}).show();
	}
	public void insertData() {
		OperasiDatabase db = new OperasiDatabase(this);
		_nokt = (EditText) findViewById(R.id.edtNoktp);
		_nam = (EditText) findViewById(R.id.edtNama);
		radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup)findViewById(R.id.radioGroup2);
		_tanggallahi = (EditText) findViewById(R.id.edtTanggallahir);

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
		
		
		// insert data Identitas
		Log.d("Insert: ", "Inserting ..");
		db.addData(new Identitas(Integer.parseInt(_nokt.getText().toString()), _nam.getText().toString(), gender, status, _tanggallahi.getText()
				.toString()));
			// Baca seluruh Identitas
			Log.d("Reading: ", "Baca seluruh Identitas..");
			List<Identitas> data = db.getAllIdentitas();

			for (Identitas cn : data) {
			String log = "No KTP: " + cn.getNOKTP() + " ,Nama : " + cn.getNama()
						+ " ,Gender : " + cn.getGender() + " ,Status : " + cn.getStatus()
								+ " ,Tanggal lahir : " + cn.getTanggallahir();
				// Writing Contacts to log
				Log.d("isinya : ", log);
			}
			
			Toast.makeText(InsertActivity.this, "Insert Data sukses dilakukan. ",
					Toast.LENGTH_LONG).show();
			
		finish();
	}
	
	}


package com.kuliah.pm2aplikasiidentitas;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class UpdateActivity extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		final OperasiDatabase myDb = new OperasiDatabase(this);
		final ArrayList<HashMap<String, String>> MebmerList = myDb
				.UpdateLihatSeluruh();

		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(UpdateActivity.this, MebmerList,
				R.layout.activity_column, new String[] { "noktp", "nama",
						"gender","status","tanggallahir" }, new int[] { R.id.ColNOKTP, R.id.ColNama,
						R.id.ColGender, R.id.ColStatus, R.id.ColTanggallahir });
		lisView1.setAdapter(sAdap);
		

		
				
		lisView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					final int position, long mylng) {
				
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(UpdateActivity.this);
				alertDialog2.setTitle("Informasi");
				alertDialog2.setMessage("Apakah anda ingin mengedit data tersebut?");
				alertDialog2.setPositiveButton("Ya", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						Intent newActivity = new Intent(UpdateActivity.this,
						FormUpdateActivity.class);
						newActivity.putExtra("MemID",
								MebmerList.get(position).get("noktp").toString());
						startActivity(newActivity);
						finish();
					}
				});

				alertDialog2.setNegativeButton("Tidak", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
					}
				});

				alertDialog2.show();

			}
		});
		

		final Button cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

	}
}

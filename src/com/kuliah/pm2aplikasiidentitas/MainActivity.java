package com.kuliah.pm2aplikasiidentitas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnInsert, btnUpdate, btnDelete, btnReport;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnInsert = (Button) findViewById(R.id.btnInsert);
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnReport = (Button) findViewById(R.id.btnReport);

		btnInsert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						InsertActivity.class);
				startActivity(intent);
			}
		});

		btnUpdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						UpdateActivity.class);
				startActivity(intent);
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						DeleteActivity.class);
				startActivity(intent);
			}
		});

		btnReport.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						ReportActivity.class);
				startActivity(intent);
			}
		});

	}

}

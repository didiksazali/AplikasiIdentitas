package com.kuliah.pm2aplikasiidentitas;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ReportActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);

		final OperasiDatabase myDb = new OperasiDatabase(this);
		final ArrayList<HashMap<String, String>> MebmerList = myDb
				.UpdateLihatSeluruh();

		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ReportActivity.this, MebmerList,
				R.layout.activity_column, new String[] { "noktp", "nama",
						"gender","status","tanggallahir" }, new int[] { R.id.ColNOKTP, R.id.ColNama,
						R.id.ColGender, R.id.ColStatus, R.id.ColTanggallahir });
		lisView1.setAdapter(sAdap);

		final Button cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

	}
}

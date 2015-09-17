package com.gallotti.desafiomeuspedidos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gallotti.desafiomeuspedidos.adapter.ItemKnowledgeAdapter;
import com.gallotti.desafiomeuspedidos.controller.Controller;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_evaluation)
public class EvaluationActivity extends AppCompatActivity{

	@ViewById
	ListView list;

	@ViewById
	EditText edtScore;

	ItemKnowledgeAdapter item;

	private Controller controller;

	@AfterViews
	protected void init(){

		controller =  Controller.getInstance();
		controller.init(this);
		createListView();

	}

	@Click({R.id.btnFinalize})
	protected void finalize(){

		 AlertDialog.Builder adb = new AlertDialog.Builder(this);

		    adb.setTitle(this.getString(R.string.finalize));
		    adb.setMessage(this.getString(R.string.msg_confirm));
		    adb.setIcon(android.R.drawable.ic_dialog_alert);
		    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {

		        	controller.verifyResult(item);
		        	finish();

		      } });
		    adb.setNegativeButton(this.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        	
		      } });
		    adb.show();
		
		

	}


	private void createListView(){
		item =  new ItemKnowledgeAdapter(this, android.R.layout.simple_list_item_1, controller.getListKnowledge());
		list.setAdapter(item);

	}

}

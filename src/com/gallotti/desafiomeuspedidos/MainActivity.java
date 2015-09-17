package com.gallotti.desafiomeuspedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.gallotti.desafiomeuspedidos.beans.Candidate;
import com.gallotti.desafiomeuspedidos.controller.Controller;
import com.gallotti.desafiomeuspedidos.util.Util;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

	@ViewById
	EditText edtName;

	@ViewById
	EditText edtEmail;

	private Controller controller;
	private Candidate candidate;

	@Click({R.id.btnInit})
	protected void initEvaluation(){
		if (isValidateFields()) 
			if (Util.isEmailValid(edtEmail.getText().toString())){
				startActivity(new Intent(this,EvaluationActivity_.class));
			} else {
				Util.callDialogAlert(this.getString(R.string.att), this.getString(R.string.email_invalidate), this);
			}
	}

	@AfterViews
	void init(){
		controller =  Controller.getInstance();
		controller.init(this);
	}

	private boolean isValidateFields(){
		if (!edtName.getText().toString().isEmpty() && !edtEmail.getText().toString().isEmpty()){
			candidate =  new Candidate();
			candidate.setEmail(edtEmail.getText().toString());
			candidate.setName(edtName.getText().toString());
			controller.setCandidate(candidate);
			return true;
		} else {
			Util.callDialogAlert(this.getString(R.string.att),this.getString(R.string.msg_error_field), this);
			return false;
		}
	}

}

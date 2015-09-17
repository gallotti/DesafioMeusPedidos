package com.gallotti.desafiomeuspedidos.controller;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.gallotti.desafiomeuspedidos.R;
import com.gallotti.desafiomeuspedidos.adapter.ItemKnowledgeAdapter;
import com.gallotti.desafiomeuspedidos.beans.Candidate;
import com.gallotti.desafiomeuspedidos.beans.Knowledge;
import com.gallotti.desafiomeuspedidos.beans.KnowledgeBack;
import com.gallotti.desafiomeuspedidos.beans.KnowledgeFront;
import com.gallotti.desafiomeuspedidos.beans.KnowledgeMobile;

public class Controller {


	private static Controller controller;

	private Context c;
	private ArrayList<KnowledgeBack> listBack;
	private ArrayList<KnowledgeFront> listFront;
	private ArrayList<KnowledgeMobile> listMobile;
	private Candidate candidate;

	public static Controller getInstance(){
		if(controller == null){
			controller = new Controller();
		}
		return controller;
	}

	public void init(Context c) {
		this.c = c;
	}

	public ArrayList<Knowledge> getListKnowledge(){

		ArrayList<Knowledge> list = new ArrayList<Knowledge>();
		list.addAll(getListFront());
		list.addAll(getListBack());
		list.addAll(getListMobile());

		return list;
	}

	private ArrayList<Knowledge> getListBack(){

		String[] names = c.getResources().getStringArray(R.array.back);
		ArrayList<Knowledge> listBack = new ArrayList<Knowledge>();
		Knowledge kBack = null;

		for (int i = 0; i < names.length; i++) {
			kBack =  new KnowledgeBack();
			kBack.setName(names[i]);
			listBack.add(kBack);
		}

		return listBack;
	}

	private ArrayList<Knowledge> getListFront(){

		String[] names = c.getResources().getStringArray(R.array.front);
		ArrayList<Knowledge> listFront = new ArrayList<Knowledge>();
		Knowledge kFront = null;

		for (int i = 0; i < names.length; i++) {
			kFront =  new KnowledgeFront();
			kFront.setName(names[i]);
			listFront.add(kFront);
		}

		return listFront;
	}

	private ArrayList<Knowledge> getListMobile(){
		String[] names = c.getResources().getStringArray(R.array.mobile);
		ArrayList<Knowledge> listMobile = new ArrayList<Knowledge>();
		Knowledge kMobile = null;

		for (int i = 0; i < names.length; i++) {
			kMobile =  new KnowledgeMobile();
			kMobile.setName(names[i]);
			listMobile.add(kMobile);
		}

		return listMobile;
	}

	public void verifyResult(ItemKnowledgeAdapter item){

		int resultBack = 0;
		int resultFront = 0;
		int resultMobile = 0;

		listBack =  new ArrayList<KnowledgeBack>();
		listFront =  new ArrayList<KnowledgeFront>();
		listMobile = new ArrayList<KnowledgeMobile>();


		for (int i=0;i<item.getCount();i++){
			Knowledge k = item.getItem(i);

			if (k instanceof KnowledgeFront){
				listFront.add((KnowledgeFront) k);
				resultFront+=k.getScore();
			} else if (k instanceof KnowledgeBack){
				listBack.add((KnowledgeBack) k);
				resultBack+=k.getScore();
			} else {
				listMobile.add((KnowledgeMobile)k);
				resultMobile+=k.getScore();
			}

		}

		resultBack = resultBack/listBack.size();
		resultFront = resultFront/listFront.size();
		resultMobile = resultMobile/listMobile.size();

		if (resultFront<= 10 && resultFront>= 7){
			sendEmail(c.getString(R.string.email_front));
		} 

		if (resultBack<= 10  && resultBack>= 7){
			sendEmail(c.getString(R.string.email_back));
		} 

		if (resultMobile<= 10  && resultMobile>= 7){
			sendEmail(c.getString(R.string.email_mobile));
		} 

		if (resultBack<7 && resultFront<7 && resultMobile<7){
			sendEmail(c.getString(R.string.email_generic));
		}
	}


	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	private void sendEmail(String msg){
		Intent intent = new Intent(Intent.ACTION_SENDTO); 
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, c.getString(R.string.subject));
		intent.putExtra(Intent.EXTRA_TEXT, msg);
		intent.setData(Uri.parse("mailto:"+ getCandidate().getEmail()));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		c.startActivity(intent);
	}

}

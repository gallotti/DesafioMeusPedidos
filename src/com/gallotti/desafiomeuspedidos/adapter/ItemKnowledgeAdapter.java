package com.gallotti.desafiomeuspedidos.adapter;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.gallotti.desafiomeuspedidos.R;
import com.gallotti.desafiomeuspedidos.beans.Knowledge;

public class ItemKnowledgeAdapter extends ArrayAdapter<Knowledge>{

	private final Context context;
	private final List<Knowledge> listKnowledge;

	public ItemKnowledgeAdapter(Context context, int resource,
			List<Knowledge> listKnowledge) {

		super(context, resource, listKnowledge);
		this.context = context;
		this.listKnowledge = listKnowledge; 

	}

	static class ViewHolderItem {
		TextView txtItem;
		EditText edtScore;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final ViewHolderItem viewHolder;

		if(convertView==null){

			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.item_knowledge, parent, false);
			viewHolder = new ViewHolderItem();
			viewHolder.txtItem = (TextView) convertView.findViewById(R.id.txtName);
			viewHolder.edtScore = (EditText) convertView.findViewById(R.id.edtScore);
			convertView.setTag(viewHolder);

			viewHolder.edtScore.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setTitle(viewHolder.txtItem.getText().toString());
					builder.setCancelable(false);

					final NumberPicker picker = new NumberPicker(context);
					picker.setMinValue(0);
					picker.setMaxValue(10);
					builder.setPositiveButton( "Confirmar", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							listKnowledge.get(position).setScore(picker.getValue());
							viewHolder.edtScore.setText(String.valueOf(picker.getValue()));
						}
					});

					builder.setView(picker);
					Dialog dialog = builder.create();
					dialog.show();

				}
			});


		} else {

			viewHolder = (ViewHolderItem) convertView.getTag();

		}

		if(listKnowledge != null) {
			viewHolder.txtItem.setText(listKnowledge.get(position).getName());
			viewHolder.edtScore.setTag(listKnowledge.get(position).getScore());
		}

		return convertView;
	}

}

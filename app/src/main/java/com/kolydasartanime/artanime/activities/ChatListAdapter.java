package com.kolydasartanime.artanime.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.kolydasartanime.artanime.R;
import com.kolydasartanime.artanime.activities.InstantMessage;

import java.util.ArrayList;

public class ChatListAdapter extends BaseAdapter {

    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private String mDisplayName;
    private ArrayList<DataSnapshot> mSnapshotList;
    //STO EPOMENO TO CHILD EVENT LISTENER EINAI O LISTENER POU VLEPEI AN EXOUN GINEI ALLAGES STIN DATABASE
    //(STO SUGKEKRIMENO PARADEIGMA AN KAPOIOS STEILEI KAPOIO NEO MINIMA TOTE UPARXEI ALLAGI
    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            mSnapshotList.add(dataSnapshot);
            notifyDataSetChanged();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public ChatListAdapter(Activity activity, DatabaseReference ref, String name)
    {
        mActivity=activity;
        mDisplayName=name;
        mDatabaseReference=ref.child("messages");
        mDatabaseReference.addChildEventListener(mListener);
        mSnapshotList= new ArrayList<>();
    }

    static class ViewHolder
    {
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams params;
        //GIA NA KANEI DISPLAY TO ONOMA KAI TO MINIMA TOU XRISTI POU ESTEILE STO CHAT
    }

    @Override
    public int getCount() {
        return mSnapshotList.size();
    }

    @Override
    public InstantMessage getItem(int position) {

        DataSnapshot snapshot=mSnapshotList.get(position);
        return snapshot.getValue(InstantMessage.class);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //GIA NA KANO CREATE ENA VIEW APO TO LAYOUT_XML ARXEIO XRISIMOPOIO TO INFLATER
            convertView= inflater.inflate(R.layout.chat_msg_row,parent,false);
            final ViewHolder holder=new ViewHolder();
            holder.authorName=(TextView) convertView.findViewById(R.id.author);
            holder.body=(TextView) convertView.findViewById(R.id.message);
            holder.params =(LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
            convertView.setTag(holder);

        }

        final InstantMessage message =getItem(position);
        final ViewHolder holder=(ViewHolder) convertView.getTag();

        boolean isMe =message.getAuthor().equals(mDisplayName);
        setChatRowAppearence(isMe,holder);

        String author =message.getAuthor();
        holder.authorName.setText(author);

        String msg =message.getMessage();
        holder.body.setText(msg);

        return convertView;
    }

    public void setChatRowAppearence(boolean isItMe,ViewHolder holder)
    {
        if(isItMe)
        {
            holder.params.gravity= Gravity.END;
            //VLEPEI ME TIN IF AN EIMAI EGO O XRISTIS POU STELNEI TO MINIMA KAI AN DEN EIMAI TOTE VAZEI TO SUNEFAKI ME TO ONOMA TOY
            //APO TIN ALLI MERIA
            holder.authorName.setTextColor(Color.GREEN);
            holder.body.setBackgroundResource(R.drawable.bubble2);

        }
        else
        {
            holder.params.gravity= Gravity.START;
            holder.authorName.setTextColor(Color.BLUE);
            holder.body.setBackgroundResource(R.drawable.bubble1);

        }

        holder.authorName.setLayoutParams(holder.params);
        holder.body.setLayoutParams(holder.params);



    }

    public void cleanup()
    {
        mDatabaseReference.removeEventListener(mListener);
    }

    //O ADAPTER EINAI GIA NA KANEI PROVIDE TA DEDOMENA APO TO LISTVIEW STI DATABASE

}

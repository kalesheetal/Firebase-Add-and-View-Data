package com.sk.firebase_add_view_data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.NameHolder> {

    Context mcontext;
    List<UserInfo> userInfoList;

    public UserAdapter(Context mcontext, List<UserInfo> userInfoList) {
        this.mcontext = mcontext;
        this.userInfoList = userInfoList;
    }

    @NonNull
    @Override
    public NameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        myview = layoutInflater.inflate(R.layout.design,parent,false);
        return new NameHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull NameHolder holder, int position) {

        UserInfo info = userInfoList.get(position);

        holder.tname.setText(info.getName());
        holder.temail.setText(info.getEmail());
        holder.tmobile.setText(info.getMobile());
        holder.taddress.setText(info.getAddress());
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    public class NameHolder extends RecyclerView.ViewHolder {

        TextView tname,temail,tmobile,taddress;

        public NameHolder(@NonNull View itemView) {
            super(itemView);

            tname = itemView.findViewById(R.id.txtname);
            temail = itemView.findViewById(R.id.txtemail);
            tmobile = itemView.findViewById(R.id.txtmobile);
            taddress = itemView.findViewById(R.id.txtaddress);
        }
    }
}

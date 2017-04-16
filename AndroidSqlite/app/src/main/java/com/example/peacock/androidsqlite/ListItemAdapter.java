package com.example.peacock.androidsqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bean.Contact;

/**
 * Created by Peacock on 15/03/17.
 */

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.CustomeViewHolder> {

    private List<Contact> itemList;
    private Context mcontext;

    public ListItemAdapter(Context mcontext, List<Contact> list) {

        this.itemList = list;
        this.mcontext = mcontext;

    }

    @Override
    public CustomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, parent,
                false);
        return new CustomeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomeViewHolder holder, int position) {
        Contact contact = itemList.get(position);
        holder.textView1.setText(String.valueOf(contact.get_id()));
        holder.textView2.setText(contact.get_ChallanNo());
        holder.textView3.setText(contact.get_Mapping());
        holder.textView4.setText(contact.get_PartyName());
        holder.textView5.setText(contact.get_Rate());
        holder.textView6.setText(contact.get_Total());
        holder.textView7.setText(contact.get_TruckNo());
        /*holder.textView1.setText(" "+contact.get_name());
        holder.textView2.setText(" "+contact.get_phone_number());*/

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class CustomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7,
                textView8, textView9;

        public CustomeViewHolder(View itemView) {
            super(itemView);

            textView1 = (TextView) itemView.findViewById(R.id.id);
            textView2 = (TextView) itemView.findViewById(R.id.challanno);
            textView3 = (TextView) itemView.findViewById(R.id.mapping);
            textView4 = (TextView) itemView.findViewById(R.id.party_name);
            textView5 = (TextView) itemView.findViewById(R.id.rate);
            textView6 = (TextView) itemView.findViewById(R.id.total);
            textView7 = (TextView) itemView.findViewById(R.id.truckno);
            textView8 = (TextView) itemView.findViewById(R.id.edit);
            textView9 = (TextView) itemView.findViewById(R.id.delete);

            textView8.setOnClickListener(this);
            textView9.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.edit:

                    //Toast.makeText(mcontext,""+itemList.get(getAdapterPosition()).get_id(),Toast.LENGTH_LONG).show();
                    Intent ii = new Intent(mcontext, MainActivity.class);
                    ii.putExtra("id", itemList.get(getAdapterPosition()).get_id());
                    ii.putExtra("challanno", itemList.get(getAdapterPosition()).get_ChallanNo());
                    ii.putExtra("mapping", itemList.get(getAdapterPosition()).get_Mapping());
                    ii.putExtra("partyname", itemList.get(getAdapterPosition()).get_PartyName());
                    ii.putExtra("rate", itemList.get(getAdapterPosition()).get_Rate());
                    ii.putExtra("truckno", itemList.get(getAdapterPosition()).get_TruckNo());
                    mcontext.startActivity(ii);

                    break;

                case R.id.delete:

                    new DatabaseHandler(mcontext).deleteContact(itemList.get(getAdapterPosition()));

                    break;

                default:

                    break;

            }
        }
    }
}

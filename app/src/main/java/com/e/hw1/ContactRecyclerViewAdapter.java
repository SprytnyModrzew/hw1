package com.e.hw1;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.hw1.ContactFragment.OnListFragmentInteractionListener;
import com.e.hw1.db.Contact;

import java.util.List;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ContactRecyclerViewAdapter(List<Contact> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String text = mValues.get(position).getName() +" "+ mValues.get(position).getSurname();
        holder.mItemText.setText(text);
        holder.mItemPic.setImageResource(mValues.get(position).getImgPath());
        //Log.i("userrr", String.valueOf(getItemCount()));
        holder.mItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClick(holder.mItem);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        try{
            return mValues.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mItemText;
        public final ImageView mItemPic;
        public final ImageButton mItemButton;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemText = view.findViewById(R.id.contactItemText);
            mItemPic = view.findViewById(R.id.contactItemPicture);
            mItemButton = view.findViewById(R.id.contactItemButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "'";
        }
    }
}

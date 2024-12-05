package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.DiscountTicket;
import com.example.myapplication.R;

import java.util.List;

public class DiscountTicketAdapter extends RecyclerView.Adapter<DiscountTicketAdapter.DiscountTicketViewHolder> {
    private List<DiscountTicket> discountTicketList;

    public DiscountTicketAdapter(List<DiscountTicket> discountTicketList) {
        this.discountTicketList = discountTicketList;
    }

    @NonNull
    @Override
    public DiscountTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discount_ticket, parent, false);
        return new DiscountTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountTicketViewHolder holder, int position) {
        DiscountTicket ticket = discountTicketList.get(position);
        holder.ticketDescriptionTextView.setText(ticket.getDescription());
        holder.discountValueTextView.setText(String.valueOf(ticket.getDiscountValue()));
    }

    @Override
    public int getItemCount() {
        return discountTicketList.size();
    }

    public static class DiscountTicketViewHolder extends RecyclerView.ViewHolder {
        TextView ticketDescriptionTextView;
        TextView discountValueTextView;

        public DiscountTicketViewHolder(View itemView) {
            super(itemView);
            ticketDescriptionTextView = itemView.findViewById(R.id.ticketDescriptionTextView);
            discountValueTextView = itemView.findViewById(R.id.discountValueTextView);
        }
    }
}

package com.example.e_numberapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class EnumAdapter extends RecyclerView.Adapter<EnumAdapter.EnumAdapterVH> implements Filterable {
    //used as adapter and filter

    private List<EnumModel> enumModelList;
    private List<EnumModel> getEnumModelListFilterd;
    private Context context;
    private SelectedEnum selectedEnum;

    public EnumAdapter(List<EnumModel> enumModelList, SelectedEnum selectedEnum) {//menu for all item, filtered item and the selection
        this.enumModelList = enumModelList;
        this.selectedEnum = selectedEnum;
        this.getEnumModelListFilterd =enumModelList;
    }

    @NonNull
    @Override
    public EnumAdapter.EnumAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //but the items/rows in the recycler view

        context = parent.getContext();
        return new EnumAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_enums,null));
    }

    @Override
    public void onBindViewHolder(@NonNull EnumAdapter.EnumAdapterVH holder, int position) {
        //change the row/item content dynamically

        EnumModel enumModel = enumModelList.get(position);
        String e_name = enumModel.getName();
        String eprefix = enumModel.getE_no();
        holder.ename.setText(e_name);
        holder.prefix.setText(eprefix);

    }

    @Override
    public int getItemCount() {
        return enumModelList.size();
    }//return how many rows

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {//filter the displayed data and adjust items accordingly.
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if(constraint == null | constraint.length() == 0 ){//ensure it is not empty
                    results.count = getEnumModelListFilterd.size();
                    results.values = getEnumModelListFilterd;
                }
                else {//filter the data based on the search.
                    String searchChr = constraint.toString().toLowerCase();
                    List<EnumModel> resultdata = new ArrayList<>();

                    for (EnumModel enumModel:getEnumModelListFilterd){
                        if (enumModel.getE_no().toLowerCase().contains(searchChr)){
                            resultdata.add(enumModel);
                        }
                    }
                    results.count = resultdata.size();
                    results.values = resultdata;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //implement filtration on the array list

                enumModelList = (List<EnumModel>) results.values;
                notifyDataSetChanged();

            }
        };

        return filter;
    }

    public interface SelectedEnum{//return the selected row/item by using onOptionsItemSelected
        void selectedEnum(EnumModel enumModel);
    }

    public class EnumAdapterVH extends RecyclerView.ViewHolder{

        TextView prefix, ename;
        ImageView icon;

        public EnumAdapterVH(@NonNull View itemView) {
            super(itemView);
            prefix = itemView.findViewById(R.id.prefix);
            ename = itemView.findViewById(R.id.ename);
            icon = itemView.findViewById(R.id.imageview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedEnum.selectedEnum(enumModelList.get(getAdapterPosition()));
                }
            });
        }
    }
}

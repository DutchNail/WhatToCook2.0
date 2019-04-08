package frankspijker.saxion.whattocookv2;

import android.app.ListActivity;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class MyAdapter<onClickViewHolder> extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Recipes> mDataset;

    public MyAdapter(List<Recipes> recepten) {
        mDataset = recepten;
    }


    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView descriptionTextView;
        public MyViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.nameTextView);
            descriptionTextView = v.findViewById(R.id.description);
        }
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.UP | ItemTouchHelper.DOWN) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            Toast.makeText(this, "on Move", Toast.LENGTH_SHORT).show();
            Toast.makeText(recyclerView.getContext(), "on Move", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            int position = viewHolder.getAdapterPosition();
            mDataset.remove(position);
            notifyDataSetChanged();
        }
    };



//     Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);

        return new MyViewHolder(itemView);

    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Recipes recipe = mDataset.get(position);
        holder.nameTextView.setText(recipe.getTitle());
        holder.descriptionTextView.setText(recipe.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Hahiofafa", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(), ShowRecipeActivity.class);
            i.putExtra("name", recipe.getTitle());
            i.putExtra("description", recipe.getDescription());

            view.getContext().startActivity(i);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

//    private final View.OnClickListener mOnClickListener = new MyOnClickListener();



}

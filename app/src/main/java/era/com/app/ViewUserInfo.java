package era.com.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import era.com.app.dao.DBHelpler;
import era.com.app.model.RegistrationModel;

public class ViewUserInfo extends AppCompatActivity {

    RecyclerView recyclerView;

    private DBHelpler dbHelpler;
    ArrayList<RegistrationModel> allData;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_info);

        recyclerView = findViewById(R.id.recyclerView);

        dbHelpler = new DBHelpler(this);
        allData = dbHelpler.getAllData();

        showCardList();
    }


    //Being adapter
    void showCardList(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // create an Object for Adapter
        mAdapter = new RecyclerViewAdapter(allData);
        // set the adapter object to the Recyclerview
        recyclerView.setAdapter(mAdapter);
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private ArrayList<RegistrationModel> dataList;

        public RecyclerViewAdapter(ArrayList<RegistrationModel> dataList) {
            this.dataList = dataList;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user_info, null);
            itemLayoutView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
           ViewHolder viewHolder = new ViewHolder(itemLayoutView);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {

            RegistrationModel fp = dataList.get(i);
            viewHolder.tv_name.setText(fp.getFullName());
            viewHolder.tv_mobile.setText(fp.getPhone());
            viewHolder.tv_email.setText(fp.getEamil());
            viewHolder.menu = fp;
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        // inner class to hold a reference to each item of RecyclerView
        public  class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_name;
            public TextView tv_mobile;
            public TextView tv_email;

            public RegistrationModel menu;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);

                tv_name = (TextView) itemLayoutView.findViewById(R.id.tv_name);
                tv_mobile = (TextView) itemLayoutView.findViewById(R.id.tv_mobile);
                tv_email = (TextView) itemLayoutView.findViewById(R.id.tv_email);

                itemLayoutView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });



            }

        }
    }


//  class  Adapter extends   RecyclerView.Adapter<Adapter.ViewHolder>{
//
//      @NonNull
//      @Override
//      public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//          return null;
//      }
//
//      @Override
//      public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
//
//      }
//
//      @Override
//      public int getItemCount() {
//          return 0;
//      }
//
// public  class ViewHolder extends RecyclerView.ViewHolder {
//
//          public TextView tv_name;
//          public TextView tv_mobile;
//
//          public RegistrationModel menu;
//
//          public ViewHolder(View itemLayoutView) {
//              super(itemLayoutView);
//
//              tv_name = (TextView) itemLayoutView.findViewById(R.id.tv_name);
//              tv_mobile = (TextView) itemLayoutView.findViewById(R.id.tv_mobile);
//
//              itemLayoutView.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View v) {
//
//                  }
//              });
//
//
//
//          }
//
//      }
//  }

}
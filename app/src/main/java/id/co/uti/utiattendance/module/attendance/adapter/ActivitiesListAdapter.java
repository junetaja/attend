package id.co.uti.utiattendance.module.attendance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.uti.utiattendance.R;
import id.co.uti.utiattendance.module.attendance.model.ActivitiesModel;

/**
 * Created by DELL on 8/24/2017.
 */

public class ActivitiesListAdapter extends RecyclerView.Adapter<ActivitiesListAdapter.ViewHolder> {

    private final ActivitiesListAdapter.Listener listener;
    private List<ActivitiesModel> activitiesModels = new ArrayList<>();

    public ActivitiesListAdapter(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        void onItemClicked(ActivitiesModel activitiesModel);
    }

    public void addAll(List<ActivitiesModel> activitiesModel) {
        this.activitiesModels.addAll(activitiesModel);
        notifyDataSetChanged();
    }

    public void clearAll() {
        if (activitiesModels.size() > 0) {
            activitiesModels.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activities_list_item, parent, false);
        return new ActivitiesListAdapter.ViewHolder(parent.getContext(), view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivitiesModel model = activitiesModels.get(position);
        holder.bindData(model);
    }

    @Override
    public int getItemCount() {
        return activitiesModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtCheckIn) TextView txtCheckIn;
        @BindView(R.id.txtCheckOut) TextView txtCheckOut;
        @BindView(R.id.txtLocationIn) TextView txtLocationIn;
        @BindView(R.id.txtLocationOut) TextView txtLocatinOut;
        @BindView(R.id.txtDuration) TextView txtDuration;

        private final Context context;
        private final ActivitiesListAdapter.Listener listener;
        private ActivitiesModel activitiesModel;

        public ViewHolder(Context context,
                          View itemView, Listener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
            this.listener = listener;
        }

        void bindData(ActivitiesModel model) {
            this.activitiesModel = model;
            txtCheckIn.setText(context.getString(R.string.prefix_checkin, model.checkin));
            txtCheckOut.setText(context.getString(R.string.prefix_checkout, model.checkout));
            txtLocationIn.setText(context.getString(R.string.prefix_locationin, model.location_in));
            txtLocatinOut.setText(context.getString(R.string.prefix_locationout, model.location_out));
            txtDuration.setText(context.getString(R.string.prefix_duration, model.duration));
        }

        @OnClick(R.id.card)
        public void onItem_Clicked() {
            listener.onItemClicked(activitiesModel);
        }

    }
}

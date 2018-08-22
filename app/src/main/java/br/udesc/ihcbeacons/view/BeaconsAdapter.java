package br.udesc.ihcbeacons.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.udesc.ihcbeacons.R;
import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.presenter.Presenter;
import br.udesc.ihcbeacons.presenter.PresenterImpl;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BeaconStatus} and makes a call to the
 * specified {@link BeaconsFragment.OnBeaconsFragmentInteractionListener}.
 */
public class BeaconsAdapter extends RecyclerView.Adapter<BeaconsAdapter.ViewHolder>
                            implements BeaconsAdapterView {

    private final List<BeaconStatus> mValues;
    private final BeaconsFragment.OnBeaconsFragmentInteractionListener mListener;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private final Presenter presenter;

    public BeaconsAdapter(Context context, List<BeaconStatus> items, BeaconsFragment.OnBeaconsFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;

        presenter = new PresenterImpl(this);

//        subscribeForBeaconStatusUpdates();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_beacons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mBeaconNameView.setText(mValues.get(position).getRegion());
        holder.mStatusView.setText(mValues.get(position).getType());
        holder.mTimestamp.setText(String.valueOf(mValues.get(position).getTimestamp()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onBeaconsFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mBeaconNameView;
        public final TextView mStatusView;
        public final TextView mTimestamp;
        public BeaconStatus mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mBeaconNameView = (TextView) view.findViewById(R.id.beacon_name);
            mStatusView = (TextView) view.findViewById(R.id.status);
            mTimestamp = (TextView) view.findViewById(R.id.timestamp);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mStatusView.getText() + "'";
        }
    }

    @Override
    public void subscribeForBeaconStatusUpdates(Context context) {
        presenter.subscribeForBeaconStatusUpdates(context);
    }

    @Override
    public void unsubscribeFromBeaconStatusUpdates() {
        presenter.unsubscribeFromBeaconStatusUpdates();
    }

    @Override
    public void onBeaconStatusAdded(BeaconStatus beaconStatus) {
        mValues.add(beaconStatus);
        notifyItemInserted(mValues.size() - 1);
    }
}

package br.udesc.ihcbeacons.view;

import static br.udesc.ihcbeacons.service.BeaconService.MAIN_ACTION;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import br.udesc.ihcbeacons.R;
import br.udesc.ihcbeacons.model.BeaconStatus;
import br.udesc.ihcbeacons.service.BeaconService;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnBeaconsFragmentInteractionListener}
 * interface.
 */
public class BeaconsFragment extends Fragment {

    // Parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    // Parameters
    private int mColumnCount = 1;

    private OnBeaconsFragmentInteractionListener mListener;
    private BeaconsAdapter beaconsAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BeaconsFragment() {
    }

    // Parameter initialization
    @SuppressWarnings("unused")
    public static BeaconsFragment newInstance(int columnCount) {
        BeaconsFragment fragment = new BeaconsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beacons_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            beaconsAdapter = new BeaconsAdapter(getContext(), BeaconStatus.getDummyBeaconStatuses(), mListener);
            recyclerView.setAdapter(beaconsAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        beaconsAdapter.subscribeForBeaconStatusUpdates(getContext());
//        startBeaconService();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startBeaconService();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startBeaconService() {
        Intent intent = new Intent(getContext(), BeaconService.class);
        intent.setAction(MAIN_ACTION);
        getContext().startService(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBeaconsFragmentInteractionListener) {
            mListener = (OnBeaconsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnBeaconsFragmentInteractionListener {
        void onBeaconsFragmentInteraction(BeaconStatus item);
    }
}

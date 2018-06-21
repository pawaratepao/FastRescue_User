package com.example.pawar.fastrescue_user.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.adapter.StatusListAdapter;
import com.example.pawar.fastrescue_user.dao.StatusCollectionDao;
import com.example.pawar.fastrescue_user.dao.StatusItemDao;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;
import com.example.pawar.fastrescue_user.manager.StatusListManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListViewStatusFragment extends Fragment {

    ListView listViewStatus;
    StatusListAdapter listAdapter;
    StatusListManager statusListManager;
    SwipeRefreshLayout swipeRefreshLayout;
    String username;

    public interface ListViewStatusFragmentListener {
        void onStatusItemClicked(StatusItemDao dao);

    }

    public ListViewStatusFragment() {
        super();
    }

    public static ListViewStatusFragment newInstance() {
        ListViewStatusFragment fragment = new ListViewStatusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_status, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        getUsername();
        statusListManager = new StatusListManager();
        listViewStatus = (ListView) rootView.findViewById(R.id.listViewStatus);
        listAdapter = new StatusListAdapter();
        listViewStatus.setAdapter(listAdapter);
        listViewStatus.setOnItemClickListener(listViewItemClickListener);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayoutStatus);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();

            }
        });
        listViewStatus.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);

            }
        });
        reloadData();


        // Init 'View' instance(s) with rootView.findViewById here
    }

    private void reloadData() {
        Call<StatusCollectionDao> call = HttpMeneger.getInstance().getService().loadStatusList(username);
        call.enqueue(new Callback<StatusCollectionDao>() {
                         @Override
                         public void onResponse(Call<StatusCollectionDao> call, Response<StatusCollectionDao> response) {
                             swipeRefreshLayout.setRefreshing(false);
                             if (response.isSuccessful()) {
                                 StatusCollectionDao dao = response.body();
                                 statusListManager.setDao(dao);
                                 listAdapter.setDao(dao);
                                 listAdapter.notifyDataSetChanged();


                             } else {
                                 try {
                                     Toast.makeText(Contextor.getInstance().getContext(),
                                             response.errorBody().string(),
                                             Toast.LENGTH_SHORT).show();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }

                             }

                         }

                         @Override
                         public void onFailure(Call<StatusCollectionDao> call,
                                               Throwable t) {
                             swipeRefreshLayout.setRefreshing(false);
                             Toast.makeText(Contextor.getInstance().getContext(),
                                     t.toString(),
                                     Toast.LENGTH_LONG).show();

                         }
                     }

        );
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    public void getUsername() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_Login", getContext().MODE_PRIVATE);
        username = sharedPreferences.getString("id", null);

    }

    AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            StatusItemDao dao = StatusListManager.getDao().getData().get(position);
            ListViewStatusFragment.ListViewStatusFragmentListener listener = (ListViewStatusFragment.ListViewStatusFragmentListener) getActivity();
            listener.onStatusItemClicked(dao);

        }
    };
}

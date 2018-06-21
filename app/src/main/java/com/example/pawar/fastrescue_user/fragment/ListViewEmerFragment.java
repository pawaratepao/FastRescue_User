package com.example.pawar.fastrescue_user.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.pawar.fastrescue_user.adapter.EmerListAdapter;
import com.example.pawar.fastrescue_user.dao.EmerCollectionDao;
import com.example.pawar.fastrescue_user.dao.EmerItemDao;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.EmerListManager;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListViewEmerFragment extends Fragment {
    ListView listViewEmer;
    EmerListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    EmerListManager emerListManager;
    String username;

    public interface ListViewEmerFragmentListener {
        void onEmerItemClicked(EmerItemDao dao);

    }

    public ListViewEmerFragment() {
        super();
    }

    public static ListViewEmerFragment newInstance() {
        ListViewEmerFragment fragment = new ListViewEmerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_re_emer, container, false);
        initInstances(rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initInstances(View rootView) {
        getUsername();
        emerListManager = new EmerListManager();
        listViewEmer = (ListView) rootView.findViewById(R.id.listViewEmer);
        listAdapter = new EmerListAdapter();
        listViewEmer.setAdapter(listAdapter);
        listViewEmer.setOnItemClickListener(listViewItemClickListener);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();

            }
        });
        listViewEmer.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        Call<EmerCollectionDao> call = HttpMeneger.getInstance().getService().loadEmerData(username);
        call.enqueue(new Callback<EmerCollectionDao>() {
                         @Override
                         public void onResponse(Call<EmerCollectionDao> call, Response<EmerCollectionDao> response) {
                             swipeRefreshLayout.setRefreshing(false);
                             if (response.isSuccessful()) {
                                 EmerCollectionDao dao = response.body();
                                 emerListManager.setDao(dao);
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
                         public void onFailure(Call<EmerCollectionDao> call,
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

    AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            EmerItemDao dao = EmerListManager.getDao().getData().get(position);
            ListViewEmerFragmentListener listener = (ListViewEmerFragmentListener) getActivity() ;
            listener.onEmerItemClicked(dao);

        }
    };

    public void getUsername() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_Login", getContext().MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

    }
}

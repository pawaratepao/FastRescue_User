package com.example.pawar.fastrescue_user.fragment;

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
import com.example.pawar.fastrescue_user.adapter.EmerPhotoListAdapter;
import com.example.pawar.fastrescue_user.dao.EmerPhotoCollectionDao;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.EmerPhotoListManager;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListViewEmerPhotoFragment extends Fragment {
    ListView listViewEmerPhoto;
    EmerPhotoListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    EmerPhotoListManager emerPhotoListManager;
    String noti_id ;


    public ListViewEmerPhotoFragment() {
        super();
    }

    public static ListViewEmerPhotoFragment newInstance(String noti_id) {
        ListViewEmerPhotoFragment fragment = new ListViewEmerPhotoFragment();
        Bundle args = new Bundle();
        args.putString("noti_id",noti_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emer_photo, container, false);
        initInstances(rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        noti_id = getArguments().getString("noti_id");
        super.onCreate(savedInstanceState);
    }

    private void initInstances(View rootView) {
        emerPhotoListManager = new EmerPhotoListManager();
        listViewEmerPhoto = (ListView) rootView.findViewById(R.id.listViewEmerPhoto);
        listAdapter = new EmerPhotoListAdapter();
        listViewEmerPhoto.setAdapter(listAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData(noti_id);

            }
        });
        listViewEmerPhoto.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);

            }
        });
        reloadData(noti_id);
        // Init 'View' instance(s) with rootView.findViewById here
    }

    private void reloadData(final String noti_id) {
        Call<EmerPhotoCollectionDao> call = HttpMeneger.getInstance().getService().loadEmerPhotoList(noti_id);
        call.enqueue(new Callback<EmerPhotoCollectionDao>() {
                         @Override
                         public void onResponse(Call<EmerPhotoCollectionDao> call, Response<EmerPhotoCollectionDao> response) {
                             swipeRefreshLayout.setRefreshing(false);
                             if (response.isSuccessful()) {
                                 EmerPhotoCollectionDao dao = response.body();
                                 emerPhotoListManager.setDao(dao);
                                 listAdapter.setDao(dao);
                                 listAdapter.notifyDataSetChanged();
                                 //Toast.makeText(Contextor.getInstance().getContext(),
                                         //dao.getData().get(2).getNewsDetail(),
                                         //Toast.LENGTH_SHORT).show();

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
                         public void onFailure(Call<EmerPhotoCollectionDao> call,
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

}

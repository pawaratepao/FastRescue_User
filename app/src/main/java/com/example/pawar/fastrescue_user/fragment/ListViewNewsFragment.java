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
import com.example.pawar.fastrescue_user.adapter.NewsListAdapter;
import com.example.pawar.fastrescue_user.dao.PhotoCollectionDao;
import com.example.pawar.fastrescue_user.dao.PhotoItemDao;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;
import com.example.pawar.fastrescue_user.manager.PhotoListManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListViewNewsFragment extends Fragment {
    ListView listViewNews;
    NewsListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    PhotoListManager photoListManager;

    public interface ListViewNewsFragmentListener {
        void onPhotoItemClicked(PhotoItemDao dao);

    }

    public ListViewNewsFragment() {
        super();
    }

    public static ListViewNewsFragment newInstance() {
        ListViewNewsFragment fragment = new ListViewNewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        initInstances(rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initInstances(View rootView) {
        photoListManager = new PhotoListManager();
        listViewNews = (ListView) rootView.findViewById(R.id.listViewNews);
        listAdapter = new NewsListAdapter();
        listViewNews.setAdapter(listAdapter);
        listViewNews.setOnItemClickListener(listViewItemClickListener);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();

            }
        });
        listViewNews.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        Call<PhotoCollectionDao> call = HttpMeneger.getInstance().getService().loadPhotoList();
        call.enqueue(new Callback<PhotoCollectionDao>() {
                         @Override
                         public void onResponse(Call<PhotoCollectionDao> call, Response<PhotoCollectionDao> response) {
                             swipeRefreshLayout.setRefreshing(false);
                             if (response.isSuccessful()) {
                                 PhotoCollectionDao dao = response.body();
                                 photoListManager.setDao(dao);
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
                         public void onFailure(Call<PhotoCollectionDao> call,
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
            PhotoItemDao dao = PhotoListManager.getDao().getData().get(position);
            ListViewNewsFragmentListener listener = (ListViewNewsFragmentListener) getActivity();
            listener.onPhotoItemClicked(dao);

        }
    };
}

package com.sb.android.myapplication.provider;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sb.android.myapplication.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PhotoViewActivityFragment extends Fragment
                        implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView mListView;
    private PhotoViewAdapter mAdapter;

    public PhotoViewActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.photo, container, false);

        mListView=(ListView) mListView.findViewById(R.id.listView);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Sync
        //getActivity().getContentResolver().query();

        mAdapter = new PhotoViewAdapter(getActivity(),null,true);
        mListView.setAdapter(mAdapter);
    }

    // CursorLoader @Background thread
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);

    }


    // on Reset
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }
}

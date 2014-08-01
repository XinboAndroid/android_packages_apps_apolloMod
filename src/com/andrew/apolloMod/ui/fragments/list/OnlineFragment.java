package com.andrew.apolloMod.ui.fragments.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andrew.apolloMod.R;
import com.andrew.apolloMod.helpers.RefreshableFragment;

public class OnlineFragment extends RefreshableFragment{

	private ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.listview, container, false);
        mListView = (ListView)root.findViewById(android.R.id.list);
        return root;
	}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mListView.setOnItemClickListener(this);
//        mListView.setAdapter(new OnlineAdapter());
//        new OnlineTask().execute("");
    }
    
//    class OnlineTask extends AbstractAsyncTask
//    {
//    	@Override
//    	protected void onPostExecute(String result) {
//    		// TODO Auto-generated method stub
//    		super.onPostExecute(result);
//    	}
//    }
//    
//    class OnlineAdapter extends AbstractAdapter
//    {
//		@Override
//		public int getCount() {
//			return 20;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			LayoutInflater inflater = getActivity().getLayoutInflater();
//			View layout = inflater.inflate(R.layout.listview_items, null);
//			return layout;
//		}
//    }
	
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	
}

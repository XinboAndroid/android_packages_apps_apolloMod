package com.andrew.apolloMod.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andrew.apolloMod.R;
import com.andrew.apolloMod.helpers.utils.MusicUtils;

import static com.andrew.apolloMod.Constants.INTENT_ADD_TO_PLAYLIST;
import static com.andrew.apolloMod.Constants.INTENT_CREATE_PLAYLIST;
import static com.andrew.apolloMod.Constants.INTENT_PLAYLIST_LIST;
import static com.andrew.apolloMod.Constants.PLAYLIST_NEW;
import static com.andrew.apolloMod.Constants.PLAYLIST_QUEUE;

public class PlaylistPicker extends Activity implements
        DialogInterface.OnClickListener, DialogInterface.OnCancelListener
{

    private AlertDialog mPlayListPickerDialog;

    List<Map<String, String>> mAllPlayLists = new ArrayList<Map<String, String>>();

    List<String> mPlayListNames = new ArrayList<String>();

    long[] mList = new long[]
    {};

    @Override
    public void onCancel(DialogInterface dialog)
    {
        finish();
    }

    // onClick()?
    @Override
    public void onClick(DialogInterface dialog, int which)
    {

        long listId = Long.valueOf(mAllPlayLists.get(which).get("id"));
        String listName = mAllPlayLists.get(which).get("name");
        Intent intent;
        boolean mCreateShortcut = getIntent().getAction().equals(
                Intent.ACTION_CREATE_SHORTCUT);
        //创建快捷方式
        if (mCreateShortcut)
        {
            final Intent shortcut = new Intent();
            // shortcut.setAction(INTENT_PLAY_SHORTCUT);
            shortcut.putExtra("id", listId);

            intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcut);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, listName);
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(this,
                            R.drawable.ic_launcher));
            //TracksBrowser
            setResult(RESULT_OK, intent);
        }
        else
        {
            //存在艺术家列表ID
            if (listId >= 0)
            {
                MusicUtils.addToPlaylist(this, mList, listId);
            }
            //添加艺术家到队列
            else if (listId == PLAYLIST_QUEUE)
            {
                MusicUtils.addToCurrentPlaylist(this, mList);
            }
            //新建列表名
            else if (listId == PLAYLIST_NEW)
            {
                intent = new Intent(INTENT_CREATE_PLAYLIST);
                intent.putExtra(INTENT_PLAYLIST_LIST, mList);
                startActivity(intent);
            }
        }
        finish();
    }

    @Override
    public void onCreate(Bundle icicle)
    {

        super.onCreate(icicle);
        setContentView(new LinearLayout(this));

        if (getIntent().getAction() != null)
        {
            // 添加歌曲
            if (INTENT_ADD_TO_PLAYLIST.equals(getIntent().getAction())
                    && getIntent().getLongArrayExtra(INTENT_PLAYLIST_LIST) != null)
            {
                //
                MusicUtils.makePlaylistList(this, false, mAllPlayLists);
                mList = getIntent().getLongArrayExtra(INTENT_PLAYLIST_LIST);
                //封装
                addToPlay(R.string.add_to_playlist);
            }
            //创建快捷方式
            else if (getIntent().getAction().equals(
                    Intent.ACTION_CREATE_SHORTCUT))
            {
                MusicUtils.makePlaylistList(this, true, mAllPlayLists);
                //dfdsggg
                //sddds
//                for (int i = 0; i < mAllPlayLists.size(); i++)
//                {
//                    mPlayListNames.add(mAllPlayLists.get(i).get("name"));
//                }
//                mPlayListPickerDialog = new AlertDialog.Builder(this)
//                .setTitle(R.string.create_Shortcut)
//                        .setItems(
//                                mPlayListNames.toArray(new CharSequence[mPlayListNames
//                                        .size()]), this)
//                        .setOnCancelListener(this).show();
            }
        }
        else
        {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void addToPlay(int title)
    {
        for (int i = 0; i < mAllPlayLists.size(); i++)
        {
            mPlayListNames.add(mAllPlayLists.get(i).get("name"));
        }
        mPlayListPickerDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setItems(
                        mPlayListNames.toArray(new CharSequence[mPlayListNames
                                .size()]), this)
                .setOnCancelListener(this).show();
    }

    @Override
    public void onPause()
    {

        if (mPlayListPickerDialog != null && mPlayListPickerDialog.isShowing())
        {
            mPlayListPickerDialog.dismiss();
        }
        super.onPause();
    }

    @Override
    protected void onResume()
    {

        super.onResume();
        if (mPlayListPickerDialog != null && !mPlayListPickerDialog.isShowing())
        {
            mPlayListPickerDialog.show();
        }
    }

}

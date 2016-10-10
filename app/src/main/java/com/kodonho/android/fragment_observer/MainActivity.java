package com.kodonho.android.fragment_observer;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<MusicData> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = getDatas();
    }

    public ArrayList<MusicData> getDatas(){
        ArrayList<MusicData> datas = new ArrayList<>();
        for(int i=0;i<30;i++){
            MusicData data = new MusicData();
            data.title = "테스트" +i;
            data.artist ="가수이름"+i;
            datas.add(data);
        }

        return datas;
    }

    public ArrayList<MusicData> getMusicInfo(){
        ArrayList<MusicData> datas = new ArrayList<>();

        // 미디어 스토어에서 가져올 컬럼명 세팅
        String projections[] = {
                MediaStore.Audio.Media._ID,       // 노래아이디
                MediaStore.Audio.Media.ALBUM_ID,  // 앨범아이디
                MediaStore.Audio.Media.TITLE,     // 제목
                MediaStore.Audio.Media.ARTIST     // 가수
        };

        //getContentResolver().query(주소, 검색해올컬럼명들, 조건절, 조건절에매핑되는값, 정렬);
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projections, null, null, null);
        /*
        - uri        : content://스키마 형태로 정해져 있는 곳의 데이터를 가져온다
        - projection : 가져올 컬럼 이름들의 배열. null 을 입력하면 모든값을 가져온다
        - selection : 조건절(where)에 해당하는 내용
        - selectionArgs : 조건절이 preparedstatement 형태일 때 ? 에 매핑되는 값의 배열
        - sort order    : 정렬 조건
         */
        if(cursor != null){
            while(cursor.moveToNext()){
                MusicData data = new MusicData();
                // 데이터에 가수이름을 입력
                // 1. 가수 이름 컬럼의 순서(index)를 가져온다
                int idx = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                // 2. 해당 index를 가진 컬럼의 실제값을 가져온다
                data.artist = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                data.title = cursor.getString(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                data.albumId = cursor.getInt(idx);

                idx = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                data.musicId = cursor.getInt(idx);

                datas.add(data);
            }
        }
        cursor.close();
        return datas;
    }
}

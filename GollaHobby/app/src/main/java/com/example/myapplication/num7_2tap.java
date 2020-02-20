package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class num7_2tap extends Fragment {
    private View view;
    ImageView ivDraw;
    ImageView ivWall;
    ImageView ivPicture;
    TextView tvDraw;
    TextView tvWall;
    TextView tvPicture;
    TextView tvDrawEx;
    TextView tvWallEx;
    TextView tvPictureEx;

    public static num7_2tap newinstance(){
        num7_2tap num7_2tap = new num7_2tap();
        return num7_2tap;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.num7_2, container, false);
        ivPicture = view.findViewById(R.id.ivPicture);
        ivDraw = view.findViewById(R.id.ivDraw);
        ivWall = view.findViewById(R.id.ivWall);
        tvDraw = view.findViewById(R.id.tvDraw);
        tvPicture = view.findViewById(R.id.tvPicture);
        tvWall = view.findViewById(R.id.tvWall);
        tvDrawEx = view.findViewById(R.id.tvDrawEx);
        tvPictureEx = view.findViewById(R.id.tvPictureEx);
        tvWallEx = view.findViewById(R.id.tvWallEx);

        GotoSmallHobby gotoSmallHobby = new GotoSmallHobby();
        ivPicture.setOnClickListener(gotoSmallHobby);
        ivDraw.setOnClickListener(gotoSmallHobby);
        ivWall.setOnClickListener(gotoSmallHobby);

        tvPicture.setOnClickListener(gotoSmallHobby);
        tvDraw.setOnClickListener(gotoSmallHobby);
        tvWall.setOnClickListener(gotoSmallHobby);

        tvPictureEx.setOnClickListener(gotoSmallHobby);
        tvDrawEx.setOnClickListener(gotoSmallHobby);
        tvWallEx.setOnClickListener(gotoSmallHobby);

        return view;
    }
    class GotoSmallHobby implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ivWall:
                case R.id.tvWallEx:
                case R.id.tvWall:
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap =((BitmapDrawable)ivWall.getDrawable()).getBitmap();
                    float scale = (1024/(float)bitmap.getWidth());
                    int image_w = (int)(bitmap.getWidth()*scale);
                    int image_h = (int)(bitmap.getHeight()*scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent = new Intent(getActivity(),SmallHobbyAcitivty.class);
                    intent.putExtra("image",byteArray);
                    intent.putExtra("Title",tvWall.getText());
                    startActivity(intent);
                    break;
                case R.id.ivDraw:
                case R.id.tvDraw:
                case R.id.tvDrawEx:
                    stream = new ByteArrayOutputStream();
                    bitmap =((BitmapDrawable)ivDraw.getDrawable()).getBitmap();
                    scale = (1024/(float)bitmap.getWidth());
                    image_w = (int)(bitmap.getWidth()*scale);
                    image_h = (int)(bitmap.getHeight()*scale);
                    resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byteArray = stream.toByteArray();

                    intent = new Intent(getActivity(),SmallHobbyAcitivty.class);
                    intent.putExtra("image",byteArray);
                    intent.putExtra("Title",tvDraw.getText());
                    startActivity(intent);
                    break;
                case R.id.ivPicture:
                case R.id.tvPicture:
                case R.id.tvPictureEx:
                    stream = new ByteArrayOutputStream();
                    bitmap =((BitmapDrawable)ivPicture.getDrawable()).getBitmap();
                    scale = (1024/(float)bitmap.getWidth());
                    image_w = (int)(bitmap.getWidth()*scale);
                    image_h = (int)(bitmap.getHeight()*scale);
                    resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byteArray = stream.toByteArray();

                    intent = new Intent(getActivity(),SmallHobbyAcitivty.class);
                    intent.putExtra("image",byteArray);
                    intent.putExtra("Title",tvPicture.getText());
                    startActivity(intent);
                    break;
            }
        }
    }
}

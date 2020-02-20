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

public class num7_1tap extends Fragment {
    private View view;
    ImageView ivFlower;
    ImageView ivWood;
    ImageView ivDessert;
    ImageView ivCross;
    TextView tvFlower;
    TextView tvWood;
    TextView tvDessert;
    TextView tvCross;
    TextView tvFlowerEx;
    TextView tvWoodEx;
    TextView tvDessertEx;
    TextView tvCrossEx;


    //상태저장을 위해
    public static num7_1tap newinstance(){
        num7_1tap num7_1tap = new num7_1tap();
        return num7_1tap;
    }



    //레이아웃과 연동을 해줌
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.num7_1, container, false);
        ivFlower = view.findViewById(R.id.ivFlower);
        ivCross = view.findViewById(R.id.ivCross);
        ivDessert = view.findViewById(R.id.ivDessert);
        ivWood = view.findViewById(R.id.ivWood);
        tvFlower = view.findViewById(R.id.tvFlower);
        tvCross = view.findViewById(R.id.tvCross);
        tvDessert = view.findViewById(R.id.tvDessert);
        tvWood = view.findViewById(R.id.tvWood);
        tvFlowerEx = view.findViewById(R.id.tvFlowerEx);
        tvCrossEx = view.findViewById(R.id.tvCrossEx);
        tvDessertEx = view.findViewById(R.id.tvDessertEx);
        tvWoodEx = view.findViewById(R.id.tvWoodEx);


        GotoSmallHobby gotoSmallHobby =new GotoSmallHobby();
        ivFlower.setOnClickListener(gotoSmallHobby);
        ivCross.setOnClickListener(gotoSmallHobby);
        ivDessert.setOnClickListener(gotoSmallHobby);
        ivWood.setOnClickListener(gotoSmallHobby);

        tvFlower.setOnClickListener(gotoSmallHobby);
        tvCross.setOnClickListener(gotoSmallHobby);
        tvDessert.setOnClickListener(gotoSmallHobby);
        tvWood.setOnClickListener(gotoSmallHobby);

        tvFlowerEx.setOnClickListener(gotoSmallHobby);
        tvCrossEx.setOnClickListener(gotoSmallHobby);
        tvDessertEx.setOnClickListener(gotoSmallHobby);
        tvWoodEx.setOnClickListener(gotoSmallHobby);

        return view;
    }
    class GotoSmallHobby implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ivWood:
                case R.id.tvWood:
                case R.id.tvWoodEx:
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmap =((BitmapDrawable)ivWood.getDrawable()).getBitmap();
                    float scale = (1024/(float)bitmap.getWidth());
                    int image_w = (int)(bitmap.getWidth()*scale);
                    int image_h = (int)(bitmap.getHeight()*scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent = new Intent(getActivity(),SmallHobbyAcitivty.class);
                    intent.putExtra("image",byteArray);
                    intent.putExtra("Title",tvWood.getText());
                    startActivity(intent);
                    break;
                case R.id.ivFlower:
                case R.id.tvFlower:
                case R.id.tvFlowerEx:
                    stream = new ByteArrayOutputStream();
                    bitmap =((BitmapDrawable)ivFlower.getDrawable()).getBitmap();
                    scale = (1024/(float)bitmap.getWidth());
                    image_w = (int)(bitmap.getWidth()*scale);
                    image_h = (int)(bitmap.getHeight()*scale);
                    resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byteArray = stream.toByteArray();

                    intent = new Intent(getActivity(),SmallHobbyAcitivty.class);
                    intent.putExtra("image",byteArray);
                    intent.putExtra("Title",tvFlower.getText());
                    startActivity(intent);
                    break;
                case R.id.ivCross:
                case R.id.tvCross:
                case R.id.tvCrossEx:
                    stream = new ByteArrayOutputStream();
                    bitmap =((BitmapDrawable)ivCross.getDrawable()).getBitmap();
                    scale = (1024/(float)bitmap.getWidth());
                    image_w = (int)(bitmap.getWidth()*scale);
                    image_h = (int)(bitmap.getHeight()*scale);
                    resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byteArray = stream.toByteArray();

                    intent = new Intent(getActivity(),SmallHobbyAcitivty.class);
                    intent.putExtra("image",byteArray);
                    intent.putExtra("Title",tvCross.getText());
                    startActivity(intent);
                    break;
                case R.id.ivDessert:
                case R.id.tvDessert:
                case R.id.tvDessertEx:
                    stream = new ByteArrayOutputStream();
                    bitmap =((BitmapDrawable)ivDessert.getDrawable()).getBitmap();
                    scale = (1024/(float)bitmap.getWidth());
                    image_w = (int)(bitmap.getWidth()*scale);
                    image_h = (int)(bitmap.getHeight()*scale);
                    resize = Bitmap.createScaledBitmap(bitmap,image_w,image_h,true);
                    resize.compress(Bitmap.CompressFormat.JPEG,100,stream);
                    byteArray = stream.toByteArray();

                    intent = new Intent(getActivity(),SmallHobbyAcitivty.class);
                    intent.putExtra("image",byteArray);
                    intent.putExtra("Title",tvDessert.getText());
                    startActivity(intent);
                    break;
            }
        }
    }
}

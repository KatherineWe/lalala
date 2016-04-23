package com.example.eagle.lalala;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eagle.lalala.PictureWork.HandlePicture;
import com.example.eagle.lalala.PictureWork.TakePicture;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/4/19.
 */
public class Edit_marks_aty extends AppCompatActivity implements View.OnClickListener {

    public static final int PICK_PHOTO=2;

    private String imageCamera;
    private String imagePick;
    private ImageButton addPhotoBtn;
    private EditText editMarkContent;
    private TextView textViewForCoordinate;
    private RadioGroup radioGroup;
    private RadioButton radio_public;
    private RadioButton radio_private;
    private RadioButton radio_some;
    private LinearLayout layoutForRadio_some;

    private ArrayList<CheckBox> checkBoxArrayList;

    public static void actionStart(Context context,String imagePath){
        Intent intent = new Intent(context, Edit_marks_aty.class);
        intent.putExtra("imagePath", imagePath);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_marks);

        initView();//初始化所有控件
        compressPhoto(getIntent());//压缩按钮上的图片


    }

    private void initView() {
        addPhotoBtn = (ImageButton) findViewById(R.id.imageBtn_editmark);
        editMarkContent= (EditText) findViewById(R.id.edittext_editmark);
        textViewForCoordinate= (TextView) findViewById(R.id.tv_position);
        radioGroup= (RadioGroup) findViewById(R.id.radiogroup_editmark);
        radio_public= (RadioButton) findViewById(R.id.radiobtn_public);
        radio_private= (RadioButton) findViewById(R.id.radiobtn_private);
        radio_some= (RadioButton) findViewById(R.id.radiobtn_some);
        layoutForRadio_some= (LinearLayout) findViewById(R.id.radio_layout_some);

        getSomeList();//获得部分好友的列表信息【可以让线程去执行耗时的获取列表操作】

        addPhotoBtn.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.radiobtn_public:
                        layoutForRadio_some.setVisibility(View.GONE);

                        break;
                    case R.id.radiobtn_private:
                        layoutForRadio_some.setVisibility(View.GONE);

                        break;
                    case R.id.radiobtn_some:
                        layoutForRadio_some.setVisibility(View.VISIBLE);

                        break;
                }
            }
        });
    }

    private void compressPhoto(Intent intent){
        imageCamera = intent.getStringExtra("imagePath");
        if (imageCamera != null) {
            Bitmap bitmap2 = null;
//            try {
//                bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            bitmap2 = HandlePicture.decodeSampleBitmapFromPath(imageCamera, 180, 380);

            addPhotoBtn.setImageBitmap(bitmap2);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePick=TakePicture.handleImageOnKitKat(Edit_marks_aty.this, data);
                    }else{
                        //4.4以下系统使用这个方法处理图片
                        imagePick = TakePicture.handleImageBeforeKitKat(Edit_marks_aty.this, data);
                    }
                    if (imagePick != null) {
                        Bitmap bitmap = HandlePicture.decodeSampleBitmapFromPath(imagePick, 180, 380);
                        addPhotoBtn.setImageBitmap(bitmap);
                    }else{
                        Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_mark_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(Edit_marks_aty.this,"onOptionsItemSelected",Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBtn_editmark:
                Toast.makeText(Edit_marks_aty.this,"onOptionsItemSelected",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,PICK_PHOTO);//打开相册
                break;
        }
    }

    private void getSomeList(){//获得部分好友的标签
        //向数据库中查找标签表
        checkBoxArrayList=new ArrayList<>();
        CheckBox box1 = new CheckBox(this);
        box1.setText("朋友");
        checkBoxArrayList.add(box1);
        CheckBox box2 = new CheckBox(this);
        box2.setText("家人");
        checkBoxArrayList.add(box2);
        CheckBox box3 = new CheckBox(this);
        box3.setText("同学");
        checkBoxArrayList.add(box3);
        CheckBox box4 = new CheckBox(this);
        box4.setText("名人");
        checkBoxArrayList.add(box4);
        CheckBox box5 = new CheckBox(this);
        box5.setText("同事");
        checkBoxArrayList.add(box5);

        //展示出来
        int i=0;
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(CheckBox checkBox:checkBoxArrayList){
            layoutForRadio_some.addView(checkBox,i,params);
            i++;
        }
        layoutForRadio_some.setVisibility(View.GONE);
    }


}

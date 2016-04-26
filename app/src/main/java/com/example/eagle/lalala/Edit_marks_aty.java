package com.example.eagle.lalala;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by NeilHY on 2016/4/19.
 */
public class Edit_marks_aty extends AppCompatActivity implements View.OnClickListener {

    private static final String serviceUrl="";
    private static long userId=0;//用户的id
    public static final int PICK_PHOTO=2;
    private static final int AUTHORITY_PUBLIC=1;
    private static final int AUTHORITY_PRIVATE=2;
    private static final int AUTHORITY_SOME=3;
    private static int AUTHORITY_TYPE=0;

    private String imageCamera;
    private String location;  //坐标，支持点数据 , 规则：经度,纬度，经纬度支持到小数点后6位 格式示例：104.394729,31.125698
    private ImageButton addPhotoBtn;
    private EditText editMarkContent;
    private TextView textViewForCoordinate;
    private RadioGroup radioGroup;
    private RadioButton radio_public;
    private RadioButton radio_private;
    private RadioButton radio_some;
    private LinearLayout layoutForRadio_some;
    private ProgressDialog progressDialog;

    private ArrayList<CheckBox> checkBoxArrayList;
    private ArrayList<String> checkedItem;//被选中的checkbox

    public static void actionStart(Context context,String imagePath,long userId){
        Intent intent = new Intent(context, Edit_marks_aty.class);
        intent.putExtra("imagePath", imagePath);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_marks);

        initView();//初始化所有控件
        userId = getIntent().getLongExtra("userId",0);
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
        radioGroup.check(radio_public.getId());//设置默认选中公开单选

        getSomeList();//获得部分好友的列表信息【可以让线程去执行耗时的获取列表操作】

        addPhotoBtn.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.radiobtn_public:
                        layoutForRadio_some.setVisibility(View.GONE);
                        AUTHORITY_TYPE=AUTHORITY_PUBLIC;

                        break;
                    case R.id.radiobtn_private:
                        layoutForRadio_some.setVisibility(View.GONE);
                        AUTHORITY_TYPE=AUTHORITY_PRIVATE;

                        break;
                    case R.id.radiobtn_some:
                        layoutForRadio_some.setVisibility(View.VISIBLE);
                        AUTHORITY_TYPE=AUTHORITY_SOME;

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
                        imageCamera=TakePicture.handleImageOnKitKat(Edit_marks_aty.this, data);
                    }else{
                        //4.4以下系统使用这个方法处理图片
                        imageCamera = TakePicture.handleImageBeforeKitKat(Edit_marks_aty.this, data);
                    }
                    if (imageCamera != null) {
                        Bitmap bitmap = HandlePicture.decodeSampleBitmapFromPath(imageCamera, 160, 420);
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
        if(userId == 0) {
            Toast.makeText(Edit_marks_aty.this, "请登录...", Toast.LENGTH_SHORT).show();
        }
        if(imageCamera != null) {
            JSONObject object = new JSONObject();
            try {
                object.put("userId", userId);           //用户的id
                object.put("content", editMarkContent.getText().toString());
                object.put("image", HandlePicture.bitmapToString(imageCamera));
//                object.put("location", location);
                object.put("authority_type", AUTHORITY_TYPE);
                if(AUTHORITY_TYPE == AUTHORITY_SOME){
                    checkedItem=new ArrayList<>();
                    for (CheckBox checkBox : checkBoxArrayList) {
                        if(checkBox.isChecked()){
                            checkedItem.add(checkBox.getText().toString());//将被选中的checkbox的名字存进list
                        }
                    }
                    object.put("authority", checkedItem);
                } else  {                                   //若只是公开和私密，则不用传权限标签
                    object.put("authority", AUTHORITY_TYPE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new UploadMark().execute(object);//用异步处理上传操作
        }else{
            Toast.makeText(Edit_marks_aty.this,"请选择要上传的图片…",Toast.LENGTH_SHORT).show();
        }
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

    //上传的异步类
    private class UploadMark extends AsyncTask<JSONObject,Void,String> {

        @Override
        protected String doInBackground(JSONObject... params) {
            final String[] result = new String[1];
            result[0] = "done";
//            HttpUtil.getJsonArrayByHttp(serviceUrl, params[0], new HttpCallbackListener() {
//                @Override
//                public void onFinishGetJson(JSONObject jsonObject) {
//                    if (jsonObject != null) {
//                        try {
//                            result[0] = jsonObject.getString("result");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFinishGetString(String response) {
//
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    Log.e("EditMarks", e.getMessage());
//                    result[0] = "error";
//                }
//            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result[0];
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Edit_marks_aty.this);
            progressDialog.setMessage("正在提交,请稍候...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            if(s.equals("done")) {
                Toast.makeText(Edit_marks_aty.this, "上传成功", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(Edit_marks_aty.this, "上传失败", Toast.LENGTH_LONG).show();
            }
            Edit_marks_aty.this.finish();
        }
    }
}

package com.example.eagle.lalala;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eagle on 2016/4/9.
 */
public class SharedFragment extends Fragment {

    List<MomentContent> contents;

    @Bind(R.id.btn_recommend)
    TextView mBtnRecommend;
    @Bind(R.id.btn_focus)
    TextView mBtnFocus;
    @Bind(R.id.recycler_moment)
    RecyclerView mRecyclerMoment;

    private JuanAdapter mJuanAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contents = new ArrayList<MomentContent>();
        for(int i = 0;i < 100;++i)
            contents.add(new MomentContent("fuck "+i));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_shared, container, false);
        ButterKnife.bind(this, v);

        mRecyclerMoment.setLayoutManager(new LinearLayoutManager(getActivity()));
        mJuanAdapter = new JuanAdapter(contents);
        mRecyclerMoment.setAdapter(mJuanAdapter);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_recommend, R.id.btn_focus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recommend:
                break;
            case R.id.btn_focus:
                break;
        }
    }

    private class JuanHolder extends RecyclerView.ViewHolder{//未完待续
        private TextView mTextView;//存放着的控件

        public JuanHolder(View itemView) {//这个itemView就是Adapter传过来的自定义的，所以用它来获取控件
            super(itemView);
            mTextView = (TextView)itemView;
        }
    }

    private class JuanAdapter extends RecyclerView.Adapter<JuanHolder>{
        private List<MomentContent> mContents;

        public JuanAdapter(List<MomentContent> contents) {
            mContents = contents;
        }

        @Override
        public int getItemCount() {
            return mContents.size();
        }

        @Override
        public JuanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_1,parent,false);
            return new JuanHolder(view);//将布局的View传给Holder
        }

        @Override
        public void onBindViewHolder(JuanHolder holder, int position) {//将Model层的数据连接”绑“上去
            MomentContent temp = mContents.get(position);
            holder.mTextView.setText(temp.name);
        }
    }


}

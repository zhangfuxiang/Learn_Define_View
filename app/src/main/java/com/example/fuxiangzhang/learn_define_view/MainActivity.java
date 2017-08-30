package com.example.fuxiangzhang.learn_define_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fuxiangzhang.learn_define_view.common.BaseRecycleAdapter;
import com.example.fuxiangzhang.learn_define_view.common.BaseViewHolder;
import com.example.fuxiangzhang.learn_define_view.common.LeftAndRightTagDecoration;
import com.example.fuxiangzhang.learn_define_view.common.SimplePaddingDecoration;
import com.example.fuxiangzhang.learn_define_view.entity.ItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.circleview)
    CircleView mCircleview;
    @BindView(R.id.ryv_main)
    RecyclerView mRyvMain;

    private List<ItemBean> datas=new ArrayList<>();
    private BaseRecycleAdapter<ItemBean> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        for (int mI = 0; mI < 10; mI++) {
            datas.add(new ItemBean(mI,"内容"+mI));
        }


        mAdapter=new BaseRecycleAdapter<ItemBean>(datas,R.layout.item,this) {
            @Override
            protected void convert(BaseViewHolder holder, ItemBean item, final int position) {
                holder.setText(R.id.tv_item_content,item.getContent());


                holder.setOnClickListener(R.id.layout_cv, new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        Toast.makeText(MainActivity.this, "item:"+position, Toast.LENGTH_SHORT).show();
                        Log.e("aaa", "onClick: "+"item:"+position);
                    }
                });
                holder.setOnLongClickListener(R.id.layout_cv, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View mView) {
                        Toast.makeText(MainActivity.this, "item long:"+position, Toast.LENGTH_SHORT).show();
                        Log.e("aaa", "item long:"+position);
                        return false;
                    }
                });
                holder.setOnClickListener(R.id.btn_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View mView) {
                        Toast.makeText(MainActivity.this, "item button:"+position, Toast.LENGTH_SHORT).show();
                        Log.e("aaa", "item button:"+position);
                    }
                });
            }



        };

        mRyvMain.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRyvMain.addItemDecoration(new LeftAndRightTagDecoration(this));
        mRyvMain.addItemDecoration(new SimplePaddingDecoration(this));
        mRyvMain.setAdapter(mAdapter);


/*        //对item设置点击事件
        mAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click:"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "longclick:"+position, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

}
